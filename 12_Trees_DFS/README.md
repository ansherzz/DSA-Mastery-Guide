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
- `P01_BinaryTreeInorderTraversalDFS.java` - Use recursion with left-node-right timing.
- `P02_MaximumDepthOfBinaryTreeDFS.java` - A node's depth is one plus the deeper child.
- `P03_DiameterOfBinaryTreeDFS.java` - Return height upward while updating a global best path.
- `P04_BalancedBinaryTreeDFS.java` - Use postorder DFS with a sentinel value to short-circuit imbalance.
- `P05_InvertBinaryTreeDFS.java` - Invert both subtrees, then swap the children.
- `P06_SameTreeDFS.java` - Walk two trees in lockstep comparing structure and values.
- `P07_SymmetricTreeDFS.java` - Compare outer and inner child pairs with a mirror helper.
- `P08_PathSumDFS.java` - Pass the remaining target down and check it at each leaf.
- `P09_SubtreeOfAnotherTreeDFS.java` - Try the same-tree check at every node of the larger tree.
- `P10_MergeTwoBinaryTreesDFS.java` - Paired DFS that sums overlapping nodes and reuses lone subtrees.
- `P11_BinaryTreePathsDFS.java` - Carry the path down the recursion and record it at each leaf.
- `P12_SumOfLeftLeavesDFS.java` - Pass an is-left-child flag downward and add only left leaves.
- `P13_UnivaluedBinaryTreeDFS.java` - Compare every node with its children and recurse.
- `P14_BinaryTreeTiltDFS.java` - Return subtree sums upward while accumulating each node's tilt.

### Medium
- `P15_PathSumIIDFS.java` - Carry the current path, record matches at leaves, and backtrack.
- `P16_LowestCommonAncestorOfBinaryTreeDFS.java` - Post-order search where the split point of the two targets is the answer.
- `P17_CountGoodNodesInBinaryTreeDFS.java` - Pass the path maximum downward and count nodes that match or beat it.
- `P18_SumRootToLeafNumbersDFS.java` - Build each number on the way down and sum at the leaves.
- `P19_FlattenBinaryTreeToLinkedListDFS.java` - Reverse pre-order traversal linking each node to the previously visited one.
- `P20_HouseRobberIIIDFS.java` - Return a (skip, rob) pair from every subtree.
- `P21_PathSumIIIDFS.java` - Prefix sums in a hash map along the current root-to-node path.
- `P22_LongestUnivaluePathDFS.java` - Return the best same-value arm and combine two arms at each node.
- `P23_DeleteNodesAndReturnForestDFS.java` - Pass down whether the parent was deleted and return null for deleted nodes.
- `P24_LongestZigZagPathDFS.java` - Carry the zigzag length for each arrival direction, extending one and resetting the other.
- `P25_DistributeCoinsInBinaryTreeDFS.java` - Compute each subtree's coin balance; its absolute value is the flow across that edge.
- `P26_MaximumDifferenceBetweenNodeAndAncestorDFS.java` - Carry the path minimum and maximum down to every node.

### Hard
- `P27_BinaryTreeMaximumPathSumDFS.java` - Update a global best path while returning only one extendable branch upward.
- `P28_SerializeAndDeserializeBinaryTreeDFS.java` - Pre-order with null markers, written and read by mirrored recursions.
- `P29_BinaryTreeCamerasDFS.java` - Greedy post-order with three node states.
- `P30_VerticalOrderTraversalDFS.java` - Label nodes with (column, row) during DFS, then sort and group by column.


## How to run one file
The number in each file name is the suggested solving order (P01 first, P30 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P01_BinaryTreeInorderTraversalDFS.java
java P01_BinaryTreeInorderTraversalDFS
```
