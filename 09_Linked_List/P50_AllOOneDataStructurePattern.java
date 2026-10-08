import java.util.*;

/*
Problem: All O`one Data Structure
Pattern: 09. Linked List
Difficulty: Hard

Interview Prompt:
Design a structure with inc(key), dec(key), getMaxKey(), and getMinKey(), all in O(1).
dec removes a key whose count drops to 0. The getters return "" when the structure is empty.

Why this pattern fits:
Counts only change by one, so a key only ever moves to a neighboring bucket.
A sorted doubly linked list of buckets gives the min at the front, the max at the back,
and O(1) moves between neighbors.

Approach:
Map each key to its bucket. inc: move the key to the bucket with count + 1 right after the current one,
creating it if missing. dec: move it to count - 1 right before, or drop the key at count 1.
Remove any bucket that becomes empty. Sentinel head and tail simplify the edges.

Worked Example:
inc(hello) twice, inc(leet): max = hello (2), min = leet (1).
dec(hello) twice removes hello: max = leet. dec(leet): the structure is empty.

Time Complexity: O(1) per operation
Space Complexity: O(n)
*/

public class P50_AllOOneDataStructurePattern {
    static class AllOne {
        private static class Bucket {
            int count;
            Set<String> keys = new LinkedHashSet<>();
            Bucket prev, next;
            Bucket(int count) { this.count = count; }
        }

        private final Bucket head = new Bucket(0);
        private final Bucket tail = new Bucket(0);
        private final Map<String, Bucket> bucketOf = new HashMap<>();

        AllOne() {
            head.next = tail;
            tail.prev = head;
        }

        private Bucket insertAfter(Bucket bucket, int count) {
            Bucket created = new Bucket(count);
            created.prev = bucket;
            created.next = bucket.next;
            bucket.next.prev = created;
            bucket.next = created;
            return created;
        }

        private void leave(Bucket bucket, String key) {
            bucket.keys.remove(key);
            if (bucket.keys.isEmpty()) {
                bucket.prev.next = bucket.next;
                bucket.next.prev = bucket.prev;
            }
        }

        public void inc(String key) {
            Bucket current = bucketOf.getOrDefault(key, head);
            Bucket target = current.next;
            if (target == tail || target.count != current.count + 1) {
                target = insertAfter(current, current.count + 1);
            }
            target.keys.add(key);
            bucketOf.put(key, target);
            if (current != head) {
                leave(current, key);
            }
        }

        public void dec(String key) {
            Bucket current = bucketOf.get(key);
            if (current.count == 1) {
                bucketOf.remove(key);
            } else {
                Bucket target = current.prev;
                if (target == head || target.count != current.count - 1) {
                    target = insertAfter(current.prev, current.count - 1);
                }
                target.keys.add(key);
                bucketOf.put(key, target);
            }
            leave(current, key);
        }

        public String getMaxKey() {
            return tail.prev == head ? "" : tail.prev.keys.iterator().next();
        }

        public String getMinKey() {
            return head.next == tail ? "" : head.next.keys.iterator().next();
        }
    }

    public static void main(String[] args) {
        AllOne counter = new AllOne();
        counter.inc("hello");
        counter.inc("hello");
        counter.inc("leet");
        System.out.println("getMaxKey() -> \"" + counter.getMaxKey() + "\"");
        System.out.println("getMinKey() -> \"" + counter.getMinKey() + "\"");
        counter.dec("hello");
        counter.dec("hello");
        System.out.println("getMaxKey() after removing hello -> \"" + counter.getMaxKey() + "\"");
        counter.dec("leet");
        System.out.println("getMinKey() when empty -> \"" + counter.getMinKey() + "\"");
    }
}
