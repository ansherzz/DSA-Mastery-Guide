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
- `P01_BinaryWatchBacktracking.java` - Choose which LEDs are on and prune invalid hours and minutes.

### Medium
- `P02_SubsetsBacktracking.java` - At each level, choose the next element to include.
- `P03_PermutationsBacktracking.java` - Choose one unused element for each position.
- `P04_CombinationSumBacktracking.java` - Backtrack over choices while carrying the remaining target.
- `P05_LetterCombinationsPhoneNumberBacktracking.java` - Each digit adds one layer of branching over its mapped letters.
- `P06_SubsetsIIBacktracking.java` - Sort and skip equal siblings to avoid duplicate subsets.
- `P07_PermutationsIIBacktracking.java` - Force equal values to be used left to right so each arrangement appears once.
- `P08_CombinationsBacktracking.java` - Choose k increasing numbers and prune when too few remain.
- `P09_CombinationSumIIBacktracking.java` - Use each element once and skip duplicate values at the same depth.
- `P10_CombinationSumIIIBacktracking.java` - Track both the count and the remaining sum over digits 1-9.
- `P11_GenerateParenthesesBacktracking.java` - Only add a bracket that keeps the prefix valid.
- `P12_PalindromePartitioningBacktracking.java` - Cut wherever the next piece is a palindrome.
- `P13_WordSearchBacktracking.java` - Grid DFS that marks cells on the path and restores them afterward.
- `P14_RestoreIPAddressesBacktracking.java` - Pick 1-3 digit parts with validity checks and length pruning.
- `P15_LetterCasePermutationBacktracking.java` - Branch on each letter's case and pass digits through.
- `P16_PartitionToKEqualSumSubsetsBacktracking.java` - Fill buckets one at a time with descending order and pruning.
- `P17_BeautifulArrangementBacktracking.java` - Fill positions one at a time and try only numbers compatible with the position.
- `P18_SplitStringIntoMaxUniqueSubstringsBacktracking.java` - Try every next piece that is not already used, with a bound that prunes hopeless branches.
- `P19_FairDistributionOfCookiesBacktracking.java` - Assign each bag to a child and prune on the best unfairness and on symmetric empty children.
- `P20_PathWithMaximumGoldBacktracking.java` - DFS from every gold cell, marking cells on the way in and restoring them on the way out.
- `P21_FactorCombinationsBacktracking.java` - Divide the remaining value by non-decreasing factors to list each combination once.
- `P22_AdditiveNumberBacktracking.java` - Choose the first two numbers and verify that the rest of the string is forced.
- `P23_LetterTilePossibilitiesBacktracking.java` - Branch on letters with remaining counts so duplicate tiles never create duplicate sequences.

### Hard
- `P24_NQueensBacktracking.java` - Place one queen per row and prune invalid columns and diagonals.
- `P25_SudokuSolverBacktracking.java` - Place digits using row, column, and box usage tables and undo on failure.
- `P26_WordSearchIIBacktracking.java` - Walk the board and a trie together to find many words in one pass.
- `P27_ExpressionAddOperatorsBacktracking.java` - Track the running value and last term to handle multiplication precedence.
- `P28_UniquePathsIIIBacktracking.java` - Walk every free square exactly once by marking, recursing, and unmarking.
- `P29_NQueensIIBacktracking.java` - Count placements row by row using bitmasks for columns and both diagonals.
- `P30_RemoveInvalidParenthesesBacktracking.java` - Count the required removals first, then delete parentheses within that exact budget.


## How to run one file
The number in each file name is the suggested solving order (P01 first, P30 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P02_SubsetsBacktracking.java
java P02_SubsetsBacktracking
```
