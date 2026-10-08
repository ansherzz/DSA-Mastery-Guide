import java.util.*;

/*
Problem: Valid Triangle Number
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
Given an integer array nums, return the number of triplets chosen from the array
    that can form the side lengths of a triangle.

Why this pattern fits:
Three lengths a <= b <= c form a triangle exactly when a + b > c.
    After sorting, fixing the largest side turns the problem into counting pairs with a sum above a threshold,
    which converging pointers do in linear time.

Approach:
Sort the array. Fix the largest side at index k, from the end down to index 2.
    Use left = 0 and right = k - 1.
    If nums[left] + nums[right] > nums[k], then every index from left to right - 1 also works with this right,
    so add right - left triangles and move right backward.
    Otherwise the sum is too small, so move left forward.

Worked Example:
Example:
    nums = [2, 2, 3, 4]
    Valid triangles: (2, 3, 4) using the first 2, (2, 3, 4) using the second 2, and (2, 2, 3). Answer = 3.

Time Complexity: O(n^2)
Space Complexity: O(1) besides the sort
*/

public class P27_ValidTriangleNumberTwoPointers {
    public static int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;

        for (int k = nums.length - 1; k >= 2; k--) {
            int left = 0;
            int right = k - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[k]) {
                    count += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(triangleNumber(new int[]{2, 2, 3, 4}));
        System.out.println(triangleNumber(new int[]{4, 2, 3, 4}));
        System.out.println(triangleNumber(new int[]{0, 1, 1}));
    }
}
