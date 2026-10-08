# 18. Union Find

## What this pattern is
Union Find, or Disjoint Set Union, tracks connected components under merge operations using near O(1) amortized find and union.

## When to use it
- When edges connect nodes dynamically.
- When you need to know whether two nodes belong to the same component.
- When merging groups is easier than traversing the graph repeatedly.
- When cycle detection in an undirected graph is needed.

## How to identify it in interviews
- The prompt says connect, merge, same group, or redundant edge.
- You process many union operations.
- Connectivity queries repeat after merges.
- Graph traversal from scratch would be wasteful.

## Upgrade path / mental model
Use path compression and union by rank or size to keep operations fast.

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Connectivity queries and counting components** - P01, P02, P04, P06, P14, P24
- **Cycle detection (redundant edges)** - P03, P09, P39
- **Grouping equivalent items** - P05, P08, P16, P17, P22, P26, P41
- **Grid cells as nodes** - P10, P11, P30, P31, P32, P34, P35
- **Rows, columns, or values as virtual nodes** - P12, P13, P21, P46, P47
- **Sort the edges, then union (Kruskal and offline queries)** - P15, P18, P19, P20, P37, P44, P45, P50
- **Weighted union find** - P23
- **Component aggregates (size, minimum, edge count)** - P07, P25, P33, P48
- **Bipartite check by uniting opposites** - P28, P29
- **Counting swaps through components** - P40
- **Several union-find structures at once** - P43
- **Union find as a "next free" pointer** - P27
- **Union with a constraint check** - P36
- **Reverse time (turn deletions into unions)** - P38, P49
- **Union find with resets** - P42

## Problems in this folder

### Easy
- `P01_UnionFindTemplateUnionFind.java` - The reusable structure: find with path compression, union by rank, a component counter, and set sizes.
- `P02_FindIfPathExistsUnionFind.java` - Union every edge, then compare the roots of source and destination.

### Medium
- `P03_RedundantConnectionUnionFind.java` - If an edge joins two nodes already connected, it is the redundant one.
- `P04_NumberOfConnectedComponentsUnionFind.java` - Each successful union merges two components into one.
- `P05_AccountsMergeUnionFind.java` - Union accounts that share emails, then aggregate emails by root.
- `P06_NumberOfProvincesUnionFind.java` - Treat every direct connection as a union and count final roots.
- `P07_LargestGroupAfterEachFriendshipUnionFind.java` - Track set sizes during unions and keep a running maximum.
- `P08_SatisfiabilityOfEqualityEquationsUnionFind.java` - Union all equalities first, then verify inequalities.
- `P09_GraphValidTreeUnionFind.java` - Exactly n - 1 edges and no union that finds a cycle.
- `P10_NumberOfIslandsUnionFind.java` - Start with one component per land cell and merge right and down neighbors.
- `P11_MaxAreaOfIslandUnionFind.java` - Union each land cell with its right and down neighbors; the largest set size is the answer.
- `P12_MostStonesRemovedUnionFind.java` - Group stones by shared row or column; removals equal stones minus groups.
- `P13_CountServersThatCommunicateUnionFind.java` - Union each server's row node with its column node, then count servers in groups of two or more.
- `P14_NetworkConnectedOperationsUnionFind.java` - Count components; components - 1 spare cables are needed.
- `P15_EarliestMomentEveryoneBecomesFriendsUnionFind.java` - Add edges in time order until one component remains.
- `P16_SmallestStringWithSwapsUnionFind.java` - Indices linked by swaps form groups whose characters can be sorted freely.
- `P17_MinimizeHammingDistanceAfterSwapOperationsUnionFind.java` - Indices linked by swaps form groups whose values can be rearranged freely; match values group by group.
- `P18_MinCostToConnectAllPointsUnionFind.java` - Kruskal's minimum spanning tree over all point pairs.
- `P19_ConnectingCitiesWithMinimumCostUnionFind.java` - Kruskal's algorithm: take edges from cheapest to most expensive, skipping those that would form a cycle.
- `P20_PathWithMinimumEffortUnionFind.java` - Add grid edges in increasing height difference until the start and end cells become connected.
- `P21_LongestConsecutiveSequenceUnionFind.java` - Union each value with value + 1 and track the largest set.
- `P22_SentenceSimilarityIIUnionFind.java` - Build equivalence classes of similar words.
- `P23_EvaluateDivisionUnionFind.java` - Weighted DSU storing each variable's ratio to its root.
- `P24_CountUnreachablePairsUnionFind.java` - Multiply each component's size by the nodes outside it.
- `P25_MinimumScoreOfPathBetweenTwoCitiesUnionFind.java` - Find the component of city 1 and take its smallest road.
- `P26_LexicographicallySmallestEquivalentStringUnionFind.java` - Union equivalent letters, always keeping the smallest letter as the root.
- `P27_NearestFreeSlotAssignmentUnionFind.java` - Each used slot points to the next slot, so find() jumps straight to the first free slot at or after a position.
- `P28_PossibleBipartitionUnionFind.java` - Union everyone a person dislikes and reject if the person lands in the same set.
- `P29_IsGraphBipartiteUnionFind.java` - All neighbors of a node must be on the opposite side, so unite them with each other.
- `P30_RegionsCutBySlashesUnionFind.java` - Split each cell into four triangles and union the ones a slash does not separate.
- `P31_CheckIfThereIsAValidPathInAGridUnionFind.java` - Union two adjacent cells only when their street pieces actually connect to each other.
- `P32_SurroundedRegionsUnionFind.java` - Connect border cells to a virtual node and flip every O that is not in its set.
- `P33_CountCompleteComponentsUnionFind.java` - Compare each component's edge count with k * (k - 1) / 2.

