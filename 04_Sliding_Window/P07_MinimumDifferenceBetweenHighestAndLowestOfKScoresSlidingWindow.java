import java.util.*;

/*
Problem: Minimum Difference Between Highest and Lowest of K Scores
Pattern: 04. Sliding Window
Difficulty: Easy

Interview Prompt:
Pick the scores of any k students so that the difference between the highest and the lowest picked score
is as small as possible. Return that minimum difference.

Why this pattern fits:
Order does not matter, so sorting is free. In sorted order the optimal group is always k consecutive values.

Approach:
Sort. For each window [i, i + k - 1], the difference is nums[i + k - 1] - nums[i]. Take the minimum.

Worked Example:
nums = [9, 4, 1, 7], k = 2 -> sorted [1, 4, 7, 9]. Windows give 3, 3, 2. Answer = 2.

Time Complexity: O(n log n)
Space Complexity: O(1) extra
*/

public class P07_MinimumDifferenceBetweenHighestAndLowestOfKScoresSlidingWindow {
    public static int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int best = Integer.MAX_VALUE;
        for (int i = 0; i + k - 1 < nums.length; i++) {
            best = Math.min(best, nums[i + k - 1] - nums[i]);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[90], k = 1 -> " + minimumDifference(new int[]{90}, 1));
        System.out.println("[9, 4, 1, 7], k = 2 -> " + minimumDifference(new int[]{9, 4, 1, 7}, 2));
    }
}
