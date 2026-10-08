import java.util.*;

/*
Problem: Plus One Linked List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
A non-negative integer is stored as a linked list with the most significant digit first.
Add one to the integer and return the head of the result.

Why this pattern fits:
The carry travels from the tail toward the head, against the direction of the links.
Instead of reversing, notice that the carry stops at the last non-9 digit.

Approach:
Use a dummy node with value 0 in front. Track the last node whose value is not 9.
Increment it and set every node after it to 0. If the dummy became 1, it is the new head.

Worked Example:
[1, 9, 9] -> the last non-9 digit is 1; it becomes 2 and the 9s become 0: [2, 0, 0].
[9, 9] -> the dummy is the last non-9: [1, 0, 0].

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P18_PlusOneLinkedListPattern {
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

    public static ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode lastNotNine = dummy;
        for (ListNode node = head; node != null; node = node.next) {
            if (node.val != 9) {
                lastNotNine = node;
            }
        }
        lastNotNine.val++;
        for (ListNode node = lastNotNine.next; node != null; node = node.next) {
            node.val = 0;
        }
        return dummy.val == 1 ? dummy : head;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 3] -> " + format(plusOne(build(1, 2, 3))));
        System.out.println("[9, 9] -> " + format(plusOne(build(9, 9))));
        System.out.println("[1, 9, 9] -> " + format(plusOne(build(1, 9, 9))));
    }
}
