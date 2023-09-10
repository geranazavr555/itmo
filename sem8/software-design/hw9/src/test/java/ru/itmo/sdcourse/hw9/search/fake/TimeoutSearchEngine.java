package ru.itmo.sdcourse.hw9.search.fake;

import ru.itmo.sdcourse.hw9.search.SearchResult;
import ru.itmo.sdcourse.hw9.search.engine.SearchEngine;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class TimeoutSearchEngine extends SearchEngine {
    private final Duration timeToWait;
    private final SearchEngine engine;

    public TimeoutSearchEngine(Duration timeToWait, SearchEngine engine) {
        this.timeToWait = timeToWait;
        this.engine = engine;
    }

    @Override
    public String getName() {
        return "Timeout-" + engine.getName();
    }

    @Override
    protected List<SearchResult> doSearch(String query) {
        try {
            Thread.sleep(timeToWait.toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return Collections.emptyList();
        }

        return engine.search(query).results();
    }
}
