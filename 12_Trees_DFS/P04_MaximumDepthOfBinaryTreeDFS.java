import java.util.*;

/*
Problem: Maximum Depth of Binary Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
Given the root of a binary tree, return its maximum depth.
    The depth is the number of nodes along the longest path from root to a leaf.

    This is a pure subtree-combination DFS problem.

Why this pattern fits:
The depth of a node is one plus the maximum depth of its two children.
    That recursive definition matches DFS perfectly.

Approach:
If the node is null, return 0.
    Otherwise return 1 + max(depth(left), depth(right)).

Worked Example:
Example:
    A tree with root 3, children 9 and 20, and grandchildren 15 and 7 has maximum depth 3.

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P04_MaximumDepthOfBinaryTreeDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Depth: " + maxDepth(root));
    }
}
