import java.util.*;

/*
Problem: Boundary of Binary Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Return the boundary of a binary tree in anti-clockwise order starting from the root:
the left boundary, then the leaves, then the right boundary in reverse. No node may appear twice.

Why this pattern fits:
The boundary is not a single standard traversal, but each of its three parts is a simple one.
The care is in excluding leaves from the two edges so nothing is duplicated.

Approach:
Add the root. Walk the left edge from root.left (prefer left, else right), adding non-leaf nodes.
DFS for all leaves (skipping the root if it is one). Walk the right edge from root.right
(prefer right, else left), collecting non-leaf nodes and appending them in reverse.

Worked Example:
[1, null, 2, 3, 4] -> root 1, no left edge, leaves 3 and 4, right edge 2. Answer = [1, 3, 4, 2].

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P43_BoundaryOfBinaryTreeDFS {
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

    private static boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    private static void addLeaves(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        if (isLeaf(node)) {
            result.add(node.val);
            return;
        }
        addLeaves(node.left, result);
        addLeaves(node.right, result);
    }

    public static List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        result.add(root.val);
        if (isLeaf(root)) {
            return result;
        }
        for (TreeNode node = root.left; node != null && !isLeaf(node); node = node.left != null ? node.left : node.right) {
            result.add(node.val);
        }
        addLeaves(root, result);
        Deque<Integer> rightEdge = new ArrayDeque<>();
        for (TreeNode node = root.right; node != null && !isLeaf(node); node = node.right != null ? node.right : node.left) {
            rightEdge.push(node.val);
        }
        result.addAll(rightEdge);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("[1, null, 2, 3, 4] -> " + boundaryOfBinaryTree(build(1, null, 2, 3, 4)));
        System.out.println("[1, 2, 3, 4, 5, 6, null, null, null, 7, 8, 9, 10] -> "
                + boundaryOfBinaryTree(build(1, 2, 3, 4, 5, 6, null, null, null, 7, 8, 9, 10)));
    }
}
