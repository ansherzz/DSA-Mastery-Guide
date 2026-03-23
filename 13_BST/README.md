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
- `ValidateBinarySearchTreeBST.java` - Carry strict lower and upper bounds through DFS.
- `LowestCommonAncestorBST.java` - Walk down the BST until the two targets split to different sides.
- `KthSmallestElementInBST.java` - Inorder traversal of a BST yields sorted values.
- `InsertIntoBSTPattern.java` - Follow BST comparisons until the correct null position is found.
- `DeleteNodeInBSTPattern.java` - Handle the three delete cases while preserving BST order.


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
