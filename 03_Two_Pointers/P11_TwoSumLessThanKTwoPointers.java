import java.util.*;

/*
Problem: Two Sum Less Than K
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
Given an array nums and an integer k, return the maximum sum of two different elements
that is strictly less than k. Return -1 if no such pair exists.

Why this pattern fits:
After sorting, a too-large sum can only be fixed by lowering the right pointer,
and a valid sum can only be improved by raising the left pointer.

Approach:
Sort. While left < right: if the sum is below k, record it and move left up; otherwise move right down.

Worked Example:
nums = [34, 23, 1, 24, 75, 33, 54, 8], k = 60
The best pair is 34 + 24 = 58.

Time Complexity: O(n log n)
Space Complexity: O(1) extra
*/

public class P11_TwoSumLessThanKTwoPointers {
    public static int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int best = -1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < k) {
                best = Math.max(best, sum);
                left++;
            } else {
                right--;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[34, 23, 1, 24, 75, 33, 54, 8], k = 60 -> "
                + twoSumLessThanK(new int[]{34, 23, 1, 24, 75, 33, 54, 8}, 60));
        System.out.println("[10, 20, 30], k = 15 -> " + twoSumLessThanK(new int[]{10, 20, 30}, 15));
    }
}
