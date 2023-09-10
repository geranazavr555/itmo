package ru.itmo.sdcourse.hw9.search.actors;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import ru.itmo.sdcourse.hw9.search.SearchResults;
import ru.itmo.sdcourse.hw9.search.actors.message.Message;
import ru.itmo.sdcourse.hw9.search.actors.message.SearchRequest;
import ru.itmo.sdcourse.hw9.search.actors.message.SearchResponse;
import ru.itmo.sdcourse.hw9.search.actors.message.Timeout;
import ru.itmo.sdcourse.hw9.search.engine.SearchEngine;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;

class MasterSearchActor extends AbstractBehavior<Message> {
    private final ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    private final Map<ActorRef<SearchRequest>, Optional<SearchResults>> childToResponse = new HashMap<>();
    private final Duration timeout;
    private final SearchEngine[] engines;
    private final Clock clock;
    private final Consumer<SearchResults> resultsConsumer;

    private Instant start;

    public MasterSearchActor(Clock clock,
                             Duration timeout,
                             Consumer<SearchResults> resultsConsumer,
                             ActorContext<Message> context,
                             SearchEngine... engines) {
        super(context);
        this.timeout = timeout;
        this.engines = engines;
        this.clock = clock;
        this.resultsConsumer = resultsConsumer;
    }

    @Override
    public Receive<Message> createReceive() {
        return newReceiveBuilder()
                .onMessage(SearchRequest.class, this::onRequest)
                .onMessage(SearchResponse.class, this::onResult)
                .onMessage(Timeout.class, this::onTimeout)
                .build();
    }

    private Behavior<Message> onRequest(SearchRequest request) {
        getContext().getLog().info("Received query: " + request.query());

        start = clock.instant();
        for (SearchEngine engine : engines) {
            getContext().getLog().info("Creating actor for engine " + engine.getName());
            var child = getContext().spawnAnonymous(SearchActor.create(engine));
            childToResponse.put(child, Optional.empty());
            child.tell(request.withMaster(getContext().getSelf()));
        }

        scheduledExecutor.schedule(() ->
                getContext().getSelf().tell(Timeout.get()), timeout.toMillis(), TimeUnit.MILLISECONDS);

        return this;
    }

    private Behavior<Message> onCompleted() {
        var execDuration = Duration.between(start, clock.instant());
        var results = new SearchResults(
            execDuration,
            childToResponse.values()
                    .stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(SearchResults::results)
                    .flatMap(List::stream)
                    .collect(Collectors.toList())
        );
        resultsConsumer.accept(results);

        childToResponse.keySet().forEach(actorRef -> getContext().stop(actorRef));

        return Behaviors.stopped();
    }

    private Behavior<Message> onResult(SearchResponse results) {
        childToResponse.put(results.from(), Optional.of(results.results()));

        boolean completed = true;
        for (Map.Entry<ActorRef<SearchRequest>, Optional<SearchResults>> childAndResults : childToResponse.entrySet()) {
            Optional<SearchResults> searchResults = childAndResults.getValue();
            if (searchResults.isEmpty()) {
                completed = false;
                break;
            }
        }

        Behavior<Message> nextBehaviour = this;
        if (completed)
            nextBehaviour = onCompleted();

        return nextBehaviour;
    }

    private Behavior<Message> onTimeout(Timeout timeout) {
        return onCompleted();
    }

    public static Behavior<Message> create(Clock clock,
                                           Duration timeout,
                                           Consumer<SearchResults> resultsConsumer,
                                           SearchEngine... engines) {
        return Behaviors.setup(context -> new MasterSearchActor(clock, timeout, resultsConsumer, context, engines));
    }
}
