import java.util.*;

/*
Problem: Delete N Nodes After M Nodes of a Linked List
Pattern: 09. Linked List
Difficulty: Easy

Interview Prompt:
Traverse the list keeping the first m nodes, deleting the next n nodes, and repeating until the end.
Return the head of the modified list.

Why this pattern fits:
Deleting a run of nodes is one pointer assignment once you hold the last kept node and the first node after the run.

Approach:
Loop: advance m - 1 steps to the last kept node. From its next, skip n nodes. Link the kept node to what follows.

Worked Example:
list = [1..13], m = 2, n = 3 -> keep 1, 2; drop 3, 4, 5; keep 6, 7; drop 8, 9, 10; keep 11, 12; drop 13.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P11_DeleteNNodesAfterMNodesPattern {
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

    public static ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode current = head;
        while (current != null) {
            for (int i = 1; i < m && current != null; i++) {
                current = current.next;
            }
            if (current == null) {
                break;
            }
            ListNode skip = current.next;
            for (int i = 0; i < n && skip != null; i++) {
                skip = skip.next;
            }
            current.next = skip;
            current = skip;
        }
        return head;
    }

    public static void main(String[] args) {
        System.out.println("[1..13], m = 2, n = 3 -> "
                + format(deleteNodes(build(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13), 2, 3)));
        System.out.println("[1..11], m = 1, n = 3 -> "
                + format(deleteNodes(build(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), 1, 3)));
    }
}
