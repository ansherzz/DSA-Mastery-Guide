import java.util.*;

/*
Problem: Symmetric Tree (Iterative with a Queue)
Pattern: 11. Queue / BFS
Difficulty: Easy

Interview Prompt:
Given the root of a binary tree, check iteratively whether it is a mirror of itself around its center.

Why this pattern fits:
The recursive solution compares (left, right) pairs. A queue of pairs performs the same comparisons
level by level without recursion.

Approach:
Enqueue (root.left, root.right). Repeatedly take two nodes: both null is fine, one null or different values fails.
Then enqueue (a.left, b.right) and (a.right, b.left).

Worked Example:
[1, 2, 2, 3, 4, 4, 3] is symmetric. [1, 2, 2, null, 3, null, 3] is not: both 3s are right children.

Time Complexity: O(n)
Space Complexity: O(width of the tree)
*/

public class P13_SymmetricTreeIterativeBFS {
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

    public static boolean isSymmetric(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            TreeNode a = queue.poll();
            TreeNode b = queue.poll();
            if (a == null && b == null) {
                continue;
            }
            if (a == null || b == null || a.val != b.val) {
                return false;
            }
            queue.add(a.left);
            queue.add(b.right);
            queue.add(a.right);
            queue.add(b.left);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 2, 3, 4, 4, 3] -> " + isSymmetric(build(1, 2, 2, 3, 4, 4, 3)));
        System.out.println("[1, 2, 2, null, 3, null, 3] -> " + isSymmetric(build(1, 2, 2, null, 3, null, 3)));
    }
}
