import java.util.*;

/*
Problem: Binary Tree Pruning
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Every node of a binary tree is 0 or 1. Remove every subtree that does not contain a 1 and return the root.

Why this pattern fits:
Whether a node survives depends on its subtrees, so the decision is made bottom-up.
Returning the (possibly null) replacement node lets each parent rewire itself.

Approach:
node.left = prune(node.left); node.right = prune(node.right).
Return null if the node is 0 and both children are now null; otherwise return the node.

Worked Example:
[1, null, 0, 0, 1] -> the left 0 leaf is removed, leaving [1, null, 0, null, 1].

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P30_BinaryTreePruningDFS {
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

    public static TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println("[1, null, 0, 0, 1] -> " + format(pruneTree(build(1, null, 0, 0, 1))));
        System.out.println("[1, 0, 1, 0, 0, 0, 1] -> " + format(pruneTree(build(1, 0, 1, 0, 0, 0, 1))));
    }
}
