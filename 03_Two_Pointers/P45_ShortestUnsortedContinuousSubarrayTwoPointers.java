import java.util.*;

/*
Problem: Shortest Unsorted Continuous Subarray
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
Given an integer array nums, find one continuous subarray such that if you only sort this subarray
    in non-decreasing order, the whole array becomes sorted.
    Return the length of the shortest such subarray.

Why this pattern fits:
The answer is defined by two boundaries, and each boundary can be located by a scan from the opposite end.
    The two scans run in opposite directions, each tracking a single running extreme.

Approach:
Scan left to right while tracking the maximum seen so far.
    Any element smaller than that maximum is out of place, so it must be inside the subarray;
    the last such index is the right boundary.
    Scan right to left while tracking the minimum seen so far.
    Any element larger than that minimum is out of place; the last such index is the left boundary.
    If no element is out of place, the array is already sorted and the answer is 0.

Worked Example:
Example:
    nums = [2, 6, 4, 8, 10, 9, 15]
    Right boundary: the last element below the running maximum is 9 at index 5.
    Left boundary: the last element above the running minimum (from the right) is 6 at index 1.
    Sorting indices 1..5 fixes the array. Answer = 5.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P45_ShortestUnsortedContinuousSubarrayTwoPointers {
    public static int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int end = -1;
        int maxSoFar = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] < maxSoFar) {
                end = i;
            } else {
                maxSoFar = nums[i];
            }
        }

        int start = 0;
        int minSoFar = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] > minSoFar) {
                start = i;
            } else {
                minSoFar = nums[i];
            }
        }

        return end == -1 ? 0 : end - start + 1;
    }

    public static void main(String[] args) {
        System.out.println(findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
        System.out.println(findUnsortedSubarray(new int[]{1, 2, 3, 4}));
        System.out.println(findUnsortedSubarray(new int[]{1, 3, 2, 2, 2}));
    }
}
