import java.util.*;

/*
Problem: Median of Medians (Worst-Case Linear Selection)
Pattern: 15. Divide & Conquer
Difficulty: Hard

Interview Prompt:
Return the k-th smallest element (1-indexed) of an unsorted array in O(n) time in the worst case.

Why this pattern fits:
Quickselect is linear on average but quadratic with unlucky pivots.
Picking the median of the medians of groups of five guarantees that at least about 30% of the elements
fall on each side, which makes the recurrence T(n) = T(n / 5) + T(7n / 10) + O(n) linear.

Approach:
If the list has at most 5 elements, sort it. Otherwise take the median of each group of 5,
recursively select the median of those medians as the pivot, partition into smaller / equal / larger,
and recurse into the part that contains rank k.

Worked Example:
[12, 3, 5, 7, 4, 19, 26], k = 3 -> sorted order is 3, 4, 5, 7, 12, 19, 26, so the answer is 5.

Time Complexity: O(n) worst case
Space Complexity: O(n)
*/

public class P50_MedianOfMediansSelectDivideConquer {
    private static int select(List<Integer> values, int rank) {
        if (values.size() <= 5) {
            List<Integer> sorted = new ArrayList<>(values);
            Collections.sort(sorted);
            return sorted.get(rank);
        }
        List<Integer> medians = new ArrayList<>();
        for (int start = 0; start < values.size(); start += 5) {
            List<Integer> group = new ArrayList<>(values.subList(start, Math.min(start + 5, values.size())));
            Collections.sort(group);
            medians.add(group.get(group.size() / 2));
        }
        int pivot = select(medians, medians.size() / 2);
        List<Integer> smaller = new ArrayList<>();
        List<Integer> larger = new ArrayList<>();
        int equal = 0;
        for (int value : values) {
            if (value < pivot) {
                smaller.add(value);
            } else if (value > pivot) {
                larger.add(value);
            } else {
                equal++;
            }
        }
        if (rank < smaller.size()) {
            return select(smaller, rank);
        }
        if (rank < smaller.size() + equal) {
            return pivot;
        }
        return select(larger, rank - smaller.size() - equal);
    }

    public static int kthSmallest(int[] nums, int k) {
        List<Integer> values = new ArrayList<>();
        for (int value : nums) {
            values.add(value);
        }
        return select(values, k - 1);
    }

    public static void main(String[] args) {
        System.out.println("[12, 3, 5, 7, 4, 19, 26], k = 3 -> " + kthSmallest(new int[]{12, 3, 5, 7, 4, 19, 26}, 3));
        System.out.println("[12, 3, 5, 7, 4, 19, 26], k = 4 -> " + kthSmallest(new int[]{12, 3, 5, 7, 4, 19, 26}, 4));
        System.out.println("16 values, k = 10 -> "
                + kthSmallest(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 15, 14, 13, 12, 11, 10}, 10));
    }
}
