import java.util.*;

/*
Problem: LFU Cache
Pattern: 02. Hashing
Difficulty: Hard

Interview Prompt:
Design a Least Frequently Used cache with get(key) and put(key, value) in O(1).
When the cache is full, evict the key with the smallest use count; break ties by least recently used.

Why this pattern fits:
Eviction needs "the least recent key among those with the lowest frequency" in O(1).
Grouping keys by frequency in insertion-ordered sets and tracking the minimum frequency gives exactly that.

Approach:
Three maps: values, frequencies, and frequency -> LinkedHashSet of keys.
Touching a key moves it from set f to set f + 1; if set f was the minimum and becomes empty, the minimum increases.
Inserting a new key evicts the first key of the minimum-frequency set if needed, then resets the minimum to 1.

Worked Example:
capacity = 2: put(1,1), put(2,2), get(1) = 1, put(3,3) evicts key 2, get(2) = -1, get(3) = 3,
put(4,4) evicts key 1 (same frequency as 3 but older), get(1) = -1, get(3) = 3, get(4) = 4.

Time Complexity: O(1) per operation
Space Complexity: O(capacity)
*/

public class P50_LFUCacheHashing {
    static class LFUCache {
        private final int capacity;
        private int minFrequency = 0;
        private final Map<Integer, Integer> values = new HashMap<>();
        private final Map<Integer, Integer> frequency = new HashMap<>();
        private final Map<Integer, LinkedHashSet<Integer>> keysByFrequency = new HashMap<>();

        LFUCache(int capacity) {
            this.capacity = capacity;
        }

        private void touch(int key) {
            int f = frequency.get(key);
            LinkedHashSet<Integer> keys = keysByFrequency.get(f);
            keys.remove(key);
            if (keys.isEmpty()) {
                keysByFrequency.remove(f);
                if (minFrequency == f) {
                    minFrequency++;
                }
            }
            frequency.put(key, f + 1);
            keysByFrequency.computeIfAbsent(f + 1, k -> new LinkedHashSet<>()).add(key);
        }

        public int get(int key) {
            if (!values.containsKey(key)) {
                return -1;
            }
            touch(key);
            return values.get(key);
        }

        public void put(int key, int value) {
            if (capacity == 0) {
                return;
            }
            if (values.containsKey(key)) {
                values.put(key, value);
                touch(key);
                return;
            }
            if (values.size() == capacity) {
                LinkedHashSet<Integer> keys = keysByFrequency.get(minFrequency);
                int evict = keys.iterator().next();
                keys.remove(evict);
                values.remove(evict);
                frequency.remove(evict);
            }
            values.put(key, value);
            frequency.put(key, 1);
            keysByFrequency.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
            minFrequency = 1;
        }
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("get(1) -> " + cache.get(1));
        cache.put(3, 3);
        System.out.println("get(2) -> " + cache.get(2));
        System.out.println("get(3) -> " + cache.get(3));
        cache.put(4, 4);
        System.out.println("get(1) -> " + cache.get(1));
        System.out.println("get(3) -> " + cache.get(3));
        System.out.println("get(4) -> " + cache.get(4));
    }
}
