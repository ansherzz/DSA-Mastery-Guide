import java.util.*;

/*
Problem: Floor and Ceil in a BST
Pattern: 13. Binary Search Tree
Difficulty: Easy

Interview Prompt:
Given the root of a binary search tree and a key, return the floor and the ceil of the key in the tree.
    The floor is the largest value less than or equal to the key.
    The ceil is the smallest value greater than or equal to the key.
    Return null for a floor or ceil that does not exist.

Why this pattern fits:
The BST ordering tells you, at every node, which side can still improve the answer.
    Each comparison both records a candidate and discards half of the remaining tree.

Approach:
Walk down from the root.
    If the node's value equals the key, it is both the floor and the ceil.
    If the node's value is smaller than the key, it is a floor candidate; anything better must be larger, so go right.
    If the node's value is larger than the key, it is a ceil candidate; anything better must be smaller, so go left.
    The last candidates recorded are the answers.

Worked Example:
Example:
            8
           / \
          4   12
         / \  / \
        2  6 10  14
    key = 5: floor = 4, ceil = 6. key = 15: floor = 14, ceil does not exist.

Time Complexity: O(h)
Space Complexity: O(1)
*/

public class P08_FloorAndCeilInBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static Integer[] floorAndCeil(TreeNode root, int key) {
        Integer floor = null;
        Integer ceil = null;

        TreeNode node = root;
        while (node != null) {
            if (node.val == key) {
                return new Integer[]{key, key};
            }
            if (node.val < key) {
                floor = node.val;
                node = node.right;
            } else {
                ceil = node.val;
                node = node.left;
            }
        }
        return new Integer[]{floor, ceil};
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
        TreeNode root = buildBST(8, 4, 12, 2, 6, 10, 14);
        for (int key : new int[]{5, 12, 1, 15}) {
            System.out.println("key = " + key + " -> " + Arrays.toString(floorAndCeil(root, key)));
        }
    }
}
