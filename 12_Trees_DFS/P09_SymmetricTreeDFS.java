import java.util.*;

/*
Problem: Symmetric Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
Given the root of a binary tree, check whether it is a mirror of itself (symmetric around its center).

Why this pattern fits:
A tree is symmetric when its left subtree is the mirror image of its right subtree.
    "Mirror of" is a relation between two trees that recurses on crossed children:
    the outer pair must match and the inner pair must match.

Approach:
Write a helper isMirror(a, b).
    Two empty trees are mirrors. If only one is empty, or the root values differ, they are not.
    Otherwise check isMirror(a.left, b.right) and isMirror(a.right, b.left).
    Call the helper on the root's two children.

Worked Example:
Example:
            1
           / \
          2   2
         / \ / \
        3  4 4  3
    The outer pair (3, 3) and the inner pair (4, 4) match, so the tree is symmetric.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class P09_SymmetricTreeDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static boolean isSymmetric(TreeNode root) {
        return root == null || isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null || a.val != b.val) {
            return false;
        }
        return isMirror(a.left, b.right) && isMirror(a.right, b.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        System.out.println("Symmetric? " + isSymmetric(root));

        TreeNode lopsided = new TreeNode(1);
        lopsided.left = new TreeNode(2);
        lopsided.right = new TreeNode(2);
        lopsided.left.right = new TreeNode(3);
        lopsided.right.right = new TreeNode(3);
        System.out.println("Symmetric? " + isSymmetric(lopsided));
    }
}
