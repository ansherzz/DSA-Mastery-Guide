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
- `FindIfPathExistsUnionFind.java` - Union every edge, then compare the roots of source and destination.

### Medium
- `RedundantConnectionUnionFind.java` - If an edge joins two nodes already connected, it is the redundant one.
- `NumberOfConnectedComponentsUnionFind.java` - Each successful union merges two components into one.
- `AccountsMergeUnionFind.java` - Union accounts that share emails, then aggregate emails by root.
- `NumberOfProvincesUnionFind.java` - Treat every direct connection as a union and count final roots.
- `SatisfiabilityOfEqualityEquationsUnionFind.java` - Union all equalities first, then verify inequalities.
- `GraphValidTreeUnionFind.java` - Exactly n - 1 edges and no union that finds a cycle.
- `NumberOfIslandsUnionFind.java` - Start with one component per land cell and merge right and down neighbors.
- `MostStonesRemovedUnionFind.java` - Group stones by shared row or column; removals equal stones minus groups.
- `NetworkConnectedOperationsUnionFind.java` - Count components; components - 1 spare cables are needed.
- `EarliestMomentEveryoneBecomesFriendsUnionFind.java` - Add edges in time order until one component remains.
- `SmallestStringWithSwapsUnionFind.java` - Indices linked by swaps form groups whose characters can be sorted freely.
- `MinCostToConnectAllPointsUnionFind.java` - Kruskal's minimum spanning tree over all point pairs.
- `LongestConsecutiveSequenceUnionFind.java` - Union each value with value + 1 and track the largest set.
- `SentenceSimilarityIIUnionFind.java` - Build equivalence classes of similar words.
- `EvaluateDivisionUnionFind.java` - Weighted DSU storing each variable's ratio to its root.
- `CountUnreachablePairsUnionFind.java` - Multiply each component's size by the nodes outside it.
- `MinimumScoreOfPathBetweenTwoCitiesUnionFind.java` - Find the component of city 1 and take its smallest road.
- `LexicographicallySmallestEquivalentStringUnionFind.java` - Union equivalent letters, always keeping the smallest letter as the root.
- `PossibleBipartitionUnionFind.java` - Union everyone a person dislikes and reject if the person lands in the same set.
- `RegionsCutBySlashesUnionFind.java` - Split each cell into four triangles and union the ones a slash does not separate.
- `SurroundedRegionsUnionFind.java` - Connect border cells to a virtual node and flip every O that is not in its set.
- `CountCompleteComponentsUnionFind.java` - Compare each component's edge count with k * (k - 1) / 2.

### Hard
- `NumberOfIslandsIIUnionFind.java` - Incremental connectivity as land cells are added one at a time.
- `SwimInRisingWaterUnionFind.java` - Activate cells by elevation until start and end are connected.
- `RedundantConnectionIIUnionFind.java` - Combine two-parent detection with cycle detection in a directed graph.
- `CouplesHoldingHandsUnionFind.java` - Union the couples that share a sofa; each group of k couples needs k - 1 swaps.
- `SimilarStringGroupsUnionFind.java` - Union every pair of strings that differ in at most two positions and count the groups.
- `RemoveMaxNumberOfEdgesUnionFind.java` - Use one DSU per person, apply shared edges first, and count the redundant edges.
- `EdgeLengthLimitedPathsUnionFind.java` - Answer queries offline in order of limit while adding edges in order of distance.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac RedundantConnectionUnionFind.java
java RedundantConnectionUnionFind
```
