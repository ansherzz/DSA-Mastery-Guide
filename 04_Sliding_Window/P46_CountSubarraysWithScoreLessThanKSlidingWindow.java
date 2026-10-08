import java.util.*;

/*
Problem: Count Subarrays With Score Less Than K
Pattern: 04. Sliding Window
Difficulty: Hard

Interview Prompt:
The score of an array is its sum multiplied by its length.
Given positive integers nums and k, return the number of non-empty subarrays whose score is strictly less than k.

Why this pattern fits:
All values are positive, so extending a window can only raise its score.
For each right end the valid starts are therefore a contiguous suffix, found with a shrinking window.

Approach:
Maintain the window sum. While sum * (right - left + 1) >= k, remove nums[left].
All subarrays ending at right and starting at left..right are valid: add right - left + 1.

Worked Example:
nums = [2, 1, 4, 3, 5], k = 10 -> [2], [1], [4], [3], [5], [2, 1]. Answer = 6.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P46_CountSubarraysWithScoreLessThanKSlidingWindow {
    public static long countSubarrays(int[] nums, long k) {
        long total = 0;
        long sum = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum * (right - left + 1) >= k) {
                sum -= nums[left++];
            }
            total += right - left + 1;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("[2, 1, 4, 3, 5], k = 10 -> " + countSubarrays(new int[]{2, 1, 4, 3, 5}, 10));
        System.out.println("[1, 1, 1], k = 5 -> " + countSubarrays(new int[]{1, 1, 1}, 5));
    }
}
