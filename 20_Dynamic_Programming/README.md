# 20. Dynamic Programming

## What this pattern is
Dynamic programming solves overlapping subproblems by storing results. The core idea is to define a state and a transition.

## When to use it
- When brute force recursion repeats the same work.
- When the problem asks for optimal count, max, min, or number of ways.
- When a solution for n depends on smaller values.
- When you can define a recurrence with manageable state.

## How to identify it in interviews
- You can say 'answer(i) depends on earlier answers.'
- Recursive choice trees overlap heavily.
- You need best value, fewest steps, or total ways.
- Memoization or tabulation removes repeated computation.

## Upgrade path / mental model
Always define the DP state in words before writing code: what exactly does dp[i] or dp[i][j] mean?

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Linear recurrences (Fibonacci style)** - P01, P02, P03, P08, P19
- **Take or skip (house robber)** - P09, P18, P31
- **Running best ending here (Kadane style)** - P05, P06, P27
- **Unbounded knapsack** - P10, P11, P24
- **0/1 knapsack and subset sums** - P22, P23, P35
- **Longest increasing subsequence** - P12, P13
- **Two-sequence DP** - P14, P15, P28, P29, P42, P47, P50
- **Grid paths** - P04, P16, P17, P30, P33, P41, P43
- **String segmentation** - P20
- **Palindromes and interval DP** - P26, P36, P37, P45
- **State machines (stock trading)** - P39, P40, P44
- **Bit DP** - P07
- **DP over sorted items with a hash map** - P21
- **Counting ways** - P25
- **Small state carried per step** - P32
- **Probability DP** - P34
- **Game DP (minimax on score difference)** - P38
- **Partition DP (best cut positions)** - P46
- **Bitmask DP over subsets** - P48
- **Digit DP** - P49

## Problems in this folder

### Easy
- `P01_ClimbingStairsDP.java` - Each state depends on the previous one-step and two-step states.
- `P02_FibonacciNumberDP.java` - Build each value from the previous two with constant memory.
- `P03_MinCostClimbingStairsDP.java` - Cheapest arrival at each step from the two steps below.
- `P04_PascalsTriangleDP.java` - Tabulate each row from the row above it.
- `P05_BestTimeToBuyAndSellStockDP.java` - Carry the minimum price so far and the best profit so far.
- `P06_MaximumSubarrayKadaneDP.java` - The best subarray ending at i either extends the best one ending at i - 1 or starts fresh at i.
- `P07_CountingBitsDP.java` - Reuse the bit count of i >> 1 and add the lowest bit.
- `P08_NthTribonacciNumberDP.java` - Roll three variables forward, each value being the sum of the previous three.

