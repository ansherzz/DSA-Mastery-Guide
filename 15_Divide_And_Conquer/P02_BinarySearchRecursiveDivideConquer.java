import java.util.*;

/*
Problem: Binary Search as Divide and Conquer
Pattern: 15. Divide & Conquer
Difficulty: Easy

Interview Prompt:
Given a sorted array of distinct integers and a target, return the index of the target or -1 if it is absent.
    Implement the search recursively and explain why it is a divide-and-conquer algorithm.

Why this pattern fits:
Binary search is the simplest divide-and-conquer algorithm: divide the range at the middle,
    conquer by recursing into only one half, and there is no combine step at all.
    Seeing it in this form makes the recurrence T(n) = T(n / 2) + O(1) = O(log n) easy to state.

Approach:
Base case: an empty range means the target is not present.
    Otherwise compare the middle element with the target.
    Return the middle index on a match, recurse left if the middle is too large, or recurse right if it is too small.

Worked Example:
Example:
    nums = [-1, 0, 3, 5, 9, 12], target = 9
    Range [0, 5]: middle is 3 (too small), recurse into [3, 5].
    Range [3, 5]: middle is 9 at index 4. Answer = 4.

Time Complexity: O(log n)
Space Complexity: O(log n) recursion stack
*/

public class P02_BinarySearchRecursiveDivideConquer {
    public static int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length - 1);
    }

    private static int search(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] > target) {
            return search(nums, target, left, mid - 1);
        }
        return search(nums, target, mid + 1, right);
    }

    private static void runDemo(int[] nums, int target) {
        System.out.println("Nums = " + Arrays.toString(nums) + ", target = " + target + " -> index " + search(nums, target));
    }

    public static void main(String[] args) {
        runDemo(new int[]{-1, 0, 3, 5, 9, 12}, 9);
        runDemo(new int[]{-1, 0, 3, 5, 9, 12}, 2);
        runDemo(new int[]{5}, 5);
    }
}
