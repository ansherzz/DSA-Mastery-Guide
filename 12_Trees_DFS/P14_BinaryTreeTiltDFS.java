import java.util.*;

/*
Problem: Binary Tree Tilt
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
Given the root of a binary tree, return the sum of every tree node's tilt.
    The tilt of a node is the absolute difference between the sum of all left subtree node values
    and the sum of all right subtree node values. A missing subtree has sum 0.

Why this pattern fits:
Each node needs the sums of its two subtrees, which are computed from the bottom up.
    This is the post-order "return one thing, accumulate another" template:
    the recursion returns the subtree sum while a separate total collects the tilts.

Approach:
Write a helper that returns the sum of the subtree rooted at a node.
    It first gets the left and right subtree sums, adds |left - right| to a running total,
    and returns left + right + node value to its parent.
    Each node is visited once.

Worked Example:
Example:
            4
           / \
          2   9
         / \    \
        3   5    7
    Tilts: leaves 0, node 2 -> |3 - 5| = 2, node 9 -> |0 - 7| = 7, node 4 -> |10 - 16| = 6. Total = 15.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class P14_BinaryTreeTiltDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int findTilt(TreeNode root) {
        int[] totalTilt = new int[1];
        subtreeSum(root, totalTilt);
        return totalTilt[0];
    }

    private static int subtreeSum(TreeNode node, int[] totalTilt) {
        if (node == null) {
            return 0;
        }

        int left = subtreeSum(node.left, totalTilt);
        int right = subtreeSum(node.right, totalTilt);
        totalTilt[0] += Math.abs(left - right);
        return left + right + node.val;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(7);
        System.out.println(findTilt(root));

        TreeNode small = new TreeNode(1);
        small.left = new TreeNode(2);
        small.right = new TreeNode(3);
        System.out.println(findTilt(small));
    }
}
