import java.util.*;

/*
Problem: Trim a Binary Search Tree
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given the root of a binary search tree and two bounds low and high,
    trim the tree so that all remaining values lie in [low, high].
    Trimming must not change the relative structure of the nodes that remain:
    a node's descendant stays its descendant. Return the root of the trimmed tree, which may be a different node.

Why this pattern fits:
The BST property tells you what to throw away without looking at it.
    If a node is too small, the node and its whole left subtree are out of range, so only its right subtree can survive.
    The mirror argument applies when a node is too large.

Approach:
Recursively trim with three cases:
    node.val < low  -> the answer is the trimmed right subtree
    node.val > high -> the answer is the trimmed left subtree
    otherwise keep the node and replace both children with their trimmed versions.

Worked Example:
Example:
            3                    3
           / \                  /
          0   4       ->       2
           \                  /
            2                1
           /
          1
    low = 1, high = 3: node 0 is too small and is replaced by its right subtree (2 with child 1).
    Node 4 is too large and has no left subtree, so it disappears.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class P17_TrimBinarySearchTreeBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }

        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

    private static void inorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        inorder(node.left, values);
        values.add(node.val);
        inorder(node.right, values);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(0);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(1);

        TreeNode trimmed = trimBST(root, 1, 3);
        List<Integer> values = new ArrayList<>();
        inorder(trimmed, values);
        System.out.println("Trimmed root   : " + trimmed.val);
        System.out.println("Trimmed inorder: " + values);
    }
}
