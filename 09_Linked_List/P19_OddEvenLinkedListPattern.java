import java.util.*;

/*
Problem: Odd Even Linked List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Given the head of a singly linked list, group all nodes at odd positions together followed by
    the nodes at even positions, and return the reordered list.
    The first node is at position 1 (odd), the second at position 2 (even), and so on.
    The relative order inside each group must be preserved.

    Solve it in O(1) extra space and O(n) time.

Why this pattern fits:
The task is pure pointer rewiring. Two chains are built in place by leapfrogging:
    each odd node links to the next odd node, and each even node links to the next even node.

Approach:
Keep a pointer for the tail of the odd chain and one for the tail of the even chain,
    and remember the head of the even chain.
    Repeatedly link odd to the node after even, advance odd, then link even to the node after odd, advance even.
    At the end attach the even chain after the odd chain.

Worked Example:
Example:
    1 -> 2 -> 3 -> 4 -> 5
    Odd positions: 1, 3, 5. Even positions: 2, 4.
    Result: 1 -> 3 -> 5 -> 2 -> 4.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P19_OddEvenLinkedListPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
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

    private static void runDemo(int... values) {
        ListNode head = build(values);
        System.out.println("Before: " + stringify(head));
        System.out.println("After : " + stringify(oddEvenList(head)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(1, 2, 3, 4, 5);
        runDemo(2, 1, 3, 5, 6, 4, 7);
    }
}
