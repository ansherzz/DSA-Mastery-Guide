import java.util.*;

/*
Problem: Count of Smaller Numbers After Self
Pattern: 15. Divide & Conquer
Difficulty: Hard

Interview Prompt:
Given an integer array nums, return an array counts where counts[i] is the number of elements
    to the right of nums[i] that are strictly smaller than nums[i].

Why this pattern fits:
The total of all counts is the number of inversions, which merge sort counts in O(n log n).
    Here each element needs its own count, so the sort is performed on indices instead of values.
    That way an element's original position is still known when it is moved during a merge.

Approach:
Sort an array of indices by the values they point to, using merge sort.
    During a merge, keep track of how many elements from the right half have already been placed.
    When an element from the left half is placed, every right-half element placed before it
    is smaller and originally to its right, so add that number to its count.
    Take from the left half on ties so that equal values are not counted.

Worked Example:
Example:
    nums = [5, 2, 6, 1]
    To the right of 5 there are 2 smaller values (2 and 1).
    To the right of 2 there is 1 smaller value (1).
    To the right of 6 there is 1 smaller value (1).
    To the right of 1 there are none.
    Answer = [2, 1, 1, 0]

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P27_CountOfSmallerNumbersAfterSelfDivideConquer {
    public static List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        int[] counts = new int[n];
        sort(nums, indices, new int[n], counts, 0, n - 1);

        List<Integer> result = new ArrayList<>();
        for (int count : counts) {
            result.add(count);
        }
        return result;
    }

    private static void sort(int[] nums, int[] indices, int[] buffer, int[] counts, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        sort(nums, indices, buffer, counts, left, mid);
        sort(nums, indices, buffer, counts, mid + 1, right);

        int i = left;
        int j = mid + 1;
        int write = left;
        int rightPlaced = 0;

        while (i <= mid && j <= right) {
            if (nums[indices[i]] <= nums[indices[j]]) {
                counts[indices[i]] += rightPlaced;
                buffer[write++] = indices[i++];
            } else {
                rightPlaced++;
                buffer[write++] = indices[j++];
            }
        }
        while (i <= mid) {
            counts[indices[i]] += rightPlaced;
            buffer[write++] = indices[i++];
        }
        while (j <= right) {
            buffer[write++] = indices[j++];
        }

        System.arraycopy(buffer, left, indices, left, right - left + 1);
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums = " + Arrays.toString(nums) + " -> " + countSmaller(nums));
    }

    public static void main(String[] args) {
        runDemo(new int[]{5, 2, 6, 1});
        runDemo(new int[]{-1, -1});
        runDemo(new int[]{2, 0, 1});
    }
}
