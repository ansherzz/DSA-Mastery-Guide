import java.util.*;

/*
Problem: Validate Binary Search Tree
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given the root of a binary tree, determine whether it is a valid binary search tree.
    In a valid BST, every node in the left subtree is strictly smaller than the node,
    and every node in the right subtree is strictly larger.

    This is about preserving value bounds, not just comparing direct children.

Why this pattern fits:
Many incorrect solutions only check the immediate children.
    The real BST rule is global: every node must respect all ancestor bounds.

Approach:
DFS with lower and upper bounds.
    A node is valid only if lower < node.val < upper, and the bounds tighten as you recurse.

Worked Example:
Example:
    A tree with root 5, left child 1, right child 4, and 4's children 3 and 6 is not a valid BST.

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class ValidateBinarySearchTreeBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return validate(node.left, lower, node.val) && validate(node.right, node.val, upper);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);

        System.out.println("Valid BST? " + isValidBST(root));
    }
}
