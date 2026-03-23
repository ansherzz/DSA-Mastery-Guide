import java.util.*;

/*
Problem: Split Array Largest Sum
Pattern: 07. Binary Search on Answer
Difficulty: Hard

Interview Prompt:
Given an array and an integer k, split the array into exactly k non-empty contiguous parts.
    Minimize the largest subarray sum among those parts and return that minimum possible value.

    This is a flagship binary-search-on-answer problem.

Why this pattern fits:
If you can split the array so every part has sum <= X, then any larger X will also work.
    That monotonic feasibility check is all binary search needs.

Approach:
Search the answer between max(nums) and sum(nums).
    Greedily count how many pieces are needed if no piece may exceed the candidate limit.

Worked Example:
Example:
    nums = [7, 2, 5, 10, 8], k = 2
    The minimum possible largest sum is 18.

Time Complexity: O(n log S)
Space Complexity: O(1)
*/

public class SplitArrayLargestSumBinarySearchAnswer {
    private static boolean canSplit(int[] nums, int k, int limit) {
        int pieces = 1;
        int current = 0;

        for (int num : nums) {
            if (current + num > limit) {
                pieces++;
                current = 0;
            }
            current += num;
        }

        return pieces <= k;
    }

    public static int splitArray(int[] nums, int k) {
        int left = 0;
        int right = 0;
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canSplit(nums, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Nums   : " + Arrays.toString(nums));
        System.out.println("k      : " + k);
        System.out.println("Answer : " + splitArray(nums, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{7, 2, 5, 10, 8}, 2);
        runDemo(new int[]{1, 2, 3, 4, 5}, 2);
    }
}
