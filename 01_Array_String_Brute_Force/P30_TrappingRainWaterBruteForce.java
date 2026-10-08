import java.util.*;

/*
Problem: Trapping Rain Water
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Hard

Interview Prompt:
Given n non-negative integers representing an elevation map where the width of each bar is 1,
    compute how much water it can trap after raining.

Why this pattern fits:
The hard part of this problem is the per-index formula, not the optimization.
    Brute force forces you to state that formula clearly:
    water above a bar is limited by the shorter of the tallest bar on its left and the tallest bar on its right.

Approach:
For every index i, scan left to find the tallest bar in [0, i] and scan right to find the tallest bar in [i, n - 1].
    The water standing on bar i is min(leftMax, rightMax) - height[i], which is never negative
    because both maximums include bar i itself.
    Upgrade path: precompute prefix and suffix maximums for O(n) time, or use two pointers for O(1) extra space.

Worked Example:
Example:
    height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
    At index 5 (height 0): leftMax = 2, rightMax = 3, so it holds 2 units.
    Summing over all indices gives 6.

Time Complexity: O(n^2)
Space Complexity: O(1)
*/

public class P30_TrappingRainWaterBruteForce {
    public static int trap(int[] height) {
        int total = 0;

        for (int i = 0; i < height.length; i++) {
            int leftMax = 0;
            for (int left = 0; left <= i; left++) {
                leftMax = Math.max(leftMax, height[left]);
            }

            int rightMax = 0;
            for (int right = i; right < height.length; right++) {
                rightMax = Math.max(rightMax, height[right]);
            }

            total += Math.min(leftMax, rightMax) - height[i];
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(trap(new int[]{4, 2, 0, 3, 2, 5}));
        System.out.println(trap(new int[]{1, 2, 3}));
    }
}
