# 01. Array & String Foundations - Brute Force Thinking

## What this pattern is
Brute force is the habit of checking all valid choices in a controlled way. For array and string problems, it is the safest starting point when you are still discovering the invariant.

## When to use it
- When the constraints are small enough for O(n^2) or O(n^3).
- When you want a correctness-first baseline before optimizing.
- When the problem asks about pairs, subarrays, or ranges and you do not yet know the faster pattern.
- When you want to reason about edge cases before introducing hashing or two pointers.

## How to identify it in interviews
- The prompt says any pair, any subarray, or any split and no sorted structure is given.
- You can describe the answer by trying every starting point or every pair.
- You feel the urge to say 'let me first write the simplest correct version.'
- You need a baseline to compare against a more optimal pattern.

## Upgrade path / mental model
After writing the brute-force solution, ask which repeated work can be avoided. That usually leads to hashing, prefix sum, sliding window, or two pointers.

## Problems in this folder
- `TwoSumBruteForce.java` - Check every pair and return matching indices.
- `BestTimeToBuySellStockBruteForce.java` - Compare every buy day with every later sell day.
- `MaximumSubarrayBruteForce.java` - Enumerate all contiguous subarrays and keep the best sum.
- `MoveZeroesArrayBasics.java` - Use a write pointer to compact non-zero values.
- `ContainsDuplicateArrayBasics.java` - Use the simplest duplicate check: compare every pair.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac TwoSumBruteForce.java
java TwoSumBruteForce
```
