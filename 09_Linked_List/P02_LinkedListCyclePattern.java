import java.util.*;

/*
Problem: Linked List Cycle
Pattern: 09. Linked List
Difficulty: Easy

Interview Prompt:
Given the head of a linked list, determine whether it contains a cycle.

    The standard solution uses slow and fast pointers, also called Floyd's cycle detection.

Why this pattern fits:
A cycle means a faster pointer will eventually lap a slower pointer if both keep moving.
    This elegant insight avoids extra memory.

Approach:
Move slow by one step and fast by two steps.
    If they ever meet, a cycle exists. If fast reaches null, no cycle exists.

Worked Example:
Example:
    3 -> 2 -> 0 -> -4, and the last node points back to the node with value 2.
    The answer is true.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P02_LinkedListCyclePattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(3);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(0);
        ListNode d = new ListNode(-4);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = b; // create cycle

        System.out.println("Cycle present? " + hasCycle(a));

        d.next = null; // break cycle
        System.out.println("Cycle present after breaking? " + hasCycle(a));
    }
}
