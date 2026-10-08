# 13. Binary Search Tree

## What this pattern is
BST problems use the invariant left < root < right. That ordering lets you prune half the tree just like binary search on arrays.

## When to use it
- When the input is explicitly a BST.
- When inorder traversal reveals sorted order.
- When you can decide left or right based on a target value.
- When you need rank queries or validation of BST properties.

## How to identify it in interviews
- The structure is a binary search tree, not just any binary tree.
- Comparisons with the root tell you which side to ignore.
- Sorted order via inorder traversal helps.
- You can avoid visiting the whole tree if the BST invariant is used.

## Upgrade path / mental model
Always use strict inequalities for validation unless duplicates are explicitly allowed.

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Search using the ordering property** - P01, P02, P08, P10, P14, P15, P16, P22, P23, P24
- **Range pruning** - P03, P04, P28
- **Inorder traversal gives sorted order** - P05, P07, P09, P11, P17, P18, P25, P26, P27, P40, P46
- **Validation and repair** - P12, P13, P29, P35, P36, P44
- **Insert, delete, and restructure** - P19, P20, P34, P41, P42
- **Construction from traversals** - P30, P31, P32, P45
- **Counting and generating BSTs** - P33, P43, P47
- **Problems on two BSTs or pairs** - P06, P37, P38, P39
- **Augmented BST (extra data per node)** - P21, P48
- **Library balanced BST (TreeSet and TreeMap)** - P49
- **Self-balancing rotations** - P50

## Problems in this folder

### Easy
- `P01_SearchInBST.java` - Follow one path, discarding a subtree at every comparison.
- `P02_MinimumAndMaximumInBST.java` - The minimum is the leftmost node and the maximum is the rightmost node.
- `P03_RangeSumOfBST.java` - Sum values in a range while pruning subtrees that cannot qualify.
- `P04_BSTKeysInRangeBST.java` - Inorder traversal that skips any subtree lying completely outside the range.
- `P05_MinimumAbsoluteDifferenceInBST.java` - Inorder traversal comparing each value with the previous one.
- `P06_TwoSumIVInputIsBST.java` - Inorder to a sorted list, then two pointers.
- `P07_FindModeInBST.java` - Count runs of equal values during an inorder traversal.
- `P08_ClosestBinarySearchTreeValueBST.java` - Walk the target's search path and keep the nearest value.
- `P09_IncreasingOrderSearchTreeBST.java` - Relink nodes into a right-leaning chain during an in-order traversal.
- `P10_FloorAndCeilInBST.java` - Record a floor or ceil candidate at each step while descending toward the key.
- `P11_KthLargestElementInBST.java` - Traverse right-node-left to visit keys in descending order and stop at the k-th.

### Medium
- `P12_ValidateBinarySearchTreeBST.java` - Carry strict lower and upper bounds through DFS.
- `P13_CheckIfBSTContainsDeadEndBST.java` - Carry the open range of allowed values down the tree; a leaf whose range is a single value is a dead end.
- `P14_LowestCommonAncestorBST.java` - Walk down the BST until the two targets split to different sides.
- `P15_DistanceBetweenTwoNodesInBST.java` - Walk down to the split point (the LCA), then add the depths of both targets below it.
- `P16_MaximumElementBetweenTwoNodesInBST.java` - The maximum on the path lies on the walk from the LCA down to the larger key.
- `P17_KthSmallestElementInBST.java` - Inorder traversal of a BST yields sorted values.
- `P18_MedianOfBST.java` - Count the nodes, then run an inorder traversal that stops at the middle position(s).
- `P19_InsertIntoBSTPattern.java` - Follow BST comparisons until the correct null position is found.
- `P20_DeleteNodeInBSTPattern.java` - Handle the three delete cases while preserving BST order.
- `P21_OrderStatisticTreeBST.java` - Store the size of each subtree so that k-th smallest and rank are answered by a single descent.
- `P22_InorderSuccessorInBST.java` - Record a candidate on every left turn.
- `P23_InorderPredecessorAndSuccessorInBST.java` - Two descents: remember the last node smaller than the key, and the last node larger than the key.
- `P24_InorderSuccessorInBSTIIBST.java` - With a right subtree, take its leftmost node; otherwise climb until arriving from a left child.
- `P25_BinarySearchTreeIteratorBST.java` - Pause and resume inorder traversal with an explicit stack.
- `P26_ClosestNodesQueriesInBST.java` - Flatten the BST to a sorted list once, then binary search the floor and ceiling for each query.
- `P27_ConvertBSTToGreaterTreeBST.java` - Reverse inorder traversal with a running sum.
- `P28_TrimBinarySearchTreeBST.java` - Replace out-of-range nodes with the one subtree that can still be in range.
- `P29_RecoverBinarySearchTreeBST.java` - Find the drops in the inorder sequence and swap the two culprits.
- `P30_ConstructBSTFromPreorderBST.java` - Consume the preorder array using an upper bound per subtree.
- `P31_ConstructBSTFromPostorderBST.java` - Consume the postorder from the end with a lower bound: root first, then right subtree, then left.
- `P32_ConstructBSTFromLevelOrderBST.java` - Queue each created node with its allowed value range; the next values become its children if they fit.
- `P33_UniqueBinarySearchTreesBST.java` - Count trees by root choice: left count times right count.
- `P34_BalanceBinarySearchTreeBST.java` - Inorder to a sorted list, then rebuild from the middle outward.
- `P35_LargestBSTSubtreeBST.java` - Post-order summaries (isBst, size, min, max) checked in O(1) per node.
- `P36_MinimumSwapsToConvertBinaryTreeToBST.java` - The inorder sequence must become sorted; count the swaps via permutation cycles.
- `P37_TwoSumBSTsPattern.java` - For each value in one tree, search the other tree for its complement.
- `P38_AllElementsInTwoBSTsPattern.java` - In-order both trees into sorted lists and merge them.
- `P39_CommonNodesInTwoBSTs.java` - Run two inorder iterators side by side and advance the one with the smaller key.
- `P40_ConvertBSTToSortedDoublyLinkedListBST.java` - Link each node to the previously visited node during in-order traversal, then close the circle.
- `P41_SplitBSTPattern.java` - Let the BST ordering assign whole subtrees to a side and split only one child recursively.
- `P42_MergeTwoBSTsIntoBalancedBST.java` - Flatten both trees to sorted lists, merge them, and rebuild from the middle outward.
- `P43_UniqueBinarySearchTreesIIBST.java` - Pick each root value and combine every left subtree with every right subtree.
- `P44_VerifyPreorderSequenceInBST.java` - Use a stack of pending ancestors and a rising lower bound to validate a preorder sequence.
- `P45_SerializeAndDeserializeBST.java` - Store only the preorder values and rebuild with value bounds instead of null markers.

### Hard
- `P46_ClosestBinarySearchTreeValueIIBST.java` - Maintain a window of k values during in-order traversal and stop when values stop getting closer.
- `P47_NumberOfWaysToReorderArrayToGetSameBST.java` - Split around the first element and count interleavings of the two sides with a binomial coefficient.
- `P48_CountOfSmallerNumbersAfterSelfBST.java` - Insert from right to left into a BST whose nodes count their left subtree; the insert path yields the answer.
- `P49_ContainsDuplicateIIIBST.java` - Keep the last indexDiff values in a TreeSet and look for a neighbor within valueDiff using ceiling().
- `P50_AVLTreeInsertionBST.java` - After each insert, restore |height(left) - height(right)| <= 1 with single or double rotations.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P12_ValidateBinarySearchTreeBST.java
java P12_ValidateBinarySearchTreeBST
```
