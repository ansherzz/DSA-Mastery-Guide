import java.util.*;

/*
Problem: Remove Duplicates from Sorted List
Pattern: 09. Linked List
Difficulty: Easy

Interview Prompt:
Given the head of a sorted linked list, delete all duplicates so that each value appears only once.
    Return the linked list, which must remain sorted.

Why this pattern fits:
Because the list is sorted, duplicates are always adjacent.
    Removing one is a single pointer rewrite: skip the next node by linking to the node after it.

Approach:
Walk the list with one pointer.
    If the next node has the same value as the current node, bypass it with current.next = current.next.next.
    Otherwise move forward. Do not advance after a deletion, because the new next node may also be a duplicate.

Worked Example:
Example:
    1 -> 1 -> 2 -> 3 -> 3 becomes 1 -> 2 -> 3.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class RemoveDuplicatesFromSortedListPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
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
        System.out.println("Before: " + stringify(head));
        System.out.println("After : " + stringify(deleteDuplicates(head)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(1, 1, 2);
        runDemo(1, 1, 2, 3, 3);
        runDemo(7, 7, 7, 7);
    }
}
