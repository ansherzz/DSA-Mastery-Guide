import java.util.*;

/*
Problem: Delete the Middle Node of a Linked List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
You are given the head of a linked list. Delete the middle node and return the head of the modified list.
    The middle node of a list of size n is the node at index floor(n / 2) using 0-based indexing.
    For a list with a single node, the result is an empty list.

Why this pattern fits:
Finding the middle without knowing the length is the slow/fast pointer technique.
    Deleting it needs the node just before the middle, so the pointers are started one step differently.

Approach:
Handle the single-node list separately.
    Start slow at the head and fast two nodes ahead. Move slow by one and fast by two until fast runs out.
    Giving fast a head start makes slow stop one node before the middle.
    Unlink the middle with slow.next = slow.next.next.

Worked Example:
Example:
    1 -> 3 -> 4 -> 7 -> 1 -> 2 -> 6
    n = 7, so the middle is index 3 (value 7). Result: 1 -> 3 -> 4 -> 1 -> 2 -> 6.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class DeleteTheMiddleNodePattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        slow.next = slow.next.next;
        return head;
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
        System.out.println(stringify(deleteMiddle(build(1, 3, 4, 7, 1, 2, 6))));
        System.out.println(stringify(deleteMiddle(build(1, 2, 3, 4))));
        System.out.println(stringify(deleteMiddle(build(2, 1))));
        System.out.println("[" + stringify(deleteMiddle(build(5))) + "]");
    }
}
