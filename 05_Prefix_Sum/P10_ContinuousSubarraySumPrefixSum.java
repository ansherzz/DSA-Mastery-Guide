import java.util.*;

/*
Problem: Continuous Subarray Sum
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Given an integer array and an integer k, return true if the array contains a continuous subarray of length
    at least 2 whose sum is a multiple of k.

    The key idea is that if two prefix sums have the same remainder when divided by k, the subarray between them
    is divisible by k.

Why this pattern fits:
This is a strong pattern-identification problem. It looks hard until you recognize the modulo form of prefix sums.

Approach:
Track the earliest index where each prefix remainder was seen.
    If the same remainder appears again and the distance is at least 2, a valid subarray exists.
    Special-case k == 0 by checking raw prefix sums instead of modulo.

Worked Example:
Example:
    nums = [23, 2, 4, 6, 7], k = 6
    The subarray [2, 4] sums to 6, so the answer is true.

Time Complexity: O(n)
Space Complexity: O(min(n, k)) or O(n) in general
*/

public class P10_ContinuousSubarraySumPrefixSum {
    public static boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> firstIndex = new HashMap<>();
        firstIndex.put(0, -1);

        int prefix = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            int remainder = (k == 0) ? prefix : prefix % k;

            if (firstIndex.containsKey(remainder)) {
                if (i - firstIndex.get(remainder) >= 2) {
                    return true;
                }
            } else {
                firstIndex.put(remainder, i);
            }
        }

        return false;
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Nums   : " + Arrays.toString(nums));
        System.out.println("k      : " + k);
        System.out.println("Exists?: " + checkSubarraySum(nums, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{23, 2, 4, 6, 7}, 6);
        runDemo(new int[]{23, 2, 6, 4, 7}, 13);
    }
}
