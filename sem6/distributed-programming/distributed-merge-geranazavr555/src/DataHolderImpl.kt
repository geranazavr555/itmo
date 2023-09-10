import system.DataHolderEnvironment

class DataHolderImpl<T : Comparable<T>>(
    private val keys: List<T>,
    private val dataHolderEnvironment: DataHolderEnvironment
) : DataHolder<T> {
    private var rollBackItr = 0
    private var curPosition = 0

    override fun checkpoint() {
        rollBackItr = curPosition
    }

    override fun rollBack() {
        curPosition = rollBackItr
    }

    override fun getBatch(): List<T> {
        val batch = dataHolderEnvironment.batchSize
        if (curPosition + batch <= keys.size) {
            val answ = ArrayList(keys.subList(curPosition, curPosition + batch))
            curPosition += batch
            return answ
        }
        if (curPosition < keys.size) {
            val answ = ArrayList(keys.subList(curPosition, keys.size))
            curPosition = keys.size
            return answ
        }
        return listOf()
    }
}