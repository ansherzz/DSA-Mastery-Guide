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

### Easy
- `BinaryTreeInorderTraversalDFS.java` - Use recursion with left-node-right timing.
- `MaximumDepthOfBinaryTreeDFS.java` - A node's depth is one plus the deeper child.
- `DiameterOfBinaryTreeDFS.java` - Return height upward while updating a global best path.
- `BalancedBinaryTreeDFS.java` - Use postorder DFS with a sentinel value to short-circuit imbalance.
- `InvertBinaryTreeDFS.java` - Invert both subtrees, then swap the children.
- `SameTreeDFS.java` - Walk two trees in lockstep comparing structure and values.
- `SymmetricTreeDFS.java` - Compare outer and inner child pairs with a mirror helper.
- `PathSumDFS.java` - Pass the remaining target down and check it at each leaf.
- `SubtreeOfAnotherTreeDFS.java` - Try the same-tree check at every node of the larger tree.
- `MergeTwoBinaryTreesDFS.java` - Paired DFS that sums overlapping nodes and reuses lone subtrees.

### Medium
- `PathSumIIDFS.java` - Carry the current path, record matches at leaves, and backtrack.
- `LowestCommonAncestorOfBinaryTreeDFS.java` - Post-order search where the split point of the two targets is the answer.
- `CountGoodNodesInBinaryTreeDFS.java` - Pass the path maximum downward and count nodes that match or beat it.
- `SumRootToLeafNumbersDFS.java` - Build each number on the way down and sum at the leaves.
- `FlattenBinaryTreeToLinkedListDFS.java` - Reverse pre-order traversal linking each node to the previously visited one.
- `HouseRobberIIIDFS.java` - Return a (skip, rob) pair from every subtree.
- `PathSumIIIDFS.java` - Prefix sums in a hash map along the current root-to-node path.

### Hard
- `BinaryTreeMaximumPathSumDFS.java` - Update a global best path while returning only one extendable branch upward.
- `SerializeAndDeserializeBinaryTreeDFS.java` - Pre-order with null markers, written and read by mirrored recursions.
- `BinaryTreeCamerasDFS.java` - Greedy post-order with three node states.


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
