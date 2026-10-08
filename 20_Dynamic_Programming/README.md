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

## Problems in this folder

### Easy
- `P01_ClimbingStairsDP.java` - Each state depends on the previous one-step and two-step states.
- `P02_FibonacciNumberDP.java` - Build each value from the previous two with constant memory.
- `P03_MinCostClimbingStairsDP.java` - Cheapest arrival at each step from the two steps below.
- `P04_PascalsTriangleDP.java` - Tabulate each row from the row above it.
- `P05_BestTimeToBuyAndSellStockDP.java` - Carry the minimum price so far and the best profit so far.
- `P06_CountingBitsDP.java` - Reuse the bit count of i >> 1 and add the lowest bit.
- `P07_NthTribonacciNumberDP.java` - Roll three variables forward, each value being the sum of the previous three.

### Medium
- `P08_HouseRobberDP.java` - For each house, choose between skipping it or robbing it.
- `P09_CoinChangeDP.java` - Build the answer for each amount from previously solved smaller amounts.
- `P10_LongestIncreasingSubsequenceDP.java` - Define dp[i] as the best increasing subsequence ending at i.
- `P11_LongestCommonSubsequenceDP.java` - Use a 2D DP table over prefixes of the two strings.
- `P12_UniquePathsDP.java` - Paths to a cell are the sum of paths from above and from the left.
- `P13_HouseRobberIIDP.java` - Break the circle into two linear robber problems.
- `P14_DecodeWaysDP.java` - Count decodings by taking one digit or a valid two-digit code.
- `P15_WordBreakDP.java` - A prefix is breakable if a shorter breakable prefix is followed by a dictionary word.
- `P16_PartitionEqualSubsetSumDP.java` - 0/1 knapsack reachability for half of the total.
- `P17_CoinChangeIIDP.java` - Count combinations with coins in the outer loop to avoid counting orderings.
- `P18_LongestPalindromicSubstringDP.java` - Interval table: ends match and the inside is a palindrome.
- `P19_MaximumProductSubarrayDP.java` - Track both the maximum and minimum product ending at each index.
- `P20_EditDistanceDP.java` - 2D table over prefixes with insert, delete, and replace transitions.
- `P21_MinimumPathSumDP.java` - Cheapest arrival at each cell from the cell above or the cell to the left.
- `P22_DeleteAndEarnDP.java` - Bucket points by value and run House Robber along the number line.
- `P23_TriangleDP.java` - Fold the triangle from the bottom row upward, keeping the cheaper child at each step.
- `P24_TargetSumDP.java` - Convert the sign choices into counting subsets with a fixed sum.
- `P25_LongestPalindromicSubsequenceDP.java` - Interval table: matching ends add 2, otherwise drop one end.
- `P26_BestTimeToBuyAndSellStockWithCooldownDP.java` - Three-state machine: holding, just sold, and resting.
- `P27_MaximalSquareDP.java` - Largest square ending at a cell is 1 plus the minimum of its three neighbors.

### Hard
- `P28_RegularExpressionMatchingDP.java` - 2D table where '*' either uses zero copies or consumes one more character.
- `P29_BurstBalloonsDP.java` - Interval DP that chooses the last balloon to burst in each range.
- `P30_DistinctSubsequencesDP.java` - Count the ways to match prefixes of t, sweeping backward so each character is used once.


## How to run one file
The number in each file name is the suggested solving order (P01 first, P30 last).

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
