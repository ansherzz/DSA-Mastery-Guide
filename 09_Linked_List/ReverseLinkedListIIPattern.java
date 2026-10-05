import java.util.*;

/*
Problem: Reverse Linked List II
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Given the head of a singly linked list and two positions left and right (1-indexed, left <= right),
    reverse the nodes from position left to position right, and return the modified list.

    Follow-up: do it in one pass.

Why this pattern fits:
This is the partial version of reversing a list. The reversal itself is easy;
    the care goes into reconnecting the reversed section to the untouched parts before and after it.
    A dummy head handles the case where the reversal starts at the first node.

Approach:
Move a pointer to the node just before position left.
    Then use head insertion: keep the first node of the section fixed, and repeatedly take the node after it
    and move it to the front of the section. After right - left moves the section is reversed.

Worked Example:
Example:
    1 -> 2 -> 3 -> 4 -> 5, left = 2, right = 4
    Move 3 to the front of the section: 1 -> 3 -> 2 -> 4 -> 5
    Move 4 to the front of the section: 1 -> 4 -> 3 -> 2 -> 5

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class ReverseLinkedListIIPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode before = dummy;
        for (int i = 1; i < left; i++) {
            before = before.next;
        }

        ListNode sectionTail = before.next;
        for (int i = 0; i < right - left; i++) {
            ListNode moving = sectionTail.next;
            sectionTail.next = moving.next;
            moving.next = before.next;
            before.next = moving;
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

    private static void runDemo(int left, int right, int... values) {
        ListNode head = build(values);
        System.out.println("Before: " + stringify(head) + ", left = " + left + ", right = " + right);
        System.out.println("After : " + stringify(reverseBetween(head, left, right)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(2, 4, 1, 2, 3, 4, 5);
        runDemo(1, 3, 1, 2, 3);
        runDemo(1, 1, 5);
    }
}
