import java.util.*;

/*
Problem: Delete Node in a BST
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given the root of a BST and a key, delete the node with that key if it exists and return the new root.

    Deletion has three structural cases: no child, one child, or two children.

Why this pattern fits:
This is a full BST maintenance problem.
    It tests whether you can preserve the ordering invariant while performing structural changes.

Approach:
Search for the key.
    If the node has at most one child, replace it directly.
    If it has two children, replace its value with the inorder successor and delete the successor.

Worked Example:
Example:
    Delete 3 from the BST [5,3,6,2,4,null,7].

Time Complexity: O(h)
Space Complexity: O(h)
*/

public class P13_DeleteNodeInBSTPattern {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            TreeNode successor = root.right;
            while (successor.left != null) {
                successor = successor.left;
            }

            root.val = successor.val;
            root.right = deleteNode(root.right, successor.val);
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
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        root = deleteNode(root, 3);

        List<Integer> values = new ArrayList<>();
        inorder(root, values);
        System.out.println(values);
    }
}
