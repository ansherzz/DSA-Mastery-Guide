import java.util.*;

/*
Problem: Contiguous Array
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
Given a binary array (only 0s and 1s), return the maximum length of a contiguous subarray
    that contains an equal number of 0s and 1s.

Why this pattern fits:
Treat every 0 as -1. Then "equal number of 0s and 1s" becomes "subarray sum equals 0".
    Two positions with the same running balance bound a zero-sum subarray,
    so you need a map from balance to the earliest index where it occurred.

Approach:
Keep a running balance: +1 for a 1 and -1 for a 0.
    Store the first index at which each balance appears, with balance 0 stored at index -1.
    Whenever a balance repeats, the subarray between the first occurrence and the current index is balanced.

Worked Example:
Example:
    nums = [0, 1, 0]
    Balances after each index: -1, 0, -1.
    Balance 0 was first seen at index -1 and again at index 1, giving length 2.
    Balance -1 was first seen at index 0 and again at index 2, also length 2. Answer = 2.

Time Complexity: O(n) average
Space Complexity: O(n)
*/

public class P36_ContiguousArrayHashing {
    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> firstIndex = new HashMap<>();
        firstIndex.put(0, -1);

        int balance = 0;
        int best = 0;

        for (int i = 0; i < nums.length; i++) {
            balance += nums[i] == 1 ? 1 : -1;

            Integer earlier = firstIndex.get(balance);
            if (earlier != null) {
                best = Math.max(best, i - earlier);
            } else {
                firstIndex.put(balance, i);
            }
        }
        return best;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Array : " + Arrays.toString(nums));
        System.out.println("Best  : " + findMaxLength(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{0, 1});
        runDemo(new int[]{0, 1, 0});
        runDemo(new int[]{0, 0, 1, 0, 0, 0, 1, 1});
    }
}
