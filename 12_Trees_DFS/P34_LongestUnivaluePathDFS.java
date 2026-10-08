import java.util.*;

/*
Problem: Longest Univalue Path
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Given the root of a binary tree, return the length of the longest path where each node in the path has the same value.
    The path may or may not pass through the root.
    The length of a path is the number of edges between its nodes.

Why this pattern fits:
This has the same shape as Diameter of Binary Tree: the best path bends at some node,
    using one downward arm on each side. The recursion returns a single arm, and a global tracks the best bend.

Approach:
The helper returns the length of the longest same-value arm that starts at the node and goes downward.
    Get the arm lengths from both children. An arm through a child is usable only if the child's value equals the node's value;
    in that case its length is the child's arm + 1, otherwise it is 0.
    The best path bending at this node is leftArm + rightArm; update the global with it.
    Return the longer of the two arms to the parent.

Worked Example:
Example:
            1
           / \
          4   5
         / \    \
        4   4    5
    The path 4 - 4 - 4 through the left child has 2 edges. Answer = 2.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class P34_LongestUnivaluePathDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int longestUnivaluePath(TreeNode root) {
        int[] best = new int[1];
        arm(root, best);
        return best[0];
    }

    private static int arm(TreeNode node, int[] best) {
        if (node == null) {
            return 0;
        }

        int left = arm(node.left, best);
        int right = arm(node.right, best);
        int leftArm = node.left != null && node.left.val == node.val ? left + 1 : 0;
        int rightArm = node.right != null && node.right.val == node.val ? right + 1 : 0;

        best[0] = Math.max(best[0], leftArm + rightArm);
        return Math.max(leftArm, rightArm);
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
        System.out.println(longestUnivaluePath(build(1, 4, 5, 4, 4, null, 5)));
        System.out.println(longestUnivaluePath(build(5, 4, 5, 1, 1, null, 5)));
        System.out.println(longestUnivaluePath(build(1)));
    }
}
