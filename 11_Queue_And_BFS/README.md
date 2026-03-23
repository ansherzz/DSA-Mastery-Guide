# 11. Queue / BFS

## What this pattern is
Breadth-first search explores layer by layer. A queue is the right tool when the shortest number of steps or level-wise expansion matters.

## When to use it
- When distance in edges or moves matters.
- When you need level order traversal or multi-source expansion.
- When states spread outward one layer at a time.
- When the first time you reach a state is guaranteed to be optimal.

## How to identify it in interviews
- Words like minimum steps, level order, minutes, or shortest path in an unweighted graph appear.
- You can picture a wave expanding from one or more starting points.
- The search must process nodes in the order discovered.
- You need to avoid revisiting states.

## Upgrade path / mental model
Mark states visited as soon as you enqueue them to avoid duplicates.

## Problems in this folder
- `BinaryTreeLevelOrderTraversalBFS.java` - Use a queue to process the tree level by level.
- `RottingOrangesBFS.java` - Start BFS from every rotten orange simultaneously.
- `NumberOfIslandsBFS.java` - Every new unvisited land cell launches a BFS flood fill.
- `ZeroOneMatrixBFS.java` - Multi-source BFS gives the nearest zero distance in one pass.
- `OpenTheLockBFS.java` - Treat combinations as graph states and use BFS for minimum moves.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac BinaryTreeLevelOrderTraversalBFS.java
java BinaryTreeLevelOrderTraversalBFS
```
