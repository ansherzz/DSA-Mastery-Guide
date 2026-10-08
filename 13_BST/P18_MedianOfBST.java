import java.util.*;

/*
Problem: Median of a BST
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given the root of a BST, return the median of its keys.
For an even number of keys, return the average of the two middle keys.

Why this pattern fits:
Inorder traversal visits BST keys in sorted order, so the median is the middle element of that order.
No array is needed: just count the visits.

Approach:
Count the nodes n. Traverse inorder with a stack and a counter, remembering the previous key.
For odd n return the key at position n / 2 + 1; for even n average positions n / 2 and n / 2 + 1.

Worked Example:
Keys 1, 3, 4, 6, 7, 8, 10, 13, 14 -> median 7. Keys 1, 3, 4, 6, 7, 8 -> (4 + 6) / 2 = 5.

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P18_MedianOfBST {
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

    private static int count(TreeNode node) {
        return node == null ? 0 : 1 + count(node.left) + count(node.right);
    }

    public static double median(TreeNode root) {
        int n = count(root);
        int target = n / 2 + 1;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        int visited = 0;
        int previous = 0;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            visited++;
            if (visited == target) {
                return n % 2 == 1 ? current.val : (previous + current.val) / 2.0;
            }
            previous = current.val;
            current = current.right;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("nine keys -> " + median(build(8, 3, 10, 1, 6, null, 14, null, null, 4, 7, 13)));
        System.out.println("six keys -> " + median(build(6, 3, 8, 1, 4, 7)));
    }
}
