import java.util.*;

/*
Problem: Closest Binary Search Tree Value II
Pattern: 13. Binary Search Tree
Difficulty: Hard

Interview Prompt:
Given the root of a binary search tree, a target value, and an integer k,
    return the k values in the BST that are closest to the target. You may return the answer in any order.
    It is guaranteed that there is only one unique set of k closest values.

Why this pattern fits:
In-order traversal delivers the values in sorted order, and in a sorted sequence
    the k closest values to a target always form a contiguous window.
    That window can be maintained on the fly while the traversal runs.

Approach:
Traverse in-order while keeping a deque of at most k values (the current window).
    If the window has fewer than k values, append the new value.
    Otherwise compare the new value with the oldest value in the window:
    if the new value is closer to the target, drop the oldest and append the new one.
    If it is not closer, every later value is even farther away (they only grow), so stop the traversal.

Worked Example:
Example:
            4
           / \
          2   5
         / \
        1   3
    target = 3.714286, k = 2. In-order: 1, 2, 3, 4, 5.
    Window: [1, 2] -> [2, 3] -> [3, 4]. Then 5 is not closer than 3, so stop. Answer: [3, 4].

Time Complexity: O(n) worst case, with early termination
Space Complexity: O(h + k)
*/

public class ClosestBinarySearchTreeValueIIBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<Integer> closestKValues(TreeNode root, double target, int k) {
        Deque<Integer> window = new ArrayDeque<>();
        inorder(root, target, k, window);
        return new ArrayList<>(window);
    }

    private static boolean inorder(TreeNode node, double target, int k, Deque<Integer> window) {
        if (node == null) {
            return true;
        }
        if (!inorder(node.left, target, k, window)) {
            return false;
        }

        if (window.size() < k) {
            window.offerLast(node.val);
        } else if (Math.abs(node.val - target) < Math.abs(window.peekFirst() - target)) {
            window.pollFirst();
            window.offerLast(node.val);
        } else {
            return false;
        }

        return inorder(node.right, target, k, window);
    }

    private static TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.val) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }
        return root;
    }

    private static TreeNode buildBST(int... values) {
        TreeNode root = null;
        for (int value : values) {
            root = insert(root, value);
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = buildBST(4, 2, 5, 1, 3);
        System.out.println(closestKValues(root, 3.714286, 2));
        System.out.println(closestKValues(root, 0.0, 3));
        System.out.println(closestKValues(buildBST(1), 0.0, 1));
    }
}
