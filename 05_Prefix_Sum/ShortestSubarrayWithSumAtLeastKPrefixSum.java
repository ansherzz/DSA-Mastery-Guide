import java.util.*;

/*
Problem: Shortest Subarray with Sum at Least K
Pattern: 05. Prefix Sum
Difficulty: Hard

Interview Prompt:
Given an integer array nums and an integer k, return the length of the shortest non-empty contiguous subarray
    whose sum is at least k. If there is no such subarray, return -1. Values may be negative.

Why this pattern fits:
Negative values break the usual sliding window, so work with prefix sums instead:
    you need indices i < j with prefix[j] - prefix[i] >= k and j - i as small as possible.
    A deque of candidate start indices with increasing prefix values makes this linear.

Approach:
Build the prefix array. Process each index j while keeping a deque of start candidates.
    1. While prefix[j] - prefix[front] >= k, record j - front and pop the front.
       That start can never give a shorter answer with a later j.
    2. While prefix[j] <= prefix[back], pop the back.
       The index j is a later and smaller start, so it is better in every way.
    3. Push j.

Worked Example:
Example:
    nums = [2, -1, 2], k = 3
    prefix = [0, 2, 1, 3]
    At j = 3, prefix[3] - prefix[0] = 3 >= 3, so the length is 3. No shorter subarray reaches 3.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class ShortestSubarrayWithSumAtLeastKPrefixSum {
    public static int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        Deque<Integer> starts = new ArrayDeque<>();
        int best = n + 1;

        for (int j = 0; j <= n; j++) {
            while (!starts.isEmpty() && prefix[j] - prefix[starts.peekFirst()] >= k) {
                best = Math.min(best, j - starts.pollFirst());
            }
            while (!starts.isEmpty() && prefix[j] <= prefix[starts.peekLast()]) {
                starts.pollLast();
            }
            starts.offerLast(j);
        }
        return best == n + 1 ? -1 : best;
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Nums   : " + Arrays.toString(nums));
        System.out.println("k      : " + k);
        System.out.println("Length : " + shortestSubarray(nums, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1}, 1);
        runDemo(new int[]{1, 2}, 4);
        runDemo(new int[]{2, -1, 2}, 3);
        runDemo(new int[]{84, -37, 32, 40, 95}, 167);
    }
}
