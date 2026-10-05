import java.util.*;

/*
Problem: Middle of the Linked List
Pattern: 09. Linked List
Difficulty: Easy

Interview Prompt:
Given the head of a singly linked list, return the middle node.
    If there are two middle nodes (even length), return the second one.

Why this pattern fits:
A linked list has no random access and no stored length.
    The slow/fast pointer technique finds the middle in a single pass without counting first.

Approach:
Start slow and fast at the head.
    Move slow one step and fast two steps at a time.
    When fast reaches the end, slow is at the middle.

Worked Example:
Example:
    1 -> 2 -> 3 -> 4 -> 5 has middle 3.
    1 -> 2 -> 3 -> 4 -> 5 -> 6 has two middles (3 and 4); return 4.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class MiddleOfLinkedListPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
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

    private static void runDemo(int... values) {
        ListNode head = build(values);
        System.out.println("List       : " + stringify(head));
        System.out.println("From middle: " + stringify(middleNode(head)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(1, 2, 3, 4, 5);
        runDemo(1, 2, 3, 4, 5, 6);
    }
}
