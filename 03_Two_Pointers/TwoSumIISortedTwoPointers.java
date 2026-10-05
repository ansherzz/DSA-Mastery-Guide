import java.util.*;

/*
Problem: Two Sum II - Input Array Is Sorted
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
Given an integer array sorted in non-decreasing order and a target, find two numbers that add up to the target.
    Return their positions as 1-based indices [index1, index2] with index1 < index2.
    Exactly one solution exists, and you must use only constant extra space.

Why this pattern fits:
Sorted order tells you which direction to move.
    If the current sum is too small, only moving the left pointer right can increase it.
    If it is too large, only moving the right pointer left can decrease it.

Approach:
Place one pointer at the start and one at the end.
    Compare the sum of the two values with the target and move the pointer that can fix the difference.
    Each step discards one value that cannot be part of the answer.

Worked Example:
Example:
    numbers = [2, 7, 11, 15], target = 9
    2 + 15 = 17 is too large, move right. 2 + 11 = 13 is too large, move right.
    2 + 7 = 9 matches, so the answer is [1, 2].

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class TwoSumIISortedTwoPointers {
    public static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }

    private static void runDemo(int[] numbers, int target) {
        System.out.println("Numbers: " + Arrays.toString(numbers));
        System.out.println("Target : " + target);
        System.out.println("Answer : " + Arrays.toString(twoSum(numbers, target)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{2, 7, 11, 15}, 9);
        runDemo(new int[]{2, 3, 4}, 6);
        runDemo(new int[]{-1, 0}, -1);
    }
}
