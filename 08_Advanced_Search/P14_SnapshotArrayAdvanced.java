import java.util.*;

/*
Problem: Snapshot Array
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
Implement a SnapshotArray that supports:
    - SnapshotArray(length): create an array of the given length, initially all zeros
    - set(index, val): set the element at the given index
    - snap(): take a snapshot of the array and return its snap_id (the number of snaps taken so far, minus 1)
    - get(index, snap_id): return the value at the given index as it was when that snapshot was taken

Why this pattern fits:
Copying the whole array on every snap wastes time and memory when few cells change.
    Instead, store for each index only the history of its changes, tagged with the snap id.
    A get then becomes "find the latest change at or before this snap id", a floor binary search.

Approach:
Keep, for every index, a list of [snapId, value] records in increasing snapId order, starting with [0, 0].
    set appends a record for the current snap id, or overwrites the last record if it already has that id.
    snap just increments the current id.
    get binary searches the index's history for the last record whose snapId is <= the requested one.

Worked Example:
Example:
    SnapshotArray(3)
    set(0, 5)
    snap()      -> 0
    set(0, 6)
    get(0, 0)   -> 5   (the value at snapshot 0)
    snap()      -> 1
    get(0, 1)   -> 6

Time Complexity: set and snap: O(1), get: O(log(number of changes at that index))
Space Complexity: O(length + number of set calls)
*/

public class P14_SnapshotArrayAdvanced {
    static class SnapshotArray {
        private final List<List<int[]>> history = new ArrayList<>();
        private int currentSnap = 0;

        SnapshotArray(int length) {
            for (int i = 0; i < length; i++) {
                List<int[]> records = new ArrayList<>();
                records.add(new int[]{0, 0});
                history.add(records);
            }
        }

        public void set(int index, int val) {
            List<int[]> records = history.get(index);
            int[] last = records.get(records.size() - 1);
            if (last[0] == currentSnap) {
                last[1] = val;
            } else {
                records.add(new int[]{currentSnap, val});
            }
        }

        public int snap() {
            return currentSnap++;
        }

        public int get(int index, int snapId) {
            List<int[]> records = history.get(index);
            int left = 0;
            int right = records.size() - 1;

            while (left < right) {
                int mid = left + (right - left + 1) / 2;
                if (records.get(mid)[0] <= snapId) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            return records.get(left)[1];
        }
    }

    public static void main(String[] args) {
        SnapshotArray array = new SnapshotArray(3);
        array.set(0, 5);
        System.out.println("snap()    -> " + array.snap());
        array.set(0, 6);
        System.out.println("get(0, 0) -> " + array.get(0, 0));
        System.out.println("snap()    -> " + array.snap());
        System.out.println("get(0, 1) -> " + array.get(0, 1));
        System.out.println("get(2, 1) -> " + array.get(2, 1));
    }
}
