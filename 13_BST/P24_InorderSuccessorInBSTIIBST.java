import java.util.*;

/*
Problem: Inorder Successor in BST II (Parent Pointers)
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Each node has a pointer to its parent. Given only a node (not the root),
return its inorder successor, or null if it has none.

Why this pattern fits:
The successor is defined by position alone: the next node in sorted order is either the smallest node
of the right subtree or the nearest ancestor for which this node lies in the left subtree.

Approach:
If node.right exists, go right once and then left as far as possible.
Otherwise move up while the current node is its parent's right child; the parent reached is the successor.

Worked Example:
Insert 5, 3, 6, 2, 4, 1: successor(1) = 2, successor(4) = 5 (climbing past 3), successor(6) = none.

Time Complexity: O(h)
Space Complexity: O(1)
*/

public class P24_InorderSuccessorInBSTIIBST {
    static class Node {
        int val;
        Node left, right, parent;
        Node(int val) { this.val = val; }
    }

    public static Node inorderSuccessor(Node node) {
        if (node.right != null) {
            Node current = node.right;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    private static Node insert(Node root, Node node) {
        if (root == null) {
            return node;
        }
        Node current = root;
        while (true) {
            if (node.val < current.val) {
                if (current.left == null) {
                    current.left = node;
                    break;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = node;
                    break;
                }
                current = current.right;
            }
        }
        node.parent = current;
        return root;
    }

    public static void main(String[] args) {
        Map<Integer, Node> nodes = new HashMap<>();
        Node root = null;
        for (int value : new int[]{5, 3, 6, 2, 4, 1}) {
            nodes.put(value, new Node(value));
            root = insert(root, nodes.get(value));
        }
        for (int value : new int[]{1, 4, 6}) {
            Node successor = inorderSuccessor(nodes.get(value));
            System.out.println("successor of " + value + " -> " + (successor == null ? -1 : successor.val));
        }
    }
}
