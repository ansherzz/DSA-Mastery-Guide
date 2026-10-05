import java.util.*;

/*
Problem: Convert Sorted List to Binary Search Tree
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Given the head of a singly linked list whose values are sorted in ascending order,
    convert it into a height-balanced binary search tree.

Why this pattern fits:
The middle value of a sorted sequence is the natural root of a balanced BST:
    everything before it forms the left subtree and everything after it forms the right subtree.
    A linked list has no index access, so the middle is found with slow and fast pointers on each recursive call.

Approach:
Recurse on a half-open range of the list, from a start node up to (but not including) an end node.
    Find the middle of the range with slow and fast pointers.
    The middle node becomes the root. Build the left subtree from [start, middle) and the right subtree from (middle, end).

Worked Example:
Example:
    list = -10 -> -3 -> 0 -> 5 -> 9
    The middle is 0, so it becomes the root.
    Left part [-10, -3] gives root -3 with left child -10. Right part [5, 9] gives root 9 with left child 5.
    The inorder traversal of the result is the original list.

Time Complexity: O(n log n)
Space Complexity: O(log n) recursion stack
*/

public class ConvertSortedListToBSTDivideConquer {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode sortedListToBST(ListNode head) {
        return build(head, null);
    }

    private static TreeNode build(ListNode start, ListNode end) {
        if (start == end) {
            return null;
        }

        ListNode slow = start;
        ListNode fast = start;
        while (fast != end && fast.next != end) {
            slow = slow.next;
            fast = fast.next.next;
        }

        TreeNode root = new TreeNode(slow.val);
        root.left = build(start, slow);
        root.right = build(slow.next, end);
        return root;
    }

    private static void inorder(TreeNode node, List<Integer> values) {
        if (node == null) return;
        inorder(node.left, values);
        values.add(node.val);
        inorder(node.right, values);
    }

    private static int height(TreeNode node) {
        return node == null ? 0 : 1 + Math.max(height(node.left), height(node.right));
    }

    public static void main(String[] args) {
        int[] sorted = {-10, -3, 0, 5, 9};
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int value : sorted) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }

        TreeNode root = sortedListToBST(dummy.next);
        List<Integer> values = new ArrayList<>();
        inorder(root, values);

        System.out.println("List    : " + Arrays.toString(sorted));
        System.out.println("Root    : " + root.val);
        System.out.println("Inorder : " + values);
        System.out.println("Height  : " + height(root));
    }
}
