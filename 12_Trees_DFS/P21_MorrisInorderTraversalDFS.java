import java.util.*;

/*
Problem: Morris Inorder Traversal
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Return the inorder traversal of a binary tree using O(1) extra space (no recursion and no stack).
The tree may be modified temporarily but must be restored.

Why this pattern fits:
A stack is needed only to find the way back up after finishing a left subtree.
Morris traversal stores that way back inside the tree: the rightmost node of the left subtree points to the current node.

Approach:
At a node with no left child: visit it and go right.
Otherwise find its inorder predecessor. If the predecessor's right is null, thread it to the node and go left.
If it already points to the node, remove the thread, visit the node, and go right.

Worked Example:
[4, 2, 6, 1, 3, 5, 7] -> [1, 2, 3, 4, 5, 6, 7].

Time Complexity: O(n)
Space Complexity: O(1) extra
*/

public class P21_MorrisInorderTraversalDFS {
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

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;
        while (current != null) {
            if (current.left == null) {
                result.add(current.val);
                current = current.right;
                continue;
            }
            TreeNode predecessor = current.left;
            while (predecessor.right != null && predecessor.right != current) {
                predecessor = predecessor.right;
            }
            if (predecessor.right == null) {
                predecessor.right = current;
                current = current.left;
            } else {
                predecessor.right = null;
                result.add(current.val);
                current = current.right;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("[1, null, 2, 3] -> " + inorderTraversal(build(1, null, 2, 3)));
        System.out.println("[4, 2, 6, 1, 3, 5, 7] -> " + inorderTraversal(build(4, 2, 6, 1, 3, 5, 7)));
    }
}
