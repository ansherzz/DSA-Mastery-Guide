import java.util.*;

/*
Problem: Balanced Binary Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
Return true if a binary tree is height-balanced.
    A tree is balanced if for every node, the heights of the left and right subtrees differ by at most 1.

    The best solution checks balance and height in the same DFS.

Why this pattern fits:
A naive approach recomputes heights many times.
    The DFS pattern here returns a special value to signal imbalance immediately.

Approach:
Use postorder DFS.
    Return the subtree height if balanced. Return -1 if unbalanced.
    If either child returns -1 or the height difference exceeds 1, propagate -1 upward.

Worked Example:
Example:
    A line-shaped tree of four nodes is not balanced.

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P06_BalancedBinaryTreeDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static boolean isBalanced(TreeNode root) {
        return check(root) != -1;
    }

    private static int check(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = check(node.left);
        if (left == -1) return -1;

        int right = check(node.right);
        if (right == -1) return -1;

        if (Math.abs(left - right) > 1) {
            return -1;
        }

        return 1 + Math.max(left, right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);

        System.out.println("Balanced? " + isBalanced(root));
    }
}
