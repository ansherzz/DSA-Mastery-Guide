import java.util.*;

/*
Problem: Intersection of Two Arrays
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given two integer arrays nums1 and nums2, return an array of their intersection.
    Each element in the result must be unique, and the result may be returned in any order.

Why this pattern fits:
The definition is a direct double condition: a value belongs in the answer if it appears in both arrays
    and has not been reported yet. Nested loops express that literally.

Approach:
For each value in nums1, scan nums2 to see whether it appears there.
    Before adding it to the result, scan the result so far to avoid reporting the same value twice.
    Upgrade path: put one array in a HashSet so each membership test becomes O(1).

Worked Example:
Example:
    nums1 = [4, 9, 5], nums2 = [9, 4, 9, 8, 4]
    4 and 9 appear in both arrays; 5 does not. Result: [4, 9].

Time Complexity: O(n * m)
Space Complexity: O(min(n, m)) for the result
*/

public class IntersectionOfTwoArraysBruteForce {
    public static List<Integer> intersection(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList<>();

        for (int candidate : nums1) {
            if (contains(nums2, candidate) && !result.contains(candidate)) {
                result.add(candidate);
            }
        }
        return result;
    }

    private static boolean contains(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2}));
        System.out.println(intersection(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}));
        System.out.println(intersection(new int[]{1, 2}, new int[]{3}));
    }
}
