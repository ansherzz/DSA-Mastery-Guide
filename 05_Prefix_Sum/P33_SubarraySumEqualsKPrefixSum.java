import java.util.*;

/*
Problem: Subarray Sum Equals K
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Given an array of integers nums and an integer k,
    return the total number of contiguous subarrays whose sum equals k.
    The array may contain negative numbers.

Why this pattern fits:
The sum of nums[i..j] is prefix[j + 1] - prefix[i]. So a subarray ending at j has sum k
    exactly when an earlier prefix equals prefix[j + 1] - k.
    Negative values rule out a sliding window, but the prefix identity still holds.

Approach:
Keep a running prefix sum and a map from prefix value to how many times it has occurred.
    Start with prefix 0 seen once, representing the empty prefix.
    At each element, add the count of (prefix - k) to the answer, then record the current prefix.

Worked Example:
Example:
    nums = [1, 2, 3], k = 3
    Prefix sums: 0, 1, 3, 6. 3 - 3 = 0 was seen once ([1, 2]); 6 - 3 = 3 was seen once ([3]). Answer = 2.

Time Complexity: O(n) average
Space Complexity: O(n)
*/

public class P33_SubarraySumEqualsKPrefixSum {
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);

        int prefix = 0;
        int count = 0;
        for (int num : nums) {
            prefix += num;
            count += prefixCount.getOrDefault(prefix - k, 0);
            prefixCount.merge(prefix, 1, Integer::sum);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1, 1, 1}, 2));
        System.out.println(subarraySum(new int[]{1, 2, 3}, 3));
        System.out.println(subarraySum(new int[]{1, -1, 0}, 0));
    }
}
