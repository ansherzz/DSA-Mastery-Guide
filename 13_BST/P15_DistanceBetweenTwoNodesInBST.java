import java.util.*;

/*
Problem: Distance Between Two Nodes in a BST
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given a BST and two keys that both exist in it, return the number of edges on the path between them.

Why this pattern fits:
In a BST the lowest common ancestor is the first node whose value lies between the two keys,
found without any backtracking. From there, each key is reached by an ordinary BST search.

Approach:
Move from the root while both keys are on the same side. At the split node,
answer = stepsTo(a) + stepsTo(b), each counted by a standard search.

Worked Example:
Tree [8, 3, 10, 1, 6, null, 14, null, null, 4, 7, 13], keys 4 and 13 -> path 4-6-3-8-10-14-13 has 6 edges.

Time Complexity: O(h)
Space Complexity: O(1)
*/

public class P15_DistanceBetweenTwoNodesInBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // Builds a tree from level-order values, using null for a missing child.
    private static TreeNode build(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int i = 1;
        while (i < values.length) {
            TreeNode node = queue.poll();
            if (values[i] != null) {
                node.left = new TreeNode(values[i]);
                queue.add(node.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                node.right = new TreeNode(values[i]);
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

    private static int stepsTo(TreeNode node, int key) {
        int steps = 0;
        while (node.val != key) {
            node = key < node.val ? node.left : node.right;
            steps++;
        }
        return steps;
    }

    public static int distance(TreeNode root, int a, int b) {
        TreeNode split = root;
        while ((a < split.val && b < split.val) || (a > split.val && b > split.val)) {
            split = a < split.val ? split.left : split.right;
        }
        return stepsTo(split, a) + stepsTo(split, b);
    }

    public static void main(String[] args) {
        TreeNode root = build(8, 3, 10, 1, 6, null, 14, null, null, 4, 7, 13);
        System.out.println("4 and 13 -> " + distance(root, 4, 13));
        System.out.println("1 and 7 -> " + distance(root, 1, 7));
        System.out.println("6 and 6 -> " + distance(root, 6, 6));
    }
}
