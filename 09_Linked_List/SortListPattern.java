import java.util.*;

/*
Problem: Sort List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Given the head of a linked list, return the list after sorting it in ascending order.
    The solution should run in O(n log n) time.

Why this pattern fits:
Merge sort is the natural sorting algorithm for linked lists.
    It only needs sequential access, splitting is done with slow/fast pointers,
    and merging two sorted lists needs no extra array because nodes are simply relinked.

Approach:
Base case: an empty list or a single node is already sorted.
    Split the list into two halves by finding the middle and cutting the link before it.
    Recursively sort each half, then merge the two sorted halves with a dummy head.

Worked Example:
Example:
    4 -> 2 -> 1 -> 3
    Split into 4 -> 2 and 1 -> 3. Sort each to 2 -> 4 and 1 -> 3.
    Merge to get 1 -> 2 -> 3 -> 4.

Time Complexity: O(n log n)
Space Complexity: O(log n) recursion stack
*/

public class SortListPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHalf = slow.next;
        slow.next = null;

        return merge(sortList(head), sortList(secondHalf));
    }

    private static ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (a != null && b != null) {
            if (a.val <= b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = (a != null) ? a : b;
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

    private static void runDemo(int... values) {
        ListNode head = build(values);
        System.out.println("Before: " + stringify(head));
        System.out.println("After : " + stringify(sortList(head)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(4, 2, 1, 3);
        runDemo(-1, 5, 3, 4, 0);
    }
}
