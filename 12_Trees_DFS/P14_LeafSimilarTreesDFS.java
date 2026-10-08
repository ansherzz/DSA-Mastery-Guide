import java.util.*;

/*
Problem: Leaf-Similar Trees
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
The leaf value sequence of a tree is its leaves read from left to right.
Return true if two trees have the same leaf value sequence.

Why this pattern fits:
Left-to-right leaf order is the order in which a DFS (left before right) reaches the leaves,
regardless of how different the internal shapes are.

Approach:
DFS each tree, appending a node's value when it has no children. Compare the two lists.

Worked Example:
Both example trees have the leaf sequence 6, 7, 4, 9, 8 even though their shapes differ. Answer = true.

Time Complexity: O(n1 + n2)
Space Complexity: O(n1 + n2)
*/

public class P14_LeafSimilarTreesDFS {
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

    private static void collectLeaves(TreeNode node, List<Integer> leaves) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            leaves.add(node.val);
        }
        collectLeaves(node.left, leaves);
        collectLeaves(node.right, leaves);
    }

    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        collectLeaves(root1, first);
        collectLeaves(root2, second);
        return first.equals(second);
    }

    public static void main(String[] args) {
        TreeNode a = build(3, 5, 1, 6, 2, 9, 8, null, null, 7, 4);
        TreeNode b = build(3, 5, 1, 6, 7, 4, 2, null, null, null, null, null, null, 9, 8);
        System.out.println("same leaves, different shapes -> " + leafSimilar(a, b));
        System.out.println("[1, 2, 3] vs [1, 3, 2] -> " + leafSimilar(build(1, 2, 3), build(1, 3, 2)));
    }
}
