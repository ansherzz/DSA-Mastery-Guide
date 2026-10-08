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
- `SearchInBST.java` - Follow one path, discarding a subtree at every comparison.
- `RangeSumOfBST.java` - Sum values in a range while pruning subtrees that cannot qualify.
- `MinimumAbsoluteDifferenceInBST.java` - Inorder traversal comparing each value with the previous one.
- `TwoSumIVInputIsBST.java` - Inorder to a sorted list, then two pointers.
- `FindModeInBST.java` - Count runs of equal values during an inorder traversal.
- `ClosestBinarySearchTreeValueBST.java` - Walk the target's search path and keep the nearest value.
- `IncreasingOrderSearchTreeBST.java` - Relink nodes into a right-leaning chain during an in-order traversal.
- `FloorAndCeilInBST.java` - Record a floor or ceil candidate at each step while descending toward the key.

### Medium
- `ValidateBinarySearchTreeBST.java` - Carry strict lower and upper bounds through DFS.
- `LowestCommonAncestorBST.java` - Walk down the BST until the two targets split to different sides.
- `KthSmallestElementInBST.java` - Inorder traversal of a BST yields sorted values.
- `InsertIntoBSTPattern.java` - Follow BST comparisons until the correct null position is found.
- `DeleteNodeInBSTPattern.java` - Handle the three delete cases while preserving BST order.
- `InorderSuccessorInBST.java` - Record a candidate on every left turn.
- `BinarySearchTreeIteratorBST.java` - Pause and resume inorder traversal with an explicit stack.
- `ConvertBSTToGreaterTreeBST.java` - Reverse inorder traversal with a running sum.
- `TrimBinarySearchTreeBST.java` - Replace out-of-range nodes with the one subtree that can still be in range.
- `RecoverBinarySearchTreeBST.java` - Find the drops in the inorder sequence and swap the two culprits.
- `ConstructBSTFromPreorderBST.java` - Consume the preorder array using an upper bound per subtree.
- `UniqueBinarySearchTreesBST.java` - Count trees by root choice: left count times right count.
- `BalanceBinarySearchTreeBST.java` - Inorder to a sorted list, then rebuild from the middle outward.
- `LargestBSTSubtreeBST.java` - Post-order summaries (isBst, size, min, max) checked in O(1) per node.
- `TwoSumBSTsPattern.java` - For each value in one tree, search the other tree for its complement.
- `AllElementsInTwoBSTsPattern.java` - In-order both trees into sorted lists and merge them.
- `ConvertBSTToSortedDoublyLinkedListBST.java` - Link each node to the previously visited node during in-order traversal, then close the circle.
- `SplitBSTPattern.java` - Let the BST ordering assign whole subtrees to a side and split only one child recursively.
- `UniqueBinarySearchTreesIIBST.java` - Pick each root value and combine every left subtree with every right subtree.
- `VerifyPreorderSequenceInBST.java` - Use a stack of pending ancestors and a rising lower bound to validate a preorder sequence.
- `SerializeAndDeserializeBST.java` - Store only the preorder values and rebuild with value bounds instead of null markers.

### Hard
- `ClosestBinarySearchTreeValueIIBST.java` - Maintain a window of k values during in-order traversal and stop when values stop getting closer.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac ValidateBinarySearchTreeBST.java
java ValidateBinarySearchTreeBST
```
