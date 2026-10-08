import java.util.*;

/*
Problem: Find All Numbers Disappeared in an Array
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given an array of n integers where every value is in the range [1, n],
return all the numbers in [1, n] that do not appear in the array.
Try to do it without extra space (the output list does not count).

Why this pattern fits:
Brute force checks every number from 1 to n against the whole array, O(n^2).
Because values are in [1, n], each value can point at an index, so the array can record its own "seen" marks.

Approach:
For every value v, make nums[|v| - 1] negative.
Afterwards, any index i whose value is still positive was never pointed at, so i + 1 is missing.

Worked Example:
nums = [4, 3, 2, 7, 8, 2, 3, 1]
Indices 4 and 5 are never marked, so the missing numbers are 5 and 6.

Time Complexity: O(n)
Space Complexity: O(1) extra
*/

public class P18_FindAllNumbersDisappearedArrayBasics {
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }
        List<Integer> missing = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                missing.add(i + 1);
            }
        }
        return missing;
    }

    public static void main(String[] args) {
        System.out.println("[4, 3, 2, 7, 8, 2, 3, 1] -> " + findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
        System.out.println("[1, 1] -> " + findDisappearedNumbers(new int[]{1, 1}));
    }
}
