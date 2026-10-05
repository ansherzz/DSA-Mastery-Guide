import java.util.*;

/*
Problem: Reverse Nodes in k-Group
Pattern: 09. Linked List
Difficulty: Hard

Interview Prompt:
Given the head of a linked list, reverse the nodes of the list k at a time and return the modified list.
    k is a positive integer no larger than the length of the list.
    If the number of remaining nodes is not a multiple of k, the leftover nodes at the end stay in their original order.
    You may not alter the values in the nodes; only the links may be changed.

    Follow-up: use O(1) extra memory.

Why this pattern fits:
This combines every linked-list skill in one problem: a dummy head, counting ahead to confirm a full group,
    in-place reversal of a section, and reconnecting the section to what comes before and after it.

Approach:
Keep groupPrevious, the node just before the current group.
    Look ahead k nodes to find the group's last node; if fewer than k remain, stop.
    Reverse the group by walking through it and pointing each node at the node that follows the group,
    then at the previously processed node. Finally hook groupPrevious to the new front
    and move groupPrevious to the group's new tail.

Worked Example:
Example:
    1 -> 2 -> 3 -> 4 -> 5, k = 2 becomes 2 -> 1 -> 4 -> 3 -> 5
    1 -> 2 -> 3 -> 4 -> 5, k = 3 becomes 3 -> 2 -> 1 -> 4 -> 5

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class ReverseNodesInKGroupPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrevious = dummy;

        while (true) {
            ListNode groupLast = groupPrevious;
            for (int i = 0; i < k && groupLast != null; i++) {
                groupLast = groupLast.next;
            }
            if (groupLast == null) {
                break;
            }

            ListNode groupNext = groupLast.next;
            ListNode previous = groupNext;
            ListNode current = groupPrevious.next;
            while (current != groupNext) {
                ListNode next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }

            ListNode newTail = groupPrevious.next;
            groupPrevious.next = groupLast;
            groupPrevious = newTail;
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

    private static void runDemo(int k, int... values) {
        ListNode head = build(values);
        System.out.println("Before: " + stringify(head) + ", k = " + k);
        System.out.println("After : " + stringify(reverseKGroup(head, k)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(2, 1, 2, 3, 4, 5);
        runDemo(3, 1, 2, 3, 4, 5);
        runDemo(3, 1, 2, 3, 4, 5, 6);
    }
}
