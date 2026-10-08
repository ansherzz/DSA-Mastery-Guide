import java.util.*;

/*
Problem: Max Consecutive Ones III
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given a binary array nums and an integer k, return the maximum number of consecutive 1s in the array
    if you are allowed to flip at most k zeros to ones.

Why this pattern fits:
Rephrase the question: find the longest window that contains at most k zeros.
    Adding elements on the right can only increase the zero count, and removing from the left can only decrease it,
    which is the monotonic behavior a variable-size window needs.

Approach:
Expand the right pointer and count zeros inside the window.
    While the window holds more than k zeros, move the left pointer forward.
    After each adjustment the window is valid, so update the best length.

Worked Example:
Example:
    nums = [1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0], k = 2
    Flipping the zeros at indices 5 and 10 gives the window from index 5 to 10, length 6.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P15_MaxConsecutiveOnesIIISlidingWindow {
    public static int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeros = 0;
        int best = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeros++;
            }
            while (zeros > k) {
                if (nums[left] == 0) {
                    zeros--;
                }
                left++;
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Array: " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("Best : " + longestOnes(nums, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2);
        runDemo(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3);
    }
}
