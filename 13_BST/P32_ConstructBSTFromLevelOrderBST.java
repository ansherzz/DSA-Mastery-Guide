import java.util.*;

/*
Problem: Construct BST from Level Order Traversal
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given the level order traversal of a BST with distinct keys, rebuild the tree in O(n).

Why this pattern fits:
Inserting the values one by one costs O(n * h). In level order, the children of earlier nodes appear
before the children of later nodes, so a queue of nodes with their (low, high) ranges can hand out
the values in a single pass.

Approach:
Queue the root with range (-inf, +inf). Pop a node: if the next value lies in (low, node) it is the left child;
then if the next value lies in (node, high) it is the right child. Queue each child with its narrowed range.

Worked Example:
[7, 4, 12, 3, 6, 8, 1, 5, 10] -> 4 and 12 are children of 7; 3 and 6 of 4; 8 of 12; 1 of 3; 5 of 6; 10 of 8.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P32_ConstructBSTFromLevelOrderBST {
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

    private static class Slot {
        final TreeNode node;
        final long low;
        final long high;
        Slot(TreeNode node, long low, long high) {
            this.node = node;
            this.low = low;
            this.high = high;
        }
    }

    public static TreeNode bstFromLevelOrder(int[] values) {
        TreeNode root = new TreeNode(values[0]);
        Deque<Slot> queue = new ArrayDeque<>();
        queue.add(new Slot(root, Long.MIN_VALUE, Long.MAX_VALUE));
        int i = 1;
        while (i < values.length) {
            Slot slot = queue.poll();
            TreeNode node = slot.node;
            if (i < values.length && values[i] > slot.low && values[i] < node.val) {
                node.left = new TreeNode(values[i++]);
                queue.add(new Slot(node.left, slot.low, node.val));
            }
            if (i < values.length && values[i] > node.val && values[i] < slot.high) {
                node.right = new TreeNode(values[i++]);
                queue.add(new Slot(node.right, node.val, slot.high));
            }
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println("[7, 4, 12, 3, 6, 8, 1, 5, 10] -> "
                + format(bstFromLevelOrder(new int[]{7, 4, 12, 3, 6, 8, 1, 5, 10})));
    }
}
