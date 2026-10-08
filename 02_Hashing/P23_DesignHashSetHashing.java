import java.util.*;

/*
Problem: Design HashSet
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Design a HashSet without using any built-in hash table library.
Support add(key), remove(key), and contains(key) for non-negative integer keys.

Why this pattern fits:
Building the structure yourself shows what "O(1) average" really relies on:
a hash function, an array of buckets, and a collision strategy.

Approach:
Use a fixed array of buckets and key % bucketCount as the hash.
Each bucket is a linked list of keys (separate chaining).
Every operation hashes to one bucket and scans only that bucket.

Worked Example:
add(1), add(2), contains(1) = true, contains(3) = false, add(2), contains(2) = true,
remove(2), contains(2) = false.

Time Complexity: O(1) average per operation
Space Complexity: O(n + bucketCount)
*/

public class P23_DesignHashSetHashing {
    static class MyHashSet {
        private static final int BUCKETS = 769;
        private final List<List<Integer>> buckets = new ArrayList<>();

        MyHashSet() {
            for (int i = 0; i < BUCKETS; i++) {
                buckets.add(new LinkedList<>());
            }
        }

        private List<Integer> bucketOf(int key) {
            return buckets.get(key % BUCKETS);
        }

        public void add(int key) {
            if (!contains(key)) {
                bucketOf(key).add(key);
            }
        }

        public void remove(int key) {
            bucketOf(key).remove(Integer.valueOf(key));
        }

        public boolean contains(int key) {
            return bucketOf(key).contains(key);
        }
    }

    public static void main(String[] args) {
        MyHashSet set = new MyHashSet();
        set.add(1);
        set.add(2);
        System.out.println("contains(1) -> " + set.contains(1));
        System.out.println("contains(3) -> " + set.contains(3));
        set.add(2);
        System.out.println("contains(2) -> " + set.contains(2));
        set.remove(2);
        System.out.println("contains(2) after remove -> " + set.contains(2));
    }
}
