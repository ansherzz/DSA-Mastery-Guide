import java.util.*;

/*
Problem: Insertion Sort List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Given the head of a singly linked list, sort the list using insertion sort and return the sorted list's head.
    Insertion sort removes one element from the input at a time, finds the location where it belongs
    within the sorted part, and inserts it there.

Why this pattern fits:
Insertion sort on an array shifts elements; on a linked list an insertion is only two pointer changes.
    The exercise is pure pointer manipulation: detach a node, find its predecessor, and splice it in.

Approach:
Keep a separate sorted list behind a dummy head.
    Take each node from the input, save its next pointer, and walk the sorted list from the dummy
    until the next sorted value is not smaller than the node's value.
    Splice the node in after that position.
    Optimization: if the node is not smaller than the last inserted position, continue the walk from there
    instead of from the dummy, which makes already-sorted input linear.

Worked Example:
Example:
    4 -> 2 -> 1 -> 3
    Sorted list grows: 4, then 2 -> 4, then 1 -> 2 -> 4, then 1 -> 2 -> 3 -> 4.

Time Complexity: O(n^2) worst case, O(n) for already sorted input
Space Complexity: O(1)
*/

public class P28_InsertionSortListPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode previous = dummy;
        ListNode current = head;

        while (current != null) {
            ListNode next = current.next;

            if (previous != dummy && previous.val > current.val) {
                previous = dummy;
            }
            while (previous.next != null && previous.next.val < current.val) {
                previous = previous.next;
            }

            current.next = previous.next;
            previous.next = current;
            current = next;
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

    public static void main(String[] args) {
        System.out.println(stringify(insertionSortList(build(4, 2, 1, 3))));
        System.out.println(stringify(insertionSortList(build(-1, 5, 3, 4, 0))));
        System.out.println(stringify(insertionSortList(build(1, 2, 3))));
    }
}
