import java.util.*;

/*
Problem: Binary Tree Level Order Traversal II
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Return the level order traversal of a binary tree from the bottom level up to the root,
with each level listed from left to right.

Why this pattern fits:
The levels are discovered top-down by BFS. Only the output order changes,
so each finished level is added to the front of a linked list.

Approach:
Standard BFS by level size. Collect the values of one level, then addFirst that list to the result.

Worked Example:
[3, 9, 20, null, null, 15, 7] -> [[15, 7], [9, 20], [3]].

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P16_BinaryTreeLevelOrderTraversalIIBFS {
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

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            for (int size = queue.size(); size > 0; size--) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.addFirst(level);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("[3, 9, 20, null, null, 15, 7] -> " + levelOrderBottom(build(3, 9, 20, null, null, 15, 7)));
        System.out.println("empty tree -> " + levelOrderBottom(null));
    }
}
