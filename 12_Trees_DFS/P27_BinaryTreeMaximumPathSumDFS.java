import java.util.*;

/*
Problem: Binary Tree Maximum Path Sum
Pattern: 12. Trees & Recursion - DFS
Difficulty: Hard

Interview Prompt:
A path in a binary tree can start and end at any nodes, but it must go through parent-child connections.
    Return the maximum path sum.

    The best path through a node can use the node plus both children, but the value returned upward can only choose one side.

Why this pattern fits:
This is a classic tree DP / DFS problem.
    It teaches the difference between a local complete answer and the single-branch value that can be extended upward.

Approach:
Use postorder DFS.
    For each node, compute the best downward gain from the left and right children, ignoring negative gains.
    Update the global answer with node.val + leftGain + rightGain.
    Return node.val + max(leftGain, rightGain).

Worked Example:
Example:
    In the tree [-10, 9, 20, null, null, 15, 7], the maximum path sum is 42 from 15 -> 20 -> 7.

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P27_BinaryTreeMaximumPathSumDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static int best;

    public static int maxPathSum(TreeNode root) {
        best = Integer.MIN_VALUE;
        gain(root);
        return best;
    }

    private static int gain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftGain = Math.max(0, gain(node.left));
        int rightGain = Math.max(0, gain(node.right));

        best = Math.max(best, node.val + leftGain + rightGain);
        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Max path sum: " + maxPathSum(root));
    }
}
