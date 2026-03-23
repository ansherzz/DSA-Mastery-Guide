import java.util.*;

/*
Problem: Merge K Sorted Lists with Divide and Conquer
Pattern: 15. Divide & Conquer
Difficulty: Hard

Interview Prompt:
Given an array of sorted linked lists, merge them into one sorted linked list.

    This version uses divide and conquer rather than a heap.

Why this pattern fits:
Merging k lists one by one is slower.
    Pairing lists recursively like merge sort reduces the total work.

Approach:
Recursively split the list array into two halves.
    Merge the left result and right result using the standard two-list merge routine.

Worked Example:
Example:
    [[1,4,5],[1,3,4],[2,6]] becomes [1,1,2,3,4,4,5,6].

Time Complexity: O(N log k), where N is the total number of nodes
Space Complexity: O(log k) recursion stack
*/

public class MergeKSortedListsDivideConquer {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeRange(lists, 0, lists.length - 1);
    }

    private static ListNode mergeRange(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        return mergeTwo(mergeRange(lists, left, mid), mergeRange(lists, mid + 1, right));
    }

    private static ListNode mergeTwo(ListNode a, ListNode b) {
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
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[]{
            build(1, 4, 5),
            build(1, 3, 4),
            build(2, 6)
        };

        System.out.println(stringify(mergeKLists(lists)));
    }
}
