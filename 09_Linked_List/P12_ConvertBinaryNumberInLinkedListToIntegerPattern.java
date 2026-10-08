import java.util.*;

/*
Problem: Convert Binary Number in a Linked List to Integer
Pattern: 09. Linked List
Difficulty: Easy

Interview Prompt:
Given head, a reference to a singly linked list where the value of each node is either 0 or 1,
    the list holds the binary representation of a number with the most significant bit at the head.
    Return the decimal value of the number.

Why this pattern fits:
A linked list can only be read from head to tail, and the head is the most significant bit.
    That matches the way a number is built digit by digit: shift what you have, then add the new digit.

Approach:
Start with value 0. For each node, double the value (shift left by one bit) and add the node's bit.
    No length calculation or reversal is needed.

Worked Example:
Example:
    1 -> 0 -> 1
    value: 0 -> 1 -> 2 -> 5. Answer = 5.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P12_ConvertBinaryNumberInLinkedListToIntegerPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static int getDecimalValue(ListNode head) {
        int value = 0;
        for (ListNode node = head; node != null; node = node.next) {
            value = value * 2 + node.val;
        }
        return value;
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
        System.out.println(stringify(build(1, 0, 1)) + " = " + getDecimalValue(build(1, 0, 1)));
        System.out.println(stringify(build(0)) + " = " + getDecimalValue(build(0)));
        System.out.println(stringify(build(1, 1, 1, 1)) + " = " + getDecimalValue(build(1, 1, 1, 1)));
    }
}
