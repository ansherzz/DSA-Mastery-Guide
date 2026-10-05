import java.util.*;

/*
Problem: Subarray Sums Divisible by K
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Given an integer array nums and a positive integer k, return the number of non-empty contiguous subarrays
    whose sum is divisible by k. Values may be negative.

Why this pattern fits:
A subarray sum is prefix[j] - prefix[i]. It is divisible by k exactly when the two prefixes
    leave the same remainder when divided by k. So the problem becomes counting pairs of equal remainders.

Approach:
Keep a count of how many prefixes have produced each remainder, starting with remainder 0 seen once.
    For each new prefix, normalize its remainder into the range [0, k - 1] because Java's % can be negative.
    Add the number of earlier prefixes with the same remainder, then record the current one.

Worked Example:
Example:
    nums = [4, 5, 0, -2, -3, 1], k = 5
    Prefix remainders: 4, 4, 4, 2, 4, 0 (plus the initial 0).
    Remainder 4 appears four times (6 pairs) and remainder 0 appears twice (1 pair). Answer = 7.

Time Complexity: O(n)
Space Complexity: O(k)
*/

public class SubarraySumsDivisibleByKPrefixSum {
    public static int subarraysDivByK(int[] nums, int k) {
        int[] remainderCount = new int[k];
        remainderCount[0] = 1;

        int prefix = 0;
        int answer = 0;

        for (int num : nums) {
            prefix += num;
            int remainder = ((prefix % k) + k) % k;
            answer += remainderCount[remainder];
            remainderCount[remainder]++;
        }
        return answer;
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Nums  : " + Arrays.toString(nums));
        System.out.println("k     : " + k);
        System.out.println("Count : " + subarraysDivByK(nums, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{4, 5, 0, -2, -3, 1}, 5);
        runDemo(new int[]{5}, 9);
    }
}
