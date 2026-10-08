import java.util.*;

/*
Problem: Binary Search
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Given a sorted integer array and a target value, return the index of the target if it exists.
    Otherwise return -1.

    This is the canonical binary search and the foundation for all later search variations.

Why this pattern fits:
The array is sorted, so a single comparison at the middle tells you which half can be discarded.
    This is the purest form of the pattern.

Approach:
Maintain an inclusive search range [left, right].
    At each step, compute mid. If nums[mid] equals the target, return it.
    Otherwise discard the half that cannot contain the target.

Worked Example:
Example:
    nums = [-1, 0, 3, 5, 9, 12], target = 9
    The answer is index 4.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P01_BinarySearchClassic {
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    private static void runDemo(int[] nums, int target) {
        System.out.println("Nums   : " + Arrays.toString(nums));
        System.out.println("Target : " + target);
        System.out.println("Index  : " + search(nums, target));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{-1, 0, 3, 5, 9, 12}, 9);
        runDemo(new int[]{-1, 0, 3, 5, 9, 12}, 2);
    }
}
