import java.util.*;

/*
Problem: K-diff Pairs in an Array
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
Given an integer array and an integer k >= 0, return the number of unique pairs (a, b)
from the array with b - a == k. Pairs with the same values count once.

Why this pattern fits:
After sorting, increasing the right pointer grows the difference and increasing the left pointer shrinks it.
Both pointers only move forward, giving a linear scan.

Approach:
Sort. Keep i < j. If the difference is too small (or the pointers collide) advance j; if too large advance i.
On a match, count it and advance i past all copies of the same value so the pair is not counted again.

Worked Example:
nums = [3, 1, 4, 1, 5], k = 2 -> sorted [1, 1, 3, 4, 5]. Pairs (1, 3) and (3, 5). Answer = 2.

Time Complexity: O(n log n)
Space Complexity: O(1) extra
*/

public class P28_KDiffPairsInAnArrayTwoPointers {
    public static int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0;
        int j = 1;
        int count = 0;
        while (j < nums.length) {
            if (i == j || nums[j] - nums[i] < k) {
                j++;
            } else if (nums[j] - nums[i] > k) {
                i++;
            } else {
                count++;
                i++;
                while (i < nums.length && nums[i] == nums[i - 1]) {
                    i++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("[3, 1, 4, 1, 5], k = 2 -> " + findPairs(new int[]{3, 1, 4, 1, 5}, 2));
        System.out.println("[1, 2, 3, 4, 5], k = 1 -> " + findPairs(new int[]{1, 2, 3, 4, 5}, 1));
        System.out.println("[1, 3, 1, 5, 4], k = 0 -> " + findPairs(new int[]{1, 3, 1, 5, 4}, 0));
    }
}
