import java.util.*;

/*
Problem: Largest Perimeter Triangle
Pattern: 19. Heap & Greedy
Difficulty: Easy

Interview Prompt:
Given side lengths, return the largest perimeter of a triangle with non-zero area formed from three of them,
or 0 if no such triangle exists.

Why this pattern fits:
For a fixed longest side c, the best chance of satisfying a + b > c is to take the two largest remaining sides.
So after sorting, only adjacent triples need to be checked, and the first valid one from the top is the largest.

Approach:
Sort ascending. For i from n - 1 down to 2: if nums[i - 2] + nums[i - 1] > nums[i], return their sum.

Worked Example:
nums = [1, 2, 1, 10] -> 10 fails (2 + 1 <= 10) and 2 fails (1 + 1 <= 2). Answer = 0.

Time Complexity: O(n log n)
Space Complexity: O(1) extra
*/

public class P05_LargestPerimeterTriangleHeapGreedy {
    public static int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i - 2] + nums[i - 1] + nums[i];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("[2, 1, 2] -> " + largestPerimeter(new int[]{2, 1, 2}));
        System.out.println("[1, 2, 1, 10] -> " + largestPerimeter(new int[]{1, 2, 1, 10}));
    }
}
