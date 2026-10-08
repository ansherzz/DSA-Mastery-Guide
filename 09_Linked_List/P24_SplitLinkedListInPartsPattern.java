import java.util.*;

/*
Problem: Split Linked List in Parts
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Split a linked list into k consecutive parts whose lengths differ by at most one,
with earlier parts at least as long as later ones. Some parts may be empty.

Why this pattern fits:
The sizes are determined by arithmetic: every part gets length / k nodes and the first length % k parts get one extra.
After that it is repeated "walk size nodes and cut".

Approach:
Count the nodes. For each part, record the current head, advance size - 1 nodes,
cut the link after that node, and continue from the next node.

Worked Example:
[1..10], k = 3 -> sizes 4, 3, 3: [1, 2, 3, 4], [5, 6, 7], [8, 9, 10].

Time Complexity: O(n + k)
Space Complexity: O(k) for the output
*/

public class P24_SplitLinkedListInPartsPattern {
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

    public static ListNode[] splitListToParts(ListNode head, int k) {
        int length = 0;
        for (ListNode node = head; node != null; node = node.next) {
            length++;
        }
        ListNode[] parts = new ListNode[k];
        ListNode current = head;
        for (int i = 0; i < k && current != null; i++) {
            parts[i] = current;
            int size = length / k + (i < length % k ? 1 : 0);
            for (int step = 1; step < size; step++) {
                current = current.next;
            }
            ListNode next = current.next;
            current.next = null;
            current = next;
        }
        return parts;
    }

    private static String formatParts(ListNode[] parts) {
        List<String> text = new ArrayList<>();
        for (ListNode part : parts) {
            text.add(format(part));
        }
        return text.toString();
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 3], k = 5 -> " + formatParts(splitListToParts(build(1, 2, 3), 5)));
        System.out.println("[1..10], k = 3 -> " + formatParts(splitListToParts(build(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 3)));
    }
}
