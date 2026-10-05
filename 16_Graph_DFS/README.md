# 16. Graph DFS

## What this pattern is
Depth-first search on graphs explores as far as possible before backtracking. It is useful for connected components, cycle detection, and reachability.

## When to use it
- When you need to visit everything reachable from a node.
- When connected components or cycle detection matters.
- When recursion naturally models exploration paths.
- When graph state must be marked visited to avoid repeats.

## How to identify it in interviews
- The input is an adjacency list, matrix, or grid with graph behavior.
- You need to answer reachability or component questions.
- The order of traversal is flexible, not strictly shortest path.
- States form a graph and can be revisited without a visited set.

## Upgrade path / mental model
Be explicit about visited state: unvisited, visiting, and visited when detecting directed cycles.

## Problems in this folder

### Easy
- `FindIfPathExistsInGraphDFSGraph.java` - Iterative DFS reachability check with a visited array.
- `IslandPerimeterDFSGraph.java` - Each step off the island or into water counts one perimeter edge.

### Medium
- `NumberOfProvincesDFSGraph.java` - Each DFS marks one full connected component.
- `CourseScheduleDFSGraph.java` - Use DFS states to detect a back edge in the directed graph.
- `CloneGraphDFSGraph.java` - DFS the graph while memoizing already-cloned nodes.
- `PacificAtlanticWaterFlowDFSGraph.java` - Reverse the thinking: start from each ocean and DFS inward.
- `KeysAndRoomsDFSGraph.java` - Run DFS from room 0 and mark every reachable room.
- `NumberOfIslandsDFSGraph.java` - Count components by sinking each island when it is first found.
- `MaxAreaOfIslandDFSGraph.java` - DFS returns the size of each component.
- `SurroundedRegionsDFSGraph.java` - Mark regions connected to the border as safe, then flip the rest.
- `NumberOfEnclavesDFSGraph.java` - Erase land that touches the border and count what remains.
- `CourseScheduleIIDFSGraph.java` - Topological order from reversed DFS finishing order with cycle detection.
- `AllPathsFromSourceToTargetDFSGraph.java` - Enumerate every path in a DAG with push, recurse, pop.
- `FindEventualSafeStatesDFSGraph.java` - Three-state DFS where a node is safe if it can never reach a cycle.
- `ReorderRoutesToCityZeroDFSGraph.java` - Walk the tree outward from city 0 and count roads pointing away.
- `TimeNeededToInformAllEmployeesDFSGraph.java` - Longest weighted root-to-leaf path in the management tree.
- `EvaluateDivisionDFSGraph.java` - Model ratios as weighted edges and multiply along a found path.

### Hard
- `ReconstructItineraryDFSGraph.java` - Hierholzer's Eulerian path with smallest-destination-first DFS.
- `CriticalConnectionsDFSGraph.java` - Tarjan's bridge finding with discovery and low-link times.
- `LongestIncreasingPathInMatrixDFSGraph.java` - Memoized DFS over the implicit DAG of increasing moves.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac NumberOfProvincesDFSGraph.java
java NumberOfProvincesDFSGraph
```
