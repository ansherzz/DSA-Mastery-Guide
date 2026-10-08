import java.util.*;

/*
Problem: Construct Binary Tree from Inorder and Postorder Traversal
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Given two integer arrays inorder and postorder, which are the inorder and postorder traversals
    of the same binary tree with unique values, construct and return the binary tree.

Why this pattern fits:
The last element of a postorder range is always the root of that subtree.
    Locating the root in the inorder array divides the remaining values into the left subtree and the right subtree.
    Each side is then the same problem on a smaller range.

Approach:
Store the index of every value in the inorder array in a hash map.
    Read the postorder array from the end with a shared index: each value read is the root of the current range.
    Because postorder is left, right, root, reading backward yields root, right, left,
    so build the right subtree before the left subtree.

Worked Example:
Example:
    inorder   = [9, 3, 15, 20, 7]
    postorder = [9, 15, 7, 20, 3]
    The last postorder value 3 is the root. In inorder, 9 is to its left and 15, 20, 7 are to its right.
            3
           / \
          9  20
             / \
            15  7

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P23_ConstructBinaryTreeFromInorderAndPostorderDivideConquer {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static int postIndex;

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inorderIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndex.put(inorder[i], i);
        }

        postIndex = postorder.length - 1;
        return build(postorder, inorderIndex, 0, inorder.length - 1);
    }

    private static TreeNode build(int[] postorder, Map<Integer, Integer> inorderIndex, int left, int right) {
        if (left > right) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postIndex--]);
        int split = inorderIndex.get(root.val);

        root.right = build(postorder, inorderIndex, split + 1, right);
        root.left = build(postorder, inorderIndex, left, split - 1);
        return root;
    }

    private static void preorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        values.add(node.val);
        preorder(node.left, values);
        preorder(node.right, values);
    }

    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        List<Integer> values = new ArrayList<>();
        preorder(buildTree(inorder, postorder), values);

        System.out.println("Inorder   : " + Arrays.toString(inorder));
        System.out.println("Postorder : " + Arrays.toString(postorder));
        System.out.println("Preorder of rebuilt tree: " + values);
    }
}
