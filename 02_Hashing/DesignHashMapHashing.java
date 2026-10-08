import java.util.*;

/*
Problem: Design HashMap
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Design a HashMap without using any built-in hash table libraries. Implement:
    - put(key, value): insert the pair, or update the value if the key already exists.
    - get(key): return the value mapped to key, or -1 if the key is not present.
    - remove(key): remove the key and its value if present.
    Keys and values are non-negative integers.

Why this pattern fits:
Every other problem in this folder relies on a hash table as a black box.
    Building one shows where the O(1) average cost comes from and why collisions matter.

Approach:
Use an array of buckets, where each bucket is a singly linked chain of (key, value) nodes (separate chaining).
    The bucket index is key % bucketCount.
    All three operations walk only the chain of one bucket. Each chain starts with a dummy head
    so that removal never needs a special case for the first node.

Worked Example:
Example:
    put(1, 1), put(2, 2), get(1) -> 1, get(3) -> -1,
    put(2, 1) updates the value, get(2) -> 1, remove(2), get(2) -> -1.

Time Complexity: O(1) average per operation, O(n) worst case if all keys collide
Space Complexity: O(bucketCount + number of stored keys)
*/

public class DesignHashMapHashing {
    static class Node {
        int key;
        int value;
        Node next;
        Node(int key, int value) { this.key = key; this.value = value; }
    }

    static class MyHashMap {
        private static final int BUCKETS = 1009;
        private final Node[] buckets = new Node[BUCKETS];

        private Node findPrevious(int key) {
            int index = key % BUCKETS;
            if (buckets[index] == null) {
                buckets[index] = new Node(-1, -1);
            }
            Node previous = buckets[index];
            while (previous.next != null && previous.next.key != key) {
                previous = previous.next;
            }
            return previous;
        }

        void put(int key, int value) {
            Node previous = findPrevious(key);
            if (previous.next == null) {
                previous.next = new Node(key, value);
            } else {
                previous.next.value = value;
            }
        }

        int get(int key) {
            Node previous = findPrevious(key);
            return previous.next == null ? -1 : previous.next.value;
        }

        void remove(int key) {
            Node previous = findPrevious(key);
            if (previous.next != null) {
                previous.next = previous.next.next;
            }
        }
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put(1, 1);
        map.put(2, 2);
        System.out.println(map.get(1));
        System.out.println(map.get(3));
        map.put(2, 1);
        System.out.println(map.get(2));
        map.remove(2);
        System.out.println(map.get(2));

        map.put(1010, 77);
        System.out.println("Colliding keys 1 and 1010: " + map.get(1) + ", " + map.get(1010));
    }
}
