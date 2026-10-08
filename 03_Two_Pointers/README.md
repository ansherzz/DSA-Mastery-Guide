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

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Opposite ends converging** - P02, P03, P07, P10, P11, P17, P21, P22, P24, P30, P31, P47
- **Read/write pointers for in-place compaction** - P04, P05, P15, P16, P36, P42
- **Two sequences walked in parallel** - P01, P09, P12, P13, P14, P19, P32, P33, P48
- **Sort, fix one element, sweep the rest** - P23, P27, P29, P40, P43, P44
- **Partitioning and placement** - P18, P25, P26
- **Reversal tricks** - P08, P34, P41
- **Fast and slow pointers (cycle detection)** - P20, P38, P39
- **Merging from the sorted ends** - P06
- **Boundary scans from both sides** - P45, P46
- **Same-direction pointers on sorted data** - P28
- **Expanding outward from a center** - P35, P37, P50
- **Tracking last-seen positions** - P49

## Problems in this folder

### Easy
- `P01_MergeStringsAlternatelyTwoPointers.java` - Advance one pointer per string and append whatever each still has.
- `P02_ValidPalindromeTwoPointers.java` - Move inward from both ends and compare only useful characters.
- `P03_StrobogrammaticNumberTwoPointers.java` - Compare mirrored positions and require each pair to be a valid rotation pair.
- `P04_RemoveDuplicatesFromSortedArrayTwoPointers.java` - Exploit sorted order to compress duplicates in-place.
- `P05_RemoveElementTwoPointers.java` - Use read and write pointers to overwrite unwanted values in-place.
- `P06_SquaresOfSortedArrayTwoPointers.java` - Fill the result from the back using the larger absolute value at either end.
- `P07_ReverseVowelsTwoPointers.java` - Move inward from both ends and swap only the vowels.
- `P08_ReverseStringIITwoPointers.java` - Jump in blocks of 2k and reverse the first k characters of each block with two pointers.
- `P09_IsSubsequenceTwoPointers.java` - Advance through both strings, matching characters greedily in order.
- `P10_ValidPalindromeIITwoPointers.java` - At the first mismatch, try skipping either side once.
- `P11_TwoSumLessThanKTwoPointers.java` - Sort, then move the ends inward keeping the best sum that stays below k.
- `P12_BackspaceStringCompareTwoPointers.java` - Walk both strings backward while resolving pending backspaces.
- `P13_MergeSortedArrayTwoPointers.java` - Merge from the back so the empty tail is filled without overwriting unread values.
- `P14_IntersectionOfTwoSortedArraysTwoPointers.java` - Advance the pointer at the smaller value; equal values are common elements.
- `P15_MoveZeroesTwoPointers.java` - Use a read pointer and a write pointer to compact the non-zero values in place.
- `P16_SortArrayByParityTwoPointers.java` - Partition evens and odds with pointers converging from both ends.
- `P17_DIStringMatchTwoPointers.java` - Hand out the smallest remaining number for 'I' and the largest for 'D'.
- `P18_SortArrayByParityIITwoPointers.java` - One pointer walks even indices, another walks odd indices; swap the two misplaced values.
- `P19_LongPressedNameTwoPointers.java` - Walk both strings together, letting the typed pointer absorb repeated characters.
- `P20_HappyNumberTwoPointers.java` - Treat "sum of squared digits" as a next pointer and run Floyd's cycle detection.

### Medium
- `P21_ContainerWithMostWaterTwoPointers.java` - Use two ends and always move the shorter wall.
- `P22_MinimumLengthAfterDeletingSimilarEndsTwoPointers.java` - While both ends hold the same letter, strip that whole run from both sides.
- `P23_ThreeSumTwoPointers.java` - Sort once, then solve each fixed-element case with two pointers.
- `P24_TwoSumIISortedTwoPointers.java` - Use sorted order to move the pointer that fixes the sum.
- `P25_SortColorsTwoPointers.java` - Dutch National Flag partition with low, mid, and high pointers.
- `P26_RearrangeArrayElementsBySignTwoPointers.java` - Keep one write pointer for even slots (positives) and one for odd slots (negatives).
- `P27_ThreeSumClosestTwoPointers.java` - Sort, fix one value, and track the nearest sum with two pointers.
- `P28_KDiffPairsInAnArrayTwoPointers.java` - Sort and chase a fixed gap with two forward-moving pointers, skipping duplicates.
- `P29_FourSumTwoPointers.java` - Fix two values and solve the remaining pair with two pointers while skipping duplicates.
- `P30_BoatsToSavePeopleTwoPointers.java` - Pair the lightest with the heaviest whenever they fit.
- `P31_BagOfTokensTwoPointers.java` - Buy score with the cheapest tokens and sell score for the most expensive ones.
- `P32_IntervalListIntersectionsTwoPointers.java` - Intersect the current pair and advance the interval that ends first.
- `P33_SwapAdjacentInLRStringTwoPointers.java` - Skip X's in both strings and compare the L/R pieces and the direction each one moved.
- `P34_NextPermutationTwoPointers.java` - Find the pivot from the right, swap with its successor, and reverse the suffix.
- `P35_PalindromicSubstringsTwoPointers.java` - Grow two pointers outward from each of the 2n - 1 centers while the ends match.
- `P36_RemoveDuplicatesFromSortedArrayIITwoPointers.java` - Keep at most two copies by comparing against write - 2.
- `P37_LongestMountainInArrayTwoPointers.java` - Find each peak and walk two pointers down both slopes.
- `P38_FindTheDuplicateNumberTwoPointers.java` - Treat values as next pointers and find the cycle entrance with slow and fast pointers.
- `P39_CircularArrayLoopTwoPointers.java` - Run fast and slow pointers from each start, staying on one direction, and zero out dead paths.
- `P40_ThreeSumSmallerTwoPointers.java` - After sorting, count a whole block of valid pairs whenever the sum is small enough.
- `P41_RotateArrayTwoPointers.java` - Rotate in place with three range reversals.
- `P42_StringCompressionTwoPointers.java` - Read each run and write its compressed form behind the read pointer.
- `P43_ValidTriangleNumberTwoPointers.java` - Fix the largest side and count pairs whose sum exceeds it.
- `P44_NumberOfSubsequencesThatSatisfySumConditionTwoPointers.java` - Sort; for each minimum, find the farthest maximum that fits and count 2^(gap) subsequences.
- `P45_ShortestUnsortedContinuousSubarrayTwoPointers.java` - Find each boundary with a scan from the opposite end and a running extreme.
- `P46_ShortestSubarrayToBeRemovedToMakeArraySortedTwoPointers.java` - Find the sorted prefix and sorted suffix, then merge-scan them to find the best bridge.

### Hard
- `P47_TrappingRainWaterTwoPointers.java` - Track best walls from both sides and process the limiting side.
- `P48_MinimumWindowSubsequenceTwoPointers.java` - Match forward to find a window end, then walk backward to tighten its start.
- `P49_CountSubarraysWithFixedBoundsTwoPointers.java` - For each right end, track the last out-of-range index and the last positions of minK and maxK.
- `P50_MaximumScoreOfAGoodSubarrayTwoPointers.java` - Start both pointers at k and always expand toward the larger neighbor.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P02_ValidPalindromeTwoPointers.java
java P02_ValidPalindromeTwoPointers
```
