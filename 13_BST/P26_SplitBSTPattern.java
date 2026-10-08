import java.util.*;

/*
Problem: Split BST
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given the root of a binary search tree and an integer target, split the tree into two subtrees:
    the first has all nodes with values less than or equal to target, and the second has all nodes greater than target.
    The tree does not necessarily contain a node with the value target.
    Most of the structure of the original tree should remain: if a parent and child end up in the same subtree,
    the child must still be a child of that parent. Return the roots of the two subtrees.

Why this pattern fits:
At every node the BST ordering decides, for an entire subtree at once, which side of the split it belongs to.
    Only one child needs to be split further, so the work follows a single root-to-leaf path.

Approach:
Recursive function split(node) returns {small tree, large tree}.
    If node.val <= target, the node and its whole left subtree belong to the small side.
    Split the right subtree; its small part becomes the node's new right child, and its large part is the large side.
    Otherwise the node and its whole right subtree belong to the large side.
    Split the left subtree; its large part becomes the node's new left child, and its small part is the small side.

Worked Example:
Example:
              4
            /   \
           2     6
          / \   / \
         1   3 5   7
    target = 2: the small tree is 2 with left child 1.
    The large tree is 4 with left child 3 and right subtree 6 (5, 7).

Time Complexity: O(h)
Space Complexity: O(h) recursion stack
*/

public class P26_SplitBSTPattern {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode[] splitBST(TreeNode root, int target) {
        if (root == null) {
            return new TreeNode[]{null, null};
        }

        if (root.val <= target) {
            TreeNode[] parts = splitBST(root.right, target);
            root.right = parts[0];
            return new TreeNode[]{root, parts[1]};
        }

        TreeNode[] parts = splitBST(root.left, target);
        root.left = parts[1];
        return new TreeNode[]{parts[0], root};
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

    private static void preorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        values.add(node.val);
        preorder(node.left, values);
        preorder(node.right, values);
    }

    public static void main(String[] args) {
        TreeNode[] parts = splitBST(buildBST(4, 2, 6, 1, 3, 5, 7), 2);

        List<Integer> small = new ArrayList<>();
        List<Integer> large = new ArrayList<>();
        preorder(parts[0], small);
        preorder(parts[1], large);
        System.out.println("Small tree preorder: " + small);
        System.out.println("Large tree preorder: " + large);
    }
}
