import java.util.*;

/*
Problem: Number of Subsequences That Satisfy the Given Sum Condition
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
Return the number of non-empty subsequences of nums whose minimum plus maximum is at most target.
Return the answer modulo 1e9 + 7.

Why this pattern fits:
Only the min and max of a subsequence matter, so order is irrelevant and sorting is allowed.
With the smallest element fixed at `left`, every subset of the elements up to `right` is valid.

Approach:
Sort and precompute powers of two. Use left = 0 and right = n - 1.
If nums[left] + nums[right] <= target, add 2^(right - left) (left is mandatory, the rest optional) and move left.
Otherwise move right down.

Worked Example:
nums = [3, 5, 6, 7], target = 9
left = 3 pairs up to 6: 2^2 = 4 subsequences. No other minimum works. Answer = 4.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P44_NumberOfSubsequencesThatSatisfySumConditionTwoPointers {
    public static int numSubseq(int[] nums, int target) {
        int mod = 1_000_000_007;
        Arrays.sort(nums);
        int n = nums.length;
        int[] power = new int[n];
        power[0] = 1;
        for (int i = 1; i < n; i++) {
            power[i] = power[i - 1] * 2 % mod;
        }
        int left = 0;
        int right = n - 1;
        int count = 0;
        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                count = (count + power[right - left]) % mod;
                left++;
            } else {
                right--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("[3, 5, 6, 7], target = 9 -> " + numSubseq(new int[]{3, 5, 6, 7}, 9));
        System.out.println("[3, 3, 6, 8], target = 10 -> " + numSubseq(new int[]{3, 3, 6, 8}, 10));
        System.out.println("[2, 3, 3, 4, 6, 7], target = 12 -> " + numSubseq(new int[]{2, 3, 3, 4, 6, 7}, 12));
    }
}
