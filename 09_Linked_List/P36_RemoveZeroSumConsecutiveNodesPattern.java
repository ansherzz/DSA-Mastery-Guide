import java.util.*;

/*
Problem: Remove Zero Sum Consecutive Nodes from Linked List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Repeatedly delete consecutive sequences of nodes that sum to 0 until no such sequence remains. Return the head.

Why this pattern fits:
Two positions with the same prefix sum enclose a zero-sum run.
Recording the last node for every prefix sum lets each node jump over the longest such run in one step.

Approach:
First pass (from a dummy with prefix 0): store prefix -> node, letting later nodes overwrite earlier ones.
Second pass: recompute the prefix and set node.next = map.get(prefix).next.

Worked Example:
[1, 2, -3, 3, 1] -> prefixes 1, 3, 0, 3, 4. The dummy (prefix 0) jumps to the node after -3. Answer = [3, 1].

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P36_RemoveZeroSumConsecutiveNodesPattern {
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

    public static ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        Map<Integer, ListNode> lastWithPrefix = new HashMap<>();
        int prefix = 0;
        for (ListNode node = dummy; node != null; node = node.next) {
            prefix += node.val;
            lastWithPrefix.put(prefix, node);
        }
        prefix = 0;
        for (ListNode node = dummy; node != null; node = node.next) {
            prefix += node.val;
            node.next = lastWithPrefix.get(prefix).next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, -3, 3, 1] -> " + format(removeZeroSumSublists(build(1, 2, -3, 3, 1))));
        System.out.println("[1, 2, 3, -3, 4] -> " + format(removeZeroSumSublists(build(1, 2, 3, -3, 4))));
        System.out.println("[1, 2, 3, -3, -2] -> " + format(removeZeroSumSublists(build(1, 2, 3, -3, -2))));
    }
}
