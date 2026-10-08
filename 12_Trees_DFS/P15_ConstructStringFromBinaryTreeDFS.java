import java.util.*;

/*
Problem: Construct String from Binary Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Easy

Interview Prompt:
Build a string from a binary tree using preorder traversal: each child subtree is wrapped in parentheses.
Omit empty parentheses unless they are needed to show that a right child has no left sibling.

Why this pattern fits:
Serializing a tree is a preorder DFS where each call returns the text of its subtree.
The only subtlety is which empty pairs are required to keep the mapping unambiguous.

Approach:
text(node) = value, then "(" + text(left) + ")" if there is a left or a right child,
then "(" + text(right) + ")" if there is a right child.

Worked Example:
[1, 2, 3, null, 4] -> node 2 has only a right child, so the empty "()" must stay: "1(2()(4))(3)".

Time Complexity: O(n)
Space Complexity: O(h) besides the output
*/

public class P15_ConstructStringFromBinaryTreeDFS {
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

    private static void write(TreeNode node, StringBuilder text) {
        text.append(node.val);
        if (node.left == null && node.right == null) {
            return;
        }
        text.append('(');
        if (node.left != null) {
            write(node.left, text);
        }
        text.append(')');
        if (node.right != null) {
            text.append('(');
            write(node.right, text);
            text.append(')');
        }
    }

    public static String tree2str(TreeNode root) {
        StringBuilder text = new StringBuilder();
        write(root, text);
        return text.toString();
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 3, 4] -> " + tree2str(build(1, 2, 3, 4)));
        System.out.println("[1, 2, 3, null, 4] -> " + tree2str(build(1, 2, 3, null, 4)));
    }
}
