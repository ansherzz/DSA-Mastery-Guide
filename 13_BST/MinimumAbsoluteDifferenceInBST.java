import java.util.*;

/*
Problem: Minimum Absolute Difference in BST
Pattern: 13. Binary Search Tree
Difficulty: Easy

Interview Prompt:
Given the root of a binary search tree, return the minimum absolute difference
    between the values of any two different nodes in the tree.

Why this pattern fits:
An inorder traversal of a BST visits the values in sorted order.
    In a sorted sequence the closest pair is always adjacent, so you only need to compare
    each value with the one visited just before it.

Approach:
Do an inorder traversal while remembering the previously visited value.
    At each node, update the answer with (current value - previous value), then make the current value the previous one.

Worked Example:
Example:
            4
           / \
          2   6
         / \
        1   3
    Inorder: 1, 2, 3, 4, 6. Adjacent differences: 1, 1, 1, 2. Answer = 1.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class MinimumAbsoluteDifferenceInBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static Integer previous;
    private static int best;

    public static int getMinimumDifference(TreeNode root) {
        previous = null;
        best = Integer.MAX_VALUE;
        inorder(root);
        return best;
    }

    private static void inorder(TreeNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        if (previous != null) {
            best = Math.min(best, node.val - previous);
        }
        previous = node.val;
        inorder(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        System.out.println("Min difference: " + getMinimumDifference(root));

        TreeNode sparse = new TreeNode(100);
        sparse.left = new TreeNode(10);
        sparse.right = new TreeNode(190);
        sparse.left.right = new TreeNode(95);
        System.out.println("Min difference: " + getMinimumDifference(sparse));
    }
}
