import java.util.*;

/*
Problem: Remove Nth Node From End of List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Given the head of a linked list, remove the nth node from the end and return the new head.

    This is a perfect example of using two pointers with an intentional gap.

Why this pattern fits:
If one pointer starts n steps ahead of another, then when the first reaches the end,
    the second is positioned exactly where the removal must happen.

Approach:
Use a dummy node before the head.
    Advance fast by n + 1 steps from the dummy, then move both fast and slow together until fast becomes null.
    Slow will then point to the node before the one that must be removed.

Worked Example:
Example:
    1 -> 2 -> 3 -> 4 -> 5, n = 2
    After removal, the list becomes 1 -> 2 -> 3 -> 5.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P10_RemoveNthNodeFromEndPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
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
        head = removeNthFromEnd(head, 2);
        System.out.println("After : " + stringify(head));
    }
}
