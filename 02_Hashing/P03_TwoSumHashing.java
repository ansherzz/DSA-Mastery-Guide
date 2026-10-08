import java.util.*;

/*
Problem: Two Sum with Hashing
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Given an integer array and a target value, return the indices of two different elements whose sum equals the target.
    Assume at most one valid answer exists. If no such pair exists, return {-1, -1}.

    This is the optimized follow-up to the brute-force version in folder 01.

Why this pattern fits:
For each value x, the only useful partner is target - x.
    A hash map answers "have I already seen that partner, and where?" in O(1) average time.

Approach:
Scan left to right. For each value, look up its complement in the map.
    If the complement is present, return the stored index and the current index.
    Otherwise store the current value with its index and continue.

Worked Example:
Example:
    nums = [2, 7, 11, 15], target = 9
    At 2, store 2 -> 0. At 7, the complement 2 is already stored, so the answer is [0, 1].

Time Complexity: O(n) average
Space Complexity: O(n)
*/

public class P03_TwoSumHashing {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexByValue = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer partnerIndex = indexByValue.get(target - nums[i]);
            if (partnerIndex != null) {
                return new int[]{partnerIndex, i};
            }
            indexByValue.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    private static void runDemo(int[] nums, int target) {
        System.out.println("Array  : " + Arrays.toString(nums));
        System.out.println("Target : " + target);
        System.out.println("Answer : " + Arrays.toString(twoSum(nums, target)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{2, 7, 11, 15}, 9);
        runDemo(new int[]{3, 2, 4}, 6);
        runDemo(new int[]{3, 3}, 6);
    }
}
