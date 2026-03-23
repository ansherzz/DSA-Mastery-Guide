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
- `NumberOfProvincesDFSGraph.java` - Each DFS marks one full connected component.
- `CourseScheduleDFSGraph.java` - Use DFS states to detect a back edge in the directed graph.
- `CloneGraphDFSGraph.java` - DFS the graph while memoizing already-cloned nodes.
- `PacificAtlanticWaterFlowDFSGraph.java` - Reverse the thinking: start from each ocean and DFS inward.
- `KeysAndRoomsDFSGraph.java` - Run DFS from room 0 and mark every reachable room.


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
