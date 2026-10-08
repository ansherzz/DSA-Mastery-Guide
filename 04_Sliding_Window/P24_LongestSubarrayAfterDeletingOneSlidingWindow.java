import java.util.*;

/*
Problem: Longest Subarray of 1's After Deleting One Element
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given a binary array nums, you must delete exactly one element from it.
    Return the length of the longest non-empty subarray containing only 1s in the resulting array.
    Return 0 if there is no such subarray.

Why this pattern fits:
Deleting one element lets a window "absorb" at most one zero.
    So the task is the longest window with at most one zero, and the answer is that window length minus one
    for the deleted element.

Approach:
Expand the right pointer and count zeros in the window.
    While the window contains more than one zero, shrink from the left.
    The candidate answer is (window length - 1), because one element is always deleted,
    even when the window has no zero at all.

Worked Example:
Example:
    nums = [0, 1, 1, 1, 0, 1, 1, 0, 1]
    The window [1, 1, 1, 0, 1, 1] has one zero. Deleting it leaves five 1s. Answer = 5.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P24_LongestSubarrayAfterDeletingOneSlidingWindow {
    public static int longestSubarray(int[] nums) {
        int left = 0;
        int zeros = 0;
        int best = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeros++;
            }
            while (zeros > 1) {
                if (nums[left] == 0) {
                    zeros--;
                }
                left++;
            }
            best = Math.max(best, right - left);
        }
        return best;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Best : " + longestSubarray(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 1, 0, 1});
        runDemo(new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1});
        runDemo(new int[]{1, 1, 1});
    }
}
