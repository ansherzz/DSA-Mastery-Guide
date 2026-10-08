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
- `P01_FindIfPathExistsInGraphDFSGraph.java` - Iterative DFS reachability check with a visited array.
- `P02_IslandPerimeterDFSGraph.java` - Each step off the island or into water counts one perimeter edge.
- `P03_FloodFillDFSGraph.java` - Recolor the connected component of the starting pixel, using the new color as the visited mark.

### Medium
- `P04_NumberOfProvincesDFSGraph.java` - Each DFS marks one full connected component.
- `P05_CourseScheduleDFSGraph.java` - Use DFS states to detect a back edge in the directed graph.
- `P06_CloneGraphDFSGraph.java` - DFS the graph while memoizing already-cloned nodes.
- `P07_PacificAtlanticWaterFlowDFSGraph.java` - Reverse the thinking: start from each ocean and DFS inward.
- `P08_KeysAndRoomsDFSGraph.java` - Run DFS from room 0 and mark every reachable room.
- `P09_NumberOfIslandsDFSGraph.java` - Count components by sinking each island when it is first found.
- `P10_MaxAreaOfIslandDFSGraph.java` - DFS returns the size of each component.
- `P11_SurroundedRegionsDFSGraph.java` - Mark regions connected to the border as safe, then flip the rest.
- `P12_NumberOfEnclavesDFSGraph.java` - Erase land that touches the border and count what remains.
- `P13_CourseScheduleIIDFSGraph.java` - Topological order from reversed DFS finishing order with cycle detection.
- `P14_AllPathsFromSourceToTargetDFSGraph.java` - Enumerate every path in a DAG with push, recurse, pop.
- `P15_FindEventualSafeStatesDFSGraph.java` - Three-state DFS where a node is safe if it can never reach a cycle.
- `P16_ReorderRoutesToCityZeroDFSGraph.java` - Walk the tree outward from city 0 and count roads pointing away.
- `P17_TimeNeededToInformAllEmployeesDFSGraph.java` - Longest weighted root-to-leaf path in the management tree.
- `P18_EvaluateDivisionDFSGraph.java` - Model ratios as weighted edges and multiply along a found path.
- `P19_EmployeeImportanceDFSGraph.java` - Map ids to employees and sum importance over the reporting tree.
- `P20_NumberOfClosedIslandsDFSGraph.java` - Sink everything connected to the border, then count the islands that remain.
- `P21_CountSubIslandsDFSGraph.java` - Traverse each island of the second grid fully while checking it against the first grid.
- `P22_ColoringABorderDFSGraph.java` - Collect border cells during the traversal and recolor them only afterward.
- `P23_IsGraphBipartiteDFSGraph.java` - Two-color each component and look for an edge whose endpoints share a color.
- `P24_MinimumTimeToCollectAllApplesDFSGraph.java` - Walk an edge twice only if the subtree below it contains an apple.
- `P25_LoudAndRichDFSGraph.java` - Memoized DFS over the poorer-to-richer DAG to find the quietest reachable person.

### Hard
- `P26_ReconstructItineraryDFSGraph.java` - Hierholzer's Eulerian path with smallest-destination-first DFS.
- `P27_CriticalConnectionsDFSGraph.java` - Tarjan's bridge finding with discovery and low-link times.
- `P28_LongestIncreasingPathInMatrixDFSGraph.java` - Memoized DFS over the implicit DAG of increasing moves.
- `P29_SumOfDistancesInTreeDFSGraph.java` - Solve for one root, then reroot across each edge in O(1).
- `P30_MakingALargeIslandDFSGraph.java` - Label islands with ids and sizes, then test each zero against its neighboring islands.


## How to run one file
The number in each file name is the suggested solving order (P01 first, P30 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P04_NumberOfProvincesDFSGraph.java
java P04_NumberOfProvincesDFSGraph
```
