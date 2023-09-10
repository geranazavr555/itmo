package ru.itmo.sdcourse.hw9.search.actors;

import akka.actor.typed.ActorSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itmo.sdcourse.hw9.search.SearchResults;
import ru.itmo.sdcourse.hw9.search.Searcher;
import ru.itmo.sdcourse.hw9.search.actors.message.GuardianRequest;
import ru.itmo.sdcourse.hw9.search.actors.message.SearchRequest;
import ru.itmo.sdcourse.hw9.search.engine.SearchEngine;

import java.time.Clock;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class ActorsSearcher implements Searcher {
    private static final Logger logger = LogManager.getLogger(ActorsSearcher.class);

    private final SearchEngine[] engines;
    private final Clock clock;

    private final Object actorSystemLock = new Object();
    private volatile ActorSystem<GuardianRequest> actorSystem;

    public ActorsSearcher(SearchEngine... engines) {
        this(Clock.systemDefaultZone(), engines);
    }

    public ActorsSearcher(Clock clock, SearchEngine... engines) {
        this.engines = engines;
        this.clock = clock;
        if (engines.length == 0)
            logger.warn("No engines. Results will be empty");
    }

    private ActorSystem<GuardianRequest> getActorSystem() {
        if (actorSystem == null) {
            synchronized (actorSystemLock) {
                if (actorSystem == null)
                    actorSystem = ActorSystem.create(GuardianSearchActor.create(), getClass().getSimpleName());
            }
        }
        return actorSystem;
    }

    @Override
    public SearchResults search(String query, Duration timeout) {
        var latch = new CountDownLatch(1);
        AtomicReference<SearchResults> resultsRef = new AtomicReference<>();

        getActorSystem().tell(new GuardianRequest(
                clock,
                timeout,
                results -> {
                    resultsRef.compareAndSet(null, results);
                    latch.countDown();
                },
                SearchRequest.of(query),
                engines
        ));

        try {
            logger.warn("Awaiting result");
            latch.await();
        } catch (InterruptedException e) {
            logger.warn("Search interrupted");
            Thread.currentThread().interrupt();
        }

        SearchResults results = resultsRef.get();
        if (results == null)
            results = SearchResults.empty();

        return results;
    }

    @Override
    public void shutdown() {
        actorSystem.terminate();
    }
}
