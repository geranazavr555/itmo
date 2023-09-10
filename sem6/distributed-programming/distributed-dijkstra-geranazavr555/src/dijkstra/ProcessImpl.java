package dijkstra;

import dijkstra.messages.*;
import dijkstra.system.environment.Environment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ProcessImpl implements Process {
    private final Environment env;
    private Long distance = Long.MAX_VALUE;
    private int balance = 0;
    private int childCount = 0;
    private int parentId = -1;
    private boolean active = false;

    public ProcessImpl(Environment env) {
        this.env = env;
    }

    @Override
    public void onMessage(int srcId, @NotNull Message message) {
        if (active) {
            if (message instanceof CutMessage) {
                childCount--;
                trySelfCut();
            } else if (message instanceof DistAckMessage) {
                if (((DistAckMessage) message).getActivated())
                    childCount++;
                balance--;
                trySelfCut();
            } else if (message instanceof DistMessage) {
                send(srcId, new DistAckMessage(false));
                tryRelax(((DistMessage) message).getDist());
            }
        } else {
            if (message instanceof DistMessage) {
                active = true;
                parentId = srcId;
                send(srcId, new DistAckMessage(true));
                tryRelax(((DistMessage) message).getDist());
            }
        }
    }

    private void trySelfCut() {
        if (childCount == 0 && balance == 0) {
            if (parentId == -1) {
                env.finishExecution();
                return;
            }
            send(parentId, CutMessage.INSTANCE);
            active = false;
        }
    }

    private void relax() {
        for (Map.Entry<Integer, Long> vAndW : env.getNeighbours().entrySet()) {
            int v = vAndW.getKey();
            long w = vAndW.getValue();
            if (v == env.getPid())
                continue;
            balance++;
            send(v, new DistMessage(distance + w));
        }
    }

    private void tryRelax(long newDist) {
        if (distance > newDist) {
            distance = newDist;
            relax();
        }
        trySelfCut();
    }

    private void send(int dstId, Message message) {
        env.send(dstId, message);
    }

    @Nullable
    @Override
    public Long getDistance() {
        return distance == Long.MAX_VALUE ? null : distance;
    }

    @Override
    public void startComputation() {
        active = true;
        tryRelax(0);
    }
}
