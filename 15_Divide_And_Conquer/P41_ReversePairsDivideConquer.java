import java.util.*;

/*
Problem: Reverse Pairs
Pattern: 15. Divide & Conquer
Difficulty: Hard

Interview Prompt:
Given an integer array nums, return the number of reverse pairs.
    A reverse pair is a pair of indices (i, j) with i < j and nums[i] > 2 * nums[j].

Why this pattern fits:
This is a stricter cousin of counting inversions. The merge-sort framework still applies:
    pairs inside each half are counted recursively, and cross pairs are counted while both halves are sorted.
    The difference is that the counting condition is not the same as the merge condition,
    so counting and merging are done as two separate passes.

Approach:
Recursively sort and count both halves.
    Before merging, count cross pairs with two pointers: for each i in the left half,
    advance j in the right half while nums[i] > 2 * nums[j]. Because both halves are sorted,
    j never moves backward. Add the number of right elements passed.
    Then merge the two halves normally. Use long arithmetic for 2 * nums[j].

Worked Example:
Example:
    nums = [1, 3, 2, 3, 1]
    The reverse pairs are (index 1, index 4): 3 > 2 * 1, and (index 3, index 4): 3 > 2 * 1.
    Answer = 2.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P41_ReversePairsDivideConquer {
    public static int reversePairs(int[] nums) {
        int[] work = nums.clone();
        return sortAndCount(work, new int[work.length], 0, work.length - 1);
    }

    private static int sortAndCount(int[] nums, int[] buffer, int left, int right) {
        if (left >= right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int count = sortAndCount(nums, buffer, left, mid) + sortAndCount(nums, buffer, mid + 1, right);

        int j = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (j <= right && (long) nums[i] > 2L * nums[j]) {
                j++;
            }
            count += j - (mid + 1);
        }

        int a = left;
        int b = mid + 1;
        int write = left;
        while (a <= mid && b <= right) {
            buffer[write++] = nums[a] <= nums[b] ? nums[a++] : nums[b++];
        }
        while (a <= mid) {
            buffer[write++] = nums[a++];
        }
        while (b <= right) {
            buffer[write++] = nums[b++];
        }
        System.arraycopy(buffer, left, nums, left, right - left + 1);

        return count;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums = " + Arrays.toString(nums) + " -> reverse pairs " + reversePairs(nums));
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 3, 2, 3, 1});
        runDemo(new int[]{2, 4, 3, 5, 1});
        runDemo(new int[]{2147483647, 2147483647, 2147483647});
    }
}
