import java.util.*;

/*
Problem: Check Completeness of a Binary Tree
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
A complete binary tree has every level full except possibly the last, whose nodes are as far left as possible.
Return true if the given tree is complete.

Why this pattern fits:
Level order lists the positions of a complete tree with no gaps.
Including null children in the queue makes any gap visible as "a node after a null".

Approach:
BFS that enqueues children even when they are null. When a null is dequeued, set a flag.
If a non-null node is dequeued after the flag is set, the tree is not complete.

Worked Example:
[1, 2, 3, 4, 5, 6] is complete. [1, 2, 3, 4, 5, null, 7] has a gap before 7, so it is not.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P27_CheckCompletenessOfABinaryTreeBFS {
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

    public static boolean isCompleteTree(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean seenGap = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                seenGap = true;
                continue;
            }
            if (seenGap) {
                return false;
            }
            queue.add(node.left);
            queue.add(node.right);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 3, 4, 5, 6] -> " + isCompleteTree(build(1, 2, 3, 4, 5, 6)));
        System.out.println("[1, 2, 3, 4, 5, null, 7] -> " + isCompleteTree(build(1, 2, 3, 4, 5, null, 7)));
    }
}
