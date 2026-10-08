import java.util.*;

/*
Problem: Insert into a Binary Search Tree
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given the root of a BST and a value to insert, return the root of the BST after insertion.
    The new value does not already exist in the tree.

    This problem is a direct application of the BST invariant.

Why this pattern fits:
Comparing the value against the current node immediately tells you which subtree should receive the insertion.

Approach:
Recursively or iteratively walk left if the value is smaller, right if larger.
    Insert a new node when a null child position is found.

Worked Example:
Example:
    Insert 5 into a BST containing 4,2,7,1,3.
    One valid result adds 5 as the left child of 7.

Time Complexity: O(h)
Space Complexity: O(h) recursive or O(1) iterative extra space
*/

public class P12_InsertIntoBSTPattern {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }

    private static void inorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        inorder(node.left, values);
        values.add(node.val);
        inorder(node.right, values);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        root = insertIntoBST(root, 5);

        List<Integer> values = new ArrayList<>();
        inorder(root, values);
        System.out.println(values);
    }
}
