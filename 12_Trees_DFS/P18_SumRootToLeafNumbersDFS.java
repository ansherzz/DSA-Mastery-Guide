import java.util.*;

/*
Problem: Sum Root to Leaf Numbers
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
You are given a binary tree whose nodes contain digits from 0 to 9.
    Each root-to-leaf path represents a number, formed by reading the digits from the root down.
    Return the total sum of all root-to-leaf numbers.

Why this pattern fits:
The number built so far is state that flows from parent to child.
    DFS passes it down as a parameter, and each leaf contributes its finished number to the total on the way back up.

Approach:
Carry the number formed so far. At each node, compute current = previous * 10 + node.val.
    If the node is a leaf, return current.
    Otherwise return the sum of the results from the left and right subtrees.

Worked Example:
Example:
            4
           / \
          9   0
         / \
        5   1
    Paths: 4 -> 9 -> 5 = 495, 4 -> 9 -> 1 = 491, 4 -> 0 = 40.
    Total = 495 + 491 + 40 = 1026.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class P18_SumRootToLeafNumbersDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int sumNumbers(TreeNode root) {
        return sum(root, 0);
    }

    private static int sum(TreeNode node, int numberSoFar) {
        if (node == null) {
            return 0;
        }

        int current = numberSoFar * 10 + node.val;
        if (node.left == null && node.right == null) {
            return current;
        }
        return sum(node.left, current) + sum(node.right, current);
    }

    public static void main(String[] args) {
        TreeNode small = new TreeNode(1);
        small.left = new TreeNode(2);
        small.right = new TreeNode(3);
        System.out.println("Total: " + sumNumbers(small));

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);
        System.out.println("Total: " + sumNumbers(root));
    }
}