### Hard
- `P34_NumberOfIslandsIIUnionFind.java` - Incremental connectivity as land cells are added one at a time.
- `P35_MakingALargeIslandUnionFind.java` - Build islands with unions, then for each water cell add up the sizes of its distinct neighboring islands.
- `P36_ProcessRestrictedFriendRequestsUnionFind.java` - Before merging two groups, verify that no restricted pair would end up in the same group.
- `P37_SwimInRisingWaterUnionFind.java` - Activate cells by elevation until start and end are connected.
- `P38_LastDayWhereYouCanStillCrossUnionFind.java` - Replay the flooding backwards: adding land back is a union, and the first moment top meets bottom is the answer.
- `P39_RedundantConnectionIIUnionFind.java` - Combine two-parent detection with cycle detection in a directed graph.
- `P40_CouplesHoldingHandsUnionFind.java` - Union the couples that share a sofa; each group of k couples needs k - 1 swaps.
- `P41_SimilarStringGroupsUnionFind.java` - Union every pair of strings that differ in at most two positions and count the groups.
- `P42_FindAllPeopleWithSecretUnionFind.java` - Process meetings one time slot at a time; afterwards, detach everyone whose group did not include person 0.
- `P43_RemoveMaxNumberOfEdgesUnionFind.java` - Use one DSU per person, apply shared edges first, and count the redundant edges.
- `P44_OptimizeWaterDistributionInAVillageUnionFind.java` - Model each well as an edge from a virtual water source, then run Kruskal on the combined edge list.
- `P45_EdgeLengthLimitedPathsUnionFind.java` - Answer queries offline in order of limit while adding edges in order of distance.
- `P46_GraphConnectivityWithThresholdUnionFind.java` - Union every number above the threshold with all of its multiples, then answer queries by root comparison.
- `P47_LargestComponentSizeByCommonFactorUnionFind.java` - Union each number with its factors, so numbers sharing a factor meet through that factor's node.
- `P48_MinimizeMalwareSpreadUnionFind.java` - Removing an infected node helps only if it is the sole infected node of its component; prefer the largest such component.
- `P49_BricksFallingWhenHitUnionFind.java` - Remove all hit bricks first, then add them back in reverse order and measure how much the roof's component grows.
- `P50_NumberOfGoodPathsUnionFind.java` - Activate nodes in increasing value; equal-valued nodes that end up in the same component form good paths.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P03_RedundantConnectionUnionFind.java
java P03_RedundantConnectionUnionFind
```
