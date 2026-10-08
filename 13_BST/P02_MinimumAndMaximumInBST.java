import java.util.*;

/*
Problem: Minimum and Maximum in a BST
Pattern: 13. Binary Search Tree
Difficulty: Easy

Interview Prompt:
Given the root of a non-empty binary search tree, return its minimum and maximum values.

Why this pattern fits:
In a BST everything smaller is to the left and everything larger is to the right,
so the extremes are found by walking one edge of the tree instead of visiting every node.

Approach:
Follow left pointers until there is no left child for the minimum; follow right pointers for the maximum.

Worked Example:
Tree [8, 3, 10, 1, 6, null, 14, null, null, 4, 7, 13] -> leftmost node 1, rightmost node 14.

Time Complexity: O(h)
Space Complexity: O(1)
*/

public class P02_MinimumAndMaximumInBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // Builds a tree from level-order values, using null for a missing child.
    private static TreeNode build(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int i = 1;
        while (i < values.length) {
            TreeNode node = queue.poll();
            if (values[i] != null) {
                node.left = new TreeNode(values[i]);
                queue.add(node.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                node.right = new TreeNode(values[i]);
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

    public static int minimum(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }

    public static int maximum(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root.val;
    }

    public static void main(String[] args) {
        TreeNode root = build(8, 3, 10, 1, 6, null, 14, null, null, 4, 7, 13);
        System.out.println("minimum -> " + minimum(root));
        System.out.println("maximum -> " + maximum(root));
    }
}
