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

## Problems in this folder

### Easy
- `P01_SearchInBST.java` - Follow one path, discarding a subtree at every comparison.
- `P02_RangeSumOfBST.java` - Sum values in a range while pruning subtrees that cannot qualify.
- `P03_MinimumAbsoluteDifferenceInBST.java` - Inorder traversal comparing each value with the previous one.
- `P04_TwoSumIVInputIsBST.java` - Inorder to a sorted list, then two pointers.
- `P05_FindModeInBST.java` - Count runs of equal values during an inorder traversal.
- `P06_ClosestBinarySearchTreeValueBST.java` - Walk the target's search path and keep the nearest value.
- `P07_IncreasingOrderSearchTreeBST.java` - Relink nodes into a right-leaning chain during an in-order traversal.
- `P08_FloorAndCeilInBST.java` - Record a floor or ceil candidate at each step while descending toward the key.

### Medium
- `P09_ValidateBinarySearchTreeBST.java` - Carry strict lower and upper bounds through DFS.
- `P10_LowestCommonAncestorBST.java` - Walk down the BST until the two targets split to different sides.
- `P11_KthSmallestElementInBST.java` - Inorder traversal of a BST yields sorted values.
- `P12_InsertIntoBSTPattern.java` - Follow BST comparisons until the correct null position is found.
- `P13_DeleteNodeInBSTPattern.java` - Handle the three delete cases while preserving BST order.
- `P14_InorderSuccessorInBST.java` - Record a candidate on every left turn.
- `P15_BinarySearchTreeIteratorBST.java` - Pause and resume inorder traversal with an explicit stack.
- `P16_ConvertBSTToGreaterTreeBST.java` - Reverse inorder traversal with a running sum.
- `P17_TrimBinarySearchTreeBST.java` - Replace out-of-range nodes with the one subtree that can still be in range.
- `P18_RecoverBinarySearchTreeBST.java` - Find the drops in the inorder sequence and swap the two culprits.
- `P19_ConstructBSTFromPreorderBST.java` - Consume the preorder array using an upper bound per subtree.
- `P20_UniqueBinarySearchTreesBST.java` - Count trees by root choice: left count times right count.
- `P21_BalanceBinarySearchTreeBST.java` - Inorder to a sorted list, then rebuild from the middle outward.
- `P22_LargestBSTSubtreeBST.java` - Post-order summaries (isBst, size, min, max) checked in O(1) per node.
- `P23_TwoSumBSTsPattern.java` - For each value in one tree, search the other tree for its complement.
- `P24_AllElementsInTwoBSTsPattern.java` - In-order both trees into sorted lists and merge them.
- `P25_ConvertBSTToSortedDoublyLinkedListBST.java` - Link each node to the previously visited node during in-order traversal, then close the circle.
- `P26_SplitBSTPattern.java` - Let the BST ordering assign whole subtrees to a side and split only one child recursively.
- `P27_UniqueBinarySearchTreesIIBST.java` - Pick each root value and combine every left subtree with every right subtree.
- `P28_VerifyPreorderSequenceInBST.java` - Use a stack of pending ancestors and a rising lower bound to validate a preorder sequence.
- `P29_SerializeAndDeserializeBST.java` - Store only the preorder values and rebuild with value bounds instead of null markers.

### Hard
- `P30_ClosestBinarySearchTreeValueIIBST.java` - Maintain a window of k values during in-order traversal and stop when values stop getting closer.


## How to run one file
The number in each file name is the suggested solving order (P01 first, P30 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P09_ValidateBinarySearchTreeBST.java
java P09_ValidateBinarySearchTreeBST
```
