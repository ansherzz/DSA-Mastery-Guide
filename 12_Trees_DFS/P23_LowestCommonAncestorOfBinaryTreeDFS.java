import java.util.*;

/*
Problem: Lowest Common Ancestor of a Binary Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Given a binary tree and two nodes p and q that both exist in the tree,
    find their lowest common ancestor (LCA): the deepest node that has both p and q as descendants.
    A node is allowed to be a descendant of itself.

    This is a general binary tree, not a BST, so node values give no direction.

Why this pattern fits:
Without ordering, the only way to locate p and q is to search.
    A post-order DFS lets each node learn what its subtrees found, and the first node that sees
    the two targets arrive from different sides (or is itself one of them) is the answer.

Approach:
If the current node is null, p, or q, return it.
    Recurse into both children.
    If both sides return a non-null result, p and q are in different subtrees, so the current node is the LCA.
    Otherwise pass up whichever side found something.

Worked Example:
Example:
              3
             / \
            5   1
           / \ / \
          6  2 0  8
            / \
           7   4
    LCA(5, 1) = 3 because they are in different subtrees of the root.
    LCA(5, 4) = 5 because 4 is a descendant of 5.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class P23_LowestCommonAncestorOfBinaryTreeDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode fromLeft = lowestCommonAncestor(root.left, p, q);
        TreeNode fromRight = lowestCommonAncestor(root.right, p, q);

        if (fromLeft != null && fromRight != null) {
            return root;
        }
        return fromLeft != null ? fromLeft : fromRight;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        TreeNode one = new TreeNode(1);
        TreeNode four = new TreeNode(4);
        TreeNode seven = new TreeNode(7);

        root.left = five;
        root.right = one;
        five.left = new TreeNode(6);
        five.right = new TreeNode(2);
        five.right.left = seven;
        five.right.right = four;
        one.left = new TreeNode(0);
        one.right = new TreeNode(8);

        System.out.println("LCA(5, 1) = " + lowestCommonAncestor(root, five, one).val);
        System.out.println("LCA(5, 4) = " + lowestCommonAncestor(root, five, four).val);
        System.out.println("LCA(7, 4) = " + lowestCommonAncestor(root, seven, four).val);
    }
}
