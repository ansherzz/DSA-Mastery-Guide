import java.util.*;

/*
Problem: Path Sum III
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Given the root of a binary tree and an integer targetSum, return the number of paths
    whose node values sum to targetSum.
    A path does not need to start at the root or end at a leaf, but it must go downward
    (from a parent to its descendants only).

Why this pattern fits:
Along any single root-to-node path, the tree looks like an array, and "count subarrays with sum k"
    is solved with prefix sums and a hash map. DFS extends this to a tree:
    add the current prefix to the map on the way down and remove it on the way back up,
    so the map always describes exactly the current root-to-node path.

Approach:
Keep a running prefix sum from the root and a map of prefix sum -> how many times it occurs on the current path.
    At each node, the number of paths ending here equals the count of (prefix - targetSum) in the map.
    Record the current prefix, recurse into both children, then undo the record before returning.

Worked Example:
Example:
               10
              /  \
             5   -3
            / \    \
           3   2   11
          / \   \
         3  -2   1
    targetSum = 8: the paths are 5 -> 3, 5 -> 2 -> 1, and -3 -> 11. Answer = 3.

Time Complexity: O(n)
Space Complexity: O(h) for the map and recursion stack
*/

public class P21_PathSumIIIDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0L, 1);
        return count(root, 0L, targetSum, prefixCount);
    }

    private static int count(TreeNode node, long prefix, int targetSum, Map<Long, Integer> prefixCount) {
        if (node == null) {
            return 0;
        }

        prefix += node.val;
        int paths = prefixCount.getOrDefault(prefix - targetSum, 0);

        prefixCount.merge(prefix, 1, Integer::sum);
        paths += count(node.left, prefix, targetSum, prefixCount);
        paths += count(node.right, prefix, targetSum, prefixCount);
        prefixCount.merge(prefix, -1, Integer::sum);

        return paths;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        System.out.println("Paths with sum 8: " + pathSum(root, 8));
        System.out.println("Paths with sum 18: " + pathSum(root, 18));
    }
}
