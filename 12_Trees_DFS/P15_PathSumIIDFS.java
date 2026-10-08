import java.util.*;

/*
Problem: Path Sum II
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths
    where the sum of the node values equals targetSum. Each path is returned as a list of node values.

Why this pattern fits:
This is Path Sum with the paths themselves required.
    DFS naturally carries the current path. The key technique is to add a node on the way down
    and remove it on the way back up, so one list can be reused for every branch.

Approach:
Keep a running path list and the remaining target.
    On entering a node, append its value and subtract it from the remainder.
    If the node is a leaf and the remainder is zero, record a copy of the path.
    Recurse into both children, then remove the node's value from the path before returning.

Worked Example:
Example:
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
    targetSum = 22: the paths are [5, 4, 11, 2] and [5, 8, 4, 5].

Time Complexity: O(n * h) in the worst case, because each recorded path is copied
Space Complexity: O(h) for the path and recursion stack, excluding the output
*/

public class P15_PathSumIIDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> paths = new ArrayList<>();
        collect(root, targetSum, new ArrayList<>(), paths);
        return paths;
    }

    private static void collect(TreeNode node, int remaining, List<Integer> path, List<List<Integer>> paths) {
        if (node == null) {
            return;
        }

        path.add(node.val);
        remaining -= node.val;

        if (node.left == null && node.right == null && remaining == 0) {
            paths.add(new ArrayList<>(path));
        } else {
            collect(node.left, remaining, path, paths);
            collect(node.right, remaining, path, paths);
        }

        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        System.out.println("Paths with sum 22: " + pathSum(root, 22));
        System.out.println("Paths with sum 100: " + pathSum(root, 100));
    }
}
