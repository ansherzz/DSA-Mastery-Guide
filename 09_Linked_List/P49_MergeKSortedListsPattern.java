import java.util.*;

/*
Problem: Merge k Sorted Lists
Pattern: 09. Linked List
Difficulty: Hard

Interview Prompt:
Given an array of k sorted linked lists, merge them into one sorted list and return its head.

Why this pattern fits:
Merging two sorted lists is the basic building block. Merging the lists pairwise in rounds
keeps every node involved in only log k merges, instead of k merges when folding one list at a time.

Approach:
While the step is smaller than k: merge lists[i] with lists[i + step] for i = 0, 2 * step, 4 * step, ...
and double the step. The fully merged list ends up at index 0.

Worked Example:
lists = [[1, 4, 5], [1, 3, 4], [2, 6]] -> [1, 1, 2, 3, 4, 4, 5, 6].

Time Complexity: O(N log k) for N total nodes
Space Complexity: O(1) extra
*/

public class P49_MergeKSortedListsPattern {
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

    private static ListNode mergeTwo(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = a != null ? a : b;
        return dummy.next;
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        for (int step = 1; step < lists.length; step *= 2) {
            for (int i = 0; i + step < lists.length; i += 2 * step) {
                lists[i] = mergeTwo(lists[i], lists[i + step]);
            }
        }
        return lists[0];
    }

    public static void main(String[] args) {
        ListNode[] lists = {build(1, 4, 5), build(1, 3, 4), build(2, 6)};
        System.out.println("[[1, 4, 5], [1, 3, 4], [2, 6]] -> " + format(mergeKLists(lists)));
        System.out.println("no lists -> " + format(mergeKLists(new ListNode[0])));
    }
}
