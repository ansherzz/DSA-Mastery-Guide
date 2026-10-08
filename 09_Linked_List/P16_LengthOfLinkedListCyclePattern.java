import java.util.*;

/*
Problem: Length of the Cycle in a Linked List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Given the head of a linked list, return the number of nodes in its cycle, or 0 if the list has no cycle.

Why this pattern fits:
Floyd's algorithm guarantees that the two pointers meet inside the cycle.
From any node inside a cycle, walking until you return to it counts the cycle length.

Approach:
Move slow by one and fast by two until they meet or fast reaches null (no cycle).
From the meeting node, step forward counting nodes until the meeting node is reached again.

Worked Example:
1 -> 2 -> 3 -> 4 -> 5 -> back to 3: the cycle is 3, 4, 5, so the answer is 3.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P16_LengthOfLinkedListCyclePattern {
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

    public static int cycleLength(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                int length = 1;
                for (ListNode node = slow.next; node != slow; node = node.next) {
                    length++;
                }
                return length;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        ListNode head = build(1, 2, 3, 4, 5);
        head.next.next.next.next.next = head.next.next;
        System.out.println("tail links back to node 3 -> " + cycleLength(head));
        System.out.println("no cycle -> " + cycleLength(build(1, 2, 3)));
        ListNode single = build(7);
        single.next = single;
        System.out.println("single self loop -> " + cycleLength(single));
    }
}
