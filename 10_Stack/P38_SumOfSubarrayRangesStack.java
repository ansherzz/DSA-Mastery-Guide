import java.util.*;

/*
Problem: Sum of Subarray Ranges
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
The range of a subarray is its largest element minus its smallest element.
Return the sum of the ranges of all subarrays of nums.

Why this pattern fits:
The total splits into (sum of maximums) - (sum of minimums).
Each element's contribution is its value times the number of subarrays in which it is the max (or min),
which a monotonic stack gives through the nearest greater (or smaller) element on each side.

Approach:
For maximums keep a decreasing stack: when index i pops index m, with new top l,
element m is the maximum of (m - l) * (i - m) subarrays. Do the same with an increasing stack for minimums.
Strict comparison on one side and non-strict on the other avoids double counting equal values.

Worked Example:
nums = [1, 2, 3] -> ranges 0, 0, 0, 1, 1, 2 sum to 4.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P38_SumOfSubarrayRangesStack {
    private static long contribution(int[] nums, boolean asMaximum) {
        long total = 0;
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n
                    || (asMaximum ? nums[stack.peek()] < nums[i] : nums[stack.peek()] > nums[i]))) {
                int mid = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                total += (long) nums[mid] * (mid - left) * (i - mid);
            }
            stack.push(i);
        }
        return total;
    }

    public static long subArrayRanges(int[] nums) {
        return contribution(nums, true) - contribution(nums, false);
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 3] -> " + subArrayRanges(new int[]{1, 2, 3}));
        System.out.println("[1, 3, 3] -> " + subArrayRanges(new int[]{1, 3, 3}));
        System.out.println("[4, -2, -3, 4, 1] -> " + subArrayRanges(new int[]{4, -2, -3, 4, 1}));
    }
}
