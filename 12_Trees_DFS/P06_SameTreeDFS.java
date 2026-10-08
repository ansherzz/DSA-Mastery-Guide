import java.util.*;

/*
Problem: Same Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
Given the roots of two binary trees p and q, return true if the trees are the same.
    Two trees are the same when they have identical structure and every pair of corresponding nodes has the same value.

Why this pattern fits:
Two trees are equal exactly when their roots match and their left subtrees are equal and their right subtrees are equal.
    That recursive definition translates directly into a simultaneous DFS over both trees.

Approach:
If both nodes are null, they match.
    If only one is null, or the values differ, they do not match.
    Otherwise compare the left children and the right children recursively.

Worked Example:
Example:
    p = [1, 2, 3] and q = [1, 2, 3] are the same.
    p = [1, 2] (2 as left child) and q = [1, null, 2] (2 as right child) differ in structure.

Time Complexity: O(min(n, m))
Space Complexity: O(h) recursion stack
*/

public class P06_SameTreeDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        a.left = new TreeNode(2);
        a.right = new TreeNode(3);

        TreeNode b = new TreeNode(1);
        b.left = new TreeNode(2);
        b.right = new TreeNode(3);
        System.out.println("Identical trees      : " + isSameTree(a, b));

        TreeNode c = new TreeNode(1);
        c.left = new TreeNode(2);
        TreeNode d = new TreeNode(1);
        d.right = new TreeNode(2);
        System.out.println("Different structure  : " + isSameTree(c, d));

        b.right.val = 4;
        System.out.println("Different value      : " + isSameTree(a, b));
    }
}
