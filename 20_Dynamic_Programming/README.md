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
- `ClimbingStairsDP.java` - Each state depends on the previous one-step and two-step states.
- `FibonacciNumberDP.java` - Build each value from the previous two with constant memory.
- `MinCostClimbingStairsDP.java` - Cheapest arrival at each step from the two steps below.
- `PascalsTriangleDP.java` - Tabulate each row from the row above it.
- `BestTimeToBuyAndSellStockDP.java` - Carry the minimum price so far and the best profit so far.

### Medium
- `HouseRobberDP.java` - For each house, choose between skipping it or robbing it.
- `CoinChangeDP.java` - Build the answer for each amount from previously solved smaller amounts.
- `LongestIncreasingSubsequenceDP.java` - Define dp[i] as the best increasing subsequence ending at i.
- `LongestCommonSubsequenceDP.java` - Use a 2D DP table over prefixes of the two strings.
- `UniquePathsDP.java` - Paths to a cell are the sum of paths from above and from the left.
- `HouseRobberIIDP.java` - Break the circle into two linear robber problems.
- `DecodeWaysDP.java` - Count decodings by taking one digit or a valid two-digit code.
- `WordBreakDP.java` - A prefix is breakable if a shorter breakable prefix is followed by a dictionary word.
- `PartitionEqualSubsetSumDP.java` - 0/1 knapsack reachability for half of the total.
- `CoinChangeIIDP.java` - Count combinations with coins in the outer loop to avoid counting orderings.
- `LongestPalindromicSubstringDP.java` - Interval table: ends match and the inside is a palindrome.
- `MaximumProductSubarrayDP.java` - Track both the maximum and minimum product ending at each index.
- `EditDistanceDP.java` - 2D table over prefixes with insert, delete, and replace transitions.

### Hard
- `RegularExpressionMatchingDP.java` - 2D table where '*' either uses zero copies or consumes one more character.
- `BurstBalloonsDP.java` - Interval DP that chooses the last balloon to burst in each range.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac ClimbingStairsDP.java
java ClimbingStairsDP
```
