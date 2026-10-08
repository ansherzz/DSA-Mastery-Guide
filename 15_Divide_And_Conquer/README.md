# 15. Divide & Conquer

## What this pattern is
Divide and conquer splits a problem into smaller independent parts, solves them recursively, and merges the results.

## When to use it
- When the problem can be broken into similar subproblems.
- When merging two solved halves is easier than solving the whole directly.
- When recursive sorting, building trees, or merging structures appears.
- When the best solution combines left and right answers.

## How to identify it in interviews
- You can draw a recursion tree that halves the work.
- Sorting, merging, partitioning, or reconstructing from traversals appears.
- Subproblems are independent or nearly independent.
- A merge step combines the recursive answers.

## Upgrade path / mental model
Write the base case first, then define the exact split and merge steps.

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Halve the input and recurse** - P02, P05, P16, P18, P35
- **Sorting algorithms** - P10, P11, P12, P13
- **Counting during merge sort** - P21, P41, P42, P45, P46
- **Quickselect partitioning** - P19, P20, P38, P39, P50
- **Building a tree by splitting a range** - P01, P14, P23, P24, P25, P29, P37
- **Splitting at a pivot element** - P07, P27, P28, P44, P48
- **Combining the answers of two halves** - P03, P06, P15, P40, P43, P47
- **Splitting an expression at each operator** - P22
- **Recursive construction** - P04, P26, P32, P33, P34, P49
- **Bit-level and arithmetic divide and conquer** - P08, P09, P17, P30, P31
- **Segment tree (divide the index range)** - P36

## Problems in this folder

### Easy
- `P01_ConvertSortedArrayToBSTDivideConquer.java` - Pick the midpoint as root and recurse on both halves.
- `P02_BinarySearchRecursiveDivideConquer.java` - Divide at the middle and recurse into only one half.
- `P03_MajorityElementDivideConquer.java` - The majority of the whole must be the majority of a half; count the two candidates.
- `P04_TowerOfHanoiDivideConquer.java` - Move n - 1 disks aside, move the largest, then move the n - 1 back on top.
- `P05_FindMinAndMaxDivideConquer.java` - Solve both halves and combine with two comparisons to save comparisons overall.
- `P06_LongestCommonPrefixDivideConquer.java` - The common prefix of all strings is the common prefix of the answers for the two halves.
- `P07_LongestNiceSubstringDivideConquer.java` - Split at any character whose other case is missing and solve both sides.
- `P08_ReverseBitsDivideConquer.java` - Swap halves, then halves of halves, all blocks at once with masks.
- `P09_NumberOf1BitsDivideConquer.java` - Count bits in pairs, then nibbles, then bytes, merging adjacent counts with masks.

