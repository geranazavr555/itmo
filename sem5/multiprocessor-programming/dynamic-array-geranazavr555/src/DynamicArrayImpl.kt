import kotlinx.atomicfu.*

class DynamicArrayImpl<E> : DynamicArray<E> {
    private val core = atomic(Core<Any>(INITIAL_CAPACITY))
    private val _size = atomic(0)

    override fun get(index: Int): E {
        if (_size.value <= index)
            throw IllegalArgumentException()
        while (true) {
            val res = core.value.array[index].value
            if (res !is Moved<*>)
                return res as E
            else
                return res.value as E
        }
    }

    override fun put(index: Int, element: E) {
        if (_size.value <= index)
            throw IllegalArgumentException()
        while (true) {
            val curVal = core.value.array[index].value
            if (curVal is Moved<*>)
                continue
            if (core.value.array[index].compareAndSet(curVal, element))
                return
        }
    }

    override fun pushBack(element: E) {
        yy@while (true) {
            val curCore = core.value
            val curSize = _size.value
            if (curSize < curCore.array.size) {
                if (curCore.array[curSize].compareAndSet(null, element)) {
                    _size.getAndIncrement()
                    return
                }
            } else {
                val newCore = Core<Any>(curCore.array.size * 2)
//                if (!curCore.nextCore.compareAndSet(null, newCore)) newCore = curCore.nextCore.value!!
                xx@for (i in 0 until curCore.array.size) {
//                    while (true) {
                    val x = curCore.array[i].value ?: continue@xx
                    if (x is Moved<*>) {
                        newCore.array[i].compareAndSet(null, x.value as E)
                        if (!curCore.array[i].compareAndSet(x, Moved(x.value as E))) continue@yy
                    } else {
                        newCore.array[i].compareAndSet(null, x as E)
                        if (!curCore.array[i].compareAndSet(x, Moved(x as E))) continue@yy
                    }
//                    }
                }
                core.compareAndSet(curCore, newCore)
            }
        }
    }

    override val size: Int get() = _size.value
}

private class Core<E>(
    capacity: Int
) {
    //    val size: AtomicInt = atomic(0)
    val nextCore: AtomicRef<Core<E>?> = atomic(null)
    val array = atomicArrayOfNulls<E>(capacity)
}

private class Moved<E>(
    val value: E?
)

private const val INITIAL_CAPACITY = 1 // DO NOT CHANGE ME