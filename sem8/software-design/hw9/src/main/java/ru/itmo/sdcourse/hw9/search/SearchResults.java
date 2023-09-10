package ru.itmo.sdcourse.hw9.search;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public record SearchResults(Duration execDuration, List<SearchResult> results) {
    private static final SearchResults EMPTY = new SearchResults(Duration.ZERO, Collections.emptyList());

    public static SearchResults empty() {
        return EMPTY;
    }
}
