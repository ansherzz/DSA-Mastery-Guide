import java.util.*;

/*
Problem: Number of Sub-arrays With Odd Sum
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Given an array of integers, return the number of subarrays with an odd sum, modulo 1e9 + 7.

Why this pattern fits:
sum(i..j) = prefix[j] - prefix[i - 1]. The difference is odd exactly when the two prefixes differ in parity.
Only two counters are needed instead of a hash map.

Approach:
Track how many prefixes so far are even and how many are odd (the empty prefix is even).
For each new prefix, add the count of earlier prefixes with the opposite parity.

Worked Example:
arr = [1, 3, 5] -> odd-sum subarrays: [1], [3], [5], [1, 3, 5]. Answer = 4.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P25_NumberOfSubarraysWithOddSumPrefixSum {
    public static int numOfSubarrays(int[] arr) {
        int mod = 1_000_000_007;
        int even = 1;
        int odd = 0;
        int parity = 0;
        int count = 0;
        for (int value : arr) {
            parity = (parity + value) % 2;
            if (parity == 1) {
                count = (count + even) % mod;
                odd++;
            } else {
                count = (count + odd) % mod;
                even++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("[1, 3, 5] -> " + numOfSubarrays(new int[]{1, 3, 5}));
        System.out.println("[2, 4, 6] -> " + numOfSubarrays(new int[]{2, 4, 6}));
        System.out.println("[1, 2, 3, 4, 5, 6, 7] -> " + numOfSubarrays(new int[]{1, 2, 3, 4, 5, 6, 7}));
    }
}
