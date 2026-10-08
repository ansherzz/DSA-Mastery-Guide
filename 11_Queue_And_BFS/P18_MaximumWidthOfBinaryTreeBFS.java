import java.util.*;

/*
Problem: Maximum Width of Binary Tree
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Given the root of a binary tree, return its maximum width.
    The width of a level is the distance between its leftmost and rightmost non-null nodes,
    counting the null positions between them as if the level belonged to a complete binary tree.

Why this pattern fits:
Width is a per-level measurement, so BFS is the natural traversal.
    To count the gaps, give each node the index it would have in a complete binary tree:
    the children of index i are 2i and 2i + 1. The width of a level is last index - first index + 1.

Approach:
Run a level-order traversal over (node, index) pairs.
    For each level, record the index of the first node, and subtract it from every index in that level
    before computing child indices. This keeps the numbers small even in very deep, skewed trees.
    The level width is the last normalized index + 1.

Worked Example:
Example:
            1
           / \
          3   2
         / \   \
        5   3   9
    Bottom level indices are 0, 1, and 3 (the missing left child of 2 would be index 2).
    Width = 3 - 0 + 1 = 4.

Time Complexity: O(n)
Space Complexity: O(width of the tree)
*/

public class P18_MaximumWidthOfBinaryTreeBFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static class Positioned {
        final TreeNode node;
        final long index;
        Positioned(TreeNode node, long index) {
            this.node = node;
            this.index = index;
        }
    }

    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<Positioned> queue = new ArrayDeque<>();
        queue.offer(new Positioned(root, 0));
        long best = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            long first = queue.peek().index;
            long last = first;

            for (int i = 0; i < size; i++) {
                Positioned current = queue.poll();
                long index = current.index - first;
                last = index;

                if (current.node.left != null) {
                    queue.offer(new Positioned(current.node.left, 2 * index));
                }
                if (current.node.right != null) {
                    queue.offer(new Positioned(current.node.right, 2 * index + 1));
                }
            }
            best = Math.max(best, last + 1);
        }
        return (int) best;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);
        System.out.println("Max width: " + widthOfBinaryTree(root));

        TreeNode sparse = new TreeNode(1);
        sparse.left = new TreeNode(3);
        sparse.right = new TreeNode(2);
        sparse.left.left = new TreeNode(5);
        sparse.right.right = new TreeNode(9);
        sparse.left.left.left = new TreeNode(6);
        sparse.right.right.left = new TreeNode(7);
        System.out.println("Max width: " + widthOfBinaryTree(sparse));
    }
}
