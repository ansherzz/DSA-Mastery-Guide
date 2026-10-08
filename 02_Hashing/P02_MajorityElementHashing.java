import java.util.*;

/*
Problem: Majority Element
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Given an integer array of length n, return the element that appears more than floor(n / 2) times.
    You may assume such an element always exists.

    This version uses a hashmap frequency count, which is often the first clean interview answer.

Why this pattern fits:
The majority element is a frequency question. A hashmap gives a direct and readable solution,
    even though there is also a Boyer-Moore optimization.

Approach:
Count each number as you scan the array.
    As soon as a count becomes greater than n / 2, return that number.

Worked Example:
Example:
    nums = [2, 2, 1, 1, 1, 2, 2]
    The answer is 2 because it appears 4 times, which is more than 7 / 2.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P02_MajorityElementHashing {
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> frequency = new HashMap<>();
        int threshold = nums.length / 2;
        for (int num : nums) {
            int updated = frequency.getOrDefault(num, 0) + 1;
            frequency.put(num, updated);
            if (updated > threshold) {
                return num;
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
    }
}
