import java.util.*;

/*
Problem: Find All Duplicates in an Array
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
Given an array of n integers where each value is in [1, n] and appears once or twice,
return all values that appear twice. Use O(n) time and only constant extra space.

Why this pattern fits:
A hash set solves it with O(n) memory. Values in [1, n] can be used as indices,
so the sign bit of the array itself can store the "seen" flag.

Approach:
For each value v = |nums[i]|, look at index v - 1.
If the value there is already negative, v was seen before, so record it. Otherwise negate it.

Worked Example:
nums = [4, 3, 2, 7, 8, 2, 3, 1]
The second 2 finds index 1 already negative and the second 3 finds index 2 negative. Answer = [2, 3].

Time Complexity: O(n)
Space Complexity: O(1) extra
*/

public class P38_FindAllDuplicatesInAnArrayArrayBasics {
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> duplicates = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                duplicates.add(index + 1);
            } else {
                nums[index] = -nums[index];
            }
        }
        return duplicates;
    }

    public static void main(String[] args) {
        System.out.println("[4, 3, 2, 7, 8, 2, 3, 1] -> " + findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
        System.out.println("[1, 1, 2] -> " + findDuplicates(new int[]{1, 1, 2}));
        System.out.println("[1] -> " + findDuplicates(new int[]{1}));
    }
}
