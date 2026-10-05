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
- `ConvertSortedArrayToBSTDivideConquer.java` - Pick the midpoint as root and recurse on both halves.
- `BinarySearchRecursiveDivideConquer.java` - Divide at the middle and recurse into only one half.
- `MajorityElementDivideConquer.java` - The majority of the whole must be the majority of a half; count the two candidates.
- `TowerOfHanoiDivideConquer.java` - Move n - 1 disks aside, move the largest, then move the n - 1 back on top.

### Medium
- `MergeSortDivideAndConquer.java` - Split, sort both halves, then merge.
- `QuickSortDivideAndConquer.java` - Partition around a pivot, then recursively sort the two partitions.
- `ConstructBinaryTreeFromPreorderAndInorderDivideConquer.java` - Use preorder for roots and inorder for left/right subtree boundaries.
- `MaximumSubarrayDivideConquer.java` - Best of left half, right half, and the subarray crossing the middle.
- `PowXNDivideConquer.java` - Fast exponentiation by squaring the half power.
- `KthLargestElementQuickSelectDivideConquer.java` - Partition like quicksort but continue into one side only.
- `CountInversionsDivideConquer.java` - Count cross inversions during the merge step of merge sort.
- `DifferentWaysToAddParenthesesDivideConquer.java` - Split at each operator and combine all left and right results.
- `ConstructBinaryTreeFromInorderAndPostorderDivideConquer.java` - The last postorder value is the root; inorder splits the subtrees.
- `MaximumBinaryTreeDivideConquer.java` - Use the range maximum as the root and recurse on both sides.
- `LongestSubstringWithAtLeastKRepeatingDivideConquer.java` - Split on characters that are too rare to be included.
- `ConvertSortedListToBSTDivideConquer.java` - Use the list's middle node as the root of each range.

### Hard
- `MergeKSortedListsDivideConquer.java` - Merge pairs of lists recursively, just like merge sort combines halves.
- `ReversePairsDivideConquer.java` - Count pairs with a two-pointer pass before each merge.
- `CountOfSmallerNumbersAfterSelfDivideConquer.java` - Merge sort on indices, crediting each left element with right elements placed before it.
- `TheSkylineProblemDivideConquer.java` - Merge two skylines by tracking the current height of each.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac MergeSortDivideAndConquer.java
java MergeSortDivideAndConquer
```
