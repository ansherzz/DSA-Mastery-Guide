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
- `ShortestPathInUnweightedGraphBFSGraph.java` - BFS with a parent array to rebuild one shortest path.
- `FindIfPathExistsInGraphBFSGraph.java` - Explore outward from the source and stop when the destination is polled.

### Medium
- `ShortestPathInBinaryMatrixBFSGraph.java` - BFS gives the shortest path length in an unweighted grid.
- `IsGraphBipartiteBFSGraph.java` - Color each BFS layer with the opposite color.
- `MinimumGeneticMutationBFSGraph.java` - Treat each valid gene as a state and mutate one position at a time with BFS.
- `SnakesAndLaddersBFSGraph.java` - Board squares are states; BFS over die rolls gives the fewest moves.
- `TopologicalSortKahnBFSGraph.java` - Kahn's algorithm: repeatedly process nodes whose in-degree is zero.
- `CloneGraphBFSGraph.java` - BFS with an original-to-copy map that doubles as the visited set.
- `MinimumHeightTreesBFSGraph.java` - Peel leaves layer by layer until the tree's centers remain.
- `NetworkDelayTimeBFSGraph.java` - Dijkstra: BFS with a min-heap for weighted edges.
- `CheapestFlightsWithinKStopsBFSGraph.java` - Layered BFS limited to k + 1 flights with price pruning.
- `ShortestBridgeBFSGraph.java` - Flood-fill one island, then multi-source BFS to the other.
- `ShortestPathWithAlternatingColorsBFSGraph.java` - BFS over (node, last edge color) states.
- `PathWithMinimumEffortBFSGraph.java` - Dijkstra where a path's cost is its largest step.
- `MinimumKnightMovesBFSGraph.java` - Bounded BFS on an infinite board using symmetry.
- `AllNodesDistanceKInBinaryTreeBFSGraph.java` - Add parent links to make the tree a graph, then take BFS layer k.
- `KeysAndRoomsBFSGraph.java` - Reachability from room 0 where each key is a directed edge.
- `ParallelCoursesBFSGraph.java` - Run Kahn's algorithm level by level; each level is one semester.
- `TheMazeBFSGraph.java` - BFS over stopping positions, where each edge is a full roll until a wall.
- `WaterAndJugProblemBFSGraph.java` - Search the implicit graph of (jug 1, jug 2) states under fill, empty, and pour moves.
- `DetonateTheMaximumBombsBFSGraph.java` - Build a directed in-range graph and count the bombs reachable from each start.
- `PathWithMaximumProbabilityBFSGraph.java` - Dijkstra with a max-heap, multiplying probabilities instead of adding distances.
- `NumberOfWaysToArriveAtDestinationBFSGraph.java` - Dijkstra that also counts how many shortest routes reach each node.

### Hard
- `WordLadderBFSGraph.java` - BFS over one-letter transformations yields the shortest chain.
- `AlienDictionaryBFSGraph.java` - Derive letter-order edges from adjacent words and topologically sort them.
- `BusRoutesBFSGraph.java` - BFS where one layer means boarding one more bus.
- `ShortestPathVisitingAllNodesBFSGraph.java` - BFS over (node, visited bitmask) states from all starts at once.
- `JumpGameIVBFSGraph.java` - BFS over indices, clearing each value group after its first use to stay linear.
- `MinimumCostToMakeAtLeastOneValidPathBFSGraph.java` - 0-1 BFS with a deque: following the sign is free, changing it costs one.
- `ShortestPathToGetAllKeysBFSGraph.java` - BFS over (cell, key bitmask) states until every key bit is set.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac ShortestPathInBinaryMatrixBFSGraph.java
java ShortestPathInBinaryMatrixBFSGraph
```
