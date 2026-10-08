import java.util.*;

/*
Problem: Length of Longest Subarray With at Most K Frequency
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
A subarray is good if the frequency of each of its elements is at most k.
Return the length of the longest good subarray.

Why this pattern fits:
Only the element just added can break the constraint, so the shrink condition checks a single counter.

Approach:
Add nums[right] to the frequency map. While its count exceeds k, remove nums[left] and advance left.
Record the window size.

Worked Example:
nums = [1, 2, 3, 1, 2, 3, 1, 2], k = 2 -> [1, 2, 3, 1, 2, 3] has every frequency equal to 2. Answer = 6.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P32_LengthOfLongestSubarrayWithAtMostKFrequencySlidingWindow {
    public static int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        int best = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            count.merge(nums[right], 1, Integer::sum);
            while (count.get(nums[right]) > k) {
                count.merge(nums[left++], -1, Integer::sum);
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 3, 1, 2, 3, 1, 2], k = 2 -> " + maxSubarrayLength(new int[]{1, 2, 3, 1, 2, 3, 1, 2}, 2));
        System.out.println("[1, 2, 1, 2, 1, 2, 1, 2], k = 1 -> " + maxSubarrayLength(new int[]{1, 2, 1, 2, 1, 2, 1, 2}, 1));
        System.out.println("[5, 5, 5, 5, 5, 5, 5], k = 4 -> " + maxSubarrayLength(new int[]{5, 5, 5, 5, 5, 5, 5}, 4));
    }
}