### Medium
- `P10_MergeSortDivideAndConquer.java` - Split, sort both halves, then merge.
- `P11_QuickSortDivideAndConquer.java` - Partition around a pivot, then recursively sort the two partitions.
- `P12_BottomUpMergeSortDivideConquer.java` - Merge runs of width 1, 2, 4, ... instead of recursing; the conquer steps happen in the same order.
- `P13_ThreeWayQuickSortDivideConquer.java` - Partition into less-than, equal-to, and greater-than the pivot, then recurse only on the outer parts.
- `P14_ConstructBinaryTreeFromPreorderAndInorderDivideConquer.java` - Use preorder for roots and inorder for left/right subtree boundaries.
- `P15_MaximumSubarrayDivideConquer.java` - Best of left half, right half, and the subarray crossing the middle.
- `P16_PowXNDivideConquer.java` - Fast exponentiation by squaring the half power.
- `P17_FibonacciFastDoublingDivideConquer.java` - Compute (F(k), F(k+1)) for k = n / 2 and double it with two identities.
- `P18_SuperPowDivideConquer.java` - Peel off the last digit of the exponent: a^(10q + d) = (a^q)^10 * a^d.
- `P19_KthLargestElementQuickSelectDivideConquer.java` - Partition like quicksort but continue into one side only.
- `P20_MinimumMovesToEqualArrayElementsIIDivideConquer.java` - The best meeting value is the median, found in linear average time with quickselect.
- `P21_CountInversionsDivideConquer.java` - Count cross inversions during the merge step of merge sort.
- `P22_DifferentWaysToAddParenthesesDivideConquer.java` - Split at each operator and combine all left and right results.
- `P23_ConstructBinaryTreeFromInorderAndPostorderDivideConquer.java` - The last postorder value is the root; inorder splits the subtrees.
- `P24_ConstructBinaryTreeFromPreorderAndPostorderDivideConquer.java` - The second preorder value is the left child; its position in postorder tells the size of the left subtree.
- `P25_MaximumBinaryTreeDivideConquer.java` - Use the range maximum as the root and recurse on both sides.
- `P26_AllPossibleFullBinaryTreesDivideConquer.java` - Give i nodes to the left subtree and n - 1 - i to the right, and combine every pair of results.
- `P27_LongestSubstringWithAtLeastKRepeatingDivideConquer.java` - Split on characters that are too rare to be included.
- `P28_MinimumStrokesToPaintFenceDivideConquer.java` - Either paint every plank vertically, or paint horizontally up to the lowest plank and recurse on the pieces above it.
- `P29_ConvertSortedListToBSTDivideConquer.java` - Use the list's middle node as the root of each range.
- `P30_KaratsubaMultiplicationDivideConquer.java` - Split the numbers in half and use three recursive products instead of four.
- `P31_StrassenMatrixMultiplicationDivideConquer.java` - Split each matrix into four blocks and multiply with seven recursive products instead of eight.
- `P32_BeautifulArrayDivideConquer.java` - Place odds before evens and build each group from a smaller beautiful array.
- `P33_StrobogrammaticNumberIIDivideConquer.java` - Solve for length n - 2 and wrap every result with each valid pair of outer digits.
- `P34_GrayCodeDivideConquer.java` - Take the sequence for n - 1 bits, then append its mirror image with the new top bit set.
- `P35_SearchA2DMatrixIIDivideConquer.java` - Use the middle column to discard two quadrants and recurse into the other two.
- `P36_SegmentTreeRangeSumQueryDivideConquer.java` - Each node stores the sum of a range that is split in half between its two children.
- `P37_ConstructQuadTreeDivideConquer.java` - Build four quadrants recursively and merge them when they are identical leaves.
- `P38_KClosestPointsQuickSelectDivideConquer.java` - Partition by distance and continue only in the side that contains position k.
- `P39_TopKFrequentElementsQuickSelectDivideConquer.java` - Partition the distinct values by frequency until the k most frequent occupy the front.

### Hard
- `P40_MergeKSortedListsDivideConquer.java` - Merge pairs of lists recursively, just like merge sort combines halves.
- `P41_ReversePairsDivideConquer.java` - Count pairs with a two-pointer pass before each merge.
- `P42_CountOfSmallerNumbersAfterSelfDivideConquer.java` - Merge sort on indices, crediting each left element with right elements placed before it.
- `P43_TheSkylineProblemDivideConquer.java` - Merge two skylines by tracking the current height of each.
- `P44_LargestRectangleInHistogramDivideConquer.java` - The best rectangle either spans the whole range at the minimum height or lies entirely on one side of the minimum bar.
- `P45_CountOfRangeSumDivideConquer.java` - Merge sort the prefix sums and count cross pairs within the range using two pointers.
- `P46_NumberOfPairsSatisfyingInequalityDivideConquer.java` - Reduce to one array of differences and count cross pairs between sorted halves before merging.
- `P47_ClosestPairOfPointsDivideConquer.java` - Split by x, solve both halves, and check only a thin strip around the dividing line.
- `P48_ScrambleStringDivideConquer.java` - Try every split point and check both the unswapped and the swapped pairing of the halves.
- `P49_SpecialBinaryStringDivideConquer.java` - Split into top-level special blocks, maximize the inside of each recursively, and sort the blocks descending.
- `P50_MedianOfMediansSelectDivideConquer.java` - Choose the pivot as the median of the group-of-five medians so that every partition is guaranteed to be balanced.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P10_MergeSortDivideAndConquer.java
java P10_MergeSortDivideAndConquer
```
