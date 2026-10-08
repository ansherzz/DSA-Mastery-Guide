import java.util.*;

/*
Problem: Remove Nodes From Linked List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Remove every node that has a node with a strictly greater value anywhere to its right. Return the head.

Why this pattern fits:
The decision for a node depends on everything to its right, which a singly linked list cannot look at directly.
Reversing turns "to the right" into "already seen", and a running maximum is enough.

Approach:
Reverse the list. Scan it keeping the maximum so far; unlink any node smaller than that maximum.
Reverse the result again to restore the original order.

Worked Example:
[5, 2, 13, 3, 8] -> 5 and 2 have 13 to their right, 3 has 8. Answer = [13, 8].

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P35_RemoveNodesFromLinkedListPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
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

    private static String format(ListNode head) {
        StringBuilder text = new StringBuilder("[");
        for (ListNode node = head; node != null; node = node.next) {
            text.append(node.val).append(node.next != null ? ", " : "");
        }
        return text.append("]").toString();
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static ListNode removeNodes(ListNode head) {
        head = reverse(head);
        ListNode kept = head;
        while (kept.next != null) {
            if (kept.next.val < kept.val) {
                kept.next = kept.next.next;
            } else {
                kept = kept.next;
            }
        }
        return reverse(head);
    }

    public static void main(String[] args) {
        System.out.println("[5, 2, 13, 3, 8] -> " + format(removeNodes(build(5, 2, 13, 3, 8))));
        System.out.println("[1, 1, 1, 1] -> " + format(removeNodes(build(1, 1, 1, 1))));
    }
}
