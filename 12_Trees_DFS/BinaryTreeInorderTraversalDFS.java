import java.util.*;

/*
Problem: Binary Tree Inorder Traversal
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
Given the root of a binary tree, return the inorder traversal of its values.
    Inorder means: left subtree, then node, then right subtree.

    This is a simple DFS recursion warm-up for tree problems.

Why this pattern fits:
Tree traversals teach the timing of recursive work.
    Inorder is especially useful because it produces sorted order on a BST.

Approach:
Recursively visit left, record the current value, then visit right.

Worked Example:
Example:
        1
                   2
         /
        3
    Inorder traversal is [1, 3, 2].

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class BinaryTreeInorderTraversalDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        dfs(root, answer);
        return answer;
    }

    private static void dfs(TreeNode node, List<Integer> answer) {
        if (node == null) {
            return;
        }
        dfs(node.left, answer);
        answer.add(node.val);
        dfs(node.right, answer);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        System.out.println(inorderTraversal(root));
    }
}
