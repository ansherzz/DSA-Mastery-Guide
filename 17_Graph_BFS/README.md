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
- `ShortestPathInBinaryMatrixBFSGraph.java` - BFS gives the shortest path length in an unweighted grid.
- `IsGraphBipartiteBFSGraph.java` - Color each BFS layer with the opposite color.
- `WordLadderBFSGraph.java` - BFS over one-letter transformations yields the shortest chain.
- `MinimumGeneticMutationBFSGraph.java` - Treat each valid gene as a state and mutate one position at a time with BFS.
- `SnakesAndLaddersBFSGraph.java` - Board squares are states; BFS over die rolls gives the fewest moves.


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
