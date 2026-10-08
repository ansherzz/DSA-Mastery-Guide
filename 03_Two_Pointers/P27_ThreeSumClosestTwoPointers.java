import java.util.*;

/*
Problem: 3Sum Closest
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
Given an integer array of length at least 3 and a target, find three integers whose sum is closest to the target.
    Return that sum. You may assume exactly one closest answer exists.

Why this pattern fits:
This is 3Sum with a different goal: instead of hitting zero exactly, you track the nearest sum.
    Sorting plus two pointers still works because the sum moves predictably as the pointers move.

Approach:
Sort the array. Fix each index i as the first value.
    Use two pointers on the remainder. Update the best sum whenever the current sum is closer to the target.
    Move left when the sum is too small and right when it is too large. Return immediately on an exact match.

Worked Example:
Example:
    nums = [-1, 2, 1, -4], target = 1
    Sorted: [-4, -1, 1, 2]
    The triple (-1, 1, 2) sums to 2, which is distance 1 from the target and the closest possible.

Time Complexity: O(n^2)
Space Complexity: O(1) extra, ignoring the sort
*/

public class P27_ThreeSumClosestTwoPointers {
    public static int threeSumClosest(int[] nums, int target) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int best = sorted[0] + sorted[1] + sorted[2];

        for (int i = 0; i < sorted.length - 2; i++) {
            int left = i + 1;
            int right = sorted.length - 1;

            while (left < right) {
                int sum = sorted[i] + sorted[left] + sorted[right];
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }

                if (sum == target) {
                    return sum;
                }
                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return best;
    }

    private static void runDemo(int[] nums, int target) {
        System.out.println("Array  : " + Arrays.toString(nums));
        System.out.println("Target : " + target);
        System.out.println("Closest: " + threeSumClosest(nums, target));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{-1, 2, 1, -4}, 1);
        runDemo(new int[]{0, 0, 0}, 1);
        runDemo(new int[]{1, 1, 1, 0}, -100);
    }
}
