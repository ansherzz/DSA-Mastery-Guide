import java.util.*;

/*
Problem: Max Consecutive Ones II
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given a binary array nums, return the maximum number of consecutive 1s in the array
    if you can flip at most one 0.

Why this pattern fits:
Flipping at most one 0 is the same as finding the longest window that contains at most one 0.
    The constraint is monotonic: extending the window can only add zeros, shrinking can only remove them.

Approach:
Expand the window to the right and count zeros inside it.
    When the count exceeds 1, move left forward until a zero leaves the window.
    The window is valid after that, so update the best length.

Worked Example:
Example:
    nums = [1, 0, 1, 1, 0]
    The window [1, 0, 1, 1] has one zero and length 4. Flipping that zero gives four consecutive 1s.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P33_MaxConsecutiveOnesIISlidingWindow {
    public static int findMaxConsecutiveOnes(int[] nums) {
        int left = 0;
        int zeros = 0;
        int best = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeros++;
            }
            while (zeros > 1) {
                if (nums[left++] == 0) {
                    zeros--;
                }
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0}));
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1}));
        System.out.println(findMaxConsecutiveOnes(new int[]{0, 0}));
    }
}
