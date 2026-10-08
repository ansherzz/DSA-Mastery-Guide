import java.util.*;

/*
Problem: Convert BST to Greater Tree
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given the root of a binary search tree, convert it to a Greater Tree:
    every node's new value must be its original value plus the sum of all values in the tree that are greater than it.

Why this pattern fits:
Inorder traversal visits a BST in ascending order. Reversing it (right, node, left) visits in descending order.
    In descending order, the running total of everything seen so far is exactly "the sum of all greater values".

Approach:
Traverse the tree in reverse inorder while keeping a running sum.
    At each node, add its value to the running sum and then overwrite the node's value with that sum.

Worked Example:
Example:
            4                       30
           / \                     /  \
          1   6        ->        36    21
         / \ / \                /  \   / \
        0  2 5  7             36   35 26  15
            \    \                   \      \
             3    8                  33      8
    Descending order: 8, 7, 6, 5, 4, 3, 2, 1, 0 with running sums 8, 15, 21, 26, 30, 33, 35, 36, 36.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class P16_ConvertBSTToGreaterTreeBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static int runningSum;

    public static TreeNode convertBST(TreeNode root) {
        runningSum = 0;
        reverseInorder(root);
        return root;
    }

    private static void reverseInorder(TreeNode node) {
        if (node == null) {
            return;
        }
        reverseInorder(node.right);
        runningSum += node.val;
        node.val = runningSum;
        reverseInorder(node.left);
    }

    private static void inorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        inorder(node.left, values);
        values.add(node.val);
        inorder(node.right, values);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(8);

        List<Integer> before = new ArrayList<>();
        inorder(root, before);
        System.out.println("Inorder before: " + before);

        List<Integer> after = new ArrayList<>();
        inorder(convertBST(root), after);
        System.out.println("Inorder after : " + after);
    }
}
