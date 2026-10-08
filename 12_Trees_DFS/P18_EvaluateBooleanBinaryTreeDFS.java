import java.util.*;

/*
Problem: Evaluate Boolean Binary Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
In a full binary tree, leaves hold 0 (false) or 1 (true) and internal nodes hold 2 (OR) or 3 (AND).
Return the boolean result of evaluating the root.

Why this pattern fits:
A node's value depends only on the results of its children: the definition of a postorder (bottom-up) computation.

Approach:
If the node is a leaf return its value. Otherwise evaluate left and right recursively and apply OR or AND.

Worked Example:
[2, 1, 3, null, null, 0, 1] -> the AND node gives false, and true OR false = true.

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P18_EvaluateBooleanBinaryTreeDFS {
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

    public static boolean evaluateTree(TreeNode root) {
        if (root.left == null) {
            return root.val == 1;
        }
        boolean left = evaluateTree(root.left);
        boolean right = evaluateTree(root.right);
        return root.val == 2 ? left || right : left && right;
    }

    public static void main(String[] args) {
        System.out.println("[2, 1, 3, null, null, 0, 1] -> " + evaluateTree(build(2, 1, 3, null, null, 0, 1)));
        System.out.println("[0] -> " + evaluateTree(build(0)));
    }
}
