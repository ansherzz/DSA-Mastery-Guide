import java.util.*;

/*
Problem: Range Frequency Queries
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
Design a structure built from an array that answers query(left, right, value):
the number of occurrences of value in arr[left..right].

Why this pattern fits:
The positions of each value form a sorted list.
Counting how many fall in a range is upperBound(right) - lowerBound(left) on that list.

Approach:
Build a map from value to the increasing list of its indices.
For a query, binary search that list for the first index >= left and the first index > right.

Worked Example:
arr = [12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]
query(1, 2, 4) = 1 and query(0, 11, 33) = 2.

Time Complexity: O(log n) per query after O(n) preprocessing
Space Complexity: O(n)
*/

public class P36_RangeFrequencyQueriesBinarySearch {
    static class RangeFreqQuery {
        private final Map<Integer, List<Integer>> positions = new HashMap<>();

        RangeFreqQuery(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                positions.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
            }
        }

        private static int firstAtLeast(List<Integer> list, int target) {
            int low = 0;
            int high = list.size();
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (list.get(mid) < target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }

        public int query(int left, int right, int value) {
            List<Integer> list = positions.get(value);
            if (list == null) {
                return 0;
            }
            return firstAtLeast(list, right + 1) - firstAtLeast(list, left);
        }
    }

    public static void main(String[] args) {
        RangeFreqQuery structure = new RangeFreqQuery(new int[]{12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56});
        System.out.println("query(1, 2, 4) -> " + structure.query(1, 2, 4));
        System.out.println("query(0, 11, 33) -> " + structure.query(0, 11, 33));
        System.out.println("query(0, 3, 22) -> " + structure.query(0, 3, 22));
    }
}
