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

## Problems in this folder

### Easy
- `P01_ConvertSortedArrayToBSTDivideConquer.java` - Pick the midpoint as root and recurse on both halves.
- `P02_BinarySearchRecursiveDivideConquer.java` - Divide at the middle and recurse into only one half.
- `P03_MajorityElementDivideConquer.java` - The majority of the whole must be the majority of a half; count the two candidates.
- `P04_TowerOfHanoiDivideConquer.java` - Move n - 1 disks aside, move the largest, then move the n - 1 back on top.
- `P05_FindMinAndMaxDivideConquer.java` - Solve both halves and combine with two comparisons to save comparisons overall.
- `P06_LongestNiceSubstringDivideConquer.java` - Split at any character whose other case is missing and solve both sides.
- `P07_ReverseBitsDivideConquer.java` - Swap halves, then halves of halves, all blocks at once with masks.

### Medium
- `P08_MergeSortDivideAndConquer.java` - Split, sort both halves, then merge.
- `P09_QuickSortDivideAndConquer.java` - Partition around a pivot, then recursively sort the two partitions.
- `P10_ConstructBinaryTreeFromPreorderAndInorderDivideConquer.java` - Use preorder for roots and inorder for left/right subtree boundaries.
- `P11_MaximumSubarrayDivideConquer.java` - Best of left half, right half, and the subarray crossing the middle.
- `P12_PowXNDivideConquer.java` - Fast exponentiation by squaring the half power.
- `P13_KthLargestElementQuickSelectDivideConquer.java` - Partition like quicksort but continue into one side only.
- `P14_CountInversionsDivideConquer.java` - Count cross inversions during the merge step of merge sort.
- `P15_DifferentWaysToAddParenthesesDivideConquer.java` - Split at each operator and combine all left and right results.
- `P16_ConstructBinaryTreeFromInorderAndPostorderDivideConquer.java` - The last postorder value is the root; inorder splits the subtrees.
- `P17_MaximumBinaryTreeDivideConquer.java` - Use the range maximum as the root and recurse on both sides.
- `P18_LongestSubstringWithAtLeastKRepeatingDivideConquer.java` - Split on characters that are too rare to be included.
- `P19_ConvertSortedListToBSTDivideConquer.java` - Use the list's middle node as the root of each range.
- `P20_KaratsubaMultiplicationDivideConquer.java` - Split the numbers in half and use three recursive products instead of four.
- `P21_BeautifulArrayDivideConquer.java` - Place odds before evens and build each group from a smaller beautiful array.
- `P22_SearchA2DMatrixIIDivideConquer.java` - Use the middle column to discard two quadrants and recurse into the other two.
- `P23_ConstructQuadTreeDivideConquer.java` - Build four quadrants recursively and merge them when they are identical leaves.
- `P24_KClosestPointsQuickSelectDivideConquer.java` - Partition by distance and continue only in the side that contains position k.

### Hard
- `P25_MergeKSortedListsDivideConquer.java` - Merge pairs of lists recursively, just like merge sort combines halves.
- `P26_ReversePairsDivideConquer.java` - Count pairs with a two-pointer pass before each merge.
- `P27_CountOfSmallerNumbersAfterSelfDivideConquer.java` - Merge sort on indices, crediting each left element with right elements placed before it.
- `P28_TheSkylineProblemDivideConquer.java` - Merge two skylines by tracking the current height of each.
- `P29_CountOfRangeSumDivideConquer.java` - Merge sort the prefix sums and count cross pairs within the range using two pointers.
- `P30_ClosestPairOfPointsDivideConquer.java` - Split by x, solve both halves, and check only a thin strip around the dividing line.


## How to run one file
The number in each file name is the suggested solving order (P01 first, P30 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P08_MergeSortDivideAndConquer.java
java P08_MergeSortDivideAndConquer
```
