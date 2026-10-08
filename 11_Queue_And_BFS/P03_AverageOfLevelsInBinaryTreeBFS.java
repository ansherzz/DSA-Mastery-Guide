import java.util.*;

/*
Problem: Average of Levels in Binary Tree
Pattern: 11. Queue / BFS
Difficulty: Easy

Interview Prompt:
Given the root of a binary tree, return the average value of the nodes on each level,
    as a list ordered from the root level downward.

Why this pattern fits:
The answer is organized by level, and BFS with a queue visits a tree exactly one level at a time.
    Recording the queue size at the start of each round tells you how many nodes belong to that level.

Approach:
Run a level-order traversal.
    At the start of each round, note the queue size. Poll exactly that many nodes, summing their values
    and enqueueing their children. Divide the sum by the size and record the average.
    Use a long or double for the sum to avoid overflow.

Worked Example:
Example:
            3
           / \
          9  20
             / \
            15  7
    Level averages: 3.0, (9 + 20) / 2 = 14.5, (15 + 7) / 2 = 11.0

Time Complexity: O(n)
Space Complexity: O(width of the tree)
*/

public class P03_AverageOfLevelsInBinaryTreeBFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> averages = new ArrayList<>();
        if (root == null) {
            return averages;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            averages.add(sum / size);
        }
        return averages;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Averages: " + averageOfLevels(root));
    }
}
