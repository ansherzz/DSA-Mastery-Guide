import java.util.*;

/*
Problem: Binary Tree Zigzag Level Order Traversal
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Given the root of a binary tree, return the zigzag level order traversal of its node values:
    the first level from left to right, the next level from right to left, and alternating after that.

Why this pattern fits:
This is level-order traversal with a presentation twist.
    The traversal itself stays a normal BFS; only the order in which a level's values are written changes.

Approach:
Run a standard level-order traversal with a queue.
    For each level, fill a fixed-size array: write at index i when going left to right,
    or at index size - 1 - i when going right to left.
    Flip the direction after every level.

Worked Example:
Example:
            3
           / \
          9  20
             / \
            15  7
    Level 0 left to right: [3]
    Level 1 right to left: [20, 9]
    Level 2 left to right: [15, 7]

Time Complexity: O(n)
Space Complexity: O(width of the tree)
*/

public class BinaryTreeZigzagLevelOrderBFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        if (root == null) {
            return answer;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            Integer[] level = new Integer[size];

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level[leftToRight ? i : size - 1 - i] = node.val;

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            answer.add(Arrays.asList(level));
            leftToRight = !leftToRight;
        }
        return answer;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(zigzagLevelOrder(root));
    }
}
