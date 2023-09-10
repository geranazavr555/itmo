package ru.itmo.sdcourse.hw9.search.actors.message;

import akka.actor.typed.ActorRef;
import ru.itmo.sdcourse.hw9.search.SearchResults;

public record SearchResponse(SearchResults results, ActorRef<SearchRequest> from) implements Message {
}
