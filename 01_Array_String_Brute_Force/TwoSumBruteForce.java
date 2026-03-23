import java.util.*;

/*
Problem: Two Sum with Brute Force
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given an integer array and a target value, return the indices of two different elements whose sum equals the target.
    Assume at most one valid answer exists. If no such pair exists, return {-1, -1}.

    The goal here is not the optimal method. The goal is to practice the most direct baseline:
    try every pair and stop when the target is found.

Why this pattern fits:
This is the simplest example of brute-force pair checking. You choose the first index,
    then check every index after it. It is also the perfect warm-up before learning the hashing upgrade.

Approach:
Use two nested loops. For every i, test every j > i.
    If nums[i] + nums[j] == target, return the pair of indices immediately.

Worked Example:
Example:
    nums = [2, 7, 11, 15], target = 9
    One valid answer is indices [0, 1] because 2 + 7 = 9.

Time Complexity: O(n^2)
Space Complexity: O(1)
*/

public class TwoSumBruteForce {
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private static void runDemo(int[] nums, int target) {
        int[] answer = twoSum(nums, target);
        System.out.println("Array  : " + Arrays.toString(nums));
        System.out.println("Target : " + target);
        System.out.println("Answer : " + Arrays.toString(answer));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{2, 7, 11, 15}, 9);
        runDemo(new int[]{3, 2, 4}, 6);
    }
}
