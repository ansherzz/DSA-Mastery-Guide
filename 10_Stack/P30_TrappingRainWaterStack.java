import java.util.*;

/*
Problem: Trapping Rain Water
Pattern: 10. Stack
Difficulty: Hard

Interview Prompt:
Given n non-negative integers representing an elevation map where the width of each bar is 1,
    compute how much water it can trap after raining.

Why this pattern fits:
Water collects in a basin: a lower bar with a taller bar on each side.
    A decreasing stack holds bars that are still waiting for a right wall.
    When a taller bar arrives, it closes basins one layer at a time, which adds the water horizontally, layer by layer.

Approach:
Keep a stack of indices with decreasing heights.
    When the current bar is taller than the top, pop the top as the basin floor.
    If the stack is now empty there is no left wall, so no water is held.
    Otherwise the new top is the left wall: the layer's width is the gap between the walls,
    and its height is min(left wall, current bar) - floor.
    Add width * height, and repeat while the current bar is still taller than the top. Then push the current index.

Worked Example:
Example:
    height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
    The basins between the bars hold 1 + 1 + 3 + 1 = 6 units in total.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P30_TrappingRainWaterStack {
    public static int trap(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int water = 0;

        for (int right = 0; right < height.length; right++) {
            while (!stack.isEmpty() && height[right] > height[stack.peek()]) {
                int floor = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }

                int left = stack.peek();
                int width = right - left - 1;
                int depth = Math.min(height[left], height[right]) - height[floor];
                water += width * depth;
            }
            stack.push(right);
        }
        return water;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(trap(new int[]{4, 2, 0, 3, 2, 5}));
        System.out.println(trap(new int[]{5, 4, 3}));
    }
}
