import java.util.*;

/*
Problem: Reverse Linked List
Pattern: 09. Linked List
Difficulty: Easy

Interview Prompt:
Given the head of a singly linked list, reverse the list and return the new head.

    This is the core pointer-rewiring exercise for linked lists.

Why this pattern fits:
Reversal is pure pointer discipline. It teaches how to update links without losing access to the rest of the list.

Approach:
Iterate through the list while keeping track of previous, current, and next nodes.
    Reverse the next pointer of each node one by one.

Worked Example:
Example:
    1 -> 2 -> 3 -> 4 -> 5 becomes 5 -> 4 -> 3 -> 2 -> 1.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class ReverseLinkedListPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
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
        head = reverseList(head);
        System.out.println("After : " + stringify(head));
    }
}
