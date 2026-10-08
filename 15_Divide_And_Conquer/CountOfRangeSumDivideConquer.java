import java.util.*;

/*
Problem: Count of Range Sum
Pattern: 15. Divide & Conquer
Difficulty: Hard

Interview Prompt:
Given an integer array nums and two integers lower and upper,
    return the number of range sums that lie in [lower, upper] inclusive.
    A range sum S(i, j) is the sum of the elements of nums between indices i and j inclusive, where i <= j.

Why this pattern fits:
With prefix sums P, the task becomes counting pairs i < j with lower <= P[j] - P[i] <= upper.
    Counting ordered pairs that satisfy an inequality is the same shape as counting inversions,
    and merge sort handles it: pairs inside each half are counted recursively, and cross pairs are counted
    while both halves are sorted.

Approach:
Build the prefix sum array of length n + 1 using long values.
    In the merge sort, after both halves are sorted, count cross pairs:
    for each P[i] in the left half, find the range of right-half values in [P[i] + lower, P[i] + upper].
    Because the left half is sorted, both boundaries of that range only move forward,
    so two pointers count all cross pairs in linear time.
    Then merge the halves so the parent call receives a sorted range.

Worked Example:
Example:
    nums = [-2, 5, -1], lower = -2, upper = 2
    Range sums in [-2, 2]: S(0, 0) = -2, S(2, 2) = -1, S(0, 2) = 2. Answer = 3.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class CountOfRangeSumDivideConquer {
    public static int countRangeSum(int[] nums, int lower, int upper) {
        long[] prefix = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        return sortAndCount(prefix, new long[prefix.length], 0, prefix.length - 1, lower, upper);
    }

    private static int sortAndCount(long[] prefix, long[] buffer, int left, int right, int lower, int upper) {
        if (left >= right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int count = sortAndCount(prefix, buffer, left, mid, lower, upper)
            + sortAndCount(prefix, buffer, mid + 1, right, lower, upper);

        int low = mid + 1;
        int high = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (low <= right && prefix[low] - prefix[i] < lower) {
                low++;
            }
            while (high <= right && prefix[high] - prefix[i] <= upper) {
                high++;
            }
            count += high - low;
        }

        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            buffer[k++] = prefix[i] <= prefix[j] ? prefix[i++] : prefix[j++];
        }
        while (i <= mid) buffer[k++] = prefix[i++];
        while (j <= right) buffer[k++] = prefix[j++];
        System.arraycopy(buffer, left, prefix, left, right - left + 1);

        return count;
    }

    public static void main(String[] args) {
        System.out.println(countRangeSum(new int[]{-2, 5, -1}, -2, 2));
        System.out.println(countRangeSum(new int[]{0}, 0, 0));
        System.out.println(countRangeSum(new int[]{1, 2, 3, 4}, 3, 6));
    }
}
