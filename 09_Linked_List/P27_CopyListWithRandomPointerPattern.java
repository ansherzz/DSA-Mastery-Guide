import java.util.*;

/*
Problem: Copy List with Random Pointer
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
A linked list of length n is given where each node has a next pointer and an additional random pointer,
    which may point to any node in the list or to null.
    Construct a deep copy of the list: n brand-new nodes whose next and random pointers reproduce the same structure.
    No pointer in the copy may point to a node of the original list.

    Follow-up: do it without a hash map.

Why this pattern fits:
The difficulty is that a random pointer may refer to a node whose copy does not exist yet.
    The interleaving trick solves this with pointers alone: placing each copy right after its original
    means the copy of any node X is always X.next.

Approach:
Pass 1: insert a copy node after every original node, giving A -> A' -> B -> B' -> ...
    Pass 2: set each copy's random pointer: original.next.random = original.random.next (when random is not null).
    Pass 3: unweave the two lists, restoring the original and extracting the copy.

Worked Example:
Example:
    Nodes (value, random index): (7, null), (13, 0), (11, 4), (10, 2), (1, 0)
    The copy has the same values and the same random targets by index, but uses entirely new node objects.

Time Complexity: O(n)
Space Complexity: O(1) extra, excluding the copied list
*/

public class P27_CopyListWithRandomPointerPattern {
    static class Node {
        int val;
        Node next;
        Node random;
        Node(int val) { this.val = val; }
    }

    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        for (Node node = head; node != null; node = node.next.next) {
            Node copy = new Node(node.val);
            copy.next = node.next;
            node.next = copy;
        }

        for (Node node = head; node != null; node = node.next.next) {
            if (node.random != null) {
                node.next.random = node.random.next;
            }
        }

        Node copyHead = head.next;
        for (Node node = head; node != null; node = node.next) {
            Node copy = node.next;
            node.next = copy.next;
            if (copy.next != null) {
                copy.next = copy.next.next;
            }
        }
        return copyHead;
    }

    private static Node build(int[] values, Integer[] randomIndex) {
        Node[] nodes = new Node[values.length];
        for (int i = 0; i < values.length; i++) {
            nodes[i] = new Node(values[i]);
            if (i > 0) {
                nodes[i - 1].next = nodes[i];
            }
        }
        for (int i = 0; i < values.length; i++) {
            if (randomIndex[i] != null) {
                nodes[i].random = nodes[randomIndex[i]];
            }
        }
        return nodes[0];
    }

    private static String stringify(Node head) {
        StringBuilder sb = new StringBuilder();
        for (Node node = head; node != null; node = node.next) {
            sb.append("(").append(node.val).append(", random=")
              .append(node.random == null ? "null" : String.valueOf(node.random.val)).append(")");
            if (node.next != null) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Node original = build(new int[]{7, 13, 11, 10, 1}, new Integer[]{null, 0, 4, 2, 0});
        Node copy = copyRandomList(original);

        System.out.println("Original: " + stringify(original));
        System.out.println("Copy    : " + stringify(copy));

        boolean sharesNodes = false;
        for (Node a = original, b = copy; a != null; a = a.next, b = b.next) {
            if (a == b) {
                sharesNodes = true;
            }
        }
        System.out.println("Copy shares nodes with original? " + sharesNodes);
    }
}
