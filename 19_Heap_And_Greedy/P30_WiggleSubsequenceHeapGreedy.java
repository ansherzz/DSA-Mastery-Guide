import java.util.*;

/*
Problem: Wiggle Subsequence
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
A wiggle sequence has differences between successive numbers that strictly alternate between positive and negative.
Return the length of the longest wiggle subsequence of nums.

Why this pattern fits:
Within a monotonic run only the last element matters, because it leaves the most room for the next turn.
So the answer is the number of turning points, found by tracking the sign of the last counted difference.

Approach:
Keep the previous non-zero difference. For each new difference, if it is positive after a non-positive one
or negative after a non-negative one, count it and remember it.

Worked Example:
nums = [1, 17, 5, 10, 13, 15, 10, 5, 16, 8] -> peaks and valleys 1, 17, 5, 15, 5, 16, 8. Answer = 7.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P30_WiggleSubsequenceHeapGreedy {
    public static int wiggleMaxLength(int[] nums) {
        int length = 1;
        int previousDifference = 0;
        for (int i = 1; i < nums.length; i++) {
            int difference = nums[i] - nums[i - 1];
            if ((difference > 0 && previousDifference <= 0) || (difference < 0 && previousDifference >= 0)) {
                length++;
                previousDifference = difference;
            }
        }
        return length;
    }

    public static void main(String[] args) {
        System.out.println("[1, 7, 4, 9, 2, 5] -> " + wiggleMaxLength(new int[]{1, 7, 4, 9, 2, 5}));
        System.out.println("[1, 17, 5, 10, 13, 15, 10, 5, 16, 8] -> "
                + wiggleMaxLength(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8}));
        System.out.println("[1, 2, 3, 4, 5, 6, 7, 8, 9] -> " + wiggleMaxLength(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }
}
