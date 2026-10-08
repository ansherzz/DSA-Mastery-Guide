import java.util.*;

/*
Problem: Find Bottom Left Tree Value
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Given the root of a binary tree, return the leftmost value in the last row of the tree.

Why this pattern fits:
Level order reaches the last row last. If each level is visited right-to-left,
the final node dequeued is the leftmost node of the deepest row, with no level bookkeeping.

Approach:
BFS adding the right child before the left child. Keep the value of the last node polled.

Worked Example:
[1, 2, 3, 4, null, 5, 6, null, null, 7] -> the last row contains only 7. Answer = 7.

Time Complexity: O(n)
Space Complexity: O(width of the tree)
*/

public class P23_FindBottomLeftTreeValueBFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // Builds a tree from level-order values, using null for a missing child.
    private static TreeNode build(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int i = 1;
        while (i < values.length) {
            TreeNode node = queue.poll();
            if (values[i] != null) {
                node.left = new TreeNode(values[i]);
                queue.add(node.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                node.right = new TreeNode(values[i]);
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

    public static int findBottomLeftValue(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int last = root.val;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            last = node.val;
            if (node.right != null) {
                queue.add(node.right);
            }
            if (node.left != null) {
                queue.add(node.left);
            }
        }
        return last;
    }

    public static void main(String[] args) {
        System.out.println("[2, 1, 3] -> " + findBottomLeftValue(build(2, 1, 3)));
        System.out.println("[1, 2, 3, 4, null, 5, 6, null, null, 7] -> "
                + findBottomLeftValue(build(1, 2, 3, 4, null, 5, 6, null, null, 7)));
    }
}
