import java.util.*;

/*
Problem: Add Two Numbers II
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
You are given two non-empty linked lists representing two non-negative integers.
    The most significant digit comes first, and each node contains a single digit.
    Add the two numbers and return the sum as a linked list in the same most-significant-first order.
    Follow-up: solve it without reversing the input lists.

Why this pattern fits:
Addition proceeds from the least significant digit, but a singly linked list is read from the most significant one.
    The standard way to reverse the access order without touching the lists is to push the digits onto stacks.

Approach:
Push every digit of each list onto its own stack.
    Pop from both stacks while either has digits or a carry remains, adding digit + digit + carry.
    Each new result digit is more significant than the previous one, so insert each new node at the front of the result.

Worked Example:
Example:
    7 -> 2 -> 4 -> 3  plus  5 -> 6 -> 4
    7243 + 564 = 7807. Result: 7 -> 8 -> 0 -> 7.

Time Complexity: O(n + m)
Space Complexity: O(n + m)
*/

public class P24_AddTwoNumbersIIPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> first = new ArrayDeque<>();
        Deque<Integer> second = new ArrayDeque<>();
        for (ListNode node = l1; node != null; node = node.next) {
            first.push(node.val);
        }
        for (ListNode node = l2; node != null; node = node.next) {
            second.push(node.val);
        }

        ListNode head = null;
        int carry = 0;
        while (!first.isEmpty() || !second.isEmpty() || carry != 0) {
            int sum = carry;
            if (!first.isEmpty()) {
                sum += first.pop();
            }
            if (!second.isEmpty()) {
                sum += second.pop();
            }

            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
            carry = sum / 10;
        }
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
        System.out.println(stringify(addTwoNumbers(build(7, 2, 4, 3), build(5, 6, 4))));
        System.out.println(stringify(addTwoNumbers(build(9, 9), build(1))));
        System.out.println(stringify(addTwoNumbers(build(0), build(0))));
    }
}
