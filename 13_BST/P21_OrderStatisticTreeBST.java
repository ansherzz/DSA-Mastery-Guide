import java.util.*;

/*
Problem: Order Statistic Tree (BST with Subtree Sizes)
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Design a BST of distinct keys supporting insert(key), kthSmallest(k), and rank(key)
(the number of keys strictly smaller than key), each in O(h).

Why this pattern fits:
Plain inorder needs O(k) to find the k-th key. If every node knows how many nodes are in its left subtree,
each step of a descent can decide to go left, stop, or go right while skipping a known number of keys.

Approach:
Each node keeps `size`. insert increments sizes along the path.
kthSmallest: compare k with leftSize + 1 and descend, subtracting leftSize + 1 when going right.
rank: when going right, add leftSize + 1 to the count.

Worked Example:
Insert 20, 10, 30, 5, 15, 25 -> kthSmallest(1) = 5, kthSmallest(4) = 20, rank(15) = 2, rank(26) = 5.
After inserting 12, kthSmallest(3) = 12.

Time Complexity: O(h) per operation
Space Complexity: O(n)
*/

public class P21_OrderStatisticTreeBST {
    static class OrderStatisticTree {
        private static class Node {
            int key;
            int size = 1;
            Node left, right;
            Node(int key) { this.key = key; }
        }

        private Node root;

        private static int size(Node node) {
            return node == null ? 0 : node.size;
        }

        public void insert(int key) {
            root = insert(root, key);
        }

        private Node insert(Node node, int key) {
            if (node == null) {
                return new Node(key);
            }
            if (key < node.key) {
                node.left = insert(node.left, key);
            } else if (key > node.key) {
                node.right = insert(node.right, key);
            }
            node.size = 1 + size(node.left) + size(node.right);
            return node;
        }

        public int kthSmallest(int k) {
            Node node = root;
            while (node != null) {
                int leftSize = size(node.left);
                if (k == leftSize + 1) {
                    return node.key;
                }
                if (k <= leftSize) {
                    node = node.left;
                } else {
                    k -= leftSize + 1;
                    node = node.right;
                }
            }
            return -1;
        }

        public int rank(int key) {
            int smaller = 0;
            Node node = root;
            while (node != null) {
                if (key <= node.key) {
                    node = node.left;
                } else {
                    smaller += size(node.left) + 1;
                    node = node.right;
                }
            }
            return smaller;
        }
    }

    public static void main(String[] args) {
        OrderStatisticTree tree = new OrderStatisticTree();
        for (int key : new int[]{20, 10, 30, 5, 15, 25}) {
            tree.insert(key);
        }
        System.out.println("kthSmallest(1) -> " + tree.kthSmallest(1));
        System.out.println("kthSmallest(4) -> " + tree.kthSmallest(4));
        System.out.println("rank(15) -> " + tree.rank(15));
        System.out.println("rank(26) -> " + tree.rank(26));
        tree.insert(12);
        System.out.println("after insert(12): kthSmallest(3) -> " + tree.kthSmallest(3));
    }
}
