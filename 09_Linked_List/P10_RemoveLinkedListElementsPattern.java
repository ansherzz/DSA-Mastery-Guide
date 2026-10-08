import java.util.*;

/*
Problem: Remove Linked List Elements
Pattern: 09. Linked List
Difficulty: Easy

Interview Prompt:
Given the head of a linked list and an integer val, remove every node whose value equals val
    and return the new head.

Why this pattern fits:
Deleting a node requires access to the node before it.
    The head has no previous node, which creates a special case unless you add a dummy node in front.
    The dummy-head technique is one of the most reusable linked-list tools.

Approach:
Create a dummy node that points to the head.
    Walk with a pointer that always sits on the node before the one being inspected.
    If the next node should be removed, bypass it; otherwise advance.
    Return dummy.next, which is correct even if the original head was removed.

Worked Example:
Example:
    1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6 with val = 6 becomes 1 -> 2 -> 3 -> 4 -> 5.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P10_RemoveLinkedListElementsPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode previous = dummy;
        while (previous.next != null) {
            if (previous.next.val == val) {
                previous.next = previous.next.next;
            } else {
                previous = previous.next;
            }
        }
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
        if (head == null) {
            return "(empty)";
        }
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

    private static void runDemo(int val, int... values) {
        ListNode head = build(values);
        System.out.println("Before: " + stringify(head) + ", val = " + val);
        System.out.println("After : " + stringify(removeElements(head, val)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(6, 1, 2, 6, 3, 4, 5, 6);
        runDemo(7, 7, 7, 7, 7);
    }
}
