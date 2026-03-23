import java.util.*;

/*
Problem: Container With Most Water
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
Given an array where each value represents the height of a vertical line, choose two lines that together
    hold the maximum amount of water. The area is width times the smaller of the two heights.

    Return that maximum area.

Why this pattern fits:
Two pointers work because the width always shrinks, so the only hope of improving the area is to move
    away from the shorter wall and search for a taller one.

Approach:
Start with the widest container: left at 0 and right at n - 1.
    Compute the current area, then move the pointer at the smaller height inward.

Worked Example:
Example:
    heights = [1, 8, 6, 2, 5, 4, 8, 3, 7]
    The best answer is 49.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class ContainerWithMostWaterTwoPointers {
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int best = 0;

        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            best = Math.max(best, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return best;
    }

    private static void runDemo(int[] height) {
        System.out.println("Heights : " + Arrays.toString(height));
        System.out.println("Max area: " + maxArea(height));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        runDemo(new int[]{1, 1});
    }
}
