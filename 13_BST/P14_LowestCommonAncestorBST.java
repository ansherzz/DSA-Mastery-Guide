import java.util.*;

/*
Problem: Lowest Common Ancestor of a BST
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given the root of a BST and two nodes p and q that exist in the tree, return their lowest common ancestor.

    The BST ordering lets you walk downward without exploring both sides fully.

Why this pattern fits:
If both targets are smaller than the current node, the LCA must be in the left subtree.
    If both are larger, it must be in the right subtree.
    Otherwise the current node is the split point and therefore the LCA.

Approach:
Iterate from the root using BST comparisons until you find the first split point.

Worked Example:
Example:
    In the BST [6,2,8,0,4,7,9,null,null,3,5], the LCA of 2 and 8 is 6.

Time Complexity: O(h)
Space Complexity: O(1)
*/

public class P14_LowestCommonAncestorBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode current = root;
        while (current != null) {
            if (p.val < current.val && q.val < current.val) {
                current = current.left;
            } else if (p.val > current.val && q.val > current.val) {
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        TreeNode p = root.left;
        TreeNode q = root.right;

        System.out.println("LCA: " + lowestCommonAncestor(root, p, q).val);
    }
}
