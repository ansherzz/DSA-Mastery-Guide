import java.util.*;

/*
Problem: Inorder Successor in BST
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given the root of a binary search tree and a node p in it, return the inorder successor of p:
    the node with the smallest value greater than p.val. If p has the largest value, return null.

Why this pattern fits:
The successor is "the smallest value larger than p". In a BST that can be found with a single top-down walk:
    every time you turn left you have found a node larger than p, and each later left turn finds a smaller such node.

Approach:
Walk from the root with a candidate set to null.
    If the current value is greater than p.val, record the node as the candidate and go left to look for a smaller one.
    Otherwise go right, because the successor must be larger.
    The last recorded candidate is the successor.

Worked Example:
Example:
              5
             / \
            3   6
           / \
          2   4
         /
        1
    Successor of 4 is 5: at 5 record it and go left; at 3 go right; at 4 go right to null.
    Successor of 6 is null because nothing is larger.

Time Complexity: O(h)
Space Complexity: O(1)
*/

public class InorderSuccessorInBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        TreeNode node = root;

        while (node != null) {
            if (node.val > p.val) {
                successor = node;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return successor;
    }

    private static void runDemo(TreeNode root, TreeNode p) {
        TreeNode successor = inorderSuccessor(root, p);
        System.out.println("Successor of " + p.val + " -> " + (successor == null ? "null" : successor.val));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode three = new TreeNode(3);
        TreeNode six = new TreeNode(6);
        TreeNode four = new TreeNode(4);
        TreeNode one = new TreeNode(1);

        root.left = three;
        root.right = six;
        three.left = new TreeNode(2);
        three.right = four;
        three.left.left = one;

        runDemo(root, one);
        runDemo(root, four);
        runDemo(root, three);
        runDemo(root, six);
    }
}
