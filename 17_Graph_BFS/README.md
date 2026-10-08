# 17. Graph BFS

## What this pattern is
BFS on graphs is the default pattern for shortest path in unweighted graphs and for minimum number of transformations.

## When to use it
- When each edge has equal cost.
- When you need minimum moves, transformations, or layers.
- When the graph may be implicit rather than explicitly listed.
- When queue order guarantees the earliest discovery is optimal.

## How to identify it in interviews
- The question asks for the fewest steps in an unweighted setting.
- You can generate neighbors of a state on the fly.
- Each move has cost 1.
- The answer increases one layer at a time.

## Upgrade path / mental model
Model each state carefully and define how neighbors are generated before writing BFS.

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Unweighted shortest path** - P02, P03, P05, P08, P20, P21, P22, P47
- **Reachability by BFS** - P01, P04, P12, P18, P23, P29
- **Shortest transformation sequence (implicit graph)** - P07, P27, P28, P36, P43, P50
- **Topological sort (Kahn) and in-degrees** - P09, P10, P11, P24, P25, P38, P39, P40
- **Peeling leaves layer by layer** - P13
- **Dijkstra (weighted shortest path)** - P14, P19, P30, P31, P33, P37
- **BFS with a step or resource limit** - P15
- **Multi-source and two-phase BFS** - P16, P48
- **BFS with extra state per node** - P17, P26, P42, P46, P49
- **Graphs with group or hub nodes** - P41
- **0-1 BFS** - P44, P45
- **Two-coloring by BFS** - P06
- **Minimum spanning tree (Prim)** - P32
- **All-pairs shortest paths (Floyd-Warshall)** - P34
- **Bellman-Ford (negative weights)** - P35

## Problems in this folder

### Easy
- `P01_BFSTraversalOfGraphBFSGraph.java` - Visit nodes in order of their distance from the start using a queue and a visited array.
- `P02_ShortestPathInUnweightedGraphBFSGraph.java` - BFS with a parent array to rebuild one shortest path.
- `P03_NodesWithinKHopsBFSGraph.java` - Run BFS level by level and stop after k levels.
- `P04_FindIfPathExistsInGraphBFSGraph.java` - Explore outward from the source and stop when the destination is polled.

### Medium
- `P05_ShortestPathInBinaryMatrixBFSGraph.java` - BFS gives the shortest path length in an unweighted grid.
- `P06_IsGraphBipartiteBFSGraph.java` - Color each BFS layer with the opposite color.
- `P07_MinimumGeneticMutationBFSGraph.java` - Treat each valid gene as a state and mutate one position at a time with BFS.
- `P08_SnakesAndLaddersBFSGraph.java` - Board squares are states; BFS over die rolls gives the fewest moves.
- `P09_TopologicalSortKahnBFSGraph.java` - Kahn's algorithm: repeatedly process nodes whose in-degree is zero.
- `P10_CourseScheduleKahnBFSGraph.java` - Repeatedly take courses with no remaining prerequisites; a cycle leaves some courses untaken.
- `P11_MinimumNumberOfVerticesToReachAllNodesBFSGraph.java` - In a DAG, exactly the nodes with in-degree zero must be chosen.
- `P12_CloneGraphBFSGraph.java` - BFS with an original-to-copy map that doubles as the visited set.
- `P13_MinimumHeightTreesBFSGraph.java` - Peel leaves layer by layer until the tree's centers remain.
- `P14_NetworkDelayTimeBFSGraph.java` - Dijkstra: BFS with a min-heap for weighted edges.
- `P15_CheapestFlightsWithinKStopsBFSGraph.java` - Layered BFS limited to k + 1 flights with price pruning.
- `P16_ShortestBridgeBFSGraph.java` - Flood-fill one island, then multi-source BFS to the other.
- `P17_ShortestPathWithAlternatingColorsBFSGraph.java` - BFS over (node, last edge color) states.
- `P18_JumpGameVIIBFSGraph.java` - BFS over indices, with a pointer that prevents re-scanning positions already examined.
- `P19_PathWithMinimumEffortBFSGraph.java` - Dijkstra where a path's cost is its largest step.
- `P20_MinimumKnightMovesBFSGraph.java` - Bounded BFS on an infinite board using symmetry.
- `P21_AllNodesDistanceKInBinaryTreeBFSGraph.java` - Add parent links to make the tree a graph, then take BFS layer k.
- `P22_FindClosestNodeToGivenTwoNodesBFSGraph.java` - Compute distances from both start nodes and pick the node minimizing the larger of its two distances.
- `P23_KeysAndRoomsBFSGraph.java` - Reachability from room 0 where each key is a directed edge.
- `P24_ParallelCoursesBFSGraph.java` - Run Kahn's algorithm level by level; each level is one semester.
- `P25_FindEventualSafeStatesKahnBFSGraph.java` - Reverse the edges and peel off nodes whose every original outgoing edge leads to a safe node.
- `P26_TheMazeBFSGraph.java` - BFS over stopping positions, where each edge is a full roll until a wall.
- `P27_WaterAndJugProblemBFSGraph.java` - Search the implicit graph of (jug 1, jug 2) states under fill, empty, and pour moves.
- `P28_MinimumOperationsToMakeXAndYEqualBFSGraph.java` - BFS over integer values where each value has up to four neighbors.
- `P29_DetonateTheMaximumBombsBFSGraph.java` - Build a directed in-range graph and count the bombs reachable from each start.
- `P30_PathWithMaximumProbabilityBFSGraph.java` - Dijkstra with a max-heap, multiplying probabilities instead of adding distances.
- `P31_TheMazeIIBFSGraph.java` - Each roll is an edge whose weight is the number of cells travelled, so use Dijkstra over stopping points.
- `P32_MinimumSpanningTreePrimBFSGraph.java` - Grow one tree from a start node, always adding the cheapest edge that reaches a new node.
- `P33_NumberOfWaysToArriveAtDestinationBFSGraph.java` - Dijkstra that also counts how many shortest routes reach each node.
- `P34_FindTheCityWithTheSmallestNumberOfNeighborsBFSGraph.java` - Compute all-pairs shortest paths with Floyd-Warshall and count the cities within the threshold of each city.
- `P35_BellmanFordShortestPathBFSGraph.java` - Relax every edge V - 1 times; one more successful relaxation reveals a negative cycle.

