import java.util.*;

/*
Problem: Special Array With X Elements Greater Than or Equal X
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
You are given an array nums of non-negative integers.
    nums is considered special if there exists a number x such that exactly x numbers in nums
    are greater than or equal to x. Note that x does not have to be an element of nums.
    Return x if the array is special, otherwise return -1. It can be proven that x is unique if it exists.

Why this pattern fits:
After sorting, "how many elements are >= x" is n minus the lower bound of x.
    That count can be computed with one binary search for each candidate x in [1, n].

Approach:
Sort the array. For each candidate x from 1 to n, binary search for the first index whose value is >= x.
    The number of elements >= x is n - index. If it equals x, return x.
    x = 0 can never be the answer for a non-empty array, because that would need zero elements >= 0.

Worked Example:
Example:
    nums = [0, 4, 3, 0, 4] -> sorted [0, 0, 3, 4, 4]
    x = 3: elements >= 3 are 3, 4, 4, which is exactly 3 elements. Answer = 3.

Time Complexity: O(n log n)
Space Complexity: O(1) besides the sort
*/

public class P12_SpecialArrayWithXElementsBinarySearch {
    public static int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        for (int x = 1; x <= n; x++) {
            if (n - lowerBound(nums, x) == x) {
                return x;
            }
        }
        return -1;
    }

    private static int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(specialArray(new int[]{3, 5}));
        System.out.println(specialArray(new int[]{0, 0}));
        System.out.println(specialArray(new int[]{0, 4, 3, 0, 4}));
    }
}
