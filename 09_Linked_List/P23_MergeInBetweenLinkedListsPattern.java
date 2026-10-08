import java.util.*;

/*
Problem: Merge In Between Linked Lists
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Remove the nodes of list1 from index a to index b (0-indexed, inclusive) and put list2 in their place.

Why this pattern fits:
Splicing needs exactly two anchors in list1 and the tail of list2. Everything else is two pointer assignments.

Approach:
Walk to node a - 1 (`before`). Continue to node b + 1 (`after`). Find list2's tail.
Set before.next = list2 and tail.next = after.

Worked Example:
list1 = [10, 1, 13, 6, 9, 5], a = 3, b = 4, list2 = [1000000, 1000001, 1000002]
-> [10, 1, 13, 1000000, 1000001, 1000002, 5].

Time Complexity: O(n + m)
Space Complexity: O(1)
*/

public class P23_MergeInBetweenLinkedListsPattern {
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

    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode before = list1;
        for (int i = 0; i < a - 1; i++) {
            before = before.next;
        }
        ListNode after = before;
        for (int i = a - 1; i <= b; i++) {
            after = after.next;
        }
        ListNode tail = list2;
        while (tail.next != null) {
            tail = tail.next;
        }
        before.next = list2;
        tail.next = after;
        return list1;
    }

    public static void main(String[] args) {
        ListNode result = mergeInBetween(build(10, 1, 13, 6, 9, 5), 3, 4, build(1000000, 1000001, 1000002));
        System.out.println("a = 3, b = 4 -> " + format(result));
    }
}
