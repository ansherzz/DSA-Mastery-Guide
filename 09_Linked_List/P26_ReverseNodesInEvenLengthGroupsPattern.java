import java.util.*;

/*
Problem: Reverse Nodes in Even Length Groups
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
The nodes are split into consecutive groups of sizes 1, 2, 3, ... (the last group may be shorter).
Reverse the nodes of every group whose length is even and return the head.

Why this pattern fits:
It is sublist reversal applied group by group. The detail that matters is that the last group's real length,
not its planned size, decides whether it is reversed.

Approach:
Keep `prev`, the node before the current group. Count how many nodes the group really has.
If the count is even, reverse those nodes by repeatedly moving the next node to the front of the group.
Then advance `prev` to the group's last node.

Worked Example:
[5, 2, 6, 3, 9, 1, 7, 3, 8, 4] -> groups [5], [2, 6], [3, 9, 1], [7, 3, 8, 4]; the second and fourth are reversed.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P26_ReverseNodesInEvenLengthGroupsPattern {
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

    public static ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode prev = head;
        int groupSize = 2;
        while (prev.next != null) {
            int count = 0;
            ListNode last = prev;
            while (count < groupSize && last.next != null) {
                last = last.next;
                count++;
            }
            if (count % 2 == 0) {
                ListNode groupTail = prev.next;
                for (int i = 1; i < count; i++) {
                    ListNode moved = groupTail.next;
                    groupTail.next = moved.next;
                    moved.next = prev.next;
                    prev.next = moved;
                }
                prev = groupTail;
            } else {
                prev = last;
            }
            groupSize++;
        }
        return head;
    }

    public static void main(String[] args) {
        System.out.println("[5, 2, 6, 3, 9, 1, 7, 3, 8, 4] -> "
                + format(reverseEvenLengthGroups(build(5, 2, 6, 3, 9, 1, 7, 3, 8, 4))));
        System.out.println("[1, 1, 0, 6] -> " + format(reverseEvenLengthGroups(build(1, 1, 0, 6))));
        System.out.println("[1, 1, 0, 6, 5] -> " + format(reverseEvenLengthGroups(build(1, 1, 0, 6, 5))));
    }
}
