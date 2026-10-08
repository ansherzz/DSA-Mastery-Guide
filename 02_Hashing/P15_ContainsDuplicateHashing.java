import java.util.*;

/*
Problem: Contains Duplicate
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Given an integer array nums, return true if any value appears at least twice in the array,
    and return false if every element is distinct.

Why this pattern fits:
The question is "have I seen this value before?", asked once per element.
    A HashSet answers that membership question in O(1) average time.

Approach:
Scan the array and try to add each value to a HashSet.
    HashSet.add returns false when the value is already present, which means a duplicate was found.
    If the scan finishes, all values were distinct.

Worked Example:
Example:
    nums = [1, 2, 3, 1]
    Seen set grows to {1, 2, 3}; the final 1 is already in the set, so the answer is true.

Time Complexity: O(n) average
Space Complexity: O(n)
*/

public class P15_ContainsDuplicateHashing {
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(containsDuplicate(new int[]{1, 2, 3, 1}));
        System.out.println(containsDuplicate(new int[]{1, 2, 3, 4}));
        System.out.println(containsDuplicate(new int[]{}));
    }
}
