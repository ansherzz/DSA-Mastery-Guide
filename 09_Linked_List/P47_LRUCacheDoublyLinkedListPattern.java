import java.util.*;

/*
Problem: LRU Cache (Hand-Built Doubly Linked List)
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Design a Least Recently Used cache with get(key) and put(key, value) in O(1).
When the capacity is exceeded, evict the least recently used key. Build the linked list yourself.

Why this pattern fits:
Recency order changes on every access: a node must be removed from the middle and re-inserted at the front in O(1).
Only a doubly linked list supports that, and a map supplies the node for a key.

Approach:
Sentinel head and tail nodes remove all null checks. Most recent sits after head, least recent before tail.
get: move the node to the front. put: update or insert at the front; if over capacity, unlink tail.prev and drop its key.

Worked Example:
capacity 2: put(1, 1), put(2, 2), get(1) = 1, put(3, 3) evicts 2, get(2) = -1,
put(4, 4) evicts 1, get(1) = -1, get(3) = 3, get(4) = 4.

Time Complexity: O(1) per operation
Space Complexity: O(capacity)
*/

public class P47_LRUCacheDoublyLinkedListPattern {
    static class LRUCache {
        private static class Node {
            int key, value;
            Node prev, next;
            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private final int capacity;
        private final Map<Integer, Node> nodes = new HashMap<>();
        private final Node head = new Node(0, 0);
        private final Node tail = new Node(0, 0);

        LRUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        private void unlink(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void addToFront(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        public int get(int key) {
            Node node = nodes.get(key);
            if (node == null) {
                return -1;
            }
            unlink(node);
            addToFront(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node node = nodes.get(key);
            if (node != null) {
                node.value = value;
                unlink(node);
                addToFront(node);
                return;
            }
            node = new Node(key, value);
            nodes.put(key, node);
            addToFront(node);
            if (nodes.size() > capacity) {
                Node oldest = tail.prev;
                unlink(oldest);
                nodes.remove(oldest.key);
            }
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("get(1) -> " + cache.get(1));
        cache.put(3, 3);
        System.out.println("get(2) -> " + cache.get(2));
        cache.put(4, 4);
        System.out.println("get(1) -> " + cache.get(1));
        System.out.println("get(3) -> " + cache.get(3));
        System.out.println("get(4) -> " + cache.get(4));
    }
}
