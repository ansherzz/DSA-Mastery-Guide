import java.util.*;

/*
Problem: Sliding Window Median
Pattern: 04. Sliding Window
Difficulty: Hard

Interview Prompt:
Given an array nums and a window size k, return the median of every window of size k
as the window slides from left to right.

Why this pattern fits:
The window needs insert, delete of an arbitrary element, and median, all in O(log k).
Two heaps cannot delete arbitrary elements efficiently, so two ordered sets (keyed by value, then index) are used.

Approach:
`low` holds the smaller half and `high` the larger half (high may hold one extra).
To slide: remove the outgoing index from whichever set contains it, add the new index to high,
move high's smallest to low, and rebalance if low became larger. The median comes from the boundary elements.

Worked Example:
nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3 -> medians 1, -1, -1, 3, 5, 6.

Time Complexity: O(n log k)
Space Complexity: O(k)
*/

public class P49_SlidingWindowMedianSlidingWindow {
    public static double[] medianSlidingWindow(int[] nums, int k) {
        Comparator<Integer> byValue = (a, b) ->
                nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : Integer.compare(a, b);
        TreeSet<Integer> low = new TreeSet<>(byValue);
        TreeSet<Integer> high = new TreeSet<>(byValue);
        double[] medians = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (i >= k && !low.remove(i - k)) {
                high.remove(i - k);
            }
            high.add(i);
            low.add(high.pollFirst());
            if (low.size() > high.size()) {
                high.add(low.pollLast());
            }
            if (i >= k - 1) {
                medians[i - k + 1] = k % 2 == 1
                        ? nums[high.first()]
                        : ((double) nums[low.last()] + nums[high.first()]) / 2;
            }
        }
        return medians;
    }

    public static void main(String[] args) {
        System.out.println("[1, 3, -1, -3, 5, 3, 6, 7], k = 3 -> "
                + Arrays.toString(medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println("[1, 2, 3], k = 2 -> " + Arrays.toString(medianSlidingWindow(new int[]{1, 2, 3}, 2)));
    }
}
