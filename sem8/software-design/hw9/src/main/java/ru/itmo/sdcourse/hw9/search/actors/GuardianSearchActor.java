package ru.itmo.sdcourse.hw9.search.actors;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import ru.itmo.sdcourse.hw9.search.actors.message.GuardianRequest;

public class GuardianSearchActor extends AbstractBehavior<GuardianRequest> {
    public GuardianSearchActor(ActorContext<GuardianRequest> context) {
        super(context);
    }

    @Override
    public Receive<GuardianRequest> createReceive() {
        return newReceiveBuilder().onMessage(GuardianRequest.class, this::onGuardianRequest).build();
    }

    private Behavior<GuardianRequest> onGuardianRequest(GuardianRequest request) {
        var child = getContext().spawnAnonymous(MasterSearchActor.create(
                request.clock(),
                request.timeout(),
                request.resultsConsumer(),
                request.engines()
        ));
        child.tell(request.request());
        return this;
    }

    public static Behavior<GuardianRequest> create() {
        return Behaviors.setup(GuardianSearchActor::new);
    }
}
