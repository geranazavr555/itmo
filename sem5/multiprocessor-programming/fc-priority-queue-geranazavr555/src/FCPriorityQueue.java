import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class FCPriorityQueue<E extends Comparable<E>> {
    private static final int MAX_THREAD_COUNT = 8;

    private final PriorityQueue<E> q = new PriorityQueue<>();
    private final List<AtomicReference<OperationOrResult<E>>> fcArray = new ArrayList<>(MAX_THREAD_COUNT);
    private final Lock fcLock = new ReentrantLock();
    private final AtomicInteger threadCounter = new AtomicInteger(0);
    private final ThreadLocal<Integer> threadId = ThreadLocal.withInitial(threadCounter::getAndIncrement);

    public FCPriorityQueue() {
        for (int i = 0; i < MAX_THREAD_COUNT; i++) {
            fcArray.add(new AtomicReference<>(null));
        }
    }

    private void combine() {
        for (AtomicReference<OperationOrResult<E>> opRef : fcArray) {
            OperationOrResult<E> op = opRef.get();
            if (op instanceof Operation) {
                Operation<E> realOp = (Operation<E>) op;
                switch (realOp.type) {
                    case POLL:
                        opRef.set(new Result<>(q.poll()));
                        break;
                    case PEEK:
                        opRef.set(new Result<>(q.peek()));
                        break;
                    case ADD:
                        q.add(realOp.element);
                        opRef.set(new Result<>(null));
                        break;
                }
            }
        }
    }

    public E poll() {
        return operation(OperationType.POLL, null, q::poll);
    }

    public E peek() {
        return operation(OperationType.PEEK, null, q::peek);
    }

    public void add(E element) {
        operation(OperationType.ADD, element, () -> {q.add(element); return null;});
    }

    private E operation(OperationType type, E element, Supplier<E> operation) {
        E result;
        if (fcLock.tryLock()) {
            try {
                result = operation.get();
                combine();
            } finally {
                fcLock.unlock();
            }
        } else {
            Operation<E> op = new Operation<>(type, element);
            AtomicReference<OperationOrResult<E>> syncCell = fcArray.get(threadId.get());
            syncCell.set(op);
            while (true) {
                OperationOrResult<E> opOrRes = syncCell.get();
                if (opOrRes != op) {
                    result = ((Result<E>) opOrRes).result;
                    syncCell.set(null);
                    break;
                }

                if (fcLock.tryLock()) {
                    try {
                        opOrRes = syncCell.get();
                        if (opOrRes != op) {
                            result = ((Result<E>) opOrRes).result;
                        } else {
                            result = operation.get();
                        }
                        syncCell.set(null);
                        combine();
                    } finally {
                        fcLock.unlock();
                    }
                    break;
                }
            }
        }
        return result;
    }

    private enum OperationType {
        POLL, PEEK, ADD;
    }

    private interface OperationOrResult<E> {}

    private static class Operation<E> implements OperationOrResult<E> {
        private final OperationType type;
        private final E element;

        private Operation(OperationType type, E element) {
            this.type = type;
            this.element = element;
        }

        private Operation(OperationType type) {
            this.type = type;
            this.element = null;
        }
    }

    private static class Result<E> implements OperationOrResult<E> {
        private final E result;

        private Result(E result) {
            this.result = result;
        }
    }
}
