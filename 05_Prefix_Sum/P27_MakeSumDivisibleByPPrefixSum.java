import java.util.*;

/*
Problem: Make Sum Divisible by P
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Given an array of positive integers nums, remove the smallest subarray (possibly empty)
    such that the sum of the remaining elements is divisible by p. It is not allowed to remove the whole array.
    Return the length of the smallest subarray that you need to remove, or -1 if it is impossible.

Why this pattern fits:
If the total has remainder r modulo p, the removed subarray must also have remainder r.
    A subarray's remainder is the difference of two prefix remainders, so this is a lookup problem on prefix values.

Approach:
Compute r = total % p. If r is 0, nothing needs to be removed.
    Scan with a running prefix remainder cur. A removable subarray ending at index i starts after an earlier prefix
    whose remainder is (cur - r + p) % p.
    Keep a map from prefix remainder to its latest index, because the latest index gives the shortest subarray.
    If the best length equals n, removal would delete everything, so return -1.

Worked Example:
Example:
    nums = [3, 1, 4, 2], p = 6
    total = 10, r = 4. Removing [4] leaves 6, which is divisible by 6. Answer = 1.

Time Complexity: O(n) average
Space Complexity: O(min(n, p))
*/

public class P27_MakeSumDivisibleByPPrefixSum {
    public static int minSubarray(int[] nums, int p) {
        int remainder = 0;
        for (int num : nums) {
            remainder = (remainder + num) % p;
        }
        if (remainder == 0) {
            return 0;
        }

        Map<Integer, Integer> lastIndex = new HashMap<>();
        lastIndex.put(0, -1);
        int current = 0;
        int best = nums.length;

        for (int i = 0; i < nums.length; i++) {
            current = (current + nums[i]) % p;
            int needed = (current - remainder + p) % p;
            Integer earlier = lastIndex.get(needed);
            if (earlier != null) {
                best = Math.min(best, i - earlier);
            }
            lastIndex.put(current, i);
        }
        return best == nums.length ? -1 : best;
    }

    public static void main(String[] args) {
        System.out.println(minSubarray(new int[]{3, 1, 4, 2}, 6));
        System.out.println(minSubarray(new int[]{6, 3, 5, 2}, 9));
        System.out.println(minSubarray(new int[]{1, 2, 3}, 3));
        System.out.println(minSubarray(new int[]{1, 2, 3}, 7));
    }
}
