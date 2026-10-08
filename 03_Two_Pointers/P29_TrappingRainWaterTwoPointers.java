import java.util.*;

/*
Problem: Trapping Rain Water
Pattern: 03. Two Pointers
Difficulty: Hard

Interview Prompt:
Given an array of non-negative integers representing bar heights, compute how much rain water can be trapped
    after raining.

    The two-pointer solution avoids extra prefix and suffix arrays by maintaining the best walls seen so far.

Why this pattern fits:
The amount of water at a position depends on the smaller of the best left wall and best right wall.
    Two pointers let you determine which side is already limited and therefore safe to process.

Approach:
Maintain left, right, leftMax, and rightMax.
    If leftMax <= rightMax, the left side is the bottleneck, so process left and move it inward.
    Otherwise process the right side.

Worked Example:
Example:
    height = [4, 2, 0, 3, 2, 5]
    The total trapped water is 9.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P29_TrappingRainWaterTwoPointers {
    public static int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;

        while (left < right) {
            if (height[left] <= height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }

        return water;
    }

    private static void runDemo(int[] height) {
        System.out.println("Height : " + Arrays.toString(height));
        System.out.println("Water  : " + trap(height));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        runDemo(new int[]{4, 2, 0, 3, 2, 5});
    }
}
