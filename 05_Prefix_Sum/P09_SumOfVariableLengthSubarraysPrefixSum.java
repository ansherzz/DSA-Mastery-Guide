import java.util.*;

/*
Problem: Sum of Variable Length Subarrays
Pattern: 05. Prefix Sum
Difficulty: Easy

Interview Prompt:
For each index i define the subarray nums[start..i] where start = max(0, i - nums[i]).
Return the total sum of all elements over all of these subarrays.

Why this pattern fits:
Each index asks for a range sum with a different left end. Many range sums over a static array is the prefix-sum use case.

Approach:
Build prefix where prefix[i] is the sum of the first i elements.
For each i add prefix[i + 1] - prefix[start].

Worked Example:
nums = [2, 3, 1] -> ranges [0..0] = 2, [0..1] = 5, [1..2] = 4. Answer = 11.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P09_SumOfVariableLengthSubarraysPrefixSum {
    public static int subarraySum(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        int total = 0;
        for (int i = 0; i < n; i++) {
            int start = Math.max(0, i - nums[i]);
            total += prefix[i + 1] - prefix[start];
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("[2, 3, 1] -> " + subarraySum(new int[]{2, 3, 1}));
        System.out.println("[3, 1, 1, 2] -> " + subarraySum(new int[]{3, 1, 1, 2}));
    }
}
