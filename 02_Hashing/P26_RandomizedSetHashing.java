import java.util.*;

/*
Problem: Insert Delete GetRandom O(1)
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
Design a set of integers that supports the following operations in O(1) average time:
    - insert(val): add val if it is not present; return true if it was added
    - remove(val): remove val if it is present; return true if it was removed
    - getRandom(): return a random element, where every element has the same probability

Why this pattern fits:
A hash set handles insert and remove but cannot pick a uniformly random element in O(1).
    A list can pick a random index in O(1) but cannot remove an arbitrary value quickly.
    A hash map from value to its list index bridges the two.

Approach:
Store values in an ArrayList and keep a map from value to its index in that list.
    To insert, append to the list and record the index.
    To remove, overwrite the removed slot with the last value, update that value's index, and pop the last slot.
    To get a random element, pick a random index in the list.

Worked Example:
Example:
    insert(1) -> true, remove(2) -> false, insert(2) -> true
    The list is [1, 2]. remove(1) moves 2 into slot 0 and shrinks the list to [2].
    insert(2) -> false because 2 is already present. getRandom() must now return 2.

Time Complexity: All operations: O(1) average
Space Complexity: O(n)
*/

public class P26_RandomizedSetHashing {
    static class RandomizedSet {
        private final List<Integer> values = new ArrayList<>();
        private final Map<Integer, Integer> indexByValue = new HashMap<>();
        private final Random random = new Random();

        public boolean insert(int val) {
            if (indexByValue.containsKey(val)) {
                return false;
            }
            indexByValue.put(val, values.size());
            values.add(val);
            return true;
        }

        public boolean remove(int val) {
            Integer index = indexByValue.remove(val);
            if (index == null) {
                return false;
            }

            int lastIndex = values.size() - 1;
            int lastValue = values.get(lastIndex);
            if (index != lastIndex) {
                values.set(index, lastValue);
                indexByValue.put(lastValue, index);
            }
            values.remove(lastIndex);
            return true;
        }

        public int getRandom() {
            return values.get(random.nextInt(values.size()));
        }
    }

    public static void main(String[] args) {
        RandomizedSet set = new RandomizedSet();
        System.out.println("insert(1)   -> " + set.insert(1));
        System.out.println("remove(2)   -> " + set.remove(2));
        System.out.println("insert(2)   -> " + set.insert(2));
        System.out.println("remove(1)   -> " + set.remove(1));
        System.out.println("insert(2)   -> " + set.insert(2));
        System.out.println("getRandom() -> " + set.getRandom());
    }
}
