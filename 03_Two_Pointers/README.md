# 03. Two Pointers

## What this pattern is
Two pointers move through data from one or both ends while maintaining a useful relationship, often reducing an O(n^2) search to O(n).

## When to use it
- When the array is sorted or can be processed from both ends.
- When you compare pairs, shrink a search space, or remove duplicates in-place.
- When you need to validate mirrored characters in strings.
- When the best move depends on relative order or boundaries.

## How to identify it in interviews
- The data is sorted or symmetry matters.
- You can improve by moving left or right based on a comparison.
- The prompt asks for pairs, triplets, or in-place compression.
- One pass with two moving indices feels natural.

## Upgrade path / mental model
Always ask what condition tells you which pointer should move. That rule is the heart of the pattern.

## Problems in this folder

### Easy
- `P01_ValidPalindromeTwoPointers.java` - Move inward from both ends and compare only useful characters.
- `P02_RemoveDuplicatesFromSortedArrayTwoPointers.java` - Exploit sorted order to compress duplicates in-place.
- `P03_RemoveElementTwoPointers.java` - Use read and write pointers to overwrite unwanted values in-place.
- `P04_SquaresOfSortedArrayTwoPointers.java` - Fill the result from the back using the larger absolute value at either end.
- `P05_ReverseVowelsTwoPointers.java` - Move inward from both ends and swap only the vowels.
- `P06_IsSubsequenceTwoPointers.java` - Advance through both strings, matching characters greedily in order.
- `P07_ValidPalindromeIITwoPointers.java` - At the first mismatch, try skipping either side once.
- `P08_BackspaceStringCompareTwoPointers.java` - Walk both strings backward while resolving pending backspaces.
- `P09_MergeSortedArrayTwoPointers.java` - Merge from the back so the empty tail is filled without overwriting unread values.
- `P10_MoveZeroesTwoPointers.java` - Use a read pointer and a write pointer to compact the non-zero values in place.
- `P11_SortArrayByParityTwoPointers.java` - Partition evens and odds with pointers converging from both ends.
- `P12_LongPressedNameTwoPointers.java` - Walk both strings together, letting the typed pointer absorb repeated characters.

### Medium
- `P13_ContainerWithMostWaterTwoPointers.java` - Use two ends and always move the shorter wall.
- `P14_ThreeSumTwoPointers.java` - Sort once, then solve each fixed-element case with two pointers.
- `P15_TwoSumIISortedTwoPointers.java` - Use sorted order to move the pointer that fixes the sum.
- `P16_SortColorsTwoPointers.java` - Dutch National Flag partition with low, mid, and high pointers.
- `P17_ThreeSumClosestTwoPointers.java` - Sort, fix one value, and track the nearest sum with two pointers.
- `P18_FourSumTwoPointers.java` - Fix two values and solve the remaining pair with two pointers while skipping duplicates.
- `P19_BoatsToSavePeopleTwoPointers.java` - Pair the lightest with the heaviest whenever they fit.
- `P20_IntervalListIntersectionsTwoPointers.java` - Intersect the current pair and advance the interval that ends first.
- `P21_NextPermutationTwoPointers.java` - Find the pivot from the right, swap with its successor, and reverse the suffix.
- `P22_RemoveDuplicatesFromSortedArrayIITwoPointers.java` - Keep at most two copies by comparing against write - 2.
- `P23_FindTheDuplicateNumberTwoPointers.java` - Treat values as next pointers and find the cycle entrance with slow and fast pointers.
- `P24_ThreeSumSmallerTwoPointers.java` - After sorting, count a whole block of valid pairs whenever the sum is small enough.
- `P25_RotateArrayTwoPointers.java` - Rotate in place with three range reversals.
- `P26_StringCompressionTwoPointers.java` - Read each run and write its compressed form behind the read pointer.
- `P27_ValidTriangleNumberTwoPointers.java` - Fix the largest side and count pairs whose sum exceeds it.
- `P28_ShortestUnsortedContinuousSubarrayTwoPointers.java` - Find each boundary with a scan from the opposite end and a running extreme.

### Hard
- `P29_TrappingRainWaterTwoPointers.java` - Track best walls from both sides and process the limiting side.
- `P30_MinimumWindowSubsequenceTwoPointers.java` - Match forward to find a window end, then walk backward to tighten its start.


## How to run one file
The number in each file name is the suggested solving order (P01 first, P30 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P01_ValidPalindromeTwoPointers.java
java P01_ValidPalindromeTwoPointers
```
