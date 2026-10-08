import java.util.*;

/*
Problem: Merge Two Sorted Lists
Pattern: 09. Linked List
Difficulty: Easy

Interview Prompt:
Given the heads of two sorted linked lists, merge them into one sorted linked list and return the head.

    This is a dummy-node pattern and a direct application of controlled pointer movement.

Why this pattern fits:
The problem is simple conceptually, but many candidates mishandle the head edge case.
    A dummy node removes that complexity.

Approach:
Create a dummy head and a tail pointer.
    Repeatedly attach the smaller current node from the two lists, then append the remaining list at the end.

Worked Example:
Example:
    1 -> 2 -> 4 and 1 -> 3 -> 4 become 1 -> 1 -> 2 -> 3 -> 4 -> 4.

Time Complexity: O(m + n)
Space Complexity: O(1)
*/

public class P03_MergeTwoSortedListsPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        tail.next = (list1 != null) ? list1 : list2;
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
        ListNode list1 = build(1, 2, 4);
        ListNode list2 = build(1, 3, 4);
        ListNode merged = mergeTwoLists(list1, list2);
        System.out.println("Merged: " + stringify(merged));
    }
}
