import java.util.*;

/*
Problem: Lowest Common Ancestor of Deepest Leaves
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.

Why this pattern fits:
The answer for a subtree is determined by the depths of its two children:
if one side is deeper, the deepest leaves all live there; if they tie, the current node joins them.

Approach:
DFS returns the height of the subtree and its candidate node.
Compare the left and right heights and return the deeper side's candidate, or the node itself on a tie.

Worked Example:
[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4] -> the deepest leaves 7 and 4 meet at node 2.

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P24_LowestCommonAncestorOfDeepestLeavesDFS {
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

    // Prints a tree in level order, using null for a missing child.
    private static String format(TreeNode root) {
        List<String> out = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                out.add("null");
                continue;
            }
            out.add(String.valueOf(node.val));
            queue.add(node.left);
            queue.add(node.right);
        }
        while (!out.isEmpty() && out.get(out.size() - 1).equals("null")) {
            out.remove(out.size() - 1);
        }
        return out.toString();
    }

    private static class Result {
        final int height;
        final TreeNode node;
        Result(int height, TreeNode node) {
            this.height = height;
            this.node = node;
        }
    }

    private static Result dfs(TreeNode node) {
        if (node == null) {
            return new Result(0, null);
        }
        Result left = dfs(node.left);
        Result right = dfs(node.right);
        if (left.height > right.height) {
            return new Result(left.height + 1, left.node);
        }
        if (right.height > left.height) {
            return new Result(right.height + 1, right.node);
        }
        return new Result(left.height + 1, node);
    }

    public static TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).node;
    }

    public static void main(String[] args) {
        System.out.println("[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4] -> "
                + format(lcaDeepestLeaves(build(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4))));
        System.out.println("[1] -> " + format(lcaDeepestLeaves(build(1))));
        System.out.println("[0, 1, 3, null, 2] -> " + format(lcaDeepestLeaves(build(0, 1, 3, null, 2))));
    }
}
