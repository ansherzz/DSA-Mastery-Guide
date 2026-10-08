import java.util.*;

/*
Problem: Increasing Order Search Tree
Pattern: 13. Binary Search Tree
Difficulty: Easy

Interview Prompt:
Given the root of a binary search tree, rearrange the tree in in-order so that the leftmost node
    becomes the root of the tree, and every node has no left child and only one right child.

Why this pattern fits:
In-order traversal of a BST visits the values in increasing order.
    The required shape is simply that visiting order written as a right-leaning chain.

Approach:
Keep a tail pointer, starting at a dummy node.
    Run an in-order traversal. When a node is visited, clear its left pointer,
    attach it as the right child of the tail, and move the tail to it.
    The right child is traversed after the node is attached, so the original links are read before they change.
    Return dummy.right.

Worked Example:
Example:
            5
           / \
          3   6
         / \
        2   4
    In-order: 2, 3, 4, 5, 6. Result: 2 -> 3 -> 4 -> 5 -> 6 as a chain of right children.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class P07_IncreasingOrderSearchTreeBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static TreeNode tail;

    public static TreeNode increasingBST(TreeNode root) {
        TreeNode dummy = new TreeNode(0);
        tail = dummy;
        inorder(root);
        return dummy.right;
    }

    private static void inorder(TreeNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        node.left = null;
        tail.right = node;
        tail = node;
        inorder(node.right);
    }

    private static TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.val) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }
        return root;
    }

    private static TreeNode buildBST(int... values) {
        TreeNode root = null;
        for (int value : values) {
            root = insert(root, value);
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode chain = increasingBST(buildBST(5, 3, 6, 2, 4));
        StringBuilder sb = new StringBuilder();
        for (TreeNode node = chain; node != null; node = node.right) {
            sb.append(node.val).append(node.left == null ? "" : "(has left!)");
            if (node.right != null) {
                sb.append(" -> ");
            }
        }
        System.out.println(sb);
    }
}
