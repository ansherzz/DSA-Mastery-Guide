import java.util.*;

/*
Problem: Number of Subarrays with Bounded Maximum
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given an array and two integers left and right, return the number of contiguous non-empty subarrays
whose maximum element is in the range [left, right].

Why this pattern fits:
"Maximum within a range" is hard to count directly, but "maximum at most B" is easy:
it is the number of subarrays made only of values <= B. The answer is a difference of two such counts.

Approach:
count(B): scan while tracking the length of the current run of values <= B; each element adds the run length.
Answer = count(right) - count(left - 1).

Worked Example:
nums = [2, 1, 4, 3], left = 2, right = 3 -> [2], [2, 1], [3]. Answer = 3.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P30_NumberOfSubarraysWithBoundedMaximumSlidingWindow {
    private static int countAtMost(int[] nums, int bound) {
        int total = 0;
        int run = 0;
        for (int value : nums) {
            run = value <= bound ? run + 1 : 0;
            total += run;
        }
        return total;
    }

    public static int numSubarrayBoundedMax(int[] nums, int left, int right) {
        return countAtMost(nums, right) - countAtMost(nums, left - 1);
    }

    public static void main(String[] args) {
        System.out.println("[2, 1, 4, 3], 2..3 -> " + numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 2, 3));
        System.out.println("[2, 9, 2, 5, 6], 2..8 -> " + numSubarrayBoundedMax(new int[]{2, 9, 2, 5, 6}, 2, 8));
    }
}
