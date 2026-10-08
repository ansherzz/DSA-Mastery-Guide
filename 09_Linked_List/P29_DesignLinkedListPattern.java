import java.util.*;

/*
Problem: Design Linked List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Design your own singly linked list with 0-indexed nodes. Implement:
    - get(index): return the value of the index-th node, or -1 if the index is invalid.
    - addAtHead(val): insert a node before the first node.
    - addAtTail(val): append a node as the last node.
    - addAtIndex(index, val): insert a node before the index-th node. If index equals the length,
      append it. If index is greater than the length, do nothing.
    - deleteAtIndex(index): delete the index-th node if the index is valid.

Why this pattern fits:
Every linked list problem is a combination of these primitives.
    Implementing them once, cleanly, shows the two habits that prevent most bugs:
    a dummy head so the first node is not a special case, and a stored size so invalid indices are rejected early.

Approach:
Keep a dummy head and a size counter.
    A helper walks to the node just before a given index; for index 0 that is the dummy itself.
    Insertion and deletion are then the same two-line splice regardless of position.
    addAtHead and addAtTail are addAtIndex(0) and addAtIndex(size).

Worked Example:
Example:
    addAtHead(1), addAtTail(3), addAtIndex(1, 2) -> 1 -> 2 -> 3
    get(1) -> 2, deleteAtIndex(1) -> 1 -> 3, get(1) -> 3.

Time Complexity: O(index) per operation, O(1) for addAtHead
Space Complexity: O(n)
*/

public class P29_DesignLinkedListPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static class MyLinkedList {
        private final ListNode dummy = new ListNode(0);
        private int size = 0;

        private ListNode nodeBefore(int index) {
            ListNode node = dummy;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }

        int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }
            return nodeBefore(index).next.val;
        }

        void addAtHead(int val) {
            addAtIndex(0, val);
        }

        void addAtTail(int val) {
            addAtIndex(size, val);
        }

        void addAtIndex(int index, int val) {
            if (index < 0 || index > size) {
                return;
            }
            ListNode previous = nodeBefore(index);
            ListNode node = new ListNode(val);
            node.next = previous.next;
            previous.next = node;
            size++;
        }

        void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            ListNode previous = nodeBefore(index);
            previous.next = previous.next.next;
            size--;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (ListNode node = dummy.next; node != null; node = node.next) {
                sb.append(node.val);
                if (node.next != null) {
                    sb.append(" -> ");
                }
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 2);
        System.out.println("List  : " + list);
        System.out.println("get(1): " + list.get(1));

        list.deleteAtIndex(1);
        System.out.println("List  : " + list);
        System.out.println("get(1): " + list.get(1));
        System.out.println("get(5): " + list.get(5));

        list.addAtIndex(9, 42);
        System.out.println("List  : " + list);
    }
}
