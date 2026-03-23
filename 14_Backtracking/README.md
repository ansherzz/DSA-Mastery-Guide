# 14. Backtracking

## What this pattern is
Backtracking explores all valid choices, builds partial answers, and undoes decisions after each recursive call.

## When to use it
- When you need all combinations, permutations, subsets, or placements.
- When each recursive level chooses one next option.
- When invalid partial states can be pruned early.
- When the search space is exponential but manageable with pruning.

## How to identify it in interviews
- The prompt asks for all possible results or counts of arrangements.
- You can build the answer incrementally and then revert.
- Choices branch at each level of recursion.
- Constraints can be checked locally before going deeper.

## Upgrade path / mental model
Define the state clearly: current path, start index, used array, or columns/diagonals.

## Problems in this folder
- `SubsetsBacktracking.java` - At each level, choose the next element to include.
- `PermutationsBacktracking.java` - Choose one unused element for each position.
- `CombinationSumBacktracking.java` - Backtrack over choices while carrying the remaining target.
- `LetterCombinationsPhoneNumberBacktracking.java` - Each digit adds one layer of branching over its mapped letters.
- `NQueensBacktracking.java` - Place one queen per row and prune invalid columns and diagonals.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac SubsetsBacktracking.java
java SubsetsBacktracking
```
