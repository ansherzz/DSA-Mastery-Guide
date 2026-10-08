import java.util.*;

/*
Problem: Deepest Leaves Sum
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Given the root of a binary tree, return the sum of the values of its deepest leaves.

Why this pattern fits:
The deepest leaves are exactly the nodes of the last BFS level.

Approach:
BFS by level size, resetting the sum at the start of each level. After the loop the sum is that of the last level.

Worked Example:
[1, 2, 3, 4, 5, null, 6, 7, null, null, null, null, 8] -> the deepest leaves are 7 and 8. Answer = 15.

Time Complexity: O(n)
Space Complexity: O(width of the tree)
*/

public class P37_DeepestLeavesSumBFS {
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

    public static int deepestLeavesSum(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            sum = 0;
            for (int size = queue.size(); size > 0; size--) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("tree with deepest leaves 7 and 8 -> "
                + deepestLeavesSum(build(1, 2, 3, 4, 5, null, 6, 7, null, null, null, null, 8)));
        System.out.println("tree with deepest leaves 9, 1, 4, 5 -> "
                + deepestLeavesSum(build(6, 7, 8, 2, 7, 1, 3, 9, null, 1, 4, null, null, null, 5)));
    }
}
