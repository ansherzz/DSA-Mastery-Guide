import java.util.*;

/*
Problem: Count Inversions using Merge Sort
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Given an integer array, count the number of inversions:
    pairs of indices (i, j) with i < j and nums[i] > nums[j].

    The O(n^2) pair check is in folder 01. Solve it here in O(n log n).

Why this pattern fits:
Split the array in half. Every inversion is inside the left half, inside the right half,
    or has one element in each half. The first two kinds are counted recursively.
    The cross kind is counted for free during the merge step, because both halves are sorted by then.

Approach:
Run merge sort. While merging, whenever an element from the right half is placed before
    the remaining elements of the left half, it forms an inversion with every one of those remaining elements.
    Add (number of elements still left in the left half) to the count at that moment.

Worked Example:
Example:
    nums = [2, 4, 1, 3, 5]
    Left half sorted: [1, 2, 4] contributes 2 internal inversions (2 > 1 and 4 > 1).
    Merging with [3, 5]: when 3 is placed, one left element (4) remains, adding 1 more.
    Total = 3.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P14_CountInversionsDivideConquer {
    public static long countInversions(int[] nums) {
        int[] work = nums.clone();
        return sortAndCount(work, new int[work.length], 0, work.length - 1);
    }

    private static long sortAndCount(int[] nums, int[] buffer, int left, int right) {
        if (left >= right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        long count = sortAndCount(nums, buffer, left, mid) + sortAndCount(nums, buffer, mid + 1, right);

        int i = left;
        int j = mid + 1;
        int write = left;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                buffer[write++] = nums[i++];
            } else {
                count += mid - i + 1;
                buffer[write++] = nums[j++];
            }
        }
        while (i <= mid) {
            buffer[write++] = nums[i++];
        }
        while (j <= right) {
            buffer[write++] = nums[j++];
        }

        System.arraycopy(buffer, left, nums, left, right - left + 1);
        return count;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums = " + Arrays.toString(nums) + " -> inversions " + countInversions(nums));
    }

    public static void main(String[] args) {
        runDemo(new int[]{2, 4, 1, 3, 5});
        runDemo(new int[]{5, 4, 3, 2, 1});
        runDemo(new int[]{1, 2, 3});
    }
}
