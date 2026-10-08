import java.util.*;

/*
Problem: Linked List Cycle II
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Given the head of a linked list, return the node where the cycle begins.
    If there is no cycle, return null. Do not modify the list.

    Follow-up: use O(1) extra memory.

Why this pattern fits:
Detecting a cycle with slow/fast pointers is the basic version.
    Finding where the cycle starts adds a second phase that relies on a distance argument:
    the distance from the head to the cycle entrance equals the distance from the meeting point to the entrance
    (going around the cycle).

Approach:
Phase 1: move slow by one and fast by two until they meet. If fast runs off the list, there is no cycle.
    Phase 2: put one pointer back at the head. Move both pointers one step at a time.
    The node where they meet is the start of the cycle.

Worked Example:
Example:
    3 -> 2 -> 0 -> -4, and -4 points back to the node with value 2.
    Slow and fast meet inside the cycle. Restarting one pointer from the head,
    both pointers reach the node with value 2 at the same time.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P12_LinkedListCycleIIPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                ListNode finder = head;
                while (finder != slow) {
                    finder = finder.next;
                    slow = slow.next;
                }
                return finder;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(3);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(0);
        ListNode d = new ListNode(-4);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = b;

        ListNode start = detectCycle(a);
        System.out.println("Cycle starts at value: " + (start == null ? "none" : start.val));

        d.next = null;
        start = detectCycle(a);
        System.out.println("After breaking cycle : " + (start == null ? "none" : start.val));
    }
}
