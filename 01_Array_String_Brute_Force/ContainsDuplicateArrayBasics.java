import java.util.*;

/*
Problem: Contains Duplicate
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given an integer array, return true if any value appears at least twice. Return false if all values are distinct.

    This file shows the basic brute-force baseline before the hashing upgrade.

Why this pattern fits:
This is a small but important pattern bridge. The O(n^2) solution is completely correct and easy to reason about,
    which makes it a great setup for discussing how a HashSet reduces the work.

Approach:
Compare every element with every later element.
    If a match is found, return true immediately.
    Otherwise return false after all comparisons.

Worked Example:
Example:
    nums = [1, 2, 3, 1]
    The answer is true because the value 1 appears more than once.

Time Complexity: O(n^2)
Space Complexity: O(1)
*/

public class ContainsDuplicateArrayBasics {
    public static boolean containsDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Array   : " + Arrays.toString(nums));
        System.out.println("Duplicate present? " + containsDuplicate(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 3, 1});
        runDemo(new int[]{1, 2, 3, 4});
    }
}
