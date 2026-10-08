import java.util.*;

/*
Problem: Longest ZigZag Path in a Binary Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
You are given the root of a binary tree. A ZigZag path is defined as follows:
    choose any node and a direction (left or right), move to the child in that direction,
    then switch direction and repeat until you cannot move.
    The ZigZag length is the number of nodes visited minus 1. Return the longest ZigZag path in the tree.

Why this pattern fits:
The state of a zigzag at a node is "how long is the zigzag that arrived here, and from which side".
    DFS carries that state downward, extending it in one direction and restarting it in the other.

Approach:
Recurse with two numbers describing the zigzag that ends at the current node:
    cameLeft is its length if the last move was to the left, and cameRight is its length if the last move was to the right.
    At most one of them is non-zero, and both are 0 at the root.
    Moving to the left child extends a zigzag whose last move was to the right, so the child gets cameLeft = cameRight + 1.
    Its cameRight is 0, because a path cannot arrive at a left child by a right move.
    Moving to the right child is symmetric: cameRight = cameLeft + 1 and cameLeft = 0.
    If the previous move was in the same direction, the formula yields 1, which is a fresh zigzag starting at the parent.
    The largest value seen at any node is the answer.

Worked Example:
Example:
        1
         \
          1
         / \
        1   1
           / \
          1   1
           \
            1
             \
              1
    Starting at the second node: right, left, right. Length = 3.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class LongestZigZagPathDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int longestZigZag(TreeNode root) {
        int[] best = new int[1];
        dfs(root, 0, 0, best);
        return best[0];
    }

    private static void dfs(TreeNode node, int cameLeft, int cameRight, int[] best) {
        if (node == null) {
            return;
        }

        best[0] = Math.max(best[0], Math.max(cameLeft, cameRight));
        dfs(node.left, cameRight + 1, 0, best);
        dfs(node.right, 0, cameLeft + 1, best);
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
        System.out.println(longestZigZag(build(1, null, 1, 1, 1, null, null, 1, 1, null, 1, null, null, null, 1)));
        System.out.println(longestZigZag(build(1, 1, 1, null, 1, null, null, 1, 1, null, 1)));
        System.out.println(longestZigZag(build(1)));
    }
}
