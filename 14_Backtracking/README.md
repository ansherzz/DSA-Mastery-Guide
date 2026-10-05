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

### Easy
- `BinaryWatchBacktracking.java` - Choose which LEDs are on and prune invalid hours and minutes.

### Medium
- `SubsetsBacktracking.java` - At each level, choose the next element to include.
- `PermutationsBacktracking.java` - Choose one unused element for each position.
- `CombinationSumBacktracking.java` - Backtrack over choices while carrying the remaining target.
- `LetterCombinationsPhoneNumberBacktracking.java` - Each digit adds one layer of branching over its mapped letters.
- `SubsetsIIBacktracking.java` - Sort and skip equal siblings to avoid duplicate subsets.
- `PermutationsIIBacktracking.java` - Force equal values to be used left to right so each arrangement appears once.
- `CombinationsBacktracking.java` - Choose k increasing numbers and prune when too few remain.
- `CombinationSumIIBacktracking.java` - Use each element once and skip duplicate values at the same depth.
- `CombinationSumIIIBacktracking.java` - Track both the count and the remaining sum over digits 1-9.
- `GenerateParenthesesBacktracking.java` - Only add a bracket that keeps the prefix valid.
- `PalindromePartitioningBacktracking.java` - Cut wherever the next piece is a palindrome.
- `WordSearchBacktracking.java` - Grid DFS that marks cells on the path and restores them afterward.
- `RestoreIPAddressesBacktracking.java` - Pick 1-3 digit parts with validity checks and length pruning.
- `LetterCasePermutationBacktracking.java` - Branch on each letter's case and pass digits through.
- `PartitionToKEqualSumSubsetsBacktracking.java` - Fill buckets one at a time with descending order and pruning.

### Hard
- `NQueensBacktracking.java` - Place one queen per row and prune invalid columns and diagonals.
- `SudokuSolverBacktracking.java` - Place digits using row, column, and box usage tables and undo on failure.
- `WordSearchIIBacktracking.java` - Walk the board and a trie together to find many words in one pass.
- `ExpressionAddOperatorsBacktracking.java` - Track the running value and last term to handle multiplication precedence.


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
