import java.util.*;

/*
Problem: Merge Nodes in Between Zeros
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
You are given the head of a linked list that contains a series of integers separated by 0s.
    The beginning and the end of the list both have a 0, and there are no two consecutive 0s.
    For every two consecutive 0s, merge all the nodes lying between them into a single node
    whose value is the sum of the merged nodes. The modified list must not contain any 0s.
    Return the head of the modified list.

Why this pattern fits:
The zeros act as delimiters, so one pass with a running sum is enough.
    The linked-list skill is building the output in place by reusing the existing nodes.

Approach:
Keep a write pointer on the first zero node. Walk the rest of the list:
    add non-zero values to a running sum.
    When a zero is reached, a group has ended: advance the write pointer to the next node, store the sum there, and reset the sum.
    After the walk, cut the list after the write pointer. The leading zero node is skipped by returning head.next.

Worked Example:
Example:
    0 -> 3 -> 1 -> 0 -> 4 -> 5 -> 2 -> 0
    Groups: 3 + 1 = 4 and 4 + 5 + 2 = 11. Result: 4 -> 11.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class MergeNodesInBetweenZerosPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode mergeNodes(ListNode head) {
        ListNode write = head;
        int sum = 0;

        for (ListNode node = head.next; node != null; node = node.next) {
            if (node.val != 0) {
                sum += node.val;
            } else {
                write = write.next;
                write.val = sum;
                sum = 0;
            }
        }

        write.next = null;
        return head.next;
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
        System.out.println(stringify(mergeNodes(build(0, 3, 1, 0, 4, 5, 2, 0))));
        System.out.println(stringify(mergeNodes(build(0, 1, 0, 3, 0, 2, 2, 0))));
    }
}
