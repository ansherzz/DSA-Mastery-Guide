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

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Pair and triple enumeration** - P01, P02, P33, P49
- **Subarray enumeration** - P24, P27, P31, P32
- **Substring enumeration and matching** - P08, P11, P19, P23, P30, P39
- **In-place array manipulation** - P03, P06, P12, P20, P34
- **Counting and lookup scans** - P04, P05, P07, P15, P16, P17, P22, P37
- **Digit and big-number simulation** - P13, P14, P29, P46, P47
- **Matrix traversal and simulation** - P25, P41, P42, P43, P44, P45
- **Precomputed left/right scans** - P26, P48
- **String parsing and simulation** - P09, P10, P28, P35, P36, P50
- **Index marking (array as its own hash)** - P18, P38
- **Sorting fundamentals** - P21
- **Sieve and counting arrays** - P40

## Problems in this folder

### Easy
- `P01_TwoSumBruteForce.java` - Check every pair and return matching indices.
- `P02_BestTimeToBuySellStockBruteForce.java` - Compare every buy day with every later sell day.
- `P03_MoveZeroesArrayBasics.java` - Use a write pointer to compact non-zero values.
- `P04_ContainsDuplicateArrayBasics.java` - Use the simplest duplicate check: compare every pair.
- `P05_SecondLargestElementArrayBasics.java` - Track the two best values in one pass instead of sorting.
- `P06_RotateArrayBruteForce.java` - Rotate right by k by performing k single-step rotations.
- `P07_CheckIfArrayIsSortedAndRotatedArrayBasics.java` - Count the places where the order drops, including the wrap-around pair.
- `P08_LongestCommonPrefixBruteForce.java` - Compare the words column by column until a mismatch.
- `P09_LengthOfLastWordArrayBasics.java` - Scan from the end, skipping trailing spaces before counting letters.
- `P10_RomanToIntegerArrayBasics.java` - Add each symbol, but subtract it when a larger symbol follows.
- `P11_FindFirstOccurrenceInStringBruteForce.java` - Try every start index and match the needle character by character.
- `P12_MergeSortedArrayArrayBasics.java` - Merge in-place by filling the first array from the back.
- `P13_PlusOneArrayBasics.java` - Simulate adding one with a carry moving from the last digit.
- `P14_AddBinaryArrayBasics.java` - Add from the right with a carry, exactly like grade-school addition.
- `P15_MissingNumberBruteForce.java` - Check each candidate in 0..n against the whole array.
- `P16_MajorityElementBruteForce.java` - Count occurrences of each element and compare against n / 2.
- `P17_SingleNumberBruteForce.java` - Count each value's occurrences directly, then see why XOR removes the inner loop.
- `P18_FindAllNumbersDisappearedArrayBasics.java` - Use the sign of nums[value - 1] as a "seen" flag.
- `P19_ValidAnagramBruteForce.java` - Sort both strings into a canonical order and compare.
- `P20_ReverseStringArrayBasics.java` - Swap mirrored positions in place.
- `P21_BubbleSortArrayBasics.java` - Repeatedly swap adjacent out-of-order pairs; stop early when a pass makes no swap.
- `P22_IntersectionOfTwoArraysBruteForce.java` - Check every value of one array against the other and skip repeats.
- `P23_RepeatedSubstringPatternBruteForce.java` - Try every prefix length that divides n and check whether repeating it rebuilds the string.
- `P24_MaxConsecutiveOnesArrayBasics.java` - Keep a running run length that resets on every zero.
- `P25_TransposeMatrixArrayBasics.java` - Build an n x m result where result[c][r] = matrix[r][c].
- `P26_LeadersInAnArrayArrayBasics.java` - Scan from the right while carrying the maximum seen so far.

### Medium
- `P27_MaximumSubarrayBruteForce.java` - Enumerate all contiguous subarrays and keep the best sum.
- `P28_StringToIntegerAtoiArrayBasics.java` - Parse in phases - whitespace, sign, digits - and clamp on overflow.
- `P29_ReverseIntegerArrayBasics.java` - Pop digits with % 10 and push them onto the result, checking overflow before each push.
- `P30_LongestPalindromicSubstringBruteForce.java` - Test every (start, end) substring for being a palindrome.
- `P31_MaximumProductSubarrayBruteForce.java` - Enumerate all subarrays with a running product.
- `P32_SubarraySumEqualsKBruteForce.java` - Count subarrays with sum k using a running sum from every start.
- `P33_ThreeSumBruteForce.java` - Triple loop over sorted values with a set to remove duplicate triplets.
- `P34_ReverseWordsInStringArrayBasics.java` - Scan from the right and collect words while skipping extra spaces.
- `P35_ZigzagConversionArrayBasics.java` - Simulate the pen moving down and up across the rows, then join the rows.
- `P36_CompareVersionNumbersArrayBasics.java` - Split on dots and compare revision by revision, treating missing revisions as 0.
- `P37_CountInversionsBruteForce.java` - Count every pair where an earlier value is larger than a later one.
- `P38_FindAllDuplicatesInAnArrayArrayBasics.java` - Negate nums[value - 1] on the first visit; a second visit finds it already negative.
- `P39_LongestSubstringWithoutRepeatingBruteForce.java` - Extend from every start until a character repeats.
- `P40_CountPrimesSieveArrayBasics.java` - Cross out multiples of each prime in a boolean array instead of testing every number.
- `P41_SpiralMatrixArrayBasics.java` - Simulate the spiral walk with four shrinking boundaries.
- `P42_DiagonalTraverseArrayBasics.java` - Cells on one anti-diagonal share r + c; alternate the direction of each diagonal.
- `P43_RotateImageArrayBasics.java` - Rotate in place by transposing and then reversing each row.
- `P44_SetMatrixZeroesArrayBasics.java` - Record zero rows and columns first, then apply them in a second pass.
- `P45_GameOfLifeArrayBasics.java` - Store the next state in a second bit so every cell still sees its neighbors' old state.
- `P46_MultiplyStringsArrayBasics.java` - Simulate grade-school multiplication on digit arrays.
- `P47_CountAndSayArrayBasics.java` - Build each term by run-length describing the previous one.

### Hard
- `P48_TrappingRainWaterBruteForce.java` - For each bar, scan for the tallest bar on both sides to get its water level.
- `P49_MaxPointsOnALineBruteForce.java` - Fix every pair of points and count the others that are collinear using a cross product.
- `P50_TextJustificationArrayBasics.java` - Greedily pack words per line, then distribute the spare spaces from the left.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

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
