import java.util.*;

/*
Problem: Next Permutation
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
Rearrange the numbers of an array into the lexicographically next greater permutation.
    If no greater permutation exists (the array is in descending order), rearrange it into the smallest
    permutation, which is ascending order. The change must be done in-place with constant extra space.

Why this pattern fits:
The solution is built from pointer scans from the right end plus an in-place two-pointer reversal.
    Understanding which suffix is already "maxed out" tells you exactly where the pointers should stop.

Approach:
Scan from the right to find the first index pivot where nums[pivot] < nums[pivot + 1].
    Everything after pivot is in descending order, so it is the largest arrangement of that suffix.
    If a pivot exists, scan from the right again for the first value greater than nums[pivot] and swap them.
    Finally reverse the suffix after pivot so it becomes the smallest arrangement.

Worked Example:
Example:
    nums = [1, 3, 5, 4, 2]
    Pivot is the 3 at index 1. The first larger value from the right is 4. After swapping: [1, 4, 5, 3, 2].
    Reversing the suffix gives [1, 4, 2, 3, 5].

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class NextPermutationTwoPointers {
    public static void nextPermutation(int[] nums) {
        int pivot = nums.length - 2;
        while (pivot >= 0 && nums[pivot] >= nums[pivot + 1]) {
            pivot--;
        }

        if (pivot >= 0) {
            int successor = nums.length - 1;
            while (nums[successor] <= nums[pivot]) {
                successor--;
            }
            swap(nums, pivot, successor);
        }

        int left = pivot + 1;
        int right = nums.length - 1;
        while (left < right) {
            swap(nums, left++, right--);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Before: " + Arrays.toString(nums));
        nextPermutation(nums);
        System.out.println("After : " + Arrays.toString(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 3});
        runDemo(new int[]{3, 2, 1});
        runDemo(new int[]{1, 3, 5, 4, 2});
    }
}
