import java.util.*;

/*
Problem: Height of Binary Tree After Subtree Removal Queries
Pattern: 12. Trees & Recursion - DFS
Difficulty: Hard

Interview Prompt:
Node values are unique (1..n). For each query q, return the height of the tree after removing
the subtree rooted at node q (never the root). Queries are independent.

Why this pattern fits:
After removing a node, the tallest remaining path either passes through another node on the same level
or stops at the level above. So only the best two heights per level are needed to answer any query in O(1).

Approach:
One DFS computes depth[v] and height[v] (edges below v) and updates the top two heights of v's level.
For a query: use the level's best height unless it belongs to q, in which case use the second best.
Answer = depth + that height (a missing second best acts as -1).

Worked Example:
[1, 3, 4, 2, null, 6, 5, null, null, null, null, null, 7], query 4 -> the remaining path 1 -> 3 -> 2 has height 2.

Time Complexity: O(n + q)
Space Complexity: O(n)
*/

public class P50_HeightOfBinaryTreeAfterSubtreeRemovalQueriesDFS {
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

    private static int dfs(TreeNode node, int level, int[] depth, int[] height, int[][] topTwo) {
        if (node == null) {
            return -1;
        }
        int h = 1 + Math.max(dfs(node.left, level + 1, depth, height, topTwo),
                dfs(node.right, level + 1, depth, height, topTwo));
        depth[node.val] = level;
        height[node.val] = h;
        if (h > topTwo[level][0]) {
            topTwo[level][1] = topTwo[level][0];
            topTwo[level][0] = h;
        } else if (h > topTwo[level][1]) {
            topTwo[level][1] = h;
        }
        return h;
    }

    private static int countNodes(TreeNode node) {
        return node == null ? 0 : 1 + countNodes(node.left) + countNodes(node.right);
    }

    public static int[] treeQueries(TreeNode root, int[] queries) {
        int n = countNodes(root);
        int[] depth = new int[n + 1];
        int[] height = new int[n + 1];
        int[][] topTwo = new int[n + 1][2];
        for (int[] pair : topTwo) {
            Arrays.fill(pair, -1);
        }
        dfs(root, 0, depth, height, topTwo);
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int q = queries[i];
            int level = depth[q];
            int other = height[q] == topTwo[level][0] ? topTwo[level][1] : topTwo[level][0];
            answer[i] = level + other;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println("query [4] -> " + Arrays.toString(
                treeQueries(build(1, 3, 4, 2, null, 6, 5, null, null, null, null, null, 7), new int[]{4})));
        System.out.println("queries [3, 2, 4, 8] -> " + Arrays.toString(
                treeQueries(build(5, 8, 9, 2, 1, 3, 7, 4, 6), new int[]{3, 2, 4, 8})));
    }
}
