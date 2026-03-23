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
- `MergeSortDivideAndConquer.java` - Split, sort both halves, then merge.
- `QuickSortDivideAndConquer.java` - Partition around a pivot, then recursively sort the two partitions.
- `ConstructBinaryTreeFromPreorderAndInorderDivideConquer.java` - Use preorder for roots and inorder for left/right subtree boundaries.
- `ConvertSortedArrayToBSTDivideConquer.java` - Pick the midpoint as root and recurse on both halves.
- `MergeKSortedListsDivideConquer.java` - Merge pairs of lists recursively, just like merge sort combines halves.


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
