import java.util.*;

/*
Problem: Merge K Sorted Lists with a Heap
Pattern: 19. Heap & Greedy
Difficulty: Hard

Interview Prompt:
Given k sorted linked lists, merge them into one sorted linked list.

    This version uses a min-heap so the next smallest node is always available.

Why this pattern fits:
The lists are already individually sorted.
    A min-heap across their current heads lets you always choose the globally smallest next node.

Approach:
Push the head of every non-null list into a min-heap ordered by node value.
    Repeatedly pop the smallest node, append it to the answer, and push its next node if present.

Worked Example:
Example:
    [[1,4,5],[1,3,4],[2,6]] becomes [1,1,2,3,4,4,5,6].

Time Complexity: O(N log k)
Space Complexity: O(k)
*/

public class MergeKSortedListsHeapGreedy {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            tail.next = node;
            tail = tail.next;

            if (node.next != null) {
                minHeap.offer(node.next);
            }
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
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ListNode[] lists = {
            build(1,4,5),
            build(1,3,4),
            build(2,6)
        };
        System.out.println(stringify(mergeKLists(lists)));
    }
}
