package stack;

import kotlinx.atomicfu.AtomicArray;
import kotlinx.atomicfu.AtomicRef;

import java.util.Random;

public class StackImpl implements Stack {
    private static class Node {
        final AtomicRef<Node> next;
        final int x;

        Node(int x, Node next) {
            this.next = new AtomicRef<>(next);
            this.x = x;
        }
    }

    private static class EliminationArray {
        static final Random random = new Random(0xB00BA);

        final AtomicArray<Integer> array;
        final int size, cellTries, neighbourLimit;

        EliminationArray(int size, int cellTries, int neighbourLimit) {
            this.size = size;
            this.cellTries = cellTries;
            this.neighbourLimit = neighbourLimit;
            this.array = new AtomicArray<>(size);
        }

        boolean push(int x) {
            Integer storedX = x;

            boolean success = false;
            int i = random.nextInt(size);
            for (int indexDelta = 0; indexDelta < neighbourLimit; indexDelta++) {
                AtomicRef<Integer> cell = array.get((i + indexDelta) % size); //todo
                for (int tryCount = 0; tryCount < cellTries; tryCount++) {
                    if (cell.compareAndSet(null, storedX)) {
                        success = true;
                        break;
                    }
                }

                if (success) {
                    for (int tryCount = 0; tryCount < cellTries; tryCount++) {
                        if (!cell.compareAndSet(storedX, storedX)) {
                            return true;
                        }
                    }

                    return !cell.compareAndSet(storedX, null);
                }
            }

            return false;
        }

        Integer pop() {
            int i = random.nextInt(size);
            for (int indexDelta = 0; indexDelta < neighbourLimit; indexDelta++) {
                AtomicRef<Integer> cell = array.get((size + i - indexDelta) % size); //todo
                for (int tryCount = 0; tryCount < cellTries; tryCount++) {
                    Integer value = cell.getAndSet(null);
                    if (value != null) {
                        return value;
                    }
                }
            }

            return null;
        }
    }

    private final AtomicRef<Node> head = new AtomicRef<>(null);
    private final EliminationArray eliminationArray = new EliminationArray(64, 8, 8);

    @Override
    public void push(int x) {
        if (!eliminationArray.push(x)) {
            while (true) {
                Node oldHead = head.getValue();
                Node newNode = new Node(x, oldHead);
                if (head.compareAndSet(oldHead, newNode))
                    return;
            }
        }
    }

    @Override
    public int pop() {
        Integer value = eliminationArray.pop();
        if (value != null) {
            return value;
        }

        while (true) {
            Node oldHead = head.getValue();
            if (oldHead == null) {
                return Integer.MIN_VALUE;
            }

            Node newHead = oldHead.next.getValue();
            if (head.compareAndSet(oldHead, newHead)) {
                return oldHead.x;
            }
        }
    }
}
