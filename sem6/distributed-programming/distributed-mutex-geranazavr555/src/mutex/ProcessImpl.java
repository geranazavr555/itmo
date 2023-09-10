package mutex;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Distributed mutual exclusion implementation.
 * All functions are called from the single main thread.
 *
 * @author Georgiy Nazarov
 */
public class ProcessImpl implements Process {
    private final Environment env;
    private final List<Status> status;
    private boolean wantCS = false;
    private boolean inCS = false;
    private final List<Boolean> pendingReqs;
    private final List<Boolean> pendingOks;

    public ProcessImpl(Environment env) {
        this.env = env;
        status = new ArrayList<>(env.getNProcesses());
        pendingReqs = new ArrayList<>(env.getNProcesses());
        pendingOks = new ArrayList<>(env.getNProcesses());
        for (int i = 0; i < env.getNProcesses(); i++) {
            if (i < env.getProcessId() - 1)
                status.add(Status.NO);
            else
                status.add(Status.OWN);
            pendingReqs.add(false);
            pendingOks.add(false);
        }
    }

    private static Message ok() {
        MessageBuilder builder = new MessageBuilder();
        builder.writeEnum(MessageType.OK);
        return builder.build();
    }

    private static Message req() {
        MessageBuilder builder = new MessageBuilder();
        builder.writeEnum(MessageType.REQ);
        return builder.build();
    }

    @Override
    public void onMessage(int srcId, @NotNull Message message) {
        int fSrcId = srcId - 1;
        MessageKt.parse(message, messageParser -> {
            switch (MessageType.valueOf(messageParser.readEnumName())) {
                case OK:
                    status.set(fSrcId, Status.OWN);
                    pendingOks.set(fSrcId, false);
                    checkCSEnter();
                    break;
                case REQ:
                    if (status.get(fSrcId) == Status.OWN && wantCS || inCS) {
                        pendingReqs.set(fSrcId, true);
                    } else {
                        status.set(fSrcId, Status.NO);
                        env.send(srcId, ok());
                    }
                    break;
            }
            return null;
        });
    }

    private void checkCSEnter() {
        if (!wantCS)
            throw new AssertionError();

        if (inCS)
            throw new AssertionError();

        List<Integer> need = new ArrayList<>();
        for (int i = 0; i < env.getNProcesses(); i++) {
            if (status.get(i) == Status.NO) {
                need.add(i);
            }
        }

        boolean wait = !need.isEmpty();
        for (Integer i : need) {
            if (!pendingOks.get(i)) {
                pendingOks.set(i, true);
                env.send(i + 1, req());
            }
        }

        if (wait)
            return;

        inCS = true;
        wantCS = false;
        env.locked();
    }

    @Override
    public void onLockRequest() {
        wantCS = true;
        checkCSEnter();
    }

    @Override
    public void onUnlockRequest() {
        for (int i = 0; i < status.size(); i++) {
            if (i != env.getProcessId() - 1)
                status.set(i, Status.DIRTY);
        }
        env.unlocked();
        inCS = false;
        for (int i = 0; i < env.getNProcesses(); i++) {
            if (pendingReqs.get(i)) {
                status.set(i, Status.NO);
                pendingReqs.set(i, false);
                env.send(i + 1, ok());
            }
        }
    }

    private enum Status {
        OWN, DIRTY, NO
    }

    private enum MessageType {
        REQ, OK
    }
}
