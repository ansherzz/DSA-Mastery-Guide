import java.util.*;

/*
Problem: Binary Tree Right Side View
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Imagine standing on the right side of a binary tree.
    Return the values of the nodes you can see, ordered from top to bottom.

Why this pattern fits:
From the right side, you see exactly one node per level: the rightmost one.
    Level-order BFS processes each level left to right, so the last node polled in each round is the visible one.

Approach:
Run a level-order traversal.
    In each round, poll all nodes of the level and remember the last one.
    Add its value to the answer.

Worked Example:
Example:
            1
           / \
          2   3
           \   \
            5   4
    Rightmost node per level: 1, 3, 4.
    Note that if node 4 were missing, node 5 would be visible instead.

Time Complexity: O(n)
Space Complexity: O(width of the tree)
*/

public class BinaryTreeRightSideViewBFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> view = new ArrayList<>();
        if (root == null) {
            return view;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) {
                    view.add(node.val);
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return view;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);
        System.out.println("Right side view: " + rightSideView(root));

        TreeNode leftHeavy = new TreeNode(1);
        leftHeavy.left = new TreeNode(2);
        leftHeavy.right = new TreeNode(3);
        leftHeavy.left.left = new TreeNode(4);
        System.out.println("Right side view: " + rightSideView(leftHeavy));
    }
}
