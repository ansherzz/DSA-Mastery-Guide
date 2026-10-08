import java.util.*;

/*
Problem: Binary Tree Preorder Traversal (Iterative)
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
Return the preorder traversal (node, left, right) of a binary tree without using recursion.

Why this pattern fits:
Recursion uses the call stack to remember "what to do after this subtree".
An explicit stack makes that memory visible and is the standard interview follow-up.

Approach:
Push the root. Repeatedly pop a node, record it, then push its right child and its left child
(right first, so that left is processed first).

Worked Example:
[1, 2, 3, 4, 5] -> 1, then the left subtree 2, 4, 5, then 3. Answer = [1, 2, 4, 5, 3].

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P02_BinaryTreePreorderTraversalIterativeDFS {
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

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("[1, null, 2, 3] -> " + preorderTraversal(build(1, null, 2, 3)));
        System.out.println("[1, 2, 3, 4, 5] -> " + preorderTraversal(build(1, 2, 3, 4, 5)));
    }
}
