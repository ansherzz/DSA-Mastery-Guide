import java.util.*;

/*
Problem: Bubble Sort
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Implement bubble sort to sort an integer array in ascending order, in place.
Stop as soon as the array is already sorted.

Why this pattern fits:
Bubble sort is the classic quadratic baseline every later sorting idea improves on.
Knowing its invariant (the largest remaining value settles at the end of each pass) builds intuition for in-place swaps.

Approach:
Run passes over the unsorted prefix.
In each pass swap neighbors that are out of order.
After pass k the last k elements are final. If a pass performs no swap, the array is sorted.

Worked Example:
nums = [5, 1, 4, 2, 8]
Pass 1: [1, 4, 2, 5, 8]. Pass 2: [1, 2, 4, 5, 8]. Pass 3 makes no swap, so stop.

Time Complexity: O(n^2) worst case, O(n) when already sorted
Space Complexity: O(1)
*/

public class P21_BubbleSortArrayBasics {
    public static void bubbleSort(int[] nums) {
        for (int end = nums.length - 1; end > 0; end--) {
            boolean swapped = false;
            for (int i = 0; i < end; i++) {
                if (nums[i] > nums[i + 1]) {
                    int temp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                return;
            }
        }
    }

    private static void runDemo(int[] nums) {
        String before = Arrays.toString(nums);
        bubbleSort(nums);
        System.out.println(before + " -> " + Arrays.toString(nums));
    }

    public static void main(String[] args) {
        runDemo(new int[]{5, 1, 4, 2, 8});
        runDemo(new int[]{1, 2, 3});
    }
}
