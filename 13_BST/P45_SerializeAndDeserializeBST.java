import java.util.*;

/*
Problem: Serialize and Deserialize BST
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Design an algorithm to serialize a binary search tree to a string and deserialize the string back to the same tree.
    The encoded string should be as compact as possible.

Why this pattern fits:
A general binary tree needs null markers to record its shape.
    A BST does not: the ordering property determines where every value must go,
    so the preorder sequence of values alone is enough to rebuild the tree.

Approach:
Serialize: write the preorder traversal as comma-separated values, with no null markers.
    Deserialize: read the values in order with a shared index and rebuild recursively using value bounds.
    build(low, high) consumes the next value only if it lies strictly between low and high;
    otherwise that value belongs to an ancestor's right subtree, so return null.
    The consumed value becomes a node, its left subtree is build(low, value), and its right subtree is build(value, high).

Worked Example:
Example:
            5
           / \
          3   8
         / \   \
        2   4   9
    Serialized: "5,3,2,4,8,9". Rebuilding from that string gives back the same tree.

Time Complexity: O(n) for both operations
Space Complexity: O(n)
*/

public class P45_SerializeAndDeserializeBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }

    private static void preorder(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        if (sb.length() > 0) {
            sb.append(',');
        }
        sb.append(node.val);
        preorder(node.left, sb);
        preorder(node.right, sb);
    }

    public static TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }

        String[] parts = data.split(",");
        int[] values = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            values[i] = Integer.parseInt(parts[i]);
        }
        return build(values, new int[]{0}, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static TreeNode build(int[] values, int[] index, long low, long high) {
        if (index[0] == values.length) {
            return null;
        }

        int value = values[index[0]];
        if (value <= low || value >= high) {
            return null;
        }

        index[0]++;
        TreeNode node = new TreeNode(value);
        node.left = build(values, index, low, value);
        node.right = build(values, index, value, high);
        return node;
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
        TreeNode root = buildBST(5, 3, 8, 2, 4, 9);
        String encoded = serialize(root);
        System.out.println("Serialized  : " + encoded);
        System.out.println("Round trip  : " + serialize(deserialize(encoded)));
        System.out.println("Empty tree  : [" + serialize(deserialize("")) + "]");
    }
}
