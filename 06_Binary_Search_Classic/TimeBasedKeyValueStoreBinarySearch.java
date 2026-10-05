import java.util.*;

/*
Problem: Time Based Key-Value Store
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
Design a key-value store that can hold multiple values for the same key at different timestamps.
    - set(key, value, timestamp) stores the value for the key at the given time.
    - get(key, timestamp) returns the value that was set with the largest timestamp less than or equal
      to the given timestamp. If there is no such value, return an empty string.

    All set calls for a key arrive with strictly increasing timestamps.

Why this pattern fits:
Because timestamps for a key only increase, each key's history is already a sorted list.
    A get is then "find the last entry with timestamp <= t", which is a floor binary search.

Approach:
Keep a map from key to a list of (timestamp, value) entries, appended in order.
    For get, binary search that list for the rightmost entry whose timestamp does not exceed the query time.
    Track the best candidate while moving right whenever the middle entry qualifies.

Worked Example:
Example:
    set("foo", "bar", 1)
    get("foo", 1) -> "bar"
    get("foo", 3) -> "bar"   (latest value at or before time 3)
    set("foo", "bar2", 4)
    get("foo", 4) -> "bar2"
    get("foo", 5) -> "bar2"
    get("foo", 0) -> ""      (nothing was set yet)

Time Complexity: set: O(1), get: O(log n)
Space Complexity: O(n)
*/

public class TimeBasedKeyValueStoreBinarySearch {
    static class TimeMap {
        private static class Entry {
            final int timestamp;
            final String value;
            Entry(int timestamp, String value) {
                this.timestamp = timestamp;
                this.value = value;
            }
        }

        private final Map<String, List<Entry>> history = new HashMap<>();

        public void set(String key, String value, int timestamp) {
            history.computeIfAbsent(key, unused -> new ArrayList<>()).add(new Entry(timestamp, value));
        }

        public String get(String key, int timestamp) {
            List<Entry> entries = history.get(key);
            if (entries == null) {
                return "";
            }

            int left = 0;
            int right = entries.size() - 1;
            String answer = "";

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (entries.get(mid).timestamp <= timestamp) {
                    answer = entries.get(mid).value;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        TimeMap store = new TimeMap();
        store.set("foo", "bar", 1);
        System.out.println("get(foo, 1) -> \"" + store.get("foo", 1) + "\"");
        System.out.println("get(foo, 3) -> \"" + store.get("foo", 3) + "\"");
        store.set("foo", "bar2", 4);
        System.out.println("get(foo, 4) -> \"" + store.get("foo", 4) + "\"");
        System.out.println("get(foo, 5) -> \"" + store.get("foo", 5) + "\"");
        System.out.println("get(foo, 0) -> \"" + store.get("foo", 0) + "\"");
    }
}
