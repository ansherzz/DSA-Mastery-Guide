import java.util.*;

/*
Problem: Maximum Sum of Distinct Subarrays With Length K
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given an integer array nums and an integer k, find the maximum sum among all subarrays that
    have length exactly k and contain only distinct elements.
    If no such subarray exists, return 0.

Why this pattern fits:
The length is fixed at k, so the candidates are consecutive windows.
    Both the sum and the "all distinct" check can be updated in O(1) as one value leaves and one enters.

Approach:
Maintain the window sum and a frequency map of the values inside the window.
    When the window grows beyond k, remove the leftmost value from both.
    A window of length k is all-distinct exactly when the map has k keys; in that case compare its sum with the best.

Worked Example:
Example:
    nums = [1, 5, 4, 2, 9, 9, 9], k = 3
    Windows: [1,5,4] sum 10, [5,4,2] sum 11, [4,2,9] sum 15, [2,9,9] invalid, [9,9,9] invalid.
    Answer = 15.

Time Complexity: O(n)
Space Complexity: O(k)
*/

public class P23_MaximumSumOfDistinctSubarraysSlidingWindow {
    public static long maximumSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        long windowSum = 0;
        long best = 0;

        for (int right = 0; right < nums.length; right++) {
            windowSum += nums[right];
            count.merge(nums[right], 1, Integer::sum);

            if (right >= k) {
                int leaving = nums[right - k];
                windowSum -= leaving;
                if (count.merge(leaving, -1, Integer::sum) == 0) {
                    count.remove(leaving);
                }
            }

            if (right >= k - 1 && count.size() == k) {
                best = Math.max(best, windowSum);
            }
        }
        return best;
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Array: " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("Best : " + maximumSubarraySum(nums, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 5, 4, 2, 9, 9, 9}, 3);
        runDemo(new int[]{4, 4, 4}, 3);
    }
}
