import java.util.*;

/*
Problem: Subarrays with K Different Integers
Pattern: 04. Sliding Window
Difficulty: Hard

Interview Prompt:
Given an integer array nums and an integer k, return the number of contiguous subarrays
    that contain exactly k different integers.

Why this pattern fits:
"Exactly k distinct" is not monotonic, so a single window cannot count it directly.
    "At most k distinct" is monotonic, and exactly(k) = atMost(k) - atMost(k - 1).
    Each atMost count is a standard variable-size window with a frequency map.

Approach:
Helper atMost(limit): expand right while counting values in a map.
    While the map has more than limit keys, shrink from the left.
    Every subarray ending at right and starting between left and right is valid, so add (right - left + 1).
    Return atMost(k) - atMost(k - 1).

Worked Example:
Example:
    nums = [1, 2, 1, 2, 3], k = 2
    Subarrays with exactly 2 distinct values:
    [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]. Answer = 7.

Time Complexity: O(n)
Space Complexity: O(k)
*/

public class P29_SubarraysWithKDistinctSlidingWindow {
    public static int subarraysWithKDistinct(int[] nums, int k) {
        return countAtMost(nums, k) - countAtMost(nums, k - 1);
    }

    private static int countAtMost(int[] nums, int limit) {
        Map<Integer, Integer> count = new HashMap<>();
        int left = 0;
        int total = 0;

        for (int right = 0; right < nums.length; right++) {
            count.merge(nums[right], 1, Integer::sum);

            while (count.size() > limit) {
                int leaving = nums[left++];
                if (count.merge(leaving, -1, Integer::sum) == 0) {
                    count.remove(leaving);
                }
            }
            total += right - left + 1;
        }
        return total;
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Array: " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("Count: " + subarraysWithKDistinct(nums, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 1, 2, 3}, 2);
        runDemo(new int[]{1, 2, 1, 3, 4}, 3);
    }
}
