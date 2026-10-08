import java.util.*;

/*
Problem: Find Largest Value in Each Tree Row
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed),
    from the top row to the bottom row.

Why this pattern fits:
The answer is one aggregate per level. BFS with a level-size loop isolates each level,
    so any per-level statistic (sum, average, maximum, rightmost value) becomes a one-line change.

Approach:
Run a level-order traversal. For each level, start the maximum at the smallest possible integer,
    update it with every node of the level, and append it to the result.

Worked Example:
Example:
            1
           / \
          3   2
         / \    \
        5   3    9
    Largest values per row: [1, 3, 9].

Time Complexity: O(n)
Space Complexity: O(w), where w is the maximum width of the tree
*/

public class FindLargestValueInEachTreeRowBFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int largest = Integer.MIN_VALUE;
            for (int size = queue.size(); size > 0; size--) {
                TreeNode node = queue.poll();
                largest = Math.max(largest, node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(largest);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);

        System.out.println(largestValues(root));
        System.out.println(largestValues(null));
    }
}
