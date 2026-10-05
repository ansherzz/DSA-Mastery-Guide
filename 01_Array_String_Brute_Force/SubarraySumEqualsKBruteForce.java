import java.util.*;

/*
Problem: Subarray Sum Equals K with Brute Force
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
Given an integer array and an integer k, return the number of contiguous subarrays whose sum equals k.
    Values may be negative, so a sliding window does not work directly.

Why this pattern fits:
"Count subarrays with property X" can always be answered by enumerating all subarrays.
    Writing this version first makes the prefix-sum + hash map upgrade in the Hashing folder feel natural.

Approach:
Fix every start index. Extend the end index while keeping a running sum.
    Each time the running sum equals k, count one subarray.
    Do not stop early, because negative values can bring the sum back to k later.

Worked Example:
Example:
    nums = [1, 2, 3], k = 3
    Subarrays [1, 2] and [3] both sum to 3, so the answer is 2.

Time Complexity: O(n^2)
Space Complexity: O(1)
*/

public class SubarraySumEqualsKBruteForce {
    public static int subarraySum(int[] nums, int k) {
        int count = 0;

        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Array : " + Arrays.toString(nums));
        System.out.println("k     : " + k);
        System.out.println("Count : " + subarraySum(nums, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 1, 1}, 2);
        runDemo(new int[]{1, 2, 3}, 3);
        runDemo(new int[]{1, -1, 0}, 0);
    }
}
