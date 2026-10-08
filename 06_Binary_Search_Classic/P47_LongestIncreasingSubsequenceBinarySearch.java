import java.util.*;

/*
Problem: Longest Increasing Subsequence
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
Given an integer array nums, return the length of the longest strictly increasing subsequence.
    Solve it in O(n log n) time.

Why this pattern fits:
The O(n^2) DP asks, for each element, "what is the best subsequence I can extend?".
    Keeping the smallest possible tail for every subsequence length produces a sorted array,
    and the extension question becomes a lower-bound search in it.

Approach:
Maintain tails, where tails[k] is the smallest tail value of any increasing subsequence of length k + 1.
    tails is always strictly increasing.
    For each number, find the first tail >= the number.
    If none exists, the number extends the longest subsequence, so append it.
    Otherwise replace that tail with the number, which keeps the same length but leaves more room to grow.
    The final size of tails is the answer. tails itself is not necessarily a valid subsequence.

Worked Example:
Example:
    nums = [10, 9, 2, 5, 3, 7, 101, 18]
    tails evolves: [10] -> [9] -> [2] -> [2, 5] -> [2, 3] -> [2, 3, 7] -> [2, 3, 7, 101] -> [2, 3, 7, 18].
    Answer = 4.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P47_LongestIncreasingSubsequenceBinarySearch {
    public static int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;

        for (int num : nums) {
            int left = 0;
            int right = size;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] >= num) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            tails[left] = num;
            if (left == size) {
                size++;
            }
        }
        return size;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
        System.out.println(lengthOfLIS(new int[]{7, 7, 7, 7}));
    }
}
