import java.util.*;

/*
Problem: Recover Binary Search Tree
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
The values of exactly two nodes of a binary search tree were swapped by mistake.
    Recover the tree without changing its structure.

Why this pattern fits:
An inorder traversal of a valid BST is strictly increasing.
    Swapping two values creates one or two "drops" in that sequence, and those drops identify the two misplaced nodes.

Approach:
Traverse inorder while remembering the previous node.
    Whenever previous.val > current.val, a drop has been found.
    At the first drop, the misplaced larger node is previous. At every drop, the misplaced smaller node is current
    (so a second drop overrides the first guess).
    After the traversal, swap the values of the two recorded nodes.

Worked Example:
Example:
    Inorder of the broken tree: 1, 3, 2, 4 -> one drop (3 > 2). Swap 3 and 2.
    Inorder of another broken tree: 3, 2, 1 -> two drops (3 > 2 and 2 > 1).
    The first node is 3 (from the first drop) and the second node is 1 (from the second drop). Swap 3 and 1.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class P29_RecoverBinarySearchTreeBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static TreeNode previous;
    private static TreeNode first;
    private static TreeNode second;

    public static void recoverTree(TreeNode root) {
        previous = null;
        first = null;
        second = null;
        findSwapped(root);

        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    private static void findSwapped(TreeNode node) {
        if (node == null) {
            return;
        }
        findSwapped(node.left);

        if (previous != null && previous.val > node.val) {
            if (first == null) {
                first = previous;
            }
            second = node;
        }
        previous = node;

        findSwapped(node.right);
    }

    private static void inorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        inorder(node.left, values);
        values.add(node.val);
        inorder(node.right, values);
    }

    private static void runDemo(TreeNode root) {
        List<Integer> before = new ArrayList<>();
        inorder(root, before);
        recoverTree(root);
        List<Integer> after = new ArrayList<>();
        inorder(root, after);
        System.out.println("Inorder before: " + before);
        System.out.println("Inorder after : " + after);
        System.out.println();
    }

    public static void main(String[] args) {
        TreeNode adjacent = new TreeNode(3);
        adjacent.left = new TreeNode(1);
        adjacent.right = new TreeNode(4);
        adjacent.right.left = new TreeNode(2);
        runDemo(adjacent);

        TreeNode farApart = new TreeNode(1);
        farApart.left = new TreeNode(3);
        farApart.left.right = new TreeNode(2);
        runDemo(farApart);
    }
}
