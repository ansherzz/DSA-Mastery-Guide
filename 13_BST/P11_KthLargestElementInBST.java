import java.util.*;

/*
Problem: Kth Largest Element in a BST
Pattern: 13. Binary Search Tree
Difficulty: Easy

Interview Prompt:
Given the root of a BST and an integer k, return the k-th largest value in the tree.

Why this pattern fits:
Inorder gives ascending order; the mirror traversal (right, node, left) gives descending order,
so the k-th node visited is the answer.

Approach:
Iterative reverse inorder with a stack: go right as far as possible, pop, count, then move to the left child.

Worked Example:
Tree keys in order: 1, 3, 4, 6, 7, 8, 10, 13, 14 -> the 3rd largest is 10.

Time Complexity: O(h + k)
Space Complexity: O(h)
*/

public class P11_KthLargestElementInBST {
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

    public static int kthLargest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.right;
            }
            current = stack.pop();
            if (--k == 0) {
                return current.val;
            }
            current = current.left;
        }
        return -1;
    }

    public static void main(String[] args) {
        TreeNode root = build(8, 3, 10, 1, 6, null, 14, null, null, 4, 7, 13);
        for (int k : new int[]{1, 3, 5}) {
            System.out.println("k = " + k + " -> " + kthLargest(root, k));
        }
    }
}
