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

## Problems in this folder

### Easy
- `P01_FindIfPathExistsUnionFind.java` - Union every edge, then compare the roots of source and destination.

### Medium
- `P02_RedundantConnectionUnionFind.java` - If an edge joins two nodes already connected, it is the redundant one.
- `P03_NumberOfConnectedComponentsUnionFind.java` - Each successful union merges two components into one.
- `P04_AccountsMergeUnionFind.java` - Union accounts that share emails, then aggregate emails by root.
- `P05_NumberOfProvincesUnionFind.java` - Treat every direct connection as a union and count final roots.
- `P06_SatisfiabilityOfEqualityEquationsUnionFind.java` - Union all equalities first, then verify inequalities.
- `P07_GraphValidTreeUnionFind.java` - Exactly n - 1 edges and no union that finds a cycle.
- `P08_NumberOfIslandsUnionFind.java` - Start with one component per land cell and merge right and down neighbors.
- `P09_MostStonesRemovedUnionFind.java` - Group stones by shared row or column; removals equal stones minus groups.
- `P10_NetworkConnectedOperationsUnionFind.java` - Count components; components - 1 spare cables are needed.
- `P11_EarliestMomentEveryoneBecomesFriendsUnionFind.java` - Add edges in time order until one component remains.
- `P12_SmallestStringWithSwapsUnionFind.java` - Indices linked by swaps form groups whose characters can be sorted freely.
- `P13_MinCostToConnectAllPointsUnionFind.java` - Kruskal's minimum spanning tree over all point pairs.
- `P14_LongestConsecutiveSequenceUnionFind.java` - Union each value with value + 1 and track the largest set.
- `P15_SentenceSimilarityIIUnionFind.java` - Build equivalence classes of similar words.
- `P16_EvaluateDivisionUnionFind.java` - Weighted DSU storing each variable's ratio to its root.
- `P17_CountUnreachablePairsUnionFind.java` - Multiply each component's size by the nodes outside it.
- `P18_MinimumScoreOfPathBetweenTwoCitiesUnionFind.java` - Find the component of city 1 and take its smallest road.
- `P19_LexicographicallySmallestEquivalentStringUnionFind.java` - Union equivalent letters, always keeping the smallest letter as the root.
- `P20_PossibleBipartitionUnionFind.java` - Union everyone a person dislikes and reject if the person lands in the same set.
- `P21_RegionsCutBySlashesUnionFind.java` - Split each cell into four triangles and union the ones a slash does not separate.
- `P22_SurroundedRegionsUnionFind.java` - Connect border cells to a virtual node and flip every O that is not in its set.
- `P23_CountCompleteComponentsUnionFind.java` - Compare each component's edge count with k * (k - 1) / 2.

### Hard
- `P24_NumberOfIslandsIIUnionFind.java` - Incremental connectivity as land cells are added one at a time.
- `P25_SwimInRisingWaterUnionFind.java` - Activate cells by elevation until start and end are connected.
- `P26_RedundantConnectionIIUnionFind.java` - Combine two-parent detection with cycle detection in a directed graph.
- `P27_CouplesHoldingHandsUnionFind.java` - Union the couples that share a sofa; each group of k couples needs k - 1 swaps.
- `P28_SimilarStringGroupsUnionFind.java` - Union every pair of strings that differ in at most two positions and count the groups.
- `P29_RemoveMaxNumberOfEdgesUnionFind.java` - Use one DSU per person, apply shared edges first, and count the redundant edges.
- `P30_EdgeLengthLimitedPathsUnionFind.java` - Answer queries offline in order of limit while adding edges in order of distance.


## How to run one file
The number in each file name is the suggested solving order (P01 first, P30 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P02_RedundantConnectionUnionFind.java
java P02_RedundantConnectionUnionFind
```
