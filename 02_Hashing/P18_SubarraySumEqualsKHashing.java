import java.util.*;

/*
Problem: Subarray Sum Equals K
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
Given an integer array and an integer k, return the number of contiguous subarrays whose sum equals k.

    The key insight is to combine prefix sums with hashing so previous prefix totals can be queried instantly.

Why this pattern fits:
This is one of the most important pattern-bridging questions in DSA.
    It looks like a prefix sum problem, but the reason it runs in linear time is hashing.

Approach:
Let prefix be the running sum up to the current index.
    A previous prefix of (prefix - k) means the subarray between that previous position and the current one sums to k.
    Store counts of prefix sums in a hashmap.

Worked Example:
Example:
    nums = [1, 1, 1], k = 2
    There are 2 valid subarrays: [1, 1] using indices (0,1) and (1,2).

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P18_SubarraySumEqualsKHashing {
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);

        int prefix = 0;
        int answer = 0;

        for (int num : nums) {
            prefix += num;
            answer += prefixCount.getOrDefault(prefix - k, 0);
            prefixCount.put(prefix, prefixCount.getOrDefault(prefix, 0) + 1);
        }

        return answer;
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
    }
}
