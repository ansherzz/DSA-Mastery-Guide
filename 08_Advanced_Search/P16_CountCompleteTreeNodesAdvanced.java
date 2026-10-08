import java.util.*;

/*
Problem: Count Complete Tree Nodes
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
Given the root of a complete binary tree, return the number of nodes.
    In a complete binary tree every level except possibly the last is full,
    and the nodes of the last level are as far left as possible.

    Design an algorithm that runs in less than O(n) time.

Why this pattern fits:
All levels above the last are full, so their size is known from the depth alone.
    The only unknown is how many nodes the last level has. Because those nodes are packed to the left,
    "does the node at position p exist?" is true for a prefix of positions, which can be binary searched.

Approach:
Compute the depth d by walking down the left edge. The upper levels contain 2^d - 1 nodes.
    The last level has positions 0 .. 2^d - 1. To test a position, walk from the root for d steps,
    going left or right depending on which half of the remaining range the position falls in.
    Binary search for the last existing position and add its count.

Worked Example:
Example:
    Tree in level order: [1, 2, 3, 4, 5, 6]
    Depth is 2, so the upper levels hold 3 nodes. The last level has positions 0..3.
    Positions 0, 1, 2 exist and position 3 does not. Total = 3 + 3 = 6.

Time Complexity: O(log^2 n)
Space Complexity: O(1)
*/

public class P16_CountCompleteTreeNodesAdvanced {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int depth = 0;
        for (TreeNode node = root; node.left != null; node = node.left) {
            depth++;
        }
        if (depth == 0) {
            return 1;
        }

        int left = 0;
        int right = (1 << depth) - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (exists(root, depth, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return (1 << depth) - 1 + left + 1;
    }

    private static boolean exists(TreeNode root, int depth, int position) {
        int low = 0;
        int high = (1 << depth) - 1;
        TreeNode node = root;

        for (int step = 0; step < depth; step++) {
            int mid = low + (high - low) / 2;
            if (position <= mid) {
                node = node.left;
                high = mid;
            } else {
                node = node.right;
                low = mid + 1;
            }
        }
        return node != null;
    }

    private static TreeNode buildComplete(int nodeCount) {
        if (nodeCount == 0) {
            return null;
        }
        TreeNode[] nodes = new TreeNode[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            nodes[i] = new TreeNode(i + 1);
        }
        for (int i = 0; i < nodeCount; i++) {
            if (2 * i + 1 < nodeCount) {
                nodes[i].left = nodes[2 * i + 1];
            }
            if (2 * i + 2 < nodeCount) {
                nodes[i].right = nodes[2 * i + 2];
            }
        }
        return nodes[0];
    }

    public static void main(String[] args) {
        for (int size : new int[]{0, 1, 6, 7, 12}) {
            System.out.println("Built with " + size + " nodes -> counted " + countNodes(buildComplete(size)));
        }
    }
}
