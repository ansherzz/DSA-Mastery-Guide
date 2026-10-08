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

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Reachability and connected components** - P01, P02, P05, P07, P11, P20
- **Grid flood fill** - P03, P04, P12, P13, P14, P15, P32, P50
- **Border-first grid DFS** - P10, P16, P17, P30, P31
- **Cycle detection and topological order** - P06, P08, P18, P19, P22, P33, P43
- **Copying graphs and enumerating paths** - P09, P21
- **DFS on trees given as graphs** - P24, P25, P26, P28, P29, P36, P37, P38, P48, P49
- **DFS over weighted relations** - P27
- **Graph coloring** - P34, P35
- **DFS with memoization on a DAG** - P23, P39, P46, P47
- **Eulerian paths** - P40, P41
- **Low-link algorithms (bridges, articulation points, SCC)** - P42, P44, P45

## Problems in this folder

### Easy
- `P01_DFSTraversalOfGraphDFSGraph.java` - Visit a node, mark it, and recurse into each unvisited neighbor; restart from every unvisited node.
- `P02_FindIfPathExistsInGraphDFSGraph.java` - Iterative DFS reachability check with a visited array.
- `P03_IslandPerimeterDFSGraph.java` - Each step off the island or into water counts one perimeter edge.
- `P04_FloodFillDFSGraph.java` - Recolor the connected component of the starting pixel, using the new color as the visited mark.

### Medium
- `P05_NumberOfProvincesDFSGraph.java` - Each DFS marks one full connected component.
- `P06_DetectCycleInUndirectedGraphDFSGraph.java` - A visited neighbor that is not the node we came from closes a cycle.
- `P07_NumberOfOperationsToMakeNetworkConnectedDFSGraph.java` - Count the components; joining c components needs c - 1 cables, provided at least n - 1 cables exist.
- `P08_CourseScheduleDFSGraph.java` - Use DFS states to detect a back edge in the directed graph.
- `P09_CloneGraphDFSGraph.java` - DFS the graph while memoizing already-cloned nodes.
- `P10_PacificAtlanticWaterFlowDFSGraph.java` - Reverse the thinking: start from each ocean and DFS inward.
- `P11_KeysAndRoomsDFSGraph.java` - Run DFS from room 0 and mark every reachable room.
- `P12_NumberOfIslandsDFSGraph.java` - Count components by sinking each island when it is first found.
- `P13_MinesweeperDFSGraph.java` - Reveal the clicked cell; a cell with no adjacent mines spreads the reveal to all eight neighbors.
- `P14_MaxAreaOfIslandDFSGraph.java` - DFS returns the size of each component.
- `P15_NumberOfDistinctIslandsDFSGraph.java` - Record the sequence of DFS moves (including backtracks) as a shape signature and count unique signatures.
- `P16_SurroundedRegionsDFSGraph.java` - Mark regions connected to the border as safe, then flip the rest.
- `P17_NumberOfEnclavesDFSGraph.java` - Erase land that touches the border and count what remains.
- `P18_CourseScheduleIIDFSGraph.java` - Topological order from reversed DFS finishing order with cycle detection.
- `P19_FindAllPossibleRecipesFromGivenSuppliesDFSGraph.java` - Resolve each recipe by DFS over its ingredients, with states that also catch circular dependencies.
- `P20_CourseScheduleIVDFSGraph.java` - Run a DFS from every course to fill a reachability table, then answer each query by lookup.
- `P21_AllPathsFromSourceToTargetDFSGraph.java` - Enumerate every path in a DAG with push, recurse, pop.
- `P22_FindEventualSafeStatesDFSGraph.java` - Three-state DFS where a node is safe if it can never reach a cycle.
- `P23_AllAncestorsOfANodeInADirectedAcyclicGraphDFSGraph.java` - DFS forward from each node in increasing order, adding it as an ancestor of everything it reaches.
- `P24_ReorderRoutesToCityZeroDFSGraph.java` - Walk the tree outward from city 0 and count roads pointing away.
- `P25_TimeNeededToInformAllEmployeesDFSGraph.java` - Longest weighted root-to-leaf path in the management tree.
- `P26_CountNodesWithTheHighestScoreDFSGraph.java` - Subtree sizes from one DFS give the sizes of every piece left when a node is removed.
- `P27_EvaluateDivisionDFSGraph.java` - Model ratios as weighted edges and multiply along a found path.
- `P28_EmployeeImportanceDFSGraph.java` - Map ids to employees and sum importance over the reporting tree.
- `P29_TreeDiameterDFSGraph.java` - At each node combine the two longest downward paths; the best such sum over all nodes is the diameter.
- `P30_NumberOfClosedIslandsDFSGraph.java` - Sink everything connected to the border, then count the islands that remain.
- `P31_CountSubIslandsDFSGraph.java` - Traverse each island of the second grid fully while checking it against the first grid.
- `P32_ColoringABorderDFSGraph.java` - Collect border cells during the traversal and recolor them only afterward.
- `P33_DetectCyclesIn2DGridDFSGraph.java` - DFS through equal letters remembering the cell we came from; reaching a visited cell another way is a cycle.
- `P34_IsGraphBipartiteDFSGraph.java` - Two-color each component and look for an edge whose endpoints share a color.
- `P35_FlowerPlantingWithNoAdjacentDFSGraph.java` - Every garden has at most three neighbors, so one of four flower types is always free.
- `P36_MinimumTimeToCollectAllApplesDFSGraph.java` - Walk an edge twice only if the subtree below it contains an apple.
- `P37_NumberOfNodesInTheSubTreeWithTheSameLabelDFSGraph.java` - Each subtree returns its 26 letter counts; the node's answer is the count of its own letter.
- `P38_MinimumFuelCostToReportToTheCapitalDFSGraph.java` - Each edge is crossed by all the people in the subtree below it, needing ceil(people / seats) cars.
- `P39_LoudAndRichDFSGraph.java` - Memoized DFS over the poorer-to-richer DAG to find the quietest reachable person.

### Hard
- `P40_ReconstructItineraryDFSGraph.java` - Hierholzer's Eulerian path with smallest-destination-first DFS.
- `P41_ValidArrangementOfPairsDFSGraph.java` - Treat each pair as a directed edge and find an Eulerian path with Hierholzer's algorithm.
- `P42_CriticalConnectionsDFSGraph.java` - Tarjan's bridge finding with discovery and low-link times.
- `P43_LongestCycleInAGraphDFSGraph.java` - Follow the single outgoing edge from each unvisited node, stamping visit times; meeting a node from the same walk closes a cycle.
- `P44_ArticulationPointsDFSGraph.java` - A node is a cut vertex if some DFS child cannot reach above it without passing through it.
- `P45_StronglyConnectedComponentsDFSGraph.java` - One DFS with a stack: a node whose low-link equals its own discovery time is the root of a component.
- `P46_LongestIncreasingPathInMatrixDFSGraph.java` - Memoized DFS over the implicit DAG of increasing moves.
- `P47_ParallelCoursesIIIDFSGraph.java` - The finish time of a course is its duration plus the latest finish time among its prerequisites.
- `P48_SumOfDistancesInTreeDFSGraph.java` - Solve for one root, then reroot across each edge in O(1).
- `P49_LongestPathWithDifferentAdjacentCharactersDFSGraph.java` - Tree diameter where a child's chain may be extended only if its character differs from the parent's.
- `P50_MakingALargeIslandDFSGraph.java` - Label islands with ids and sizes, then test each zero against its neighboring islands.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P05_NumberOfProvincesDFSGraph.java
java P05_NumberOfProvincesDFSGraph
```
