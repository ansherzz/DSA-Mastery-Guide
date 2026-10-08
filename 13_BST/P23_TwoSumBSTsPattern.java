import java.util.*;

/*
Problem: Two Sum BSTs
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given the roots of two binary search trees, root1 and root2, return true if and only if there is a node
    in the first tree and a node in the second tree whose values sum up to a given integer target.

Why this pattern fits:
Each tree supports an O(h) search for a specific value.
    For every value in the first tree, the needed partner in the second tree is known exactly,
    so the second tree is used for what a BST does best: lookup.

Approach:
Traverse the first tree. For each node, search the second tree for target - node.val using the BST ordering.
    Return true as soon as one search succeeds.
    Alternative: in-order both trees into sorted lists and run two pointers for O(n + m) time with O(n + m) space.

Worked Example:
Example:
    root1 = BST of [2, 1, 4], root2 = BST of [1, 0, 3], target = 5
    2 from the first tree pairs with 3 from the second tree. Answer: true.

Time Complexity: O(n * h2), where h2 is the height of the second tree
Space Complexity: O(h1) recursion stack
*/

public class P23_TwoSumBSTsPattern {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null) {
            return false;
        }
        return contains(root2, target - root1.val)
            || twoSumBSTs(root1.left, root2, target)
            || twoSumBSTs(root1.right, root2, target);
    }

    private static boolean contains(TreeNode node, int value) {
        while (node != null) {
            if (node.val == value) {
                return true;
            }
            node = value < node.val ? node.left : node.right;
        }
        return false;
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
        System.out.println(twoSumBSTs(buildBST(2, 1, 4), buildBST(1, 0, 3), 5));
        System.out.println(twoSumBSTs(buildBST(0, -10, 10), buildBST(5, 1, 7, 0, 2), 18));
    }
}
