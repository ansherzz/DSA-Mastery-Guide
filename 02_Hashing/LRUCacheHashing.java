import java.util.*;

/*
Problem: LRU Cache
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
Design a data structure that follows the Least Recently Used (LRU) cache policy.
    - LRUCache(capacity) creates the cache with a positive capacity.
    - get(key) returns the value for the key, or -1 if the key is not present.
    - put(key, value) inserts or updates the key. If the cache exceeds its capacity,
      evict the least recently used key.

    Both get and put must run in O(1) average time.

Why this pattern fits:
A hash map gives O(1) lookup, but it has no notion of recency.
    A doubly linked list gives O(1) reordering and eviction, but no fast lookup.
    Storing list nodes inside the hash map combines both strengths.

Approach:
Keep a doubly linked list ordered from most recently used (after head) to least recently used (before tail).
    The map points each key to its node.
    On get or update, unlink the node and move it right after the head.
    On insert over capacity, remove the node just before the tail and delete its key from the map.

Worked Example:
Example with capacity 2:
    put(1, 1), put(2, 2), get(1) -> 1
    put(3, 3) evicts key 2 because key 1 was used more recently
    get(2) -> -1
    put(4, 4) evicts key 1
    get(1) -> -1, get(3) -> 3, get(4) -> 4

Time Complexity: get and put: O(1) average
Space Complexity: O(capacity)
*/

public class LRUCacheHashing {
    static class LRUCache {
        private static class Node {
            int key;
            int value;
            Node prev;
            Node next;
            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private final int capacity;
        private final Map<Integer, Node> nodeByKey = new HashMap<>();
        private final Node head = new Node(0, 0);
        private final Node tail = new Node(0, 0);

        LRUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            Node node = nodeByKey.get(key);
            if (node == null) {
                return -1;
            }
            unlink(node);
            addFirst(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node node = nodeByKey.get(key);
            if (node != null) {
                node.value = value;
                unlink(node);
                addFirst(node);
                return;
            }

            if (nodeByKey.size() == capacity) {
                Node leastRecent = tail.prev;
                unlink(leastRecent);
                nodeByKey.remove(leastRecent.key);
            }

            Node fresh = new Node(key, value);
            nodeByKey.put(key, fresh);
            addFirst(fresh);
        }

        private void unlink(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
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
