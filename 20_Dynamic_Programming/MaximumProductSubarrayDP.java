import java.util.*;

/*
Problem: Maximum Product Subarray
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Given a non-empty integer array, find the contiguous subarray with the largest product and return that product.
    The array may contain negative numbers and zeros.

    The O(n^2) brute-force version is in folder 01. Solve it here in a single pass.

Why this pattern fits:
For sums, tracking the best subarray ending at each index is enough (Kadane's algorithm).
    For products that fails, because multiplying by a negative number turns the smallest product into the largest.
    The fix is to carry two states per index: the maximum and the minimum product of a subarray ending there.

Approach:
State: maxEnding and minEnding are the largest and smallest products of a subarray ending at the current index.
    Transition: both new values come from three candidates: the number itself,
    the number times the previous maximum, and the number times the previous minimum.
    The answer is the largest maxEnding seen.

Worked Example:
Example:
    nums = [-2, 3, -4]
    At -2: max = -2, min = -2.
    At 3: max = 3, min = -6.
    At -4: max = max(-4, 3 * -4, -6 * -4) = 24. Answer = 24.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class MaximumProductSubarrayDP {
    public static int maxProduct(int[] nums) {
        int maxEnding = nums[0];
        int minEnding = nums[0];
        int best = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int value = nums[i];
            int viaMax = maxEnding * value;
            int viaMin = minEnding * value;

            maxEnding = Math.max(value, Math.max(viaMax, viaMin));
            minEnding = Math.min(value, Math.min(viaMax, viaMin));
            best = Math.max(best, maxEnding);
        }
        return best;
    }

    private static void runDemo(int[] nums) {
        System.out.println(Arrays.toString(nums) + " -> " + maxProduct(nums));
    }

    public static void main(String[] args) {
        runDemo(new int[]{2, 3, -2, 4});
        runDemo(new int[]{-2, 0, -1});
        runDemo(new int[]{-2, 3, -4});
    }
}
