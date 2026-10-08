import java.util.*;

/*
Problem: Count Number of Nice Subarrays
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given an array of integers nums and an integer k, a continuous subarray is called nice
    if it contains exactly k odd numbers. Return the number of nice subarrays.

Why this pattern fits:
"Exactly k" is not monotonic, so a single window cannot count it directly.
    "At most k" is monotonic, and exactly(k) = atMost(k) - atMost(k - 1).

Approach:
Write a helper that counts subarrays containing at most k odd numbers.
    Expand right, and shrink left while the window holds more than k odds.
    Every subarray ending at right and starting anywhere in [left, right] is valid,
    which adds right - left + 1 to the count.
    The final answer is atMost(k) - atMost(k - 1).

Worked Example:
Example:
    nums = [1, 1, 2, 1, 1], k = 3
    The nice subarrays are [1, 1, 2, 1] and [1, 2, 1, 1]. Answer = 2.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P23_CountNumberOfNiceSubarraysSlidingWindow {
    public static int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private static int atMost(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }

        int left = 0;
        int odds = 0;
        int count = 0;
        for (int right = 0; right < nums.length; right++) {
            odds += nums[right] & 1;
            while (odds > k) {
                odds -= nums[left++] & 1;
            }
            count += right - left + 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(numberOfSubarrays(new int[]{1, 1, 2, 1, 1}, 3));
        System.out.println(numberOfSubarrays(new int[]{2, 4, 6}, 1));
        System.out.println(numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2));
    }
}
