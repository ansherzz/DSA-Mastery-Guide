import java.util.*;

/*
Problem: Flatten a Multilevel Doubly Linked List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Nodes of a doubly linked list may have a child pointer to another doubly linked list, recursively.
Flatten the structure so that all nodes appear in a single level, with child lists placed
directly after their parent. All child pointers must be null afterwards.

Why this pattern fits:
Each child list simply needs to be inserted right after its parent.
Doing this while scanning forward handles deeper levels automatically, because spliced-in nodes are visited later.

Approach:
For each node with a child: find the tail of the child list, connect tail <-> node.next,
connect node <-> child, and clear the child pointer. Then continue to the next node.

Worked Example:
1-2-3-4-5-6 where 3 has child 7-8-9-10 and 8 has child 11-12
-> 1, 2, 3, 7, 8, 11, 12, 9, 10, 4, 5, 6.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P28_FlattenAMultilevelDoublyLinkedListPattern {
    static class Node {
        int val;
        Node prev, next, child;
        Node(int val) { this.val = val; }
    }

    public static Node flatten(Node head) {
        for (Node node = head; node != null; node = node.next) {
            if (node.child == null) {
                continue;
            }
            Node tail = node.child;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = node.next;
            if (node.next != null) {
                node.next.prev = tail;
            }
            node.next = node.child;
            node.child.prev = node;
            node.child = null;
        }
        return head;
    }

    private static Node buildLevel(int from, int to) {
        Node head = new Node(from);
        Node tail = head;
        for (int value = from + 1; value <= to; value++) {
            Node node = new Node(value);
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        return head;
    }

    public static void main(String[] args) {
        Node top = buildLevel(1, 6);
        Node middle = buildLevel(7, 10);
        Node bottom = buildLevel(11, 12);
        top.next.next.child = middle;
        middle.next.child = bottom;
        List<Integer> values = new ArrayList<>();
        for (Node node = flatten(top); node != null; node = node.next) {
            values.add(node.val);
        }
        System.out.println("flattened -> " + values);
    }
}
