import java.util.*;

/*
Problem: Convert Binary Search Tree to Sorted Doubly Linked List
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Convert a binary search tree to a sorted circular doubly linked list in place.
    The left pointer of a node should point to its predecessor and the right pointer to its successor.
    The list is circular: the predecessor of the first element is the last element,
    and the successor of the last element is the first element.
    Return the pointer to the smallest element of the list.

Why this pattern fits:
The sorted order of a BST is its in-order traversal, and consecutive nodes in that traversal
    are exactly the neighbors in the linked list. Tracking the previously visited node is enough to link them.

Approach:
Run an in-order traversal while remembering first (the smallest node) and last (the most recently visited node).
    When a node is visited: if there is a last node, link last.right = node and node.left = last;
    otherwise this node is the first one. Then set last to the node.
    After the traversal, close the circle by linking last and first.

Worked Example:
Example:
            4
           / \
          2   5
         / \
        1   3
    List: 1 <-> 2 <-> 3 <-> 4 <-> 5, with 5's successor being 1 and 1's predecessor being 5.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class ConvertBSTToSortedDoublyLinkedListBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static TreeNode first;
    private static TreeNode last;

    public static TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }

        first = null;
        last = null;
        inorder(root);
        last.right = first;
        first.left = last;
        return first;
    }

    private static void inorder(TreeNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        if (last == null) {
            first = node;
        } else {
            last.right = node;
            node.left = last;
        }
        last = node;
        inorder(node.right);
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
        TreeNode head = treeToDoublyList(buildBST(4, 2, 5, 1, 3));

        StringBuilder forward = new StringBuilder();
        TreeNode node = head;
        do {
            forward.append(node.val).append(' ');
            node = node.right;
        } while (node != head);
        System.out.println("Forward : " + forward.toString().trim());

        StringBuilder backward = new StringBuilder();
        node = head.left;
        do {
            backward.append(node.val).append(' ');
            node = node.left;
        } while (node != head.left);
        System.out.println("Backward: " + backward.toString().trim());
    }
}
