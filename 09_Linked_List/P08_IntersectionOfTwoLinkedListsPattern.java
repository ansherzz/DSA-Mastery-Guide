import java.util.*;

/*
Problem: Intersection of Two Linked Lists
Pattern: 09. Linked List
Difficulty: Easy

Interview Prompt:
Given the heads of two singly linked lists, return the node at which the two lists intersect.
    Intersection means the two lists share the exact same node objects from that point on, not just equal values.
    If the lists do not intersect, return null. The lists must keep their original structure.

    Follow-up: solve it in O(m + n) time and O(1) memory.

Why this pattern fits:
The two lists may have different lengths before the shared part, so walking them in step does not line up.
    A pointer trick removes the length difference: each pointer walks its own list and then the other list,
    so both travel exactly m + n nodes and arrive at the intersection together.

Approach:
Start pointer a at headA and pointer b at headB.
    Advance both one step at a time. When a pointer reaches the end, redirect it to the other list's head.
    They meet at the intersection node, or both become null at the same time if there is none.

Worked Example:
Example:
    A: 4 -> 1 -> 8 -> 4 -> 5
    B: 5 -> 6 -> 1 -> 8 -> 4 -> 5   (the node 8 and everything after it are shared)
    Pointer a travels 5 nodes of A then 3 nodes of B; pointer b travels 6 nodes of B then 2 nodes of A.
    Both arrive at the shared node 8.

Time Complexity: O(m + n)
Space Complexity: O(1)
*/

public class P08_IntersectionOfTwoLinkedListsPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;

        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }
        return a;
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

    private static ListNode tailOf(ListNode head) {
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode shared = build(8, 4, 5);
        ListNode headA = build(4, 1);
        ListNode headB = build(5, 6, 1);
        tailOf(headA).next = shared;
        tailOf(headB).next = shared;

        ListNode meeting = getIntersectionNode(headA, headB);
        System.out.println("Intersect at value: " + (meeting == null ? "none" : meeting.val));
        System.out.println("Same node object? : " + (meeting == shared));

        ListNode separate = getIntersectionNode(build(2, 6, 4), build(1, 5));
        System.out.println("Separate lists    : " + (separate == null ? "no intersection" : separate.val));
    }
}
