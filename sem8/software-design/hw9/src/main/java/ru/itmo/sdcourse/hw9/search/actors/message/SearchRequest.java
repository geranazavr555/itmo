package ru.itmo.sdcourse.hw9.search.actors.message;

import akka.actor.typed.ActorRef;

import java.util.Optional;

public record SearchRequest(String query, Optional<ActorRef<Message>> masterActor) implements Message {
    public SearchRequest withMaster(ActorRef<Message> masterActor) {
        return new SearchRequest(query, Optional.of(masterActor));
    }

    public static SearchRequest of(String query) {
        return new SearchRequest(query, Optional.empty());
    }
}
