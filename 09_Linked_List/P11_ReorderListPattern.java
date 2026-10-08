import java.util.*;

/*
Problem: Reorder List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Given a linked list L0 -> L1 -> ... -> Ln, reorder it into
    L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...

    The list must be rearranged in-place.

Why this pattern fits:
This is an interview favorite because it combines three linked-list moves:
    find the middle, reverse the second half, and merge the two halves alternately.

Approach:
Use slow/fast pointers to find the middle.
    Reverse the second half.
    Then weave nodes from the first and reversed second halves together.

Worked Example:
Example:
    1 -> 2 -> 3 -> 4 -> 5 becomes 1 -> 5 -> 2 -> 4 -> 3.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P11_ReorderListPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode second = slow.next;
        slow.next = null;

        ListNode prev = null;
        while (second != null) {
            ListNode next = second.next;
            second.next = prev;
            prev = second;
            second = next;
        }

        ListNode first = head;
        second = prev;
        while (second != null) {
            ListNode nextFirst = first.next;
            ListNode nextSecond = second.next;

            first.next = second;
            second.next = nextFirst;

            first = nextFirst;
            second = nextSecond;
        }
    }

    private static ListNode build(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int value : values) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }
        return dummy.next;
    }

    private static String stringify(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(" -> ");
            }
            head = head.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ListNode head = build(1, 2, 3, 4, 5);
        System.out.println("Before: " + stringify(head));
        reorderList(head);
        System.out.println("After : " + stringify(head));
    }
}
