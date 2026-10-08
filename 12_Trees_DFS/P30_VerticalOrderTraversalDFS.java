import java.util.*;

/*
Problem: Vertical Order Traversal of a Binary Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Hard

Interview Prompt:
Given the root of a binary tree, calculate the vertical order traversal of the tree.
    The root is at position (row 0, column 0). The left child of a node at (row, col) is at (row + 1, col - 1)
    and the right child is at (row + 1, col + 1).
    The traversal lists the columns from leftmost to rightmost. Within a column, nodes are ordered from top to bottom.
    If several nodes share the same row and the same column, they are ordered by value.

Why this pattern fits:
The coordinates of a node are fully determined by the path taken from the root,
    so a DFS that passes (row, col) downward can label every node.
    After labeling, the traversal order is nothing more than a three-key sort.

Approach:
Run a DFS that records a triple (col, row, value) for every node.
    Sort the triples by column, then by row, then by value.
    Sweep the sorted list and start a new group every time the column changes.

Worked Example:
Example:
            1
           / \
          2   3
         / \ / \
        4  5 6  7
    Nodes 5 and 6 are both at (row 2, column 0), so they are ordered by value.
    Result: [[4], [2], [1, 5, 6], [3], [7]].

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P30_VerticalOrderTraversalDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<int[]> triples = new ArrayList<>();
        dfs(root, 0, 0, triples);

        triples.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[2], b[2]);
        });

        List<List<Integer>> result = new ArrayList<>();
        int currentCol = Integer.MIN_VALUE;
        for (int[] triple : triples) {
            if (triple[0] != currentCol) {
                currentCol = triple[0];
                result.add(new ArrayList<>());
            }
            result.get(result.size() - 1).add(triple[2]);
        }
        return result;
    }

    private static void dfs(TreeNode node, int row, int col, List<int[]> triples) {
        if (node == null) {
            return;
        }
        triples.add(new int[]{col, row, node.val});
        dfs(node.left, row + 1, col - 1, triples);
        dfs(node.right, row + 1, col + 1, triples);
    }

    private static TreeNode build(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(values[0]);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int i = 1;
        while (i < values.length) {
            TreeNode parent = queue.poll();
            if (values[i] != null) {
                parent.left = new TreeNode(values[i]);
                queue.offer(parent.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                parent.right = new TreeNode(values[i]);
                queue.offer(parent.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println(verticalTraversal(build(3, 9, 20, null, null, 15, 7)));
        System.out.println(verticalTraversal(build(1, 2, 3, 4, 5, 6, 7)));
        System.out.println(verticalTraversal(build(1, 2, 3, 4, 6, 5, 7)));
    }
}
