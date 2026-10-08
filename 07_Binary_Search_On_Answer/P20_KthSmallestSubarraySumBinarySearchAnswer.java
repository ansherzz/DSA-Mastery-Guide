import java.util.*;

/*
Problem: Kth Smallest Subarray Sum
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
Given an array of positive integers and an integer k, return the k-th smallest subarray sum.

Why this pattern fits:
There are O(n^2) subarray sums, too many to sort. But "how many sums are <= x" is monotonic in x
and countable in O(n) for positive numbers, so the k-th smallest is the smallest x with count >= k.

Approach:
Search x in [min element, total]. Count with a window: for each right end shrink the left end until
the window sum is <= x, then add the window length.

Worked Example:
nums = [2, 1, 3], k = 4 -> sorted sums are 1, 2, 3, 3, 4, 6. Answer = 3.

Time Complexity: O(n log(sum))
Space Complexity: O(1)
*/

public class P20_KthSmallestSubarraySumBinarySearchAnswer {
    private static long countAtMost(int[] nums, int limit) {
        long count = 0;
        int sum = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum > limit) {
                sum -= nums[left++];
            }
            count += right - left + 1;
        }
        return count;
    }

    public static int kthSmallestSubarraySum(int[] nums, int k) {
        int low = Arrays.stream(nums).min().getAsInt();
        int high = Arrays.stream(nums).sum();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (countAtMost(nums, mid) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("[2, 1, 3], k = 4 -> " + kthSmallestSubarraySum(new int[]{2, 1, 3}, 4));
        System.out.println("[3, 3, 5, 5], k = 7 -> " + kthSmallestSubarraySum(new int[]{3, 3, 5, 5}, 7));
    }
}
