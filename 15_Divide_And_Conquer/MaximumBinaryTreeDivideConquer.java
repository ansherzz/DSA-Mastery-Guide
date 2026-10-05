import java.util.*;

/*
Problem: Maximum Binary Tree
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Given an integer array with no duplicates, build a maximum binary tree using this rule:
    - the root is the maximum value of the array
    - the left subtree is the maximum binary tree built from the elements to the left of the maximum
    - the right subtree is the maximum binary tree built from the elements to the right of the maximum

    Return the root of the tree.

Why this pattern fits:
The definition is already a divide-and-conquer recipe:
    find a dividing element (the maximum), then solve the same problem independently on both sides of it.

Approach:
For the current range, scan to find the index of the maximum value.
    Create a node for it, then recursively build the left child from the range before that index
    and the right child from the range after it. An empty range produces null.

Worked Example:
Example:
    nums = [3, 2, 1, 6, 0, 5]
              6
            /   \
           3     5
            \   /
             2 0
              \
               1
    6 is the overall maximum. [3, 2, 1] forms the left subtree and [0, 5] forms the right subtree.

Time Complexity: O(n^2) in the worst case (sorted input), O(n log n) on average
Space Complexity: O(n) recursion stack in the worst case
*/

public class MaximumBinaryTreeDivideConquer {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private static TreeNode build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int maxIndex = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = build(nums, left, maxIndex - 1);
        root.right = build(nums, maxIndex + 1, right);
        return root;
    }

    private static void preorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        values.add(node.val);
        preorder(node.left, values);
        preorder(node.right, values);
    }

    private static void inorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        inorder(node.left, values);
        values.add(node.val);
        inorder(node.right, values);
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 6, 0, 5};
        TreeNode root = constructMaximumBinaryTree(nums);

        List<Integer> preorderValues = new ArrayList<>();
        preorder(root, preorderValues);
        List<Integer> inorderValues = new ArrayList<>();
        inorder(root, inorderValues);

        System.out.println("Input    : " + Arrays.toString(nums));
        System.out.println("Preorder : " + preorderValues);
        System.out.println("Inorder  : " + inorderValues + " (matches the input order)");
    }
}
