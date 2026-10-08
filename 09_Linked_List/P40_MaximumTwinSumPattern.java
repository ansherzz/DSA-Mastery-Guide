import java.util.*;

/*
Problem: Maximum Twin Sum of a Linked List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
In a linked list of even size n, the i-th node (0-indexed) is the twin of the (n - 1 - i)-th node.
    The twin sum is the sum of a node and its twin.
    Given the head of a linked list with even length, return the maximum twin sum of the list.

Why this pattern fits:
Twins are mirror positions, which would be trivial with indexing but a singly linked list has none.
    The standard trio solves it in O(1) space: find the middle, reverse the second half, walk both halves together.

Approach:
Use slow/fast pointers to find the start of the second half.
    Reverse the second half in place.
    Walk one pointer from the head and one from the reversed half; each aligned pair is a pair of twins.
    Track the largest sum.

Worked Example:
Example:
    5 -> 4 -> 2 -> 1
    Second half reversed: 1 -> 2. Twin sums: 5 + 1 = 6 and 4 + 2 = 6. Answer = 6.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P40_MaximumTwinSumPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static int pairSum(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode reversed = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = reversed;
            reversed = slow;
            slow = next;
        }

        int best = 0;
        for (ListNode front = head; reversed != null; front = front.next, reversed = reversed.next) {
            best = Math.max(best, front.val + reversed.val);
        }
        return best;
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

    public static void main(String[] args) {
        System.out.println(pairSum(build(5, 4, 2, 1)));
        System.out.println(pairSum(build(4, 2, 2, 3)));
        System.out.println(pairSum(build(1, 100000)));
    }
}
