import java.util.*;

/*
Problem: Insert into a Sorted Circular Linked List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
A circular linked list is sorted in non-decreasing order, but the given node may be any node of the ring.
Insert a value so that the list stays sorted, and return the original node (or the new node if the list was empty).

Why this pattern fits:
A circular list has no head or tail, so the insertion point is found by examining consecutive pairs.
There are exactly two valid kinds of pair: prev <= value <= next, or the max-to-min seam when the value is a new extreme.

Approach:
Starting at the given node, examine (prev, current) pairs. Stop at a pair that fits the value normally,
or at the seam (prev > current) when value >= prev or value <= current. If a full lap finds nothing
(all values equal), insert anywhere.

Worked Example:
ring 3 -> 4 -> 1 -> (back to 3), insert 2 -> it fits between 1 and 3: 3, 4, 1, 2.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P44_InsertIntoASortedCircularLinkedListPattern {
    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    public static Node insert(Node head, int value) {
        Node inserted = new Node(value);
        if (head == null) {
            inserted.next = inserted;
            return inserted;
        }
        Node prev = head;
        Node current = head.next;
        while (true) {
            boolean fits = prev.val <= value && value <= current.val;
            boolean atSeam = prev.val > current.val && (value >= prev.val || value <= current.val);
            if (fits || atSeam) {
                break;
            }
            prev = current;
            current = current.next;
            if (prev == head) {
                break;
            }
        }
        prev.next = inserted;
        inserted.next = current;
        return head;
    }

    private static Node ring(int... values) {
        Node head = new Node(values[0]);
        Node tail = head;
        for (int i = 1; i < values.length; i++) {
            tail.next = new Node(values[i]);
            tail = tail.next;
        }
        tail.next = head;
        return head;
    }

    private static String format(Node head) {
        List<Integer> values = new ArrayList<>();
        Node node = head;
        do {
            values.add(node.val);
            node = node.next;
        } while (node != head);
        return values.toString();
    }

    public static void main(String[] args) {
        System.out.println("ring [3, 4, 1], insert 2 -> " + format(insert(ring(3, 4, 1), 2)));
        System.out.println("ring [3, 5, 1], insert 6 -> " + format(insert(ring(3, 5, 1), 6)));
        System.out.println("empty ring, insert 1 -> " + format(insert(null, 1)));
    }
}
