import java.util.*;

/*
Problem: Longest Subsequence With Limited Sum
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
You are given an integer array nums of length n and an integer array queries of length m.
    Return an array answer where answer[i] is the maximum size of a subsequence of nums
    whose sum is less than or equal to queries[i].

Why this pattern fits:
To fit as many elements as possible under a limit, always take the smallest ones.
    After sorting, the cheapest subsequence of size k is the first k elements, so its cost is a prefix sum.
    Prefix sums of positive numbers are increasing, and each query is an upper-bound search over them.

Approach:
Sort nums and turn it into a prefix sum array.
    For each query, binary search for the number of prefix sums that are <= the query.
    That count is the largest subsequence size.

Worked Example:
Example:
    nums = [4, 5, 2, 1], queries = [3, 10, 21]
    Sorted prefix sums: [1, 3, 7, 12].
    Query 3 -> 2 elements, query 10 -> 3 elements, query 21 -> 4 elements.

Time Complexity: O(n log n + m log n)
Space Complexity: O(1) besides the sort and the result
*/

public class P26_LongestSubsequenceWithLimitedSumBinarySearch {
    public static int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }

        int[] answer = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int left = 0;
            int right = nums.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] <= queries[q]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            answer[q] = left;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(answerQueries(new int[]{4, 5, 2, 1}, new int[]{3, 10, 21})));
        System.out.println(Arrays.toString(answerQueries(new int[]{2, 3, 4, 5}, new int[]{1})));
    }
}
