import java.util.*;

/*
Problem: Three-Way Quick Sort (Dutch National Flag Partition)
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Implement quicksort so that it stays efficient when the array contains many duplicate values.

Why this pattern fits:
Standard two-way partitioning degrades to O(n^2) on arrays with few distinct values,
because equal elements keep being re-partitioned. Grouping all pivot-equal elements removes them from further work.

Approach:
Choose the middle value as the pivot. Maintain three regions with pointers lt, i, gt:
values < pivot are swapped to the front, values > pivot to the back, equal values stay in the middle.
Recurse on [low, lt - 1] and [gt + 1, high].

Worked Example:
[3, 5, 3, 1, 3, 9, 1] with pivot 1 -> [1, 1 | 3, 5, 3, 3, 9]; the right part is then sorted the same way.

Time Complexity: O(n log n) average, O(n) when all values are equal
Space Complexity: O(log n) average recursion depth
*/

public class P13_ThreeWayQuickSortDivideConquer {
    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    private static void sort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = nums[low + (high - low) / 2];
        int lt = low;
        int i = low;
        int gt = high;
        while (i <= gt) {
            if (nums[i] < pivot) {
                swap(nums, lt++, i++);
            } else if (nums[i] > pivot) {
                swap(nums, i, gt--);
            } else {
                i++;
            }
        }
        sort(nums, low, lt - 1);
        sort(nums, gt + 1, high);
    }

    public static void quickSort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private static void runDemo(int[] nums) {
        String before = Arrays.toString(nums);
        quickSort(nums);
        System.out.println(before + " -> " + Arrays.toString(nums));
    }

    public static void main(String[] args) {
        runDemo(new int[]{3, 5, 3, 1, 3, 9, 1});
        runDemo(new int[]{2, 2, 2});
    }
}
