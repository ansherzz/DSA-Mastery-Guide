import java.util.*;

/*
Problem: Count Nodes Equal to Average of Subtree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Return the number of nodes whose value equals the average (rounded down) of all values in their subtree.

Why this pattern fits:
The average needs two aggregates of a subtree. Returning both from a postorder DFS computes them once per node.

Approach:
dfs(node) returns {sum, count} = node value plus the children's results.
Increment the answer when sum / count equals the node's value.

Worked Example:
[4, 8, 5, 0, 1, null, 6] -> the root (24 / 6 = 4), node 5 (11 / 2 = 5), and the three leaves qualify. Answer = 5.

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P26_CountNodesEqualToAverageOfSubtreeDFS {
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

    private static int matches;

    private static int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int sum = left[0] + right[0] + node.val;
        int count = left[1] + right[1] + 1;
        if (sum / count == node.val) {
            matches++;
        }
        return new int[]{sum, count};
    }

    public static int averageOfSubtree(TreeNode root) {
        matches = 0;
        dfs(root);
        return matches;
    }

    public static void main(String[] args) {
        System.out.println("[4, 8, 5, 0, 1, null, 6] -> " + averageOfSubtree(build(4, 8, 5, 0, 1, null, 6)));
        System.out.println("[1] -> " + averageOfSubtree(build(1)));
    }
}
