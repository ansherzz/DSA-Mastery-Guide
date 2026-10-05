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

### Easy
- `TwoSumBruteForce.java` - Check every pair and return matching indices.
- `BestTimeToBuySellStockBruteForce.java` - Compare every buy day with every later sell day.
- `MoveZeroesArrayBasics.java` - Use a write pointer to compact non-zero values.
- `ContainsDuplicateArrayBasics.java` - Use the simplest duplicate check: compare every pair.
- `RotateArrayBruteForce.java` - Rotate right by k by performing k single-step rotations.
- `LongestCommonPrefixBruteForce.java` - Compare the words column by column until a mismatch.
- `FindFirstOccurrenceInStringBruteForce.java` - Try every start index and match the needle character by character.
- `MergeSortedArrayArrayBasics.java` - Merge in-place by filling the first array from the back.
- `PlusOneArrayBasics.java` - Simulate adding one with a carry moving from the last digit.
- `MissingNumberBruteForce.java` - Check each candidate in 0..n against the whole array.
- `MajorityElementBruteForce.java` - Count occurrences of each element and compare against n / 2.

### Medium
- `MaximumSubarrayBruteForce.java` - Enumerate all contiguous subarrays and keep the best sum.
- `LongestPalindromicSubstringBruteForce.java` - Test every (start, end) substring for being a palindrome.
- `MaximumProductSubarrayBruteForce.java` - Enumerate all subarrays with a running product.
- `SubarraySumEqualsKBruteForce.java` - Count subarrays with sum k using a running sum from every start.
- `ThreeSumBruteForce.java` - Triple loop over sorted values with a set to remove duplicate triplets.
- `ReverseWordsInStringArrayBasics.java` - Scan from the right and collect words while skipping extra spaces.
- `CountInversionsBruteForce.java` - Count every pair where an earlier value is larger than a later one.
- `LongestSubstringWithoutRepeatingBruteForce.java` - Extend from every start until a character repeats.
- `SpiralMatrixArrayBasics.java` - Simulate the spiral walk with four shrinking boundaries.


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
