import kotlinx.atomicfu.*

class FAAQueue<T> {
    private val head: AtomicRef<Segment> // Head pointer, similarly to the Michael-Scott queue (but the first node is _not_ sentinel)
    private val tail: AtomicRef<Segment> // Tail pointer, similarly to the Michael-Scott queue

    init {
        val firstNode = Segment()
        head = atomic(firstNode)
        tail = atomic(firstNode)
    }

    /**
     * Adds the specified element [x] to the queue.
     */
    fun enqueue(x: T) {
        while (true) {
            val tail = this.tail.value;
            val enqIdx = tail.enqIdx.getAndIncrement();
            if (enqIdx >= SEGMENT_SIZE) {
                val segment = Segment(x);
//                while (true) {
                    if (tail.next.compareAndSet(null, segment)) {
                        this.tail.compareAndSet(tail, segment)
                        return;
                    } else {
                        this.tail.compareAndSet(tail, tail.next.value!!)
                    }
//                }

                if (tail.next.compareAndSet(null, segment)) {
                    this.tail.compareAndSet(tail, tail.next.value!!);
                    return;
                }
            } else {
                if (tail.elements[enqIdx].compareAndSet(null, x)) return
            }
        }
    }

    /**
     * Retrieves the first element from the queue
     * and returns it; returns `null` if the queue
     * is empty.
     */
    fun dequeue(): T? {
        while (true) {
            val head = this.head.value;
            val deqIdx = head.deqIdx.getAndIncrement();
            if (deqIdx >= SEGMENT_SIZE) {
                val headNext = head.next.value ?: return null
                this.head.compareAndSet(head, headNext)
                continue
            }
            val res = head.elements[deqIdx].getAndSet(DONE);
            if (res == null) continue
            @Suppress("UNCHECKED_CAST")
            return res as T?
        }
    }

    /**
     * Returns `true` if this queue is empty;
     * `false` otherwise.
     */
    val isEmpty: Boolean
        get() {
            while (true) {
                if (head.value.isEmpty) {
                    if (head.value.next.value == null) return true
                    head.value = head.value.next.value!!
                    continue
                } else {
                    return false
                }
            }
        }
}

private class Segment {
    val next: AtomicRef<Segment?> = atomic(null);
    val enqIdx = atomic(0);
    val deqIdx = atomic(0);
    val elements = atomicArrayOfNulls<Any?>(SEGMENT_SIZE);

    constructor() // for the first segment creation

    constructor(x: Any?) { // each next new segment should be constructed with an element
        enqIdx.getAndIncrement();
        elements[0].getAndSet(x);
    }

    val isEmpty: Boolean get() = deqIdx.value >= enqIdx.value || deqIdx.value >= SEGMENT_SIZE
}

private val DONE = Any() // Marker for the "DONE" slot state; to avoid memory leaks
const val SEGMENT_SIZE = 2 // DO NOT CHANGE, IMPORTANT FOR TESTS

