import java.util.*;

/*
Problem: Construct BST from Postorder Traversal
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given the postorder traversal of a BST with distinct keys, rebuild the tree and return its root.

Why this pattern fits:
Read backwards, postorder becomes root, right subtree, left subtree.
The BST property supplies the missing boundaries: a value belongs to the current subtree only if it is above a bound.

Approach:
build(bound): if the next value (from the end) is smaller than bound, this subtree is empty.
Otherwise take it as the root, build the right subtree with bound = root value, then the left subtree with the old bound.

Worked Example:
postorder = [1, 7, 5, 50, 40, 10] -> root 10, right subtree 40 -> 50, left subtree 5 with children 1 and 7.

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P31_ConstructBSTFromPostorderBST {
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

    // Prints a tree in level order, using null for a missing child.
    private static String format(TreeNode root) {
        List<String> out = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                out.add("null");
                continue;
            }
            out.add(String.valueOf(node.val));
            queue.add(node.left);
            queue.add(node.right);
        }
        while (!out.isEmpty() && out.get(out.size() - 1).equals("null")) {
            out.remove(out.size() - 1);
        }
        return out.toString();
    }

    private static int index;

    private static TreeNode buildFrom(int[] postorder, int bound) {
        if (index < 0 || postorder[index] < bound) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[index--]);
        root.right = buildFrom(postorder, root.val);
        root.left = buildFrom(postorder, bound);
        return root;
    }

    public static TreeNode bstFromPostorder(int[] postorder) {
        index = postorder.length - 1;
        return buildFrom(postorder, Integer.MIN_VALUE);
    }

    public static void main(String[] args) {
        System.out.println("[1, 7, 5, 50, 40, 10] -> " + format(bstFromPostorder(new int[]{1, 7, 5, 50, 40, 10})));
    }
}
