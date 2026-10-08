import java.util.*;

/*
Problem: Squares of a Sorted Array
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
Given an integer array sorted in non-decreasing order, return an array of the squares of each number,
    also sorted in non-decreasing order. The input may contain negative numbers.

    Solve it in O(n) without sorting the squared values.

Why this pattern fits:
After squaring, the largest values come from the two ends of the array (large negatives or large positives).
    Two pointers at the ends can therefore produce the squares from largest to smallest.

Approach:
Compare the absolute values at the left and right pointers.
    Write the larger square into the result from the back, then move that pointer inward.
    Continue until the pointers cross.

Worked Example:
Example:
    nums = [-4, -1, 0, 3, 10]
    Largest square is 100 (from 10), then 16 (from -4), then 9, then 1, then 0.
    Answer = [0, 1, 9, 16, 100]

Time Complexity: O(n)
Space Complexity: O(n) for the output
*/

public class P06_SquaresOfSortedArrayTwoPointers {
    public static int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;

        for (int write = nums.length - 1; write >= 0; write--) {
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                result[write] = nums[left] * nums[left];
                left++;
            } else {
                result[write] = nums[right] * nums[right];
                right--;
            }
        }
        return result;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums    : " + Arrays.toString(nums));
        System.out.println("Squares : " + Arrays.toString(sortedSquares(nums)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{-4, -1, 0, 3, 10});
        runDemo(new int[]{-7, -3, 2, 3, 11});
    }
}
