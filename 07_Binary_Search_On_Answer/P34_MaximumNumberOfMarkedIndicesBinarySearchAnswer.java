import java.util.*;

/*
Problem: Find the Maximum Number of Marked Indices
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
You may repeatedly pick two different unmarked indices i and j with 2 * nums[i] <= nums[j] and mark both.
Return the maximum number of indices that can be marked.

Why this pattern fits:
If k pairs can be formed, so can k - 1. To test k, the safest choice is the k smallest values as the small sides
and the k largest as the big sides, matched in sorted order.

Approach:
Sort. For a candidate k, check 2 * nums[i] <= nums[n - k + i] for all i < k. Bisect the largest k and return 2 * k.

Worked Example:
nums = [9, 2, 5, 4] -> sorted [2, 4, 5, 9]: pairs (2, 5) and (4, 9). Answer = 4.

Time Complexity: O(n log n)
Space Complexity: O(1) extra
*/

public class P34_MaximumNumberOfMarkedIndicesBinarySearchAnswer {
    public static int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int low = 0;
        int high = n / 2;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            boolean ok = true;
            for (int i = 0; i < mid && ok; i++) {
                ok = 2L * nums[i] <= nums[n - mid + i];
            }
            if (ok) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return 2 * low;
    }

    public static void main(String[] args) {
        System.out.println("[3, 5, 2, 4] -> " + maxNumOfMarkedIndices(new int[]{3, 5, 2, 4}));
        System.out.println("[9, 2, 5, 4] -> " + maxNumOfMarkedIndices(new int[]{9, 2, 5, 4}));
        System.out.println("[7, 6, 8] -> " + maxNumOfMarkedIndices(new int[]{7, 6, 8}));
    }
}
