import java.util.*;

/*
Problem: Maximum Sum BST in Binary Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Hard

Interview Prompt:
Given a binary tree, return the maximum sum of all keys of any subtree that is also a binary search tree.
An empty subtree counts as a BST with sum 0.

Why this pattern fits:
Whether a subtree is a BST depends on its children being BSTs and on their value ranges.
Returning all of that from one postorder pass avoids re-validating subtrees repeatedly.

Approach:
dfs returns {isBST, min, max, sum}. A node forms a BST when both children do and left.max < val < right.min.
Then its sum is left.sum + right.sum + val and the best answer is updated.

Worked Example:
[1, 4, 3, 2, 4, 2, 5, null, null, null, null, null, null, 4, 6] -> the subtree rooted at 3 is a BST with sum 20.

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P48_MaximumSumBSTInBinaryTreeDFS {
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

    private static int best;

    private static int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        if (left[0] == 1 && right[0] == 1 && left[2] < node.val && node.val < right[1]) {
            int sum = left[3] + right[3] + node.val;
            best = Math.max(best, sum);
            return new int[]{1, Math.min(left[1], node.val), Math.max(right[2], node.val), sum};
        }
        return new int[]{0, 0, 0, 0};
    }

    public static int maxSumBST(TreeNode root) {
        best = 0;
        dfs(root);
        return best;
    }

    public static void main(String[] args) {
        System.out.println("large example -> "
                + maxSumBST(build(1, 4, 3, 2, 4, 2, 5, null, null, null, null, null, null, 4, 6)));
        System.out.println("[4, 3, null, 1, 2] -> " + maxSumBST(build(4, 3, null, 1, 2)));
        System.out.println("[-4, -2, -5] -> " + maxSumBST(build(-4, -2, -5)));
    }
}
