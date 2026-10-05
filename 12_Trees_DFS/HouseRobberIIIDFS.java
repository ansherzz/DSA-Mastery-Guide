import java.util.*;

/*
Problem: House Robber III
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Houses in a neighborhood form a binary tree, and each house holds some money.
    The alarm goes off if two directly linked houses (a parent and its child) are robbed on the same night.
    Given the root of the tree, return the maximum amount of money that can be robbed without setting off the alarm.

Why this pattern fits:
The best answer for a subtree depends on a choice at its root: rob it or skip it.
    A post-order DFS that returns both outcomes for every node lets the parent combine them
    without recomputing anything. This is dynamic programming on a tree.

Approach:
For each node return a pair [skip, rob]:
    rob  = node.val + skip(left) + skip(right), because the children must be skipped.
    skip = max(left pair) + max(right pair), because each child is free to be robbed or skipped.
    The answer is the larger value of the root's pair.

Worked Example:
Example:
            3
           / \
          2   3
           \   \
            3   1
    Robbing the root and the two grandchildren gives 3 + 3 + 1 = 7.
    Robbing the two children gives only 2 + 3 = 5. Answer = 7.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class HouseRobberIIIDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int rob(TreeNode root) {
        int[] result = solve(root);
        return Math.max(result[0], result[1]);
    }

    private static int[] solve(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = solve(node.left);
        int[] right = solve(node.right);

        int skip = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        int rob = node.val + left[0] + right[0];
        return new int[]{skip, rob};
    }

    public static void main(String[] args) {
        TreeNode first = new TreeNode(3);
        first.left = new TreeNode(2);
        first.right = new TreeNode(3);
        first.left.right = new TreeNode(3);
        first.right.right = new TreeNode(1);
        System.out.println("Max loot: " + rob(first));

        TreeNode second = new TreeNode(3);
        second.left = new TreeNode(4);
        second.right = new TreeNode(5);
        second.left.left = new TreeNode(1);
        second.left.right = new TreeNode(3);
        second.right.right = new TreeNode(1);
        System.out.println("Max loot: " + rob(second));
    }
}
