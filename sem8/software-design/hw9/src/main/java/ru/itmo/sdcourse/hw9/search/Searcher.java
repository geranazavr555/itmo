package ru.itmo.sdcourse.hw9.search;

import java.time.Duration;

public interface Searcher {
    SearchResults search(String query, Duration timeout);
    void shutdown();
}
