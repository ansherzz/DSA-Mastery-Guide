import java.util.*;

/*
Problem: Balance a Binary Search Tree
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given the root of a binary search tree, return a balanced binary search tree with the same node values.
    A tree is balanced if the depths of the two subtrees of every node never differ by more than 1.
    If several answers exist, return any of them.

Why this pattern fits:
Two BST facts combine here. An inorder traversal turns any BST into a sorted list,
    and a sorted list can be turned into a balanced BST by always choosing the middle element as the root.

Approach:
Collect the values with an inorder traversal.
    Build a new tree recursively: pick the middle index of the current range as the root,
    then build the left subtree from the left half and the right subtree from the right half.

Worked Example:
Example:
    Input (a right-leaning chain): 1 -> 2 -> 3 -> 4
    Sorted values: [1, 2, 3, 4]
    Choosing middles gives a root of 2 with left child 1 and right child 3, which has right child 4.
    The height drops from 4 to 3.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class BalanceBinarySearchTreeBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode balanceBST(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        inorder(root, values);
        return build(values, 0, values.size() - 1);
    }

    private static TreeNode build(List<Integer> values, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(values.get(mid));
        node.left = build(values, left, mid - 1);
        node.right = build(values, mid + 1, right);
        return node;
    }

    private static void inorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        inorder(node.left, values);
        values.add(node.val);
        inorder(node.right, values);
    }

    private static int height(TreeNode node) {
        return node == null ? 0 : 1 + Math.max(height(node.left), height(node.right));
    }

    public static void main(String[] args) {
        TreeNode chain = new TreeNode(1);
        TreeNode tail = chain;
        for (int value = 2; value <= 7; value++) {
            tail.right = new TreeNode(value);
            tail = tail.right;
        }

        TreeNode balanced = balanceBST(chain);
        List<Integer> values = new ArrayList<>();
        inorder(balanced, values);

        System.out.println("Height before : " + height(chain));
        System.out.println("Height after  : " + height(balanced));
        System.out.println("New root      : " + balanced.val);
        System.out.println("Inorder after : " + values);
    }
}
