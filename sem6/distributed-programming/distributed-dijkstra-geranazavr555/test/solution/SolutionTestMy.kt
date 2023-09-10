package dijkstra.solution

import dijkstra.generators.generateGraph
import dijkstra.graph.Graph
import dijkstra.sequential.dijkstraHeap
import dijkstra.system.runtime.FIFORuntime
import dijkstra.system.executor.SystemExecutor
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.random.Random

class SolutionTestMy {
    @Test
    fun stress() {
        repeat(10000) {
            if (it % 1 == 0) {
                println(it)
            }
//            val graph = generateGraph(
//                nodeFrom = 3, nodeTo = 5,
//                edgesFromTo = { nodes -> Pair(0, nodes * nodes / 5) },
//                weightFrom = 0L, weightTo = 100L,
//                skipEdges = true
//            )
            val graph = Graph(
                  arrayListOf(
                      hashMapOf(
                          Pair(0, 37),
                          Pair(1, 31),
                          Pair(2, 16)
                      ),
                      hashMapOf(
                          Pair(1, 23)
                      ),
                      hashMapOf(
                          Pair(0, 51),
                          Pair(1, 15),
                          Pair(3, 43),
                      ),
                      hashMapOf(
                          Pair(0, 57),
                      )
                  )
            );
            println(graph)
            val executor = SystemExecutor(graph = graph) { FIFORuntime() }
            val rnd = System.currentTimeMillis();
//            val rnd = 1648997869082L;
            println("=====> " + rnd)
            val sId = Random(rnd).nextInt(from = 0, until = graph.graph.size)
            val expectedAns = dijkstraHeap(graph, sId)
            val ans = executor.execute(sId)
            Assertions.assertEquals(expectedAns, ans)
        }
    }
}