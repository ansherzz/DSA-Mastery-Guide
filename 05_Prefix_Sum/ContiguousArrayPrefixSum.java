import java.util.*;

/*
Problem: Contiguous Array
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Given a binary array nums, return the maximum length of a contiguous subarray
    with an equal number of 0s and 1s.

Why this pattern fits:
Treat each 0 as -1 and each 1 as +1. A subarray has equally many 0s and 1s exactly when its sum is 0,
    which means the prefix sums at its two ends are equal.

Approach:
Keep a running balance and a map from each balance to the first index where it occurred.
    Seed the map with balance 0 at index -1 so that subarrays starting at index 0 are handled.
    When a balance repeats, the subarray between the first occurrence and the current index is balanced.
    Never overwrite the stored index, because the earliest occurrence gives the longest subarray.

Worked Example:
Example:
    nums = [0, 1, 0, 1, 1, 0]
    Balances: -1, 0, -1, 0, 1, 0. Balance 0 first occurs at index -1 and last at index 5. Answer = 6.

Time Complexity: O(n) average
Space Complexity: O(n)
*/

public class ContiguousArrayPrefixSum {
    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> firstIndex = new HashMap<>();
        firstIndex.put(0, -1);

        int balance = 0;
        int best = 0;
        for (int i = 0; i < nums.length; i++) {
            balance += nums[i] == 1 ? 1 : -1;
            Integer earlier = firstIndex.putIfAbsent(balance, i);
            if (earlier != null) {
                best = Math.max(best, i - earlier);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(findMaxLength(new int[]{0, 1}));
        System.out.println(findMaxLength(new int[]{0, 1, 0}));
        System.out.println(findMaxLength(new int[]{0, 1, 0, 1, 1, 0}));
    }
}
