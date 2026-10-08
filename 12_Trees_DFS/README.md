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

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Traversal orders** - P01, P02, P03, P21, P43, P49
- **Bottom-up return values (height style)** - P04, P05, P06, P18, P19, P26, P34, P40, P44, P50
- **Comparing two trees** - P08, P09, P11, P12, P14, P22
- **Modifying or rebuilding a tree** - P07, P29, P30, P35
- **Root-to-leaf path tracking** - P10, P13, P20, P27, P28, P32, P33
- **Top-down state passed from ancestors** - P16, P17, P25, P36, P37, P41
- **Lowest common ancestor** - P23, P24, P42
- **Tree DP with several states per node** - P31, P38, P47, P48
- **Serialization and subtree encoding** - P15, P39, P45, P46

## Problems in this folder

### Easy
- `P01_BinaryTreeInorderTraversalDFS.java` - Use recursion with left-node-right timing.
- `P02_BinaryTreePreorderTraversalIterativeDFS.java` - Replace recursion with an explicit stack, pushing the right child before the left.
- `P03_BinaryTreePostorderTraversalIterativeDFS.java` - Produce node-right-left with a stack and build the answer back to front.
- `P04_MaximumDepthOfBinaryTreeDFS.java` - A node's depth is one plus the deeper child.
- `P05_DiameterOfBinaryTreeDFS.java` - Return height upward while updating a global best path.
- `P06_BalancedBinaryTreeDFS.java` - Use postorder DFS with a sentinel value to short-circuit imbalance.
- `P07_InvertBinaryTreeDFS.java` - Invert both subtrees, then swap the children.
- `P08_SameTreeDFS.java` - Walk two trees in lockstep comparing structure and values.
- `P09_SymmetricTreeDFS.java` - Compare outer and inner child pairs with a mirror helper.
- `P10_PathSumDFS.java` - Pass the remaining target down and check it at each leaf.
- `P11_SubtreeOfAnotherTreeDFS.java` - Try the same-tree check at every node of the larger tree.
- `P12_MergeTwoBinaryTreesDFS.java` - Paired DFS that sums overlapping nodes and reuses lone subtrees.
- `P13_BinaryTreePathsDFS.java` - Carry the path down the recursion and record it at each leaf.
- `P14_LeafSimilarTreesDFS.java` - Collect each tree's leaves from left to right with DFS and compare the two sequences.
- `P15_ConstructStringFromBinaryTreeDFS.java` - Preorder with parentheses around children, keeping "()" only for a missing left child that has a right sibling.
- `P16_SumOfLeftLeavesDFS.java` - Pass an is-left-child flag downward and add only left leaves.
- `P17_UnivaluedBinaryTreeDFS.java` - Compare every node with its children and recurse.
- `P18_EvaluateBooleanBinaryTreeDFS.java` - Evaluate both children first, then combine them with the node's operator.
- `P19_BinaryTreeTiltDFS.java` - Return subtree sums upward while accumulating each node's tilt.

### Medium
- `P20_PathSumIIDFS.java` - Carry the current path, record matches at leaves, and backtrack.
- `P21_MorrisInorderTraversalDFS.java` - Use temporary threads from each inorder predecessor back to its successor instead of a stack.
- `P22_FlipEquivalentBinaryTreesDFS.java` - Two nodes match if their values are equal and their children match either straight or swapped.
- `P23_LowestCommonAncestorOfBinaryTreeDFS.java` - Post-order search where the split point of the two targets is the answer.
- `P24_LowestCommonAncestorOfDeepestLeavesDFS.java` - Return (depth, candidate) from each subtree; equal depths on both sides make the current node the answer.
- `P25_CountGoodNodesInBinaryTreeDFS.java` - Pass the path maximum downward and count nodes that match or beat it.
- `P26_CountNodesEqualToAverageOfSubtreeDFS.java` - Return (sum, count) from every subtree and test the node against sum / count.
- `P27_SumRootToLeafNumbersDFS.java` - Build each number on the way down and sum at the leaves.
- `P28_SmallestStringStartingFromLeafDFS.java` - Carry the path down, and at each leaf compare the reversed path with the best string so far.
- `P29_FlattenBinaryTreeToLinkedListDFS.java` - Reverse pre-order traversal linking each node to the previously visited one.
- `P30_BinaryTreePruningDFS.java` - Prune the children first; a node is removed when it is 0 and has no children left.
- `P31_HouseRobberIIIDFS.java` - Return a (skip, rob) pair from every subtree.
- `P32_PathSumIIIDFS.java` - Prefix sums in a hash map along the current root-to-node path.
- `P33_PseudoPalindromicPathsInABinaryTreeDFS.java` - Carry a bitmask of digit parities down the path; a leaf counts when at most one bit is set.
- `P34_LongestUnivaluePathDFS.java` - Return the best same-value arm and combine two arms at each node.
- `P35_DeleteNodesAndReturnForestDFS.java` - Pass down whether the parent was deleted and return null for deleted nodes.
- `P36_LongestZigZagPathDFS.java` - Carry the zigzag length for each arrival direction, extending one and resetting the other.
- `P37_BinaryTreeLongestConsecutiveSequenceDFS.java` - Pass the parent's value and current streak length down; extend or reset at each node.
- `P38_DistributeCoinsInBinaryTreeDFS.java` - Compute each subtree's coin balance; its absolute value is the flow across that edge.
- `P39_FindDuplicateSubtreesDFS.java` - Serialize every subtree bottom-up and report a root the second time its encoding appears.
- `P40_MaximumProductOfSplittedBinaryTreeDFS.java` - Compute the total, then every subtree sum s gives the candidate product s * (total - s).
- `P41_MaximumDifferenceBetweenNodeAndAncestorDFS.java` - Carry the path minimum and maximum down to every node.
- `P42_StepByStepDirectionsFromABinaryTreeNodeToAnotherDFS.java` - Find both root paths, drop their common prefix, and turn the start's remainder into U moves.
- `P43_BoundaryOfBinaryTreeDFS.java` - Combine three traversals: the left edge top-down, all leaves left-to-right, the right edge bottom-up.

### Hard
- `P44_BinaryTreeMaximumPathSumDFS.java` - Update a global best path while returning only one extendable branch upward.
- `P45_SerializeAndDeserializeBinaryTreeDFS.java` - Pre-order with null markers, written and read by mirrored recursions.
- `P46_RecoverATreeFromPreorderTraversalDFS.java` - Parse (depth, value) pairs; a stack of the current root-to-node path tells where each node attaches.
- `P47_BinaryTreeCamerasDFS.java` - Greedy post-order with three node states.
- `P48_MaximumSumBSTInBinaryTreeDFS.java` - Each subtree reports whether it is a BST together with its min, max, and sum.
- `P49_VerticalOrderTraversalDFS.java` - Label nodes with (column, row) during DFS, then sort and group by column.
- `P50_HeightOfBinaryTreeAfterSubtreeRemovalQueriesDFS.java` - Record each node's depth and height, plus the two largest heights on every level.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

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
