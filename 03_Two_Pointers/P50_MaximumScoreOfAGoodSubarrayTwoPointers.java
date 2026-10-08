import java.util.*;

/*
Problem: Maximum Score of a Good Subarray
Pattern: 03. Two Pointers
Difficulty: Hard

Interview Prompt:
The score of subarray (i, j) is min(nums[i..j]) * (j - i + 1). A good subarray satisfies i <= k <= j.
Return the maximum score of a good subarray.

Why this pattern fits:
The subarray must contain index k, so it grows outward from k.
Expanding toward the larger neighbor keeps the minimum as high as possible for every width.

Approach:
Set i = j = k. While the window can grow, extend to whichever side has the larger next value
(or the only side left), update the running minimum, and record min * width.

Worked Example:
nums = [1, 4, 3, 7, 4, 5], k = 3
Growing from 7 to [4, 3, 7, 4, 5] gives min 3 * width 5 = 15.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P50_MaximumScoreOfAGoodSubarrayTwoPointers {
    public static int maximumScore(int[] nums, int k) {
        int i = k;
        int j = k;
        int min = nums[k];
        int best = min;
        while (i > 0 || j < nums.length - 1) {
            if (i == 0) {
                j++;
            } else if (j == nums.length - 1) {
                i--;
            } else if (nums[i - 1] < nums[j + 1]) {
                j++;
            } else {
                i--;
            }
            min = Math.min(min, Math.min(nums[i], nums[j]));
            best = Math.max(best, min * (j - i + 1));
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[1, 4, 3, 7, 4, 5], k = 3 -> " + maximumScore(new int[]{1, 4, 3, 7, 4, 5}, 3));
        System.out.println("[5, 5, 4, 5, 4, 1, 1, 1], k = 0 -> " + maximumScore(new int[]{5, 5, 4, 5, 4, 1, 1, 1}, 0));
    }
}
