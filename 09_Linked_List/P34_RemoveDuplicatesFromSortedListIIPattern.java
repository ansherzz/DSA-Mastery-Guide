import java.util.*;

/*
Problem: Remove Duplicates from Sorted List II
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
    leaving only the numbers that appear exactly once in the original list.
    Return the linked list, which must remain sorted.

Why this pattern fits:
Unlike the easier version, every copy of a duplicated value must go, including the first one.
    The head itself may be deleted, which is exactly the situation a dummy node is designed for.

Approach:
Use a dummy node before the head and a pointer previous that marks the last node known to be kept.
    Look at the node after previous. If it starts a run of equal values, skip the entire run
    by pointing previous.next past it, and do not advance previous (the next node may start another run).
    Otherwise the node is unique, so advance previous.

Worked Example:
Example:
    1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
    The runs of 3 and 4 are removed entirely. Result: 1 -> 2 -> 5.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P34_RemoveDuplicatesFromSortedListIIPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode previous = dummy;

        while (previous.next != null) {
            ListNode current = previous.next;
            if (current.next != null && current.next.val == current.val) {
                int duplicate = current.val;
                while (current != null && current.val == duplicate) {
                    current = current.next;
                }
                previous.next = current;
            } else {
                previous = current;
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
        System.out.println(stringify(deleteDuplicates(build(1, 2, 3, 3, 4, 4, 5))));
        System.out.println(stringify(deleteDuplicates(build(1, 1, 1, 2, 3))));
        System.out.println("[" + stringify(deleteDuplicates(build(2, 2))) + "]");
    }
}
