import java.util.*;

/*
Problem: Binary Tree Postorder Traversal (Iterative)
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
Return the postorder traversal (left, right, node) of a binary tree without using recursion.

Why this pattern fits:
Postorder is the hardest order to do iteratively because a node is emitted after both subtrees.
The trick: postorder is exactly the reverse of a "node, right, left" preorder.

Approach:
Run the iterative preorder but push the left child before the right child,
and insert each visited value at the front of the result list.

Worked Example:
[1, 2, 3, 4, 5] -> node-right-left order is 1, 3, 2, 5, 4; reversed: [4, 5, 2, 3, 1].

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P03_BinaryTreePostorderTraversalIterativeDFS {
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

    public static List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.addFirst(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("[1, null, 2, 3] -> " + postorderTraversal(build(1, null, 2, 3)));
        System.out.println("[1, 2, 3, 4, 5] -> " + postorderTraversal(build(1, 2, 3, 4, 5)));
    }
}
