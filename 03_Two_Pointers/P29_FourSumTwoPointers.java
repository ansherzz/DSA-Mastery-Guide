import java.util.*;

/*
Problem: 4Sum
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
Given an integer array and a target, return all unique quadruplets [a, b, c, d] from the array
    such that a + b + c + d == target. The result must not contain duplicate quadruplets.

Why this pattern fits:
4Sum reduces to 3Sum, which reduces to a sorted two-sum.
    Two outer loops fix the first two values, and two pointers solve the remaining pair in linear time.

Approach:
Sort the array. Fix index i and index j > i, skipping repeated values at each level.
    Run two pointers on the range after j, looking for the remaining sum.
    After recording a match, skip duplicates on both pointers.
    Use long arithmetic so large values do not overflow.

Worked Example:
Example:
    nums = [1, 0, -1, 0, -2, 2], target = 0
    Sorted: [-2, -1, 0, 0, 1, 2]
    Answer = [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]

Time Complexity: O(n^3)
Space Complexity: O(1) extra, ignoring the sort and the output
*/

public class P29_FourSumTwoPointers {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int n = sorted.length;

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && sorted[i] == sorted[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && sorted[j] == sorted[j - 1]) {
                    continue;
                }

                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    long sum = (long) sorted[i] + sorted[j] + sorted[left] + sorted[right];
                    if (sum == target) {
                        result.add(Arrays.asList(sorted[i], sorted[j], sorted[left], sorted[right]));
                        left++;
                        right--;
                        while (left < right && sorted[left] == sorted[left - 1]) {
                            left++;
                        }
                        while (left < right && sorted[right] == sorted[right + 1]) {
                            right--;
                        }
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    private static void runDemo(int[] nums, int target) {
        System.out.println("Array      : " + Arrays.toString(nums));
        System.out.println("Target     : " + target);
        System.out.println("Quadruplets: " + fourSum(nums, target));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 0, -1, 0, -2, 2}, 0);
        runDemo(new int[]{2, 2, 2, 2, 2}, 8);
        runDemo(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296);
    }
}
