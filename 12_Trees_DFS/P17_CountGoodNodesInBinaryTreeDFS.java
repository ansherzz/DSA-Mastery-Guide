import java.util.*;

/*
Problem: Count Good Nodes in Binary Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Given the root of a binary tree, a node X is called good if no node on the path from the root to X
    has a value greater than X. Return the number of good nodes in the tree.

Why this pattern fits:
Whether a node is good depends only on the nodes above it, on its own root-to-node path.
    DFS can carry that information downward as a parameter: the maximum value seen so far on the path.

Approach:
Pass the maximum value seen on the path so far into each recursive call.
    A node is good if its value is at least that maximum.
    Update the maximum with the node's value and recurse into both children.
    Return the count from the node itself plus the counts from its subtrees.

Worked Example:
Example:
            3
           / \
          1   4
         /   / \
        3   1   5
    Good nodes: 3 (root), 4, 5, and the lower 3 (path 3 -> 1 -> 3 has maximum 3).
    The two 1s are not good because 3 or 4 above them is larger. Answer = 4.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class P17_CountGoodNodesInBinaryTreeDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int goodNodes(TreeNode root) {
        return count(root, Integer.MIN_VALUE);
    }

    private static int count(TreeNode node, int maxSoFar) {
        if (node == null) {
            return 0;
        }

        int good = node.val >= maxSoFar ? 1 : 0;
        int newMax = Math.max(maxSoFar, node.val);
        return good + count(node.left, newMax) + count(node.right, newMax);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(5);
        System.out.println("Good nodes: " + goodNodes(root));

        TreeNode chain = new TreeNode(3);
        chain.left = new TreeNode(3);
        chain.left.left = new TreeNode(4);
        chain.left.right = new TreeNode(2);
        System.out.println("Good nodes: " + goodNodes(chain));
    }
}
