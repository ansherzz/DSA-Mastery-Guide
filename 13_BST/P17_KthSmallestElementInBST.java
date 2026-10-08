import java.util.*;

/*
Problem: Kth Smallest Element in a BST
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given the root of a BST and an integer k, return the kth smallest value in the tree.

    Inorder traversal of a BST visits values in ascending order.

Why this pattern fits:
This is where BST ordering and DFS traversal meet.
    You do not need sorting because the tree already stores the order structurally.

Approach:
Perform inorder traversal and count visited nodes.
    The kth node visited is the answer.

Worked Example:
Example:
    In the BST [3,1,4,null,2], k = 1
    The answer is 1.

Time Complexity: O(h + k) average, O(n) worst case
Space Complexity: O(h)
*/

public class P17_KthSmallestElementInBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static int count;
    private static int answer;

    public static int kthSmallest(TreeNode root, int k) {
        count = 0;
        answer = -1;
        inorder(root, k);
        return answer;
    }

    private static void inorder(TreeNode node, int k) {
        if (node == null || count >= k) {
            return;
        }

        inorder(node.left, k);
        count++;
        if (count == k) {
            answer = node.val;
            return;
        }
        inorder(node.right, k);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        System.out.println("kth smallest: " + kthSmallest(root, 1));
    }
}
