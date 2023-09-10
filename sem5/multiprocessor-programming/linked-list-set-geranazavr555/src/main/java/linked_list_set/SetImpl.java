package linked_list_set;

import kotlinx.atomicfu.AtomicRef;

public class SetImpl implements Set {
    private static abstract class Node {
        AtomicRef<Node> next;
        int x;

        Node(int x, Node next) {
            this.next = new AtomicRef<>(next);
            this.x = x;
        }

        Node(int x, AtomicRef<Node> next) {
            this.next = next;
            this.x = x;
        }
    }

    private static class RealNode extends Node {
        RealNode(int x, Node next) {
            super(x, next);
        }

        RealNode(int x, AtomicRef<Node> next) {
            super(x, next);
        }

        RemovedNode toRemoved() {
            return new RemovedNode(x, next);
        }
    }

    private static class RemovedNode extends Node {
        RemovedNode(int x, AtomicRef<Node> next) {
            super(x, next);
        }

        RealNode toReal() {
            return new RealNode(x, next);
        }
    }

    private static class Window {
        Node cur, next;

        Window(Node cur, Node next) {
            this.cur = cur;
            this.next = next;
        }
    }

    private final Node head = new RealNode(Integer.MIN_VALUE,
            new RealNode(Integer.MAX_VALUE, new RealNode(Integer.MAX_VALUE, (Node) null)));

    /**
     * Returns the {@link Window}, where cur.x < x <= next.x
     */
    private Window findWindow(int x) {
        // Code source: The art of multiprocessor programming, page 223-224
        retry: while (true) {
            Node pred = head;
            Node curr = pred.next.getValue();
            while (true) {
                Node succ = curr.next.getValue();
                while (succ instanceof RemovedNode) {
                    Node realSucc = ((RemovedNode) succ).toReal();
                    if (!pred.next.compareAndSet(curr, realSucc)) continue retry;
                    curr = realSucc;
                    succ = curr.next.getValue();
                }
                if (curr.x >= x)
                    return new Window(pred, curr);
                pred = curr;
                curr = succ;

            }

//            while (curr.x < x) {
//                Node node = curr.next.getValue();
//                if (node instanceof RemovedNode) {
//                    Node realNode = ((RemovedNode) node).toReal();
////                    if (!pred.curr.compareAndSet(curr, realNode))
//                    pred.next.compareAndSet(curr, realNode);
//                    continue retry;
////                    curr = realNode;
//                } else {
//                    pred = curr;
//                    curr = pred.next.getValue();
//                }
//            }
//            if (curr.next.getValue() instanceof RealNode)
//                return new Window(pred, curr);
//            else {
//                Node nextNext = ((RemovedNode) curr.curr.getValue()).toReal();
//                pred.curr.compareAndSet(curr, nextNext);
//            }
        }
    }

    @Override
    public boolean add(int x) {
        // Code source: The art of multiprocessor programming, page 223-224
        while (true) {
            Window w = findWindow(x);
            Node pred = w.cur;
            Node curr = w.next;
            if (curr.x == x) return false;
            Node node = new RealNode(x, curr);
            if (pred.next.getValue() instanceof RealNode && pred.next.compareAndSet(curr, node)) {
                return true;
            }
        }

//        while (true) {
//            Window w = findWindow(x);
//            if (w.next.x == x)
//                return false;
//            Node node = new RealNode(x, w.next);
//            if (w.cur.next.getValue() instanceof RemovedNode)
//                continue;
//            if (w.cur.next.compareAndSet(w.next, node))
//                return true;
//        }
    }

    @Override
    public boolean remove(int x) {
        // Code source: The art of multiprocessor programming, page 223-224
        while (true) {
            Window w = findWindow(x);
            Node pred = w.cur;
            Node curr = w.next;
            if (curr.x != x) return false;
            Node succ = curr.next.getValue();
            if (succ instanceof RemovedNode) continue;
            RemovedNode removedSucc = ((RealNode) succ).toRemoved();
            if (!curr.next.compareAndSet(succ, removedSucc)) continue;
            pred.next.compareAndSet(curr, succ);
            return true;
        }

//        while (true) {
//            Window w = findWindow(x);
//            if (w.next.x != x)
//                return false;
//            Node node = w.next.next.getValue();
//            if (node instanceof RemovedNode)
//                continue;
//            Node removedNode = ((RealNode) node).toRemoved();
//            if (w.next.next.compareAndSet(node, removedNode)) {
////                if (w.cur.next.getValue() instanceof RealNode) {
//                w.cur.next.compareAndSet(w.next, node);
////                }
//                return true;
//            }
//        }
    }

    @Override
    public boolean contains(int x) {
        Window w = findWindow(x);
        return w.next.x == x;
    }
}