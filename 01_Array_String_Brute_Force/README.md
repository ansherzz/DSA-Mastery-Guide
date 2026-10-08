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
- `P01_TwoSumBruteForce.java` - Check every pair and return matching indices.
- `P02_BestTimeToBuySellStockBruteForce.java` - Compare every buy day with every later sell day.
- `P03_MoveZeroesArrayBasics.java` - Use a write pointer to compact non-zero values.
- `P04_ContainsDuplicateArrayBasics.java` - Use the simplest duplicate check: compare every pair.
- `P05_RotateArrayBruteForce.java` - Rotate right by k by performing k single-step rotations.
- `P06_LongestCommonPrefixBruteForce.java` - Compare the words column by column until a mismatch.
- `P07_FindFirstOccurrenceInStringBruteForce.java` - Try every start index and match the needle character by character.
- `P08_MergeSortedArrayArrayBasics.java` - Merge in-place by filling the first array from the back.
- `P09_PlusOneArrayBasics.java` - Simulate adding one with a carry moving from the last digit.
- `P10_MissingNumberBruteForce.java` - Check each candidate in 0..n against the whole array.
- `P11_MajorityElementBruteForce.java` - Count occurrences of each element and compare against n / 2.
- `P12_SingleNumberBruteForce.java` - Count each value's occurrences directly, then see why XOR removes the inner loop.
- `P13_ValidAnagramBruteForce.java` - Sort both strings into a canonical order and compare.
- `P14_ReverseStringArrayBasics.java` - Swap mirrored positions in place.
- `P15_IntersectionOfTwoArraysBruteForce.java` - Check every value of one array against the other and skip repeats.
- `P16_MaxConsecutiveOnesArrayBasics.java` - Keep a running run length that resets on every zero.

### Medium
- `P17_MaximumSubarrayBruteForce.java` - Enumerate all contiguous subarrays and keep the best sum.
- `P18_LongestPalindromicSubstringBruteForce.java` - Test every (start, end) substring for being a palindrome.
- `P19_MaximumProductSubarrayBruteForce.java` - Enumerate all subarrays with a running product.
- `P20_SubarraySumEqualsKBruteForce.java` - Count subarrays with sum k using a running sum from every start.
- `P21_ThreeSumBruteForce.java` - Triple loop over sorted values with a set to remove duplicate triplets.
- `P22_ReverseWordsInStringArrayBasics.java` - Scan from the right and collect words while skipping extra spaces.
- `P23_CountInversionsBruteForce.java` - Count every pair where an earlier value is larger than a later one.
- `P24_LongestSubstringWithoutRepeatingBruteForce.java` - Extend from every start until a character repeats.
- `P25_SpiralMatrixArrayBasics.java` - Simulate the spiral walk with four shrinking boundaries.
- `P26_RotateImageArrayBasics.java` - Rotate in place by transposing and then reversing each row.
- `P27_SetMatrixZeroesArrayBasics.java` - Record zero rows and columns first, then apply them in a second pass.
- `P28_MultiplyStringsArrayBasics.java` - Simulate grade-school multiplication on digit arrays.
- `P29_CountAndSayArrayBasics.java` - Build each term by run-length describing the previous one.

### Hard
- `P30_TrappingRainWaterBruteForce.java` - For each bar, scan for the tallest bar on both sides to get its water level.


## How to run one file
The number in each file name is the suggested solving order (P01 first, P30 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P01_TwoSumBruteForce.java
java P01_TwoSumBruteForce
```
