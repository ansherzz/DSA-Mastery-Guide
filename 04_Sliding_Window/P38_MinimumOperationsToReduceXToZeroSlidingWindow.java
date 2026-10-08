import java.util.*;

/*
Problem: Minimum Operations to Reduce X to Zero
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
You are given an array of positive integers nums and an integer x.
    In one operation, you can remove either the leftmost or the rightmost element of nums and subtract its value from x.
    Return the minimum number of operations to reduce x to exactly 0, or -1 if it is not possible.

Why this pattern fits:
Removing a prefix and a suffix leaves a contiguous middle block.
    Minimizing the removed count is the same as maximizing the length of the kept block,
    whose sum must be total - x. All values are positive, so a variable window finds it.

Approach:
Let target = total - x. If target is negative, the answer is -1.
    Slide a window, shrinking from the left while its sum exceeds target.
    Whenever the sum equals target, record the window length.
    The answer is n minus the longest such window, or -1 if no window matched.

Worked Example:
Example:
    nums = [1, 1, 4, 2, 3], x = 5
    total = 11, target = 6. The longest window with sum 6 is [1, 1, 4] with length 3.
    Remove the other 2 elements (2 and 3). Answer = 2.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P38_MinimumOperationsToReduceXToZeroSlidingWindow {
    public static int minOperations(int[] nums, int x) {
        long total = 0;
        for (int num : nums) {
            total += num;
        }

        long target = total - x;
        if (target < 0) {
            return -1;
        }

        int left = 0;
        long sum = 0;
        int longest = -1;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum > target) {
                sum -= nums[left++];
            }
            if (sum == target) {
                longest = Math.max(longest, right - left + 1);
            }
        }
        return longest == -1 ? -1 : nums.length - longest;
    }

    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{1, 1, 4, 2, 3}, 5));
        System.out.println(minOperations(new int[]{5, 6, 7, 8, 9}, 4));
        System.out.println(minOperations(new int[]{3, 2, 20, 1, 1, 3}, 10));
    }
}
