package ru.itmo.sdcourse.hw9.search.actors;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import ru.itmo.sdcourse.hw9.search.SearchResults;
import ru.itmo.sdcourse.hw9.search.actors.message.SearchRequest;
import ru.itmo.sdcourse.hw9.search.actors.message.SearchResponse;
import ru.itmo.sdcourse.hw9.search.engine.SearchEngine;

class SearchActor<T extends SearchEngine> extends AbstractBehavior<SearchRequest> {
    private final T engine;

    public SearchActor(T engine, ActorContext<SearchRequest> context) {
        super(context);
        this.engine = engine;
    }

    @Override
    public Receive<SearchRequest> createReceive() {
        return newReceiveBuilder()
                .onMessage(SearchRequest.class, request -> request.masterActor().isPresent(), this::onQuery)
                .build();
    }

    private T getEngine() {
        return engine;
    }

    private Behavior<SearchRequest> onQuery(SearchRequest request) {
        var query = request.query();
        getContext().getLog().info("Query received: " + query);
        SearchResults results = getEngine().search(query);
        //noinspection OptionalGetWithoutIsPresent
        request.masterActor().get().tell(new SearchResponse(results, getContext().getSelf()));
        return this;
    }

    public static <T extends SearchEngine> Behavior<SearchRequest> create(T engine) {
        return Behaviors.setup(context -> new SearchActor<>(engine, context));
    }
}
