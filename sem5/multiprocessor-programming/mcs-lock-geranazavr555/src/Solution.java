import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.LockSupport;

public class Solution implements Lock<Solution.Node> {
    private final Environment env;

    // todo: необходимые поля (final, используем AtomicReference)
    private final AtomicReference<Node> tail = new AtomicReference<>(null);
//    private final ThreadLocal<Node> my = ThreadLocal.withInitial(() -> null);

    public Solution(Environment env) {
        this.env = env;
    }

    @Override
    public Node lock() {
        Node my = new Node(); // сделали узел
        my.locked.set(true);
        Node pred = tail.getAndSet(my);
        if (pred != null) {
            pred.next.set(my);
            while (my.locked.get()) {
                env.park();
            }
        }
        return my;
    }

    @Override
    public void unlock(Node node) {
        Node my = node;
        if (my.next.get() == null) {
            if (tail.compareAndSet(my, null)) {
                return;
            } else
                while (my.next.get() == null) {}
        }
        Node node1 = my.next.get();
        node1.locked.set(false);
        env.unpark(node1.thread);
    }

    static class Node {
        final Thread thread = Thread.currentThread(); // запоминаем поток, которые создал узел
        // todo: необходимые поля (final, используем AtomicReference)
        final AtomicReference<Boolean> locked = new AtomicReference<>(false);
        final AtomicReference<Node> next = new AtomicReference<>(null);
    }
}
