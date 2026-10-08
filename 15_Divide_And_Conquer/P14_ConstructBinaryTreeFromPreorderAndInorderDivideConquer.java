import java.util.*;

/*
Problem: Construct Binary Tree from Preorder and Inorder Traversal
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Given preorder and inorder traversal arrays of a binary tree with unique values, reconstruct the tree.

    This is a classic recursive reconstruction problem.

Why this pattern fits:
Preorder tells you the root first.
    Inorder tells you how the remaining nodes split into left and right subtrees.

Approach:
Use a hashmap from value to inorder index.
    Recursively build the tree by consuming preorder roots and slicing the inorder segment for each subtree.

Worked Example:
Example:
    preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    Reconstruct the original tree.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P14_ConstructBinaryTreeFromPreorderAndInorderDivideConquer {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static int preorderIndex;
    private static Map<Integer, Integer> inorderPosition;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        inorderPosition = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderPosition.put(inorder[i], i);
        }
        return build(preorder, 0, inorder.length - 1);
    }

    private static TreeNode build(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }

        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);
        int mid = inorderPosition.get(rootValue);

        root.left = build(preorder, left, mid - 1);
        root.right = build(preorder, mid + 1, right);
        return root;
    }

    private static void inorderPrint(TreeNode node, List<Integer> values) {
        if (node == null) return;
        inorderPrint(node.left, values);
        values.add(node.val);
        inorderPrint(node.right, values);
    }

    public static void main(String[] args) {
        TreeNode root = buildTree(
            new int[]{3, 9, 20, 15, 7},
            new int[]{9, 3, 15, 20, 7}
        );

        List<Integer> values = new ArrayList<>();
        inorderPrint(root, values);
        System.out.println(values);
    }
}
