import java.util.*;

/*
Problem: Add Two Numbers
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Two non-empty linked lists represent two non-negative integers.
    The digits are stored in reverse order, and each node contains a single digit.
    Add the two numbers and return the sum as a linked list in the same reversed format.

Why this pattern fits:
Reverse order means the lists start at the ones digit, exactly where grade-school addition starts.
    So one synchronized pass with a carry builds the answer. A dummy head keeps the append logic uniform.

Approach:
Walk both lists together while either still has nodes or a carry remains.
    Add the two digits (treating a finished list as 0) plus the carry.
    Append a node with sum % 10 and carry sum / 10 forward.

Worked Example:
Example:
    2 -> 4 -> 3 represents 342 and 5 -> 6 -> 4 represents 465.
    342 + 465 = 807, which is stored as 7 -> 0 -> 8.

Time Complexity: O(max(m, n))
Space Complexity: O(max(m, n)) for the result
*/

public class P13_AddTwoNumbersPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            carry = sum / 10;
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

    private static void runDemo(ListNode l1, ListNode l2) {
        System.out.println("First : " + stringify(l1));
        System.out.println("Second: " + stringify(l2));
        System.out.println("Sum   : " + stringify(addTwoNumbers(l1, l2)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(build(2, 4, 3), build(5, 6, 4));
        runDemo(build(0), build(0));
        runDemo(build(9, 9, 9, 9, 9, 9, 9), build(9, 9, 9, 9));
    }
}
