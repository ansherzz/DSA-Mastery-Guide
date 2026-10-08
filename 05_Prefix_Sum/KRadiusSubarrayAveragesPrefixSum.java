import java.util.*;

/*
Problem: K Radius Subarray Averages
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
You are given an array nums of n integers and an integer k.
    The k-radius average for index i is the average of all elements between indices i - k and i + k inclusive,
    using integer division. If there are fewer than k elements before or after index i, the k-radius average is -1.
    Return an array of the k-radius averages for every index.

Why this pattern fits:
Every answer is a range sum divided by a fixed length.
    A prefix sum array answers each range sum in O(1), independent of k.

Approach:
Build prefix where prefix[i] is the sum of the first i elements, using long to avoid overflow.
    For each index i with i - k >= 0 and i + k < n, the range sum is prefix[i + k + 1] - prefix[i - k].
    Divide by 2k + 1. All other indices get -1.

Worked Example:
Example:
    nums = [7, 4, 3, 9, 1, 8, 5, 2, 6], k = 3
    Index 3: (7 + 4 + 3 + 9 + 1 + 8 + 5) / 7 = 37 / 7 = 5.
    Result: [-1, -1, -1, 5, 4, 4, -1, -1, -1].

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class KRadiusSubarrayAveragesPrefixSum {
    public static int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int[] result = new int[n];
        Arrays.fill(result, -1);
        int windowLength = 2 * k + 1;
        for (int i = k; i + k < n; i++) {
            result[i] = (int) ((prefix[i + k + 1] - prefix[i - k]) / windowLength);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getAverages(new int[]{7, 4, 3, 9, 1, 8, 5, 2, 6}, 3)));
        System.out.println(Arrays.toString(getAverages(new int[]{100000}, 0)));
        System.out.println(Arrays.toString(getAverages(new int[]{8}, 100000)));
    }
}
