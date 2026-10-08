import java.util.*;

/*
Problem: Binary Subarrays With Sum
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given a binary array nums and an integer goal, return the number of non-empty contiguous subarrays
    whose sum is exactly goal.

Why this pattern fits:
A window cannot directly count "exactly goal" because zeros let many windows share the same sum.
    But "at most goal" is easy to count with a window, and
    exactly(goal) = atMost(goal) - atMost(goal - 1). This subtraction trick is a core sliding-window technique.

Approach:
Write a helper that counts subarrays with sum at most a limit:
    expand right, shrink left while the sum exceeds the limit, and add (right - left + 1) for each right.
    Call it for goal and for goal - 1 and subtract.

Worked Example:
Example:
    nums = [1, 0, 1, 0, 1], goal = 2
    Subarrays with sum 2: [1,0,1] (0..2), [1,0,1,0] (0..3), [0,1,0,1] (1..4), [1,0,1] (2..4). Answer = 4.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P19_BinarySubarraysWithSumSlidingWindow {
    public static int numSubarraysWithSum(int[] nums, int goal) {
        return countAtMost(nums, goal) - countAtMost(nums, goal - 1);
    }

    private static int countAtMost(int[] nums, int limit) {
        if (limit < 0) {
            return 0;
        }

        int left = 0;
        int sum = 0;
        int count = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum > limit) {
                sum -= nums[left++];
            }
            count += right - left + 1;
        }
        return count;
    }

    private static void runDemo(int[] nums, int goal) {
        System.out.println("Array: " + Arrays.toString(nums) + ", goal = " + goal);
        System.out.println("Count: " + numSubarraysWithSum(nums, goal));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 0, 1, 0, 1}, 2);
        runDemo(new int[]{0, 0, 0, 0, 0}, 0);
    }
}
