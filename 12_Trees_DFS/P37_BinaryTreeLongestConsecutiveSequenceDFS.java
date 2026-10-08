import java.util.*;

/*
Problem: Binary Tree Longest Consecutive Sequence
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Return the length of the longest path of consecutive increasing values (each child = parent + 1)
that goes downward from some node, following parent-to-child links.

Why this pattern fits:
Whether a node extends the streak depends only on its parent, which is information flowing down the tree.

Approach:
dfs(node, parentValue, length): the new length is length + 1 when node.val == parentValue + 1, otherwise 1.
Track the maximum length seen.

Worked Example:
[1, null, 3, 2, 4, null, null, null, 5] -> the path 3 -> 4 -> 5 has length 3.

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P37_BinaryTreeLongestConsecutiveSequenceDFS {
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

    private static int longest;

    private static void dfs(TreeNode node, int parentValue, int length) {
        if (node == null) {
            return;
        }
        length = node.val == parentValue + 1 ? length + 1 : 1;
        longest = Math.max(longest, length);
        dfs(node.left, node.val, length);
        dfs(node.right, node.val, length);
    }

    public static int longestConsecutive(TreeNode root) {
        longest = 0;
        dfs(root, Integer.MIN_VALUE, 0);
        return longest;
    }

    public static void main(String[] args) {
        System.out.println("[1, null, 3, 2, 4, null, null, null, 5] -> "
                + longestConsecutive(build(1, null, 3, 2, 4, null, null, null, 5)));
        System.out.println("[2, null, 3, 2, null, 1] -> " + longestConsecutive(build(2, null, 3, 2, null, 1)));
    }
}