### Medium
- `P09_HouseRobberDP.java` - For each house, choose between skipping it or robbing it.
- `P10_CoinChangeDP.java` - Build the answer for each amount from previously solved smaller amounts.
- `P11_PerfectSquaresDP.java` - Coin change where the coins are the perfect squares.
- `P12_LongestIncreasingSubsequenceDP.java` - Define dp[i] as the best increasing subsequence ending at i.
- `P13_NumberOfLongestIncreasingSubsequenceDP.java` - Track both the length and the number of ways for the LIS ending at each index.
- `P14_LongestCommonSubsequenceDP.java` - Use a 2D DP table over prefixes of the two strings.
- `P15_MaximumLengthOfRepeatedSubarrayDP.java` - dp[i][j] is the length of the common run ending at nums1[i - 1] and nums2[j - 1]; a mismatch resets it to 0.
- `P16_UniquePathsDP.java` - Paths to a cell are the sum of paths from above and from the left.
- `P17_UniquePathsIIDP.java` - Count paths as in Unique Paths, but a blocked cell contributes zero.
- `P18_HouseRobberIIDP.java` - Break the circle into two linear robber problems.
- `P19_DecodeWaysDP.java` - Count decodings by taking one digit or a valid two-digit code.
- `P20_WordBreakDP.java` - A prefix is breakable if a shorter breakable prefix is followed by a dictionary word.
- `P21_LongestStringChainDP.java` - Process words from short to long; each word's chain extends the best chain of any word obtained by deleting one letter.
- `P22_PartitionEqualSubsetSumDP.java` - 0/1 knapsack reachability for half of the total.
- `P23_OnesAndZeroesDP.java` - A 0/1 knapsack with two capacities: the budget of zeros and the budget of ones.
- `P24_CoinChangeIIDP.java` - Count combinations with coins in the outer loop to avoid counting orderings.
- `P25_NumberOfDiceRollsWithTargetSumDP.java` - ways[d][s] sums ways[d - 1][s - face] over every face of the die.
- `P26_LongestPalindromicSubstringDP.java` - Interval table: ends match and the inside is a palindrome.
- `P27_MaximumProductSubarrayDP.java` - Track both the maximum and minimum product ending at each index.
- `P28_EditDistanceDP.java` - 2D table over prefixes with insert, delete, and replace transitions.
- `P29_InterleavingStringDP.java` - dp[i][j] says whether the first i letters of s1 and the first j letters of s2 can form the first i + j letters of s3.
- `P30_MinimumPathSumDP.java` - Cheapest arrival at each cell from the cell above or the cell to the left.
- `P31_DeleteAndEarnDP.java` - Bucket points by value and run House Robber along the number line.
- `P32_PaintHouseDP.java` - Keep the cheapest total for each color of the current house, built from the other two colors of the previous house.
- `P33_TriangleDP.java` - Fold the triangle from the bottom row upward, keeping the cheaper child at each step.
- `P34_KnightProbabilityInChessboardDP.java` - Push probability mass from every cell to its eight knight moves, one step at a time.
- `P35_TargetSumDP.java` - Convert the sign choices into counting subsets with a fixed sum.
- `P36_LongestPalindromicSubsequenceDP.java` - Interval table: matching ends add 2, otherwise drop one end.
- `P37_MatrixChainMultiplicationDP.java` - Try every position of the last multiplication for each interval of matrices, from short intervals to long.
- `P38_PredictTheWinnerDP.java` - dp[i][j] is the best score difference the player to move can secure on nums[i..j].
- `P39_BestTimeToBuyAndSellStockWithCooldownDP.java` - Three-state machine: holding, just sold, and resting.
- `P40_BestTimeToBuyAndSellStockWithTransactionFeeDP.java` - Two states per day - holding a share or not - with the fee charged on each sale.
- `P41_MaximalSquareDP.java` - Largest square ending at a cell is 1 plus the minimum of its three neighbors.

### Hard
- `P42_RegularExpressionMatchingDP.java` - 2D table where '*' either uses zero copies or consumes one more character.
- `P43_DungeonGameDP.java` - Fill the grid from the princess backward: each cell stores the minimum health needed on entering it.
- `P44_BestTimeToBuyAndSellStockIVDP.java` - Keep a buy state and a sell state for each transaction number and chain them.
- `P45_BurstBalloonsDP.java` - Interval DP that chooses the last balloon to burst in each range.
- `P46_PalindromePartitioningIIDP.java` - Precompute which substrings are palindromes, then find the fewest cuts for every prefix.
- `P47_DistinctSubsequencesDP.java` - Count the ways to match prefixes of t, sweeping backward so each character is used once.
- `P48_TravelingSalesmanBitmaskDP.java` - State = (set of visited cities, current city); extend by moving to any unvisited city.
- `P49_NumbersAtMostNGivenDigitSetDP.java` - Count shorter numbers freely, then walk the digits of n counting numbers that first drop below n at each position.
- `P50_ShortestCommonSupersequenceDP.java` - Build the LCS table, then walk it backwards emitting shared letters once and all other letters as they are passed.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P01_ClimbingStairsDP.java
java P01_ClimbingStairsDP
```
