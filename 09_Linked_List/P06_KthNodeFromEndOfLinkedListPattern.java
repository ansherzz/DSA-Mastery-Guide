import java.util.*;

/*
Problem: Kth Node From End of Linked List
Pattern: 09. Linked List
Difficulty: Easy

Interview Prompt:
Given the head of a singly linked list and an integer k, return the value of the k-th node from the end
(k = 1 is the last node). Return -1 if the list has fewer than k nodes. Use a single pass.

Why this pattern fits:
The length is unknown, but a gap of k nodes between two pointers stays constant while both move.
When the front pointer reaches the end, the back pointer is exactly k nodes from the end.

Approach:
Advance `lead` k times (return -1 if it runs out). Then move `lead` and `trail` together until lead is null.

Worked Example:
list = [1, 2, 3, 4, 5], k = 2 -> lead starts at 3; when lead passes 5, trail is at 4. Answer = 4.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P06_KthNodeFromEndOfLinkedListPattern {
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

    public static int kthFromEnd(ListNode head, int k) {
        ListNode lead = head;
        for (int i = 0; i < k; i++) {
            if (lead == null) {
                return -1;
            }
            lead = lead.next;
        }
        ListNode trail = head;
        while (lead != null) {
            lead = lead.next;
            trail = trail.next;
        }
        return trail.val;
    }

    public static void main(String[] args) {
        for (int k : new int[]{2, 5, 6}) {
            System.out.println("[1, 2, 3, 4, 5], k = " + k + " -> " + kthFromEnd(build(1, 2, 3, 4, 5), k));
        }
    }
}
