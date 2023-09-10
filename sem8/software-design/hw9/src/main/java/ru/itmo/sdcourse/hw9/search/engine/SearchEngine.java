package ru.itmo.sdcourse.hw9.search.engine;

import org.apache.commons.lang3.Validate;
import ru.itmo.sdcourse.hw9.search.SearchResult;
import ru.itmo.sdcourse.hw9.search.SearchResults;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public abstract class SearchEngine {
    private final Clock clock;

    protected SearchEngine(Clock clock) {
        this.clock = clock;
    }

    protected SearchEngine() {
        this(Clock.systemDefaultZone());
    }

    public final SearchResults search(String query) {
        Validate.notEmpty(query);
        Instant start = clock.instant();
        List<SearchResult> results = doSearch(query);
        Instant finish = clock.instant();
        return new SearchResults(
                Duration.between(start, finish),
                results.stream().map(result -> result.withEngineName(getName())).collect(Collectors.toList())
        );
    }

    public abstract String getName();

    protected abstract List<SearchResult> doSearch(String query);
}
