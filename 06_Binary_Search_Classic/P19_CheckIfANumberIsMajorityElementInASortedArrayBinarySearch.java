import java.util.*;

/*
Problem: Check If a Number Is Majority Element in a Sorted Array
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Given a sorted array and a target, return true if the target appears more than n / 2 times.

Why this pattern fits:
Occurrences are contiguous. If the block starts at index i and is longer than n / 2,
then index i + n / 2 must also hold the target. One lower-bound search is enough.

Approach:
Find first = lower bound of target. Return true if first + n / 2 is in range and holds the target.

Worked Example:
nums = [2, 4, 5, 5, 5, 5, 5, 6, 6], target = 5 -> first = 2 and nums[2 + 4] = 5. Answer = true.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P19_CheckIfANumberIsMajorityElementInASortedArrayBinarySearch {
    public static boolean isMajorityElement(int[] nums, int target) {
        int low = 0;
        int high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        int check = low + nums.length / 2;
        return check < nums.length && nums[check] == target;
    }

    public static void main(String[] args) {
        System.out.println("[2, 4, 5, 5, 5, 5, 5, 6, 6], 5 -> " + isMajorityElement(new int[]{2, 4, 5, 5, 5, 5, 5, 6, 6}, 5));
        System.out.println("[10, 100, 101, 101], 101 -> " + isMajorityElement(new int[]{10, 100, 101, 101}, 101));
    }
}
