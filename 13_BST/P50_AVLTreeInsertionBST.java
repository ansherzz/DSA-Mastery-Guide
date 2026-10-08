import java.util.*;

/*
Problem: AVL Tree Insertion (Self-Balancing BST)
Pattern: 13. Binary Search Tree
Difficulty: Hard

Interview Prompt:
Implement insertion into an AVL tree: a BST in which the heights of the two subtrees of every node
differ by at most one, so that the height stays O(log n) for any insertion order.

Why this pattern fits:
A plain BST degenerates into a list for sorted input. Rotations change the shape locally
while keeping the inorder order, so balance can be repaired on the way back up from an insertion.

Approach:
Insert recursively as in a BST, then update the node's height and compute its balance factor.
Left-Left -> rotate right; Right-Right -> rotate left; Left-Right -> rotate the left child left, then rotate right;
Right-Left -> rotate the right child right, then rotate left.

Worked Example:
Inserting 10, 20, 30, 40, 50, 25 in order gives root 30 with subtrees (20: 10, 25) and (40: -, 50).
A plain BST would have height 5; the AVL tree has height 3.

Time Complexity: O(log n) per insertion
Space Complexity: O(n)
*/

public class P50_AVLTreeInsertionBST {
    private static class Node {
        int key;
        int height = 1;
        Node left, right;
        Node(int key) { this.key = key; }
    }

    private static int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private static void update(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private static Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        update(y);
        update(x);
        return x;
    }

    private static Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        update(x);
        update(y);
        return y;
    }

    public static Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }
        update(node);
        int balance = height(node.left) - height(node.right);
        if (balance > 1) {
            if (key > node.left.key) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (key < node.right.key) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private static void preorder(Node node, List<Integer> keys) {
        if (node != null) {
            keys.add(node.key);
            preorder(node.left, keys);
            preorder(node.right, keys);
        }
    }

    public static void main(String[] args) {
        Node root = null;
        for (int key : new int[]{10, 20, 30, 40, 50, 25}) {
            root = insert(root, key);
        }
        List<Integer> keys = new ArrayList<>();
        preorder(root, keys);
        System.out.println("preorder after inserts -> " + keys);
        System.out.println("height -> " + height(root));
    }
}
