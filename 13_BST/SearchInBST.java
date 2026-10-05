import java.util.*;

/*
Problem: Search in a Binary Search Tree
Pattern: 13. Binary Search Tree
Difficulty: Easy

Interview Prompt:
Given the root of a binary search tree and an integer val, find the node whose value equals val
    and return the subtree rooted at that node. If no such node exists, return null.

Why this pattern fits:
The BST property says everything in the left subtree is smaller and everything in the right subtree is larger.
    One comparison at each node therefore discards an entire subtree, just like binary search discards half an array.

Approach:
Start at the root. If the current value equals val, return the node.
    If val is smaller, continue in the left subtree; otherwise continue in the right subtree.
    An iterative loop is enough because only one path is ever followed.

Worked Example:
Example:
            4
           / \
          2   7
         / \
        1   3
    Searching for 2: 2 < 4, go left, found. The returned subtree contains 2, 1, and 3.
    Searching for 5: 5 > 4, go right to 7; 5 < 7, go left to null. Not found.

Time Complexity: O(h), which is O(log n) for a balanced tree
Space Complexity: O(1)
*/

public class SearchInBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode searchBST(TreeNode root, int val) {
        TreeNode node = root;
        while (node != null && node.val != val) {
            node = val < node.val ? node.left : node.right;
        }
        return node;
    }

    private static void inorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        inorder(node.left, values);
        values.add(node.val);
        inorder(node.right, values);
    }

    private static void runDemo(TreeNode root, int val) {
        TreeNode found = searchBST(root, val);
        List<Integer> values = new ArrayList<>();
        inorder(found, values);
        System.out.println("Search " + val + " -> " + (found == null ? "not found" : "subtree (inorder) " + values));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        runDemo(root, 2);
        runDemo(root, 5);
    }
}
