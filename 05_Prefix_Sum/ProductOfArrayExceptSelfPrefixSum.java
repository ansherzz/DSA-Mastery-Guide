import java.util.*;

/*
Problem: Product of Array Except Self
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Given an integer array nums, return an array answer where answer[i] equals the product of every element
    except nums[i]. Do not use division.

    This problem is often taught as prefix and suffix products.

Why this pattern fits:
The output at each index depends on all elements to the left and all elements to the right.
    Prefix and suffix accumulation turns that into two linear passes.

Approach:
First store the product of all values to the left of each index.
    Then walk from right to left with a running suffix product and multiply it into the answer.

Worked Example:
Example:
    nums = [1, 2, 3, 4]
    The answer is [24, 12, 8, 6].

Time Complexity: O(n)
Space Complexity: O(1) extra space if the output array is not counted
*/

public class ProductOfArrayExceptSelfPrefixSum {
    public static int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];

        answer[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        int suffix = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            answer[i] *= suffix;
            suffix *= nums[i];
        }

        return answer;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums   : " + Arrays.toString(nums));
        System.out.println("Answer : " + Arrays.toString(productExceptSelf(nums)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 3, 4});
        runDemo(new int[]{-1, 1, 0, -3, 3});
    }
}
