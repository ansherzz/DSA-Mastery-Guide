import java.util.*;

/*
Problem: Swapping Nodes in a Linked List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
You are given the head of a linked list and an integer k.
    Return the head of the linked list after swapping the values of the k-th node from the beginning
    and the k-th node from the end. The list is 1-indexed.

Why this pattern fits:
The k-th node from the end is found with the same fixed-gap two-pointer idea as "remove the n-th node from the end":
    two pointers kept a constant distance apart reach the answer in a single pass.

Approach:
Advance a pointer k - 1 steps from the head; it is the k-th node from the beginning.
    Start a second pointer at the head and a runner at the k-th node.
    Move both until the runner reaches the last node. The second pointer is then the k-th node from the end.
    Swap the two values.

Worked Example:
Example:
    1 -> 2 -> 3 -> 4 -> 5, k = 2
    Second from the start is 2, and second from the end is 4. Result: 1 -> 4 -> 3 -> 2 -> 5.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P42_SwappingNodesInALinkedListPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode swapNodes(ListNode head, int k) {
        ListNode first = head;
        for (int i = 1; i < k; i++) {
            first = first.next;
        }

        ListNode second = head;
        for (ListNode runner = first; runner.next != null; runner = runner.next) {
            second = second.next;
        }

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
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
        System.out.println(stringify(swapNodes(build(1, 2, 3, 4, 5), 2)));
        System.out.println(stringify(swapNodes(build(7, 9, 6, 6, 7, 8, 3, 0, 9, 5), 5)));
        System.out.println(stringify(swapNodes(build(1, 2), 1)));
    }
}
