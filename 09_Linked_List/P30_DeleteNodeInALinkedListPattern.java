import java.util.*;

/*
Problem: Delete Node in a Linked List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
There is a singly linked list, and you are given only a reference to the node that must be deleted.
    You are not given access to the head of the list.
    All values in the list are unique, and the given node is guaranteed not to be the last node.
    Delete the node so that its value no longer appears and the order of the remaining values is unchanged.

Why this pattern fits:
Normal deletion rewires the previous node, but the previous node is unreachable here.
    The problem tests whether you understand that a node's identity is just its value plus its next pointer.

Approach:
Copy the next node's value into the given node, then unlink the next node.
    The given node now looks exactly like its successor, and the successor is gone,
    so from the outside the list reads as if the given node was removed.

Worked Example:
Example:
    4 -> 5 -> 1 -> 9, delete the node holding 5
    Copy 1 into that node: 4 -> 1 -> 1 -> 9. Skip the next node: 4 -> 1 -> 9.

Time Complexity: O(1)
Space Complexity: O(1)
*/

public class P30_DeleteNodeInALinkedListPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
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
        ListNode head = build(4, 5, 1, 9);
        System.out.println("Before: " + stringify(head));
        deleteNode(head.next);
        System.out.println("After deleting 5: " + stringify(head));
        deleteNode(head);
        System.out.println("After deleting 4: " + stringify(head));
    }
}
