import java.util.*;

/*
Problem: Quick Sort
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Sort an integer array in ascending order using quicksort.

    Quicksort partitions around a pivot, then recursively sorts the two partitions.

Why this pattern fits:
Unlike merge sort, quicksort's merge step is implicit in partitioning.
    It is a strong divide-and-conquer exercise for in-place recursion.

Approach:
Choose the last element as a pivot.
    Partition the array so all smaller or equal values are on the left and larger values on the right.
    Then recursively sort the two sides.

Worked Example:
Example:
    nums = [3, 6, 8, 10, 1, 2, 1]
    The sorted result is [1, 1, 2, 3, 6, 8, 10].

Time Complexity: Average O(n log n), worst O(n^2)
Space Complexity: O(log n) average recursion stack
*/

public class P11_QuickSortDivideAndConquer {
    public static void quickSort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotIndex = partition(nums, left, right);
        sort(nums, left, pivotIndex - 1);
        sort(nums, pivotIndex + 1, right);
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int store = left;

        for (int i = left; i < right; i++) {
            if (nums[i] <= pivot) {
                swap(nums, i, store++);
            }
        }

        swap(nums, store, right);
        return store;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 6, 8, 10, 1, 2, 1};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
