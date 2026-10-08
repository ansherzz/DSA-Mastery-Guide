import java.util.*;

/*
Problem: Double a Number Represented as a Linked List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
A non-negative integer without leading zeros is stored as a linked list, most significant digit first.
Return the head of the list after doubling the number.

Why this pattern fits:
When doubling, the carry into a digit is 1 exactly when the digit to its right is 5 or more.
That lets the carry be computed by looking ahead, so no reversal is needed.

Approach:
If the head digit is 5 or more, prepend a 0 node to receive the final carry.
For each node: value = (2 * value) % 10, plus 1 if the next node's original value is greater than 4.

Worked Example:
[1, 8, 9] -> 1 becomes 2 + 1 (since 8 > 4) = 3, 8 becomes 6 + 1 = 7, 9 becomes 8. Answer = [3, 7, 8].

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P38_DoubleANumberRepresentedAsALinkedListPattern {
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

    public static ListNode doubleIt(ListNode head) {
        if (head.val > 4) {
            ListNode front = new ListNode(0);
            front.next = head;
            head = front;
        }
        for (ListNode node = head; node != null; node = node.next) {
            node.val = node.val * 2 % 10;
            if (node.next != null && node.next.val > 4) {
                node.val++;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        System.out.println("[1, 8, 9] -> " + format(doubleIt(build(1, 8, 9))));
        System.out.println("[9, 9, 9] -> " + format(doubleIt(build(9, 9, 9))));
    }
}
