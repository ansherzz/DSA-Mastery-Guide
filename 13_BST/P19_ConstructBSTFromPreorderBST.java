import java.util.*;

/*
Problem: Construct Binary Search Tree from Preorder Traversal
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given an array of distinct integers that is the preorder traversal of a binary search tree,
    construct the tree and return its root.

Why this pattern fits:
A general binary tree cannot be rebuilt from preorder alone, but a BST can,
    because the ordering property tells you where each subtree ends:
    a value belongs to the current subtree only if it fits within that subtree's allowed value range.

Approach:
Consume the preorder array with a shared index, and pass an upper bound into each recursive call.
    If the next value exceeds the bound, it does not belong to this subtree, so return null.
    Otherwise create a node from it. The left subtree is bounded above by the node's value,
    and the right subtree inherits the caller's bound.

Worked Example:
Example:
    preorder = [8, 5, 1, 7, 10, 12]
            8
           / \
          5   10
         / \    \
        1   7    12
    After 8, the values 5, 1, 7 are all below 8 and form the left subtree. Then 10 and 12 form the right subtree.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class P19_ConstructBSTFromPreorderBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static int index;

    public static TreeNode bstFromPreorder(int[] preorder) {
        index = 0;
        return build(preorder, Integer.MAX_VALUE);
    }

    private static TreeNode build(int[] preorder, int upperBound) {
        if (index == preorder.length || preorder[index] > upperBound) {
            return null;
        }

        TreeNode node = new TreeNode(preorder[index++]);
        node.left = build(preorder, node.val);
        node.right = build(preorder, upperBound);
        return node;
    }

    private static void inorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        inorder(node.left, values);
        values.add(node.val);
        inorder(node.right, values);
    }

    private static void preorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        values.add(node.val);
        preorder(node.left, values);
        preorder(node.right, values);
    }

    public static void main(String[] args) {
        int[] input = {8, 5, 1, 7, 10, 12};
        TreeNode root = bstFromPreorder(input);

        List<Integer> inorderValues = new ArrayList<>();
        inorder(root, inorderValues);
        List<Integer> preorderValues = new ArrayList<>();
        preorder(root, preorderValues);

        System.out.println("Input preorder  : " + Arrays.toString(input));
        System.out.println("Rebuilt preorder: " + preorderValues);
        System.out.println("Rebuilt inorder : " + inorderValues);
        System.out.println("Root children   : left = " + root.left.val + ", right = " + root.right.val);
    }
}
