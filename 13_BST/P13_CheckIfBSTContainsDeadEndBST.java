import java.util.*;

/*
Problem: Check Whether BST Contains a Dead End
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
A BST contains positive integers only. A dead end is a leaf below which no new value can ever be inserted.
Return true if the BST contains a dead end.

Why this pattern fits:
Every node restricts the values that may appear in its subtrees. Passing that [low, high] range downward
(the same idea as BST validation) shows exactly when a position has no room left.

Approach:
dfs(node, low, high): if low == high, this node uses the only allowed value, so nothing can be inserted below it.
Recurse left with [low, val - 1] and right with [val + 1, high], starting from [1, infinity].

Worked Example:
[8, 5, 9, 2, 7, null, null, 1] -> the leaf 1 has range [1, 1]: nothing fits below it. Answer = true.

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P13_CheckIfBSTContainsDeadEndBST {
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

    private static boolean dfs(TreeNode node, int low, int high) {
        if (node == null) {
            return false;
        }
        if (low == high) {
            return true;
        }
        return dfs(node.left, low, node.val - 1) || dfs(node.right, node.val + 1, high);
    }

    public static boolean hasDeadEnd(TreeNode root) {
        return dfs(root, 1, Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
        System.out.println("[8, 5, 9, 2, 7, null, null, 1] -> " + hasDeadEnd(build(8, 5, 9, 2, 7, null, null, 1)));
        System.out.println("[8, 7, 10, 2, null, 9, 13] -> " + hasDeadEnd(build(8, 7, 10, 2, null, 9, 13)));
        System.out.println("[10, 5, 15] -> " + hasDeadEnd(build(10, 5, 15)));
    }
}
