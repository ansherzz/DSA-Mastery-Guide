import java.util.*;

/*
Problem: Maximum Difference Between Node and Ancestor
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b
    where v = |a.val - b.val| and a is an ancestor of b.

Why this pattern fits:
For a fixed node, the best ancestor is either the smallest or the largest value on the path from the root.
    Those two numbers are path state, and DFS carries path state downward as parameters.

Approach:
Recurse with the minimum and maximum values seen on the path from the root to the current node.
    At each node, update the two values with the node's own value.
    When the path ends (null), the largest difference along that path is max - min.
    Return the larger result of the two subtrees.

Worked Example:
Example:
              8
            /   \
           3     10
          / \      \
         1   6      14
            / \    /
           4   7  13
    The best pair is ancestor 8 and descendant 1, with difference 7.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class P41_MaximumDifferenceBetweenNodeAndAncestorDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
    }

    private static int dfs(TreeNode node, int pathMin, int pathMax) {
        if (node == null) {
            return pathMax - pathMin;
        }

        pathMin = Math.min(pathMin, node.val);
        pathMax = Math.max(pathMax, node.val);
        return Math.max(dfs(node.left, pathMin, pathMax), dfs(node.right, pathMin, pathMax));
    }

    private static TreeNode build(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(values[0]);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int i = 1;
        while (i < values.length) {
            TreeNode parent = queue.poll();
            if (values[i] != null) {
                parent.left = new TreeNode(values[i]);
                queue.offer(parent.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                parent.right = new TreeNode(values[i]);
                queue.offer(parent.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println(maxAncestorDiff(build(8, 3, 10, 1, 6, null, 14, null, null, 4, 7, 13)));
        System.out.println(maxAncestorDiff(build(1, null, 2, null, 0, 3)));
    }
}
