import java.util.*;

/*
Problem: Two Sum IV - Input is a BST
Pattern: 13. Binary Search Tree
Difficulty: Easy

Interview Prompt:
Given the root of a binary search tree and an integer k, return true if there exist two different nodes
    in the tree whose values add up to k.

Why this pattern fits:
The BST gives you sorted data for free through an inorder traversal.
    Once the values are in a sorted list, the classic two-pointer two-sum applies directly.

Approach:
Collect the values with an inorder traversal, which produces them in ascending order.
    Place one pointer at each end of the list. If the sum is too small move the left pointer right,
    if it is too large move the right pointer left, and return true on an exact match.

Worked Example:
Example:
            5
           / \
          3   6
         / \   \
        2   4   7
    Inorder: 2, 3, 4, 5, 6, 7. For k = 9: 2 + 7 = 9. Answer = true.
    For k = 28 no pair is large enough. Answer = false.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P06_TwoSumIVInputIsBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static boolean findTarget(TreeNode root, int k) {
        List<Integer> values = new ArrayList<>();
        inorder(root, values);

        int left = 0;
        int right = values.size() - 1;
        while (left < right) {
            int sum = values.get(left) + values.get(right);
            if (sum == k) {
                return true;
            }
            if (sum < k) {
                left++;
            } else {
                right--;
            }
        }
        return false;
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

        System.out.println("Pair with sum 9?  " + findTarget(root, 9));
        System.out.println("Pair with sum 28? " + findTarget(root, 28));
        System.out.println("Pair with sum 10? " + findTarget(root, 10));
    }
}
