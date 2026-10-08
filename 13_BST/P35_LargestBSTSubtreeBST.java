import java.util.*;

/*
Problem: Largest BST Subtree
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given the root of a binary tree (not necessarily a BST), find the largest subtree that is a valid
    binary search tree, where largest means the most nodes. Return its size.
    A subtree must include all descendants of its root.

    Follow-up: solve it in O(n) time.

Why this pattern fits:
Checking every subtree with a separate validation costs O(n^2).
    A post-order traversal avoids that: each node receives a summary from its children
    (is it a BST, its size, its minimum, and its maximum) and can decide in O(1) whether it forms a BST too.

Approach:
Each call returns {isBst, size, min, max} for its subtree.
    A node forms a BST when both children are BSTs, the left maximum is smaller than the node's value,
    and the right minimum is larger. Then its size is left size + right size + 1.
    Track the largest BST size seen. Empty subtrees are BSTs of size 0 with min = +infinity and max = -infinity.

Worked Example:
Example:
             10
            /  \
           5    15
          / \     \
         1   8     7
    The subtree rooted at 5 (nodes 5, 1, 8) is a BST of size 3.
    The subtree rooted at 15 is not a BST because 7 is on its right, so the whole tree is not a BST either.
    Answer = 3.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class P35_LargestBSTSubtreeBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static class Summary {
        final boolean isBst;
        final int size;
        final long min;
        final long max;
        Summary(boolean isBst, int size, long min, long max) {
            this.isBst = isBst;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    private static int best;

    public static int largestBSTSubtree(TreeNode root) {
        best = 0;
        summarize(root);
        return best;
    }

    private static Summary summarize(TreeNode node) {
        if (node == null) {
            return new Summary(true, 0, Long.MAX_VALUE, Long.MIN_VALUE);
        }

        Summary left = summarize(node.left);
        Summary right = summarize(node.right);

        if (left.isBst && right.isBst && left.max < node.val && node.val < right.min) {
            int size = left.size + right.size + 1;
            best = Math.max(best, size);
            return new Summary(true, size, Math.min(left.min, node.val), Math.max(right.max, node.val));
        }
        return new Summary(false, 0, 0, 0);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right.right = new TreeNode(7);
        System.out.println("Largest BST subtree size: " + largestBSTSubtree(root));

        root.right.right.val = 20;
        System.out.println("After fixing the bad node: " + largestBSTSubtree(root));
    }
}
