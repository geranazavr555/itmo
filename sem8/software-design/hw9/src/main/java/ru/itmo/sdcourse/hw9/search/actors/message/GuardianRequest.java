package ru.itmo.sdcourse.hw9.search.actors.message;

import ru.itmo.sdcourse.hw9.search.SearchResults;
import ru.itmo.sdcourse.hw9.search.engine.SearchEngine;

import java.time.Clock;
import java.time.Duration;
import java.util.function.Consumer;

public record GuardianRequest(Clock clock,
                              Duration timeout,
                              Consumer<SearchResults> resultsConsumer,
                              SearchRequest request,
                              SearchEngine... engines) implements Message {
}
