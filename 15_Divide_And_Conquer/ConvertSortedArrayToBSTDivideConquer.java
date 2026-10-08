import java.util.*;

/*
Problem: Convert Sorted Array to Binary Search Tree
Pattern: 15. Divide & Conquer
Difficulty: Easy

Interview Prompt:
Given a sorted integer array, convert it into a height-balanced BST.

    The midpoint naturally becomes the root, splitting the array into left and right BST parts.

Why this pattern fits:
This is divide and conquer with a tree-building flavor.
    Each midpoint choice preserves BST order and helps keep the tree balanced.

Approach:
Recursively choose the middle element as the root.
    Build the left subtree from the left half and the right subtree from the right half.

Worked Example:
Example:
    nums = [-10, -3, 0, 5, 9]
    One valid balanced BST uses 0 as the root.

Time Complexity: O(n)
Space Complexity: O(log n) recursion stack for a balanced tree
*/

public class ConvertSortedArrayToBSTDivideConquer {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private static TreeNode build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);
        return root;
    }

    private static void preorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        values.add(node.val);
        preorder(node.left, values);
        preorder(node.right, values);
    }

    public static void main(String[] args) {
        TreeNode root = sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        List<Integer> values = new ArrayList<>();
        preorder(root, values);
        System.out.println(values);
    }
}
