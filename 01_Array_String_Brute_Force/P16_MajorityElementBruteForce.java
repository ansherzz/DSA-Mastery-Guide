import java.util.*;

/*
Problem: Majority Element with Brute Force
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given an array of size n, return the majority element: the value that appears more than n / 2 times.
    If no value appears that often, return -1.

    The hashing version of this problem lives in folder 02. Here the goal is the plain baseline.

Why this pattern fits:
"Appears more than n / 2 times" can be verified for a single value with one scan.
    Doing that verification for each element is the simplest correct solution.

Approach:
For each element, count how many times it appears in the entire array.
    Return the first element whose count is greater than n / 2.

Worked Example:
Example:
    nums = [2, 2, 1, 1, 1, 2, 2]
    The value 2 appears 4 times and 4 > 7 / 2, so the answer is 2.

Time Complexity: O(n^2)
Space Complexity: O(1)
*/

public class P16_MajorityElementBruteForce {
    public static int majorityElement(int[] nums) {
        int n = nums.length;

        for (int candidate : nums) {
            int count = 0;
            for (int value : nums) {
                if (value == candidate) {
                    count++;
                }
            }
            if (count > n / 2) {
                return candidate;
            }
        }
        return -1;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Array   : " + Arrays.toString(nums));
        System.out.println("Majority: " + majorityElement(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{3, 2, 3});
        runDemo(new int[]{2, 2, 1, 1, 1, 2, 2});
        runDemo(new int[]{1, 2, 3, 4});
    }
}
