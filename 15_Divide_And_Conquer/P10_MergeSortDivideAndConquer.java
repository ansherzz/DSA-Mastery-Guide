import java.util.*;

/*
Problem: Merge Sort
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Given an integer array, sort it in ascending order using merge sort.

    Merge sort is the textbook divide-and-conquer sorting algorithm.

Why this pattern fits:
The problem splits cleanly into sorting the left half, sorting the right half, and merging the two sorted halves.

Approach:
Recursively split the array until single-element ranges remain.
    Then merge sorted halves into a temporary array and copy back.

Worked Example:
Example:
    nums = [5, 2, 3, 1]
    The sorted result is [1, 2, 3, 5].

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P10_MergeSortDivideAndConquer {
    public static void mergeSort(int[] nums) {
        int[] temp = new int[nums.length];
        sort(nums, temp, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int[] temp, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        sort(nums, temp, left, mid);
        sort(nums, temp, mid + 1, right);
        merge(nums, temp, left, mid, right);
    }

    private static void merge(int[] nums, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        while (i <= mid) temp[k++] = nums[i++];
        while (j <= right) temp[k++] = nums[j++];

        for (int idx = left; idx <= right; idx++) {
            nums[idx] = temp[idx];
        }
    }

    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 1};
        mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
