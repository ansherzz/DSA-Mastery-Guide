import java.util.*;

/*
Problem: Insert Greatest Common Divisors in Linked List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Given the head of a linked list, insert between every pair of adjacent nodes
a new node whose value is the greatest common divisor of the two. Return the head.

Why this pattern fits:
Insertion in the middle of a linked list is O(1) once you hold the node before the insertion point.
The only care needed is to step over the node that was just inserted.

Approach:
For each node with a successor: create a GCD node, link it between the two, and jump to the original successor.

Worked Example:
[18, 6, 10, 3] -> gcd(18, 6) = 6, gcd(6, 10) = 2, gcd(10, 3) = 1 -> [18, 6, 6, 2, 10, 1, 3].

Time Complexity: O(n log(max value))
Space Complexity: O(1) extra
*/

public class P32_InsertGreatestCommonDivisorsInLinkedListPattern {
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

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode node = head;
        while (node != null && node.next != null) {
            ListNode inserted = new ListNode(gcd(node.val, node.next.val));
            inserted.next = node.next;
            node.next = inserted;
            node = inserted.next;
        }
        return head;
    }

    public static void main(String[] args) {
        System.out.println("[18, 6, 10, 3] -> " + format(insertGreatestCommonDivisors(build(18, 6, 10, 3))));
        System.out.println("[7] -> " + format(insertGreatestCommonDivisors(build(7))));
    }
}
