import java.util.*;

/*
Problem: Rotate List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Given the head of a linked list, rotate the list to the right by k places.
    k may be larger than the length of the list.

Why this pattern fits:
Rotating a linked list does not require moving nodes one by one.
    If you close the list into a ring, a rotation is just choosing a different place to cut the ring open.

Approach:
Walk to the tail while counting the length n, then link the tail to the head to form a ring.
    Reduce k with k % n. The new tail is the node at position n - k - 1 from the old head.
    Walk there, take the node after it as the new head, and break the ring.

Worked Example:
Example:
    1 -> 2 -> 3 -> 4 -> 5, k = 2
    n = 5, so the new tail is n - k - 1 = 2 steps from the old head, which is the node 3.
    The new head is 4. Result: 4 -> 5 -> 1 -> 2 -> 3.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P21_RotateListPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        tail.next = head;

        int stepsToNewTail = length - (k % length) - 1;
        ListNode newTail = head;
        for (int i = 0; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;
        return newHead;
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

    private static void runDemo(int k, int... values) {
        ListNode head = build(values);
        System.out.println("Before: " + stringify(head) + ", k = " + k);
        System.out.println("After : " + stringify(rotateRight(head, k)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(2, 1, 2, 3, 4, 5);
        runDemo(4, 0, 1, 2);
        runDemo(5, 1, 2, 3, 4, 5);
    }
}
