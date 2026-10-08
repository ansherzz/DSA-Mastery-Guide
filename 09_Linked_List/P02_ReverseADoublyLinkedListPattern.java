import java.util.*;

/*
Problem: Reverse a Doubly Linked List
Pattern: 09. Linked List
Difficulty: Easy

Interview Prompt:
Given the head of a doubly linked list, reverse it in place and return the new head.

Why this pattern fits:
Reversing a singly linked list needs a trailing pointer. With prev pointers already present,
reversal is simply swapping the two links of each node.

Approach:
Walk the list. At each node swap node.prev and node.next, remember the node as the new head,
and continue with the old next (which is now stored in node.prev).

Worked Example:
1 <-> 2 <-> 3 <-> 4 becomes 4 <-> 3 <-> 2 <-> 1. Walking backward from the new tail gives 1, 2, 3, 4.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P02_ReverseADoublyLinkedListPattern {
    static class Node {
        int val;
        Node prev, next;
        Node(int val) { this.val = val; }
    }

    public static Node reverse(Node head) {
        Node newHead = head;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = current.prev;
            current.prev = next;
            newHead = current;
            current = next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node tail = head;
        for (int value = 2; value <= 4; value++) {
            Node node = new Node(value);
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        Node reversed = reverse(head);
        List<Integer> forward = new ArrayList<>();
        Node last = null;
        for (Node node = reversed; node != null; node = node.next) {
            forward.add(node.val);
            last = node;
        }
        List<Integer> backward = new ArrayList<>();
        for (Node node = last; node != null; node = node.prev) {
            backward.add(node.val);
        }
        System.out.println("forward from new head -> " + forward);
        System.out.println("backward from new tail -> " + backward);
    }
}
