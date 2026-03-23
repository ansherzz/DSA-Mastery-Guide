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
- `RedundantConnectionUnionFind.java` - If an edge joins two nodes already connected, it is the redundant one.
- `NumberOfConnectedComponentsUnionFind.java` - Each successful union merges two components into one.
- `AccountsMergeUnionFind.java` - Union accounts that share emails, then aggregate emails by root.
- `NumberOfProvincesUnionFind.java` - Treat every direct connection as a union and count final roots.
- `SatisfiabilityOfEqualityEquationsUnionFind.java` - Union all equalities first, then verify inequalities.


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
