import java.util.*;

/*
Problem: Minimum Depth of Binary Tree
Pattern: 11. Queue / BFS
Difficulty: Easy

Interview Prompt:
Given the root of a binary tree, return its minimum depth:
    the number of nodes along the shortest path from the root down to the nearest leaf.
    A leaf is a node with no children.

Why this pattern fits:
BFS explores nodes in order of distance from the root.
    The first leaf it reaches is therefore the nearest leaf, and the search can stop immediately.
    DFS would have to explore the whole tree to be sure.

Approach:
Run a level-order traversal while counting levels, starting at depth 1.
    As soon as a polled node has no left and no right child, return the current depth.
    Otherwise enqueue its children and continue.

Worked Example:
Example:
            3
           / \
          9  20
             / \
            15  7
    Node 9 is a leaf at depth 2, and it is reached before any deeper node. Answer = 2.

Time Complexity: O(n) in the worst case, often much less
Space Complexity: O(width of the tree)
*/

public class MinimumDepthOfBinaryTreeBFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int depth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            depth++;
        }
        return depth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println("Min depth: " + minDepth(root));

        TreeNode skewed = new TreeNode(2);
        skewed.right = new TreeNode(3);
        skewed.right.right = new TreeNode(4);
        skewed.right.right.right = new TreeNode(5);
        System.out.println("Min depth of a right-skewed chain: " + minDepth(skewed));
    }
}
