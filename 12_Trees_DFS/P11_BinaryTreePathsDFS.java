import java.util.*;

/*
Problem: Binary Tree Paths
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
Given the root of a binary tree, return all root-to-leaf paths in any order.
    Each path is written as the node values joined by "->". A leaf is a node with no children.

Why this pattern fits:
A root-to-leaf path is exactly what a depth-first traversal follows.
    The path so far is state carried down the recursion, and reaching a leaf is the moment to record it.

Approach:
Recurse with a StringBuilder holding the path so far.
    On entering a node, remember the builder's length and append the node's value.
    If the node is a leaf, record the builder's contents.
    Otherwise append "->" and recurse into the children.
    Before returning, truncate the builder back to the remembered length, which undoes this node's contribution.

Worked Example:
Example:
            1
           / \
          2   3
           \
            5
    Paths: "1->2->5" and "1->3".

Time Complexity: O(n * h) to copy each path, where h is the height
Space Complexity: O(h) besides the output
*/

public class P11_BinaryTreePathsDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root != null) {
            dfs(root, new StringBuilder(), paths);
        }
        return paths;
    }

    private static void dfs(TreeNode node, StringBuilder path, List<String> paths) {
        int length = path.length();
        path.append(node.val);

        if (node.left == null && node.right == null) {
            paths.add(path.toString());
        } else {
            path.append("->");
            if (node.left != null) dfs(node.left, path, paths);
            if (node.right != null) dfs(node.right, path, paths);
        }

        path.setLength(length);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        System.out.println(binaryTreePaths(root));
        System.out.println(binaryTreePaths(new TreeNode(7)));
    }
}
