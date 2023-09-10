/**
 * @author Nazarov Georgiy
 */
public class Solution implements AtomicCounter {
    private final Node root = new Node();
    private final ThreadLocal<Node> last = ThreadLocal.withInitial(() -> root);

    public int getAndAdd(int x) {
        int res, old;
        while (true) {
            old = last.get().val;
            res = old + x;
            Node node = new Node(res);
            last.set(last.get().next.decide(node));
            if (last.get() == node) break;
        }
        return old;
    }

    // вам наверняка потребуется дополнительный класс
    private static class Node {
        final int val;
        final Consensus<Node> next;

        Node() {
            val = 0;
            next = new Consensus<>();
        }

        Node(int val) {
            this.val = val;
            next = new Consensus<>();
        }
    }
}
