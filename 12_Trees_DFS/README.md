# 12. Trees & Recursion - DFS

## What this pattern is
Depth-first search on trees solves problems by asking each subtree for information and combining the answers at the parent.

## When to use it
- When the answer depends on children before parents or vice versa.
- When recursive structure matches the tree structure.
- When you need traversal, height, balance, or path values.
- When postorder information is enough to solve the problem.

## How to identify it in interviews
- The prompt is naturally expressed as 'solve left, solve right, then combine.'
- Subtree results can be summarized in a small amount of information.
- Recursion is simpler than manually managing a stack.
- The problem asks for a property of every node or subtree.

## Upgrade path / mental model
Decide whether your DFS is preorder, inorder, or postorder based on when a node can be solved.

## Problems in this folder
- `BinaryTreeInorderTraversalDFS.java` - Use recursion with left-node-right timing.
- `MaximumDepthOfBinaryTreeDFS.java` - A node's depth is one plus the deeper child.
- `DiameterOfBinaryTreeDFS.java` - Return height upward while updating a global best path.
- `BalancedBinaryTreeDFS.java` - Use postorder DFS with a sentinel value to short-circuit imbalance.
- `BinaryTreeMaximumPathSumDFS.java` - Update a global best path while returning only one extendable branch upward.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac BinaryTreeInorderTraversalDFS.java
java BinaryTreeInorderTraversalDFS
```
