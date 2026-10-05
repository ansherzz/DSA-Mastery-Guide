import java.util.*;

/*
Problem: Range Sum of BST
Pattern: 13. Binary Search Tree
Difficulty: Easy

Interview Prompt:
Given the root of a binary search tree and two integers low and high,
    return the sum of the values of all nodes whose value lies in the inclusive range [low, high].

Why this pattern fits:
A plain traversal would visit every node. The BST ordering lets you prune:
    if a node's value is already below low, its entire left subtree is also below low and can be skipped,
    and symmetrically for values above high.

Approach:
At each node:
    if the value is less than low, only the right subtree can contain values in range;
    if the value is greater than high, only the left subtree can;
    otherwise count the node and explore both sides.

Worked Example:
Example:
             10
            /  \
           5    15
          / \     \
         3   7     18
    low = 7, high = 15: the nodes in range are 7, 10, and 15. Sum = 32.
    Node 3 is never visited, because at node 5 (which is below 7) the left subtree is pruned.

Time Complexity: O(n) in the worst case, less when subtrees are pruned
Space Complexity: O(h) recursion stack
*/

public class RangeSumOfBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);

        System.out.println("Sum in [7, 15]: " + rangeSumBST(root, 7, 15));
        System.out.println("Sum in [1, 100]: " + rangeSumBST(root, 1, 100));
        System.out.println("Sum in [11, 14]: " + rangeSumBST(root, 11, 14));
    }
}
