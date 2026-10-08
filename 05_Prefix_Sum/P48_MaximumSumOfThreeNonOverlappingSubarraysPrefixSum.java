import java.util.*;

/*
Problem: Maximum Sum of 3 Non-Overlapping Subarrays
Pattern: 05. Prefix Sum
Difficulty: Hard

Interview Prompt:
Find three non-overlapping subarrays of length k with maximum total sum.
Return their starting indices; if several answers exist, return the lexicographically smallest.

Why this pattern fits:
Fixing the middle window splits the problem into "best window entirely to the left"
and "best window entirely to the right", which are prefix-best and suffix-best arrays.

Approach:
Compute window[i] = sum of nums[i..i + k - 1] from prefix sums.
left[i] = start of the best window in [0..i] (earliest on ties); right[i] = start of the best window in [i..] (earliest on ties).
For each middle start m, combine left[m - k], m, and right[m + k]; keep the first strictly better total.

Worked Example:
nums = [1, 2, 1, 2, 6, 7, 5, 1], k = 2 -> windows [1, 2], [2, 6], [7, 5] at starts 0, 3, 5.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P48_MaximumSumOfThreeNonOverlappingSubarraysPrefixSum {
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int count = nums.length - k + 1;
        int[] window = new int[count];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= k) {
                sum -= nums[i - k];
            }
            if (i >= k - 1) {
                window[i - k + 1] = sum;
            }
        }
        int[] left = new int[count];
        for (int i = 1; i < count; i++) {
            left[i] = window[i] > window[left[i - 1]] ? i : left[i - 1];
        }
        int[] right = new int[count];
        right[count - 1] = count - 1;
        for (int i = count - 2; i >= 0; i--) {
            right[i] = window[i] >= window[right[i + 1]] ? i : right[i + 1];
        }
        int[] answer = new int[3];
        int best = -1;
        for (int mid = k; mid + k < count; mid++) {
            int total = window[left[mid - k]] + window[mid] + window[right[mid + k]];
            if (total > best) {
                best = total;
                answer = new int[]{left[mid - k], mid, right[mid + k]};
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 1, 2, 6, 7, 5, 1], k = 2 -> "
                + Arrays.toString(maxSumOfThreeSubarrays(new int[]{1, 2, 1, 2, 6, 7, 5, 1}, 2)));
        System.out.println("[1, 2, 1, 2, 1, 2, 1, 2, 1], k = 2 -> "
                + Arrays.toString(maxSumOfThreeSubarrays(new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1}, 2)));
    }
}
