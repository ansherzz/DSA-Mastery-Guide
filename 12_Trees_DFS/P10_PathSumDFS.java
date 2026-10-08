import java.util.*;

/*
Problem: Path Sum
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path
    whose node values add up to targetSum. A leaf is a node with no children.

Why this pattern fits:
A root-to-leaf path is exactly what a depth-first descent follows.
    Passing the remaining sum downward turns the question at each node into the same question on a subtree.

Approach:
Subtract the current node's value from the remaining target.
    If the node is a leaf, the path works exactly when the remainder is zero.
    Otherwise recurse into the left and right children with the reduced target.
    An empty tree has no path, so return false for null.

Worked Example:
Example:
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
    targetSum = 22: the path 5 -> 4 -> 11 -> 2 adds up to 22. Answer = true.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class P10_PathSumDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        int remaining = targetSum - root.val;
        if (root.left == null && root.right == null) {
            return remaining == 0;
        }
        return hasPathSum(root.left, remaining) || hasPathSum(root.right, remaining);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        System.out.println("Path with sum 22? " + hasPathSum(root, 22));
        System.out.println("Path with sum 26? " + hasPathSum(root, 26));
        System.out.println("Path with sum 9?  " + hasPathSum(root, 9));
    }
}
