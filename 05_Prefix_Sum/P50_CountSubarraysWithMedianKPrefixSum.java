import java.util.*;

/*
Problem: Count Subarrays With Median K
Pattern: 05. Prefix Sum
Difficulty: Hard

Interview Prompt:
nums is a permutation of 1..n. Return the number of non-empty subarrays whose median is k.
For even lengths the median is the left of the two middle elements after sorting.

Why this pattern fits:
A subarray containing k has median k exactly when (count greater than k) - (count smaller than k) is 0 or 1.
That is a prefix-balance condition, so balances on the left of k can be counted in a map and matched from the right.

Approach:
Find the index of k. Walk left from it, recording each balance in a map (including the empty extension).
Walk right from it with a running balance b; add map[-b] and map[1 - b] at each step.

Worked Example:
nums = [3, 2, 1, 4, 5], k = 4 -> [4], [4, 5], [1, 4, 5]. Answer = 3.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P50_CountSubarraysWithMedianKPrefixSum {
    public static int countSubarrays(int[] nums, int k) {
        int position = 0;
        while (nums[position] != k) {
            position++;
        }
        Map<Integer, Integer> leftBalance = new HashMap<>();
        leftBalance.put(0, 1);
        int balance = 0;
        for (int i = position - 1; i >= 0; i--) {
            balance += nums[i] > k ? 1 : -1;
            leftBalance.merge(balance, 1, Integer::sum);
        }
        int count = 0;
        balance = 0;
        for (int i = position; i < nums.length; i++) {
            if (i > position) {
                balance += nums[i] > k ? 1 : -1;
            }
            count += leftBalance.getOrDefault(-balance, 0) + leftBalance.getOrDefault(1 - balance, 0);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("[3, 2, 1, 4, 5], k = 4 -> " + countSubarrays(new int[]{3, 2, 1, 4, 5}, 4));
        System.out.println("[2, 3, 1], k = 3 -> " + countSubarrays(new int[]{2, 3, 1}, 3));
    }
}
