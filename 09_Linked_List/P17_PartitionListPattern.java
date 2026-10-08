import java.util.*;

/*
Problem: Partition List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Given the head of a linked list and a value x, rearrange the list so that all nodes with values less than x
    come before all nodes with values greater than or equal to x.
    The original relative order of the nodes within each of the two groups must be preserved.

Why this pattern fits:
In an array, a stable partition needs extra space or careful shifting.
    In a linked list it is natural: detach each node and append it to one of two chains,
    then join the chains. Two dummy heads keep the appends simple.

Approach:
Create two dummy heads, one for the "less than x" chain and one for the "greater or equal" chain.
    Walk the original list and append each node to the matching chain.
    Terminate the second chain with null to avoid a leftover link, then connect the first chain to the second.

Worked Example:
Example:
    1 -> 4 -> 3 -> 2 -> 5 -> 2, x = 3
    Less chain: 1 -> 2 -> 2. Greater-or-equal chain: 4 -> 3 -> 5.
    Result: 1 -> 2 -> 2 -> 4 -> 3 -> 5.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P17_PartitionListPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode partition(ListNode head, int x) {
        ListNode lessDummy = new ListNode(0);
        ListNode greaterDummy = new ListNode(0);
        ListNode lessTail = lessDummy;
        ListNode greaterTail = greaterDummy;

        for (ListNode node = head; node != null; node = node.next) {
            if (node.val < x) {
                lessTail.next = node;
                lessTail = node;
            } else {
                greaterTail.next = node;
                greaterTail = node;
            }
        }

        greaterTail.next = null;
        lessTail.next = greaterDummy.next;
        return lessDummy.next;
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
            if (head.next != null) {
                sb.append(" -> ");
            }
            head = head.next;
        }
        return sb.toString();
    }

    private static void runDemo(int x, int... values) {
        ListNode head = build(values);
        System.out.println("Before: " + stringify(head) + ", x = " + x);
        System.out.println("After : " + stringify(partition(head, x)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(3, 1, 4, 3, 2, 5, 2);
        runDemo(2, 2, 1);
    }
}
