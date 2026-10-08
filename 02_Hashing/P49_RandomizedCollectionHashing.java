import java.util.*;

/*
Problem: Insert Delete GetRandom O(1) - Duplicates Allowed
Pattern: 02. Hashing
Difficulty: Hard

Interview Prompt:
Design a multiset supporting insert(val), remove(val) (removes one copy), and getRandom()
in average O(1) time. getRandom must return each element with probability proportional to its count.

Why this pattern fits:
Random access needs an array; O(1) lookup needs a hash map.
With duplicates, the map must hold every index of a value, so it maps to a set of indices.

Approach:
insert: append to the list and record the index.
remove: take any index of val, move the last list element into that slot, fix the moved element's index set,
and drop the last slot. getRandom: pick a random list index.

Worked Example:
insert(1) = true, insert(1) = false (already present), insert(2) = true,
remove(1) = true twice, remove(1) = false, leaving [2].

Time Complexity: O(1) average per operation
Space Complexity: O(n)
*/

public class P49_RandomizedCollectionHashing {
    static class RandomizedCollection {
        private final List<Integer> values = new ArrayList<>();
        private final Map<Integer, Set<Integer>> indices = new HashMap<>();
        private final Random random = new Random();

        public boolean insert(int val) {
            Set<Integer> set = indices.computeIfAbsent(val, k -> new LinkedHashSet<>());
            set.add(values.size());
            values.add(val);
            return set.size() == 1;
        }

        public boolean remove(int val) {
            Set<Integer> set = indices.get(val);
            if (set == null) {
                return false;
            }
            int index = set.iterator().next();
            set.remove(index);
            int lastIndex = values.size() - 1;
            int lastValue = values.get(lastIndex);
            if (index != lastIndex) {
                values.set(index, lastValue);
                indices.get(lastValue).remove(lastIndex);
                indices.get(lastValue).add(index);
            }
            values.remove(lastIndex);
            if (set.isEmpty()) {
                indices.remove(val);
            }
            return true;
        }

        public int getRandom() {
            return values.get(random.nextInt(values.size()));
        }
    }

    public static void main(String[] args) {
        RandomizedCollection collection = new RandomizedCollection();
        System.out.println("insert(1) -> " + collection.insert(1));
        System.out.println("insert(1) -> " + collection.insert(1));
        System.out.println("insert(2) -> " + collection.insert(2));
        System.out.println("remove(1) -> " + collection.remove(1));
        System.out.println("remove(1) -> " + collection.remove(1));
        System.out.println("remove(1) -> " + collection.remove(1));
        System.out.println("contents -> " + collection.values);
        System.out.println("getRandom() -> " + collection.getRandom());
    }
}
