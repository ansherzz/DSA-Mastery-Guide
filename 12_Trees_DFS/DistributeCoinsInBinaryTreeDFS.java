import java.util.*;

/*
Problem: Distribute Coins in Binary Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
You are given the root of a binary tree with n nodes, where each node has node.val coins.
    There are n coins in total throughout the whole tree.
    In one move, you may choose two adjacent nodes and move one coin from one node to the other.
    Return the minimum number of moves required to make every node have exactly one coin.

Why this pattern fits:
Think about one edge at a time. The subtree below an edge has some number of nodes and some number of coins;
    the difference must cross that edge, and nothing else can fix it.
    Post-order DFS computes that difference for every subtree.

Approach:
The helper returns the balance of a subtree: coins minus nodes.
    A positive balance means extra coins must flow up to the parent; a negative one means coins must flow down.
    Either way, |balance| moves cross the edge above the subtree, so add |left balance| + |right balance| to the answer.
    The node's own balance is node.val - 1 + left balance + right balance.

Worked Example:
Example:
          0
         / \
        3   0
    The left child has balance +2, so 2 moves cross its edge. The right child has balance -1, so 1 move crosses its edge.
    Answer = 3.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class DistributeCoinsInBinaryTreeDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int distributeCoins(TreeNode root) {
        int[] moves = new int[1];
        balance(root, moves);
        return moves[0];
    }

    private static int balance(TreeNode node, int[] moves) {
        if (node == null) {
            return 0;
        }

        int left = balance(node.left, moves);
        int right = balance(node.right, moves);
        moves[0] += Math.abs(left) + Math.abs(right);
        return node.val - 1 + left + right;
    }

    private static TreeNode build(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(values[0]);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int i = 1;
        while (i < values.length) {
            TreeNode parent = queue.poll();
            if (values[i] != null) {
                parent.left = new TreeNode(values[i]);
                queue.offer(parent.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                parent.right = new TreeNode(values[i]);
                queue.offer(parent.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println(distributeCoins(build(3, 0, 0)));
        System.out.println(distributeCoins(build(0, 3, 0)));
        System.out.println(distributeCoins(build(1, 0, 0, null, 3)));
    }
}
