import java.util.*;

/*
Problem: Sum of Left Leaves
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
Given the root of a binary tree, return the sum of all left leaves.
    A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.

Why this pattern fits:
Whether a node counts depends on information from its parent: was it reached through a left edge?
    DFS passes that fact downward as a parameter, which is the standard way to carry parent context.

Approach:
Recurse with a flag isLeft that says whether the current node is a left child.
    If the node is a leaf, return its value when isLeft is true and 0 otherwise.
    Otherwise return the sum of the results from the left child (flag true) and the right child (flag false).
    The root is called with false, so a single-node tree contributes 0.

Worked Example:
Example:
            3
           / \
          9   20
             /  \
            15   7
    The left leaves are 9 and 15. Answer = 24.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class P16_SumOfLeftLeavesDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int sumOfLeftLeaves(TreeNode root) {
        return dfs(root, false);
    }

    private static int dfs(TreeNode node, boolean isLeft) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return isLeft ? node.val : 0;
        }
        return dfs(node.left, true) + dfs(node.right, false);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(sumOfLeftLeaves(root));
        System.out.println(sumOfLeftLeaves(new TreeNode(1)));
    }
}
