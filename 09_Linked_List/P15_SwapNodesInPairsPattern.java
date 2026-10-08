import java.util.*;

/*
Problem: Swap Nodes in Pairs
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Given a linked list, swap every two adjacent nodes and return the new head.
    You must swap the nodes themselves by changing links; modifying the values inside the nodes is not allowed.

Why this pattern fits:
Each swap touches three links: the link into the pair and the two links inside it.
    A dummy head plus a "node before the pair" pointer makes every pair look the same, including the first one.

Approach:
Keep a pointer previous that sits just before the pair to be swapped.
    Name the pair first and second. Rewire: previous -> second -> first -> (rest).
    Then move previous to first, which is now the node before the next pair.

Worked Example:
Example:
    1 -> 2 -> 3 -> 4 becomes 2 -> 1 -> 4 -> 3.
    With an odd length, the last node stays where it is: 1 -> 2 -> 3 becomes 2 -> 1 -> 3.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P15_SwapNodesInPairsPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode previous = dummy;

        while (previous.next != null && previous.next.next != null) {
            ListNode first = previous.next;
            ListNode second = first.next;

            first.next = second.next;
            second.next = first;
            previous.next = second;

            previous = first;
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

    private static void runDemo(int... values) {
        ListNode head = build(values);
        System.out.println("Before: " + stringify(head));
        System.out.println("After : " + stringify(swapPairs(head)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(1, 2, 3, 4);
        runDemo(1, 2, 3);
        runDemo(1);
    }
}
