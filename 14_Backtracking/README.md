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

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Subsets (include or exclude each item)** - P02, P03, P04, P05, P10, P11, P12, P50
- **Combinations toward a target** - P07, P15, P16, P17, P18, P34
- **Permutations** - P06, P13, P14, P28, P37, P47
- **Building a string one choice at a time** - P01, P08, P09, P19, P20, P25
- **Partitioning a string** - P21, P24, P31, P35, P36, P49
- **Path search on a grid** - P22, P23, P33, P41, P44, P45
- **Assigning items to buckets** - P26, P27, P32
- **Constraint placement (boards, sequences, colorings)** - P29, P30, P39, P40, P46
- **Expression and edit search** - P42, P43, P48
- **Game search (win or lose states)** - P38

## Problems in this folder

### Easy
- `P01_GenerateAllBinaryStringsBacktracking.java` - At each position choose 0 or 1, recurse, and undo the choice.
- `P02_BinaryWatchBacktracking.java` - Choose which LEDs are on and prune invalid hours and minutes.
- `P03_SumOfAllSubsetXORTotalsBacktracking.java` - Carry the running XOR down the include/exclude tree and add it up at the leaves.

### Medium
- `P04_SubsetsBacktracking.java` - At each level, choose the next element to include.
- `P05_CountNumberOfMaximumBitwiseORSubsetsBacktracking.java` - The maximum OR is the OR of everything; count the include/exclude paths that reach it.
- `P06_PermutationsBacktracking.java` - Choose one unused element for each position.
- `P07_CombinationSumBacktracking.java` - Backtrack over choices while carrying the remaining target.
- `P08_LetterCombinationsPhoneNumberBacktracking.java` - Each digit adds one layer of branching over its mapped letters.
- `P09_NumbersWithSameConsecutiveDifferencesBacktracking.java` - Extend the number digit by digit; each digit allows at most two next digits.
- `P10_SubsetsIIBacktracking.java` - Sort and skip equal siblings to avoid duplicate subsets.
- `P11_NonDecreasingSubsequencesBacktracking.java` - Extend the current subsequence with later elements, using a per-level set to avoid duplicate branches.
- `P12_MaximumLengthOfConcatenatedStringWithUniqueCharactersBacktracking.java` - Represent each word as a letter bitmask and include it only when it does not overlap the current mask.
- `P13_PermutationsIIBacktracking.java` - Force equal values to be used left to right so each arrangement appears once.
- `P14_PalindromePermutationIIBacktracking.java` - Permute only half of the letters (with duplicate skipping) and mirror each result.
- `P15_CombinationsBacktracking.java` - Choose k increasing numbers and prune when too few remain.
- `P16_CombinationSumIIBacktracking.java` - Use each element once and skip duplicate values at the same depth.
- `P17_CombinationSumIIIBacktracking.java` - Track both the count and the remaining sum over digits 1-9.
- `P18_ClosestDessertCostBacktracking.java` - For each topping choose 0, 1, or 2 copies and track the total closest to the target.
- `P19_GenerateParenthesesBacktracking.java` - Only add a bracket that keeps the prefix valid.
- `P20_KthLexicographicalHappyStringBacktracking.java` - Generate happy strings in sorted order and stop as soon as the k-th one is produced.
- `P21_PalindromePartitioningBacktracking.java` - Cut wherever the next piece is a palindrome.
- `P22_WordSearchBacktracking.java` - Grid DFS that marks cells on the path and restores them afterward.
- `P23_RatInAMazeBacktracking.java` - Explore the four directions from each open cell, marking it visited on the way in and unmarking on the way out.
- `P24_RestoreIPAddressesBacktracking.java` - Pick 1-3 digit parts with validity checks and length pruning.
- `P25_LetterCasePermutationBacktracking.java` - Branch on each letter's case and pass digits through.
- `P26_PartitionToKEqualSumSubsetsBacktracking.java` - Fill buckets one at a time with descending order and pruning.
- `P27_MatchsticksToSquareBacktracking.java` - Place each matchstick, longest first, on one of four sides without exceeding the side length.
- `P28_BeautifulArrangementBacktracking.java` - Fill positions one at a time and try only numbers compatible with the position.
- `P29_ConstructTheLexicographicallyLargestValidSequenceBacktracking.java` - Fill the first empty slot with the largest unused number whose partner slot is also free.
- `P30_MColoringProblemBacktracking.java` - Color vertices one by one, trying each color that no already-colored neighbor uses.
- `P31_SplitStringIntoMaxUniqueSubstringsBacktracking.java` - Try every next piece that is not already used, with a bound that prunes hopeless branches.
- `P32_FairDistributionOfCookiesBacktracking.java` - Assign each bag to a child and prune on the best unfairness and on symmetric empty children.
- `P33_PathWithMaximumGoldBacktracking.java` - DFS from every gold cell, marking cells on the way in and restoring them on the way out.
- `P34_FactorCombinationsBacktracking.java` - Divide the remaining value by non-decreasing factors to list each combination once.
- `P35_AdditiveNumberBacktracking.java` - Choose the first two numbers and verify that the rest of the string is forced.
- `P36_SplitArrayIntoFibonacciSequenceBacktracking.java` - Choose where each number ends; from the third number on, the value is forced by the previous two.
- `P37_LetterTilePossibilitiesBacktracking.java` - Branch on letters with remaining counts so duplicate tiles never create duplicate sequences.
- `P38_FlipGameIIBacktracking.java` - A position is winning if some move leads to a position that is losing for the opponent.

### Hard
- `P39_NQueensBacktracking.java` - Place one queen per row and prune invalid columns and diagonals.
- `P40_SudokuSolverBacktracking.java` - Place digits using row, column, and box usage tables and undo on failure.
- `P41_WordSearchIIBacktracking.java` - Walk the board and a trie together to find many words in one pass.
- `P42_ExpressionAddOperatorsBacktracking.java` - Track the running value and last term to handle multiplication precedence.
- `P43_TwentyFourGameBacktracking.java` - Repeatedly pick two numbers, replace them with the result of an operation, and recurse on the shorter list.
- `P44_UniquePathsIIIBacktracking.java` - Walk every free square exactly once by marking, recursing, and unmarking.
- `P45_KnightsTourBacktracking.java` - Number the squares in visiting order, undoing a move whenever the knight gets stuck.
- `P46_NQueensIIBacktracking.java` - Count placements row by row using bitmasks for columns and both diagonals.
- `P47_NumberOfSquarefulArraysBacktracking.java` - Generate unique permutations, extending only when the new adjacent sum is a perfect square.
- `P48_RemoveInvalidParenthesesBacktracking.java` - Count the required removals first, then delete parentheses within that exact budget.
- `P49_WordBreakIIBacktracking.java` - Try every dictionary word as the next piece and remember start positions that are dead ends.
- `P50_MaximumNumberOfAchievableTransferRequestsBacktracking.java` - Include or exclude each request and accept a selection only if every building's net change is zero.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P04_SubsetsBacktracking.java
java P04_SubsetsBacktracking
```
