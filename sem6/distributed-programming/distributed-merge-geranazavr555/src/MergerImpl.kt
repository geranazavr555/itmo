import system.MergerEnvironment
import java.util.*

class MergerImpl<T : Comparable<T>>(
    private val mergerEnvironment: MergerEnvironment<T>,
    prevStepBatches: Map<Int, List<T>>?
) : Merger<T> {
    private val iterators: SortedSet<Node<T>> = TreeSet(compareBy { it.list[it.position] })

    init {
        if (prevStepBatches != null) {
            for (id in prevStepBatches.keys) {
                iterators.add(Node(dhID = id, position = 0, prevStepBatches[id]!!))
            }
            for (id in 0 until mergerEnvironment.dataHoldersCount) {
                if (!prevStepBatches.keys.contains(id)) {
                    val newValues = mergerEnvironment.requestBatch(id)
                    if (newValues.isNotEmpty()) {
                        iterators.add(Node(dhID = id, position = 0, newValues))
                    }
                }
            }
        } else {
            for (id in 0 until mergerEnvironment.dataHoldersCount) {
                val newValues = mergerEnvironment.requestBatch(id)
                if (newValues.isNotEmpty()) {
                    iterators.add(Node(dhID = id, position = 0, newValues))
                }
            }
        }
    }

    override fun mergeStep(): T? {
        if (iterators.isEmpty()) return null

        val answNode = iterators.first()
        iterators.remove(answNode)
        val answer = answNode.list[answNode.position]
        if (answNode.position >= answNode.list.size - 1) {
            // Запросить у датахолдера данные
            val newValues = mergerEnvironment.requestBatch(answNode.dhID)
            if (newValues.isNotEmpty()) {
                iterators.add(Node(dhID = answNode.dhID, 0, newValues))
            }
        } else {
            iterators.add(Node(dhID = answNode.dhID, answNode.position + 1, answNode.list))
        }
        return answer
    }

    override fun getRemainingBatches(): Map<Int, List<T>> {
        val answer = HashMap<Int, List<T>>()
        for (node in iterators) {
            answer[node.dhID] = ArrayList(node.list.subList(node.position, node.list.size))
        }
        return answer
    }
}

data class Node<T : Comparable<T>>(
    val dhID: Int,
    val position: Int,
    val list: List<T>
)
