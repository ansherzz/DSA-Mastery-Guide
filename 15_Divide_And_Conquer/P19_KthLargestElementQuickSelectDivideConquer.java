import java.util.*;

/*
Problem: Kth Largest Element in an Array using Quickselect
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Given an integer array nums and an integer k, return the kth largest element in the array.
    This is the kth largest in sorted order, not the kth distinct element.

    Can you solve it without fully sorting the array?

Why this pattern fits:
Quickselect reuses the partition step of quicksort. After partitioning, the pivot sits at its final sorted position.
    If that position is the one you want, you are done; otherwise only one side can contain the answer.
    Divide, then conquer just one part.

Approach:
The kth largest element is at index n - k in ascending order.
    Pick a random pivot and partition the current range so that smaller values are on the left.
    Compare the pivot's final index with the target index and continue in the left or right part only.
    The random pivot gives expected linear time.

Worked Example:
Example:
    nums = [3, 2, 1, 5, 6, 4], k = 2
    Sorted order is [1, 2, 3, 4, 5, 6], so the answer is the element at index 4, which is 5.

Time Complexity: O(n) on average, O(n^2) in the worst case
Space Complexity: O(1) extra, on a copy of the input
*/

public class P19_KthLargestElementQuickSelectDivideConquer {
    private static final Random RANDOM = new Random(42);

    public static int findKthLargest(int[] nums, int k) {
        int[] work = nums.clone();
        int targetIndex = work.length - k;

        int left = 0;
        int right = work.length - 1;
        while (left < right) {
            int pivotIndex = partition(work, left, right);
            if (pivotIndex == targetIndex) {
                break;
            }
            if (pivotIndex < targetIndex) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
        return work[targetIndex];
    }

    private static int partition(int[] nums, int left, int right) {
        int randomIndex = left + RANDOM.nextInt(right - left + 1);
        swap(nums, randomIndex, right);
        int pivot = nums[right];

        int store = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
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

    private static void runDemo(int[] nums, int k) {
        System.out.println("Nums = " + Arrays.toString(nums) + ", k = " + k + " -> " + findKthLargest(nums, k));
    }

    public static void main(String[] args) {
        runDemo(new int[]{3, 2, 1, 5, 6, 4}, 2);
        runDemo(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4);
        runDemo(new int[]{7}, 1);
    }
}
