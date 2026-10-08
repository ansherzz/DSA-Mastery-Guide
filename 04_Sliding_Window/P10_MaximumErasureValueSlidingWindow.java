import java.util.*;

/*
Problem: Maximum Erasure Value
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given an array of positive integers, erase one subarray containing only unique elements.
The score is the sum of the erased elements. Return the maximum score.

Why this pattern fits:
It is "longest substring without repeating characters" with a different objective.
The uniqueness constraint drives the window; a running sum is tracked alongside.

Approach:
Extend right, adding the value to the sum. While the value is already in the window's set,
remove nums[left] from the set and the sum. Then insert the value and record the sum.

Worked Example:
nums = [4, 2, 4, 5, 6] -> the best unique window is [2, 4, 5, 6] with sum 17.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P10_MaximumErasureValueSlidingWindow {
    public static int maximumUniqueSubarray(int[] nums) {
        Set<Integer> window = new HashSet<>();
        int sum = 0;
        int best = 0;
        int left = 0;
        for (int value : nums) {
            while (window.contains(value)) {
                window.remove(nums[left]);
                sum -= nums[left++];
            }
            window.add(value);
            sum += value;
            best = Math.max(best, sum);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[4, 2, 4, 5, 6] -> " + maximumUniqueSubarray(new int[]{4, 2, 4, 5, 6}));
        System.out.println("[5, 2, 1, 2, 5, 2, 1, 2, 5] -> " + maximumUniqueSubarray(new int[]{5, 2, 1, 2, 5, 2, 1, 2, 5}));
    }
}
