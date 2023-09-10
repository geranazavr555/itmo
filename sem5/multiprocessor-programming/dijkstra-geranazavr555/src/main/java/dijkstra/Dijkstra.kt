package dijkstra

import kotlinx.atomicfu.AtomicInt
import kotlinx.atomicfu.atomic
import java.util.*
import java.util.concurrent.Phaser
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.locks.ReentrantLock
import kotlin.Comparator
import kotlin.concurrent.thread

private val NODE_DISTANCE_COMPARATOR = Comparator<Node> { o1, o2 -> o1!!.distance.compareTo(o2!!.distance) }

// Returns `Integer.MAX_VALUE` if a path has not been found.
fun shortestPathParallel(start: Node) {
    val workers = Runtime.getRuntime().availableProcessors()
    start.distance = 0
    val q = MultiQueuePriorityQueue(2 * workers, NODE_DISTANCE_COMPARATOR)
    q.add(start)
    val onFinish = Phaser(workers + 1)
    val activeNodes = ActiveNodesHolder()._atomic
//    val activeNodes = atomic(1)
    repeat(workers) {
        thread {
            while (true) {
                val cur: Node = q.poll() ?: if (activeNodes.compareAndSet(0, 0)) break else continue
                for (e in cur.outgoingEdges) {
                    while (true) {
                        val toDist = e.to.distance
                        if (toDist > cur.distance + e.weight) {
                            val kek = cur.distance + e.weight;
                            if (e.to.casDistance(toDist, kek)) {
                                q.add(e.to)
                                activeNodes.incrementAndGet()
                            } else
                                continue
                        } else
                            break
                    }
                }
                activeNodes.decrementAndGet()
            }
            onFinish.arrive()
        }
    }
    onFinish.arriveAndAwaitAdvance()
}

class ActiveNodesHolder {
    val _atomic: AtomicInt = atomic(1)
//    private val _atomic: AtomicInt = atomic(1)
//    fun compareAndSet(cur: Int, update: Int) = _atomic.compareAndSet(cur, update)
//    fun incrementAndGet() = _atomic.incrementAndGet()
//    fun decrementAndGet() = _atomic.decrementAndGet()
}

class MultiQueuePriorityQueue internal constructor(workers: Int, nodeComparator: java.util.Comparator<Node>?) {
    private var queues: MutableList<PriorityQueue<Node>>
    private var locks: MutableList<ReentrantLock>
    private var random: ThreadLocalRandom = ThreadLocalRandom.current()
    fun add(v: Node) {
        while (true) {
            val i = random.nextInt(queues.size)
            val lock = locks[i]
            if (lock.tryLock()) {
                try {
                    queues[i].add(v)
                    return
                } finally {
                    lock.unlock()
                }
            }
        }
    }

    fun poll(): Node? {
        while (true) {
            val i = random.nextInt(queues.size)
            var j: Int
            do {
                j = random.nextInt(queues.size)
            } while (i == j)
            val lockA = locks[i]
            val lockB = locks[j]
            var resultA: Boolean
            var resultB: Boolean
            if (i < j) {
                resultA = lockA.tryLock()
                resultB = lockB.tryLock()
            } else {
                resultB = lockB.tryLock()
                resultA = lockA.tryLock()
            }
            if (resultA && resultB) {
                return try {
                    val a = queues[i].peek()
                    val b = queues[j].peek()
                    if (a != null) {
                        if (b != null) {
                            if (a.distance < b.distance) {
                                queues[i].poll()
                            } else {
                                queues[j].poll()
                            }
                        } else {
                            queues[i].poll()
                        }
                    } else {
                        queues[j].poll()
                    }
                } finally {
                    lockB.unlock()
                    lockA.unlock()
                }
            } else {
                if (resultA) lockA.unlock()
                if (resultB) lockB.unlock()
            }
        }
    }

    init {
        queues = ArrayList(workers)
        locks = ArrayList(workers)
        for (i in 0 until workers) {
            queues.add(PriorityQueue(nodeComparator))
            locks.add(ReentrantLock())
        }
    }
}