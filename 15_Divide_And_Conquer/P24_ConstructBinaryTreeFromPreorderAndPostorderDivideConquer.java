import java.util.*;

/*
Problem: Construct Binary Tree from Preorder and Postorder Traversal
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Given the preorder and postorder traversals of a binary tree with distinct values,
return any binary tree that matches both.

Why this pattern fits:
Preorder starts with the root and postorder ends with it. The left subtree's root is preorder[1],
and everything up to that value in postorder belongs to the left subtree - which splits both arrays in two.

Approach:
build(preStart, postStart, size): the root is preorder[preStart]. If size is 1, return it.
Find preorder[preStart + 1] in postorder (via a value -> index map) to get leftSize, then recurse on the two parts.

Worked Example:
preorder = [1, 2, 4, 5, 3, 6, 7], postorder = [4, 5, 2, 6, 7, 3, 1] -> the complete tree [1, 2, 3, 4, 5, 6, 7].

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P24_ConstructBinaryTreeFromPreorderAndPostorderDivideConquer {
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

    private static TreeNode buildTree(int[] preorder, int preStart, int postStart, int size, Map<Integer, Integer> postIndex) {
        if (size == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        if (size == 1) {
            return root;
        }
        int leftSize = postIndex.get(preorder[preStart + 1]) - postStart + 1;
        root.left = buildTree(preorder, preStart + 1, postStart, leftSize, postIndex);
        root.right = buildTree(preorder, preStart + 1 + leftSize, postStart + leftSize, size - 1 - leftSize, postIndex);
        return root;
    }

    public static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        Map<Integer, Integer> postIndex = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            postIndex.put(postorder[i], i);
        }
        return buildTree(preorder, 0, 0, preorder.length, postIndex);
    }

    public static void main(String[] args) {
        TreeNode root = constructFromPrePost(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 5, 2, 6, 7, 3, 1});
        System.out.println("rebuilt tree -> " + format(root));
    }
}
