import java.util.*;

/*
Problem: Maximum Product Subarray with Brute Force
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
Given a non-empty integer array, find the contiguous subarray with the largest product and return that product.
    The array may contain negative numbers and zeros.

Why this pattern fits:
Negative numbers flip the sign and zeros reset the product, so greedy intuition is easy to get wrong.
    Enumerating every subarray gives a trustworthy baseline before learning the min/max tracking trick.

Approach:
Fix a start index. Extend the end index one step at a time while maintaining a running product.
    Compare each running product against the best answer seen so far.

Worked Example:
Example:
    nums = [2, 3, -2, 4]
    Subarray [2, 3] has product 6, which is the best possible.
    Including -2 makes the product negative, and [4] alone is only 4.

Time Complexity: O(n^2)
Space Complexity: O(1)
*/

public class P31_MaximumProductSubarrayBruteForce {
    public static int maxProduct(int[] nums) {
        int best = nums[0];

        for (int start = 0; start < nums.length; start++) {
            int product = 1;
            for (int end = start; end < nums.length; end++) {
                product *= nums[end];
                best = Math.max(best, product);
            }
        }
        return best;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Array : " + Arrays.toString(nums));
        System.out.println("Best  : " + maxProduct(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{2, 3, -2, 4});
        runDemo(new int[]{-2, 0, -1});
        runDemo(new int[]{-2, 3, -4});
    }
}
