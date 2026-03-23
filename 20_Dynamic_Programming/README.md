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
- `ClimbingStairsDP.java` - Each state depends on the previous one-step and two-step states.
- `HouseRobberDP.java` - For each house, choose between skipping it or robbing it.
- `CoinChangeDP.java` - Build the answer for each amount from previously solved smaller amounts.
- `LongestIncreasingSubsequenceDP.java` - Define dp[i] as the best increasing subsequence ending at i.
- `LongestCommonSubsequenceDP.java` - Use a 2D DP table over prefixes of the two strings.


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
