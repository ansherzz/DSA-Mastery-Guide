import java.util.*;

/*
Problem: BST Keys in a Given Range
Pattern: 13. Binary Search Tree
Difficulty: Easy

Interview Prompt:
Given the root of a BST and two values low and high, return all keys in [low, high] in ascending order.

Why this pattern fits:
The BST property tells in advance when a subtree cannot contain anything in the range:
go left only if the node is greater than low, and right only if it is smaller than high.

Approach:
Recursive inorder: visit left when node.val > low, record the node when it is within the range,
visit right when node.val < high.

Worked Example:
Tree [8, 3, 10, 1, 6, null, 14, null, null, 4, 7, 13], range [5, 13] -> [6, 7, 8, 10, 13].

Time Complexity: O(h + k) for k reported keys
Space Complexity: O(h)
*/

public class P04_BSTKeysInRangeBST {
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

    private static void collect(TreeNode node, int low, int high, List<Integer> keys) {
        if (node == null) {
            return;
        }
        if (node.val > low) {
            collect(node.left, low, high, keys);
        }
        if (node.val >= low && node.val <= high) {
            keys.add(node.val);
        }
        if (node.val < high) {
            collect(node.right, low, high, keys);
        }
    }

    public static List<Integer> keysInRange(TreeNode root, int low, int high) {
        List<Integer> keys = new ArrayList<>();
        collect(root, low, high, keys);
        return keys;
    }

    public static void main(String[] args) {
        TreeNode root = build(8, 3, 10, 1, 6, null, 14, null, null, 4, 7, 13);
        System.out.println("range [5, 13] -> " + keysInRange(root, 5, 13));
        System.out.println("range [15, 20] -> " + keysInRange(root, 15, 20));
    }
}