### Hard
- `P36_WordLadderBFSGraph.java` - BFS over one-letter transformations yields the shortest chain.
- `P37_SwimInRisingWaterDijkstraBFSGraph.java` - Dijkstra where a path's cost is the maximum elevation on it instead of the sum.
- `P38_AlienDictionaryBFSGraph.java` - Derive letter-order edges from adjacent words and topologically sort them.
- `P39_SortItemsByGroupsRespectingDependenciesBFSGraph.java` - Topologically sort the groups and the items separately, then lay the items out group by group.
- `P40_LargestColorValueInADirectedGraphBFSGraph.java` - Process nodes in topological order while pushing per-color path counts along every edge.
- `P41_BusRoutesBFSGraph.java` - BFS where one layer means boarding one more bus.
- `P42_ShortestPathVisitingAllNodesBFSGraph.java` - BFS over (node, visited bitmask) states from all starts at once.
- `P43_JumpGameIVBFSGraph.java` - BFS over indices, clearing each value group after its first use to stay linear.
- `P44_MinimumCostToMakeAtLeastOneValidPathBFSGraph.java` - 0-1 BFS with a deque: following the sign is free, changing it costs one.
- `P45_MinimumObstacleRemovalToReachCornerBFSGraph.java` - Moving into an empty cell costs 0 and into an obstacle costs 1; a deque keeps the frontier sorted.
- `P46_ShortestPathToGetAllKeysBFSGraph.java` - BFS over (cell, key bitmask) states until every key bit is set.
- `P47_ShortestCycleInAGraphBFSGraph.java` - BFS from every node; a non-tree edge between two visited nodes closes a cycle of length dist[u] + dist[v] + 1.
- `P48_ShortestDistanceFromAllBuildingsBFSGraph.java` - BFS from each building, accumulating distances per empty cell and counting how many buildings reach it.
- `P49_SecondMinimumTimeToReachDestinationBFSGraph.java` - BFS that lets every node be reached twice, with its shortest and its strictly second-shortest hop counts.
- `P50_WordLadderIIBFSGraph.java` - BFS records each word's distance from the start; a backward DFS then follows only distance-decreasing steps.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P05_ShortestPathInBinaryMatrixBFSGraph.java
java P05_ShortestPathInBinaryMatrixBFSGraph
```
