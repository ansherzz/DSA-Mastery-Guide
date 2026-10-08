import java.util.*;

/*
Problem: Invert Binary Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
Given the root of a binary tree, invert the tree (mirror it left to right) and return its root.

Why this pattern fits:
Mirroring a tree means mirroring both subtrees and then swapping them.
    The problem is defined in terms of the same problem on smaller trees, which is recursion in its purest form.

Approach:
Base case: an empty tree is already inverted.
    Recursively invert the left and right subtrees, then swap the two child pointers of the current node.

Worked Example:
Example:
            4                    4
           / \                  / \
          2   7       ->       7   2
         / \ / \              / \ / \
        1  3 6  9            9  6 3  1

Time Complexity: O(n)
Space Complexity: O(h) recursion stack, where h is the height of the tree
*/

public class P05_InvertBinaryTreeDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode invertedLeft = invertTree(root.left);
        TreeNode invertedRight = invertTree(root.right);
        root.left = invertedRight;
        root.right = invertedLeft;
        return root;
    }

    private static List<Integer> levelOrder(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            values.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return values;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        System.out.println("Before (level order): " + levelOrder(root));
        System.out.println("After  (level order): " + levelOrder(invertTree(root)));
    }
}
