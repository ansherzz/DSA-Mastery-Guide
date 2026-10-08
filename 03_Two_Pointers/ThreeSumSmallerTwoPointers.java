import java.util.*;

/*
Problem: 3Sum Smaller
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
Given an array of n integers nums and an integer target,
    find the number of index triplets i < j < k such that nums[i] + nums[j] + nums[k] < target.

Why this pattern fits:
Only the count of triplets matters, so the array may be sorted.
    On a sorted array, a pair of converging pointers can count many valid pairs at once instead of one by one.

Approach:
Sort the array. Fix the first element nums[i] and use left = i + 1 and right = n - 1.
    If nums[i] + nums[left] + nums[right] < target, then every index between left + 1 and right
    also works as the third element with this left, because those values are no larger than nums[right].
    That adds right - left triplets at once; then move left forward.
    Otherwise the sum is too large, so move right backward.

Worked Example:
Example:
    nums = [-2, 0, 1, 3], target = 2
    Triplets: (-2, 0, 1) with sum -1 and (-2, 0, 3) with sum 1. Answer = 2.

Time Complexity: O(n^2)
Space Complexity: O(1) besides the sort
*/

public class ThreeSumSmallerTwoPointers {
    public static int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < target) {
                    count += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(threeSumSmaller(new int[]{-2, 0, 1, 3}, 2));
        System.out.println(threeSumSmaller(new int[]{3, 1, 0, -2}, 4));
        System.out.println(threeSumSmaller(new int[]{0, 0, 0}, 0));
    }
}
