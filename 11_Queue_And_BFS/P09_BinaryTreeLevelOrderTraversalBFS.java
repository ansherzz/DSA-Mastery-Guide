import java.util.*;

/*
Problem: Binary Tree Level Order Traversal
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Given the root of a binary tree, return its level order traversal from top to bottom.
    Nodes on the same depth should appear in the same inner list.

    This is the tree version of breadth-first search.

Why this pattern fits:
BFS processes one level at a time, which matches the requirement exactly.
    A queue naturally stores the frontier of the current level.

Approach:
Start with the root in a queue.
    For each level, process exactly queue.size() nodes, push their children, and record that level's values.

Worked Example:
Example:
        3
       /       9  20
        /         15   7
    The level order traversal is [[3], [9, 20], [15, 7]].

Time Complexity: O(n)
Space Complexity: O(w), where w is the maximum width of the tree
*/

public class P09_BinaryTreeLevelOrderTraversalBFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        if (root == null) {
            return answer;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            answer.add(level);
        }

        return answer;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(levelOrder(root));
    }
}
