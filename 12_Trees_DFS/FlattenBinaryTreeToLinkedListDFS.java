import java.util.*;

/*
Problem: Flatten Binary Tree to Linked List
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Given the root of a binary tree, flatten it into a "linked list" in-place:
    - the list uses the same TreeNode objects, with right acting as the next pointer and left always null
    - the order of the list must be the pre-order traversal of the original tree

Why this pattern fits:
The required order is pre-order: node, left subtree, right subtree.
    If you process nodes in the exact reverse of that order (right, left, node), then each node's
    correct successor is simply the node you processed just before it.

Approach:
Keep a pointer previous to the most recently processed node (initially null).
    Recurse into the right subtree first, then the left subtree.
    Then set node.right = previous, node.left = null, and update previous to the current node.

Worked Example:
Example:
            1
           / \
          2   5            ->   1 -> 2 -> 3 -> 4 -> 5 -> 6
         / \   \
        3   4   6
    Reverse pre-order visits 6, 5, 4, 3, 2, 1, linking each node to the one visited before it.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class FlattenBinaryTreeToLinkedListDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static TreeNode previous;

    public static void flatten(TreeNode root) {
        previous = null;
        relink(root);
    }

    private static void relink(TreeNode node) {
        if (node == null) {
            return;
        }

        relink(node.right);
        relink(node.left);

        node.right = previous;
        node.left = null;
        previous = node;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        flatten(root);

        StringBuilder sb = new StringBuilder();
        boolean allLeftNull = true;
        for (TreeNode node = root; node != null; node = node.right) {
            sb.append(node.val);
            if (node.right != null) {
                sb.append(" -> ");
            }
            allLeftNull &= node.left == null;
        }
        System.out.println("Flattened: " + sb);
        System.out.println("Every left pointer is null? " + allLeftNull);
    }
}
