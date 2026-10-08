import java.util.*;

/*
Problem: Bottom-Up Merge Sort (Iterative)
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Sort an array in O(n log n) time using merge sort, but without recursion.

Why this pattern fits:
Top-down merge sort divides until runs have length one and then merges on the way back.
The divide phase does no real work, so the merges can be performed directly from the bottom up.

Approach:
For width = 1, 2, 4, ...: merge every pair of adjacent runs [left, left + width) and [left + width, left + 2 * width)
into a buffer, then copy the buffer back.

Worked Example:
[5, 2, 3, 1] -> width 1: [2, 5, 1, 3]; width 2: [1, 2, 3, 5].

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P12_BottomUpMergeSortDivideConquer {
    public static void mergeSort(int[] nums) {
        int n = nums.length;
        int[] buffer = new int[n];
        for (int width = 1; width < n; width *= 2) {
            for (int left = 0; left < n; left += 2 * width) {
                int mid = Math.min(left + width, n);
                int right = Math.min(left + 2 * width, n);
                int i = left;
                int j = mid;
                for (int k = left; k < right; k++) {
                    if (i < mid && (j >= right || nums[i] <= nums[j])) {
                        buffer[k] = nums[i++];
                    } else {
                        buffer[k] = nums[j++];
                    }
                }
            }
            System.arraycopy(buffer, 0, nums, 0, n);
        }
    }

    private static void runDemo(int[] nums) {
        String before = Arrays.toString(nums);
        mergeSort(nums);
        System.out.println(before + " -> " + Arrays.toString(nums));
    }

    public static void main(String[] args) {
        runDemo(new int[]{5, 2, 3, 1});
        runDemo(new int[]{5, 1, 1, 2, 0, 0});
    }
}
