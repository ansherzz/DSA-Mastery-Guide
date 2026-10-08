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

## Problems in this folder

### Easy
- `P01_ShortestPathInUnweightedGraphBFSGraph.java` - BFS with a parent array to rebuild one shortest path.
- `P02_FindIfPathExistsInGraphBFSGraph.java` - Explore outward from the source and stop when the destination is polled.

### Medium
- `P03_ShortestPathInBinaryMatrixBFSGraph.java` - BFS gives the shortest path length in an unweighted grid.
- `P04_IsGraphBipartiteBFSGraph.java` - Color each BFS layer with the opposite color.
- `P05_MinimumGeneticMutationBFSGraph.java` - Treat each valid gene as a state and mutate one position at a time with BFS.
- `P06_SnakesAndLaddersBFSGraph.java` - Board squares are states; BFS over die rolls gives the fewest moves.
- `P07_TopologicalSortKahnBFSGraph.java` - Kahn's algorithm: repeatedly process nodes whose in-degree is zero.
- `P08_CloneGraphBFSGraph.java` - BFS with an original-to-copy map that doubles as the visited set.
- `P09_MinimumHeightTreesBFSGraph.java` - Peel leaves layer by layer until the tree's centers remain.
- `P10_NetworkDelayTimeBFSGraph.java` - Dijkstra: BFS with a min-heap for weighted edges.
- `P11_CheapestFlightsWithinKStopsBFSGraph.java` - Layered BFS limited to k + 1 flights with price pruning.
- `P12_ShortestBridgeBFSGraph.java` - Flood-fill one island, then multi-source BFS to the other.
- `P13_ShortestPathWithAlternatingColorsBFSGraph.java` - BFS over (node, last edge color) states.
- `P14_PathWithMinimumEffortBFSGraph.java` - Dijkstra where a path's cost is its largest step.
- `P15_MinimumKnightMovesBFSGraph.java` - Bounded BFS on an infinite board using symmetry.
- `P16_AllNodesDistanceKInBinaryTreeBFSGraph.java` - Add parent links to make the tree a graph, then take BFS layer k.
- `P17_KeysAndRoomsBFSGraph.java` - Reachability from room 0 where each key is a directed edge.
- `P18_ParallelCoursesBFSGraph.java` - Run Kahn's algorithm level by level; each level is one semester.
- `P19_TheMazeBFSGraph.java` - BFS over stopping positions, where each edge is a full roll until a wall.
- `P20_WaterAndJugProblemBFSGraph.java` - Search the implicit graph of (jug 1, jug 2) states under fill, empty, and pour moves.
- `P21_DetonateTheMaximumBombsBFSGraph.java` - Build a directed in-range graph and count the bombs reachable from each start.
- `P22_PathWithMaximumProbabilityBFSGraph.java` - Dijkstra with a max-heap, multiplying probabilities instead of adding distances.
- `P23_NumberOfWaysToArriveAtDestinationBFSGraph.java` - Dijkstra that also counts how many shortest routes reach each node.

### Hard
- `P24_WordLadderBFSGraph.java` - BFS over one-letter transformations yields the shortest chain.
- `P25_AlienDictionaryBFSGraph.java` - Derive letter-order edges from adjacent words and topologically sort them.
- `P26_BusRoutesBFSGraph.java` - BFS where one layer means boarding one more bus.
- `P27_ShortestPathVisitingAllNodesBFSGraph.java` - BFS over (node, visited bitmask) states from all starts at once.
- `P28_JumpGameIVBFSGraph.java` - BFS over indices, clearing each value group after its first use to stay linear.
- `P29_MinimumCostToMakeAtLeastOneValidPathBFSGraph.java` - 0-1 BFS with a deque: following the sign is free, changing it costs one.
- `P30_ShortestPathToGetAllKeysBFSGraph.java` - BFS over (cell, key bitmask) states until every key bit is set.


## How to run one file
The number in each file name is the suggested solving order (P01 first, P30 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P03_ShortestPathInBinaryMatrixBFSGraph.java
java P03_ShortestPathInBinaryMatrixBFSGraph
```
