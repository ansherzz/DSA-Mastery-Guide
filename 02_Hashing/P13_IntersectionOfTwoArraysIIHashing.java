import java.util.*;

/*
Problem: Intersection of Two Arrays II
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Given two integer arrays, return their intersection where each value appears
as many times as it shows in both arrays. Order does not matter.

Why this pattern fits:
Unlike the set version, multiplicity matters, so a frequency map replaces the set.

Approach:
Count the values of nums1. For each value in nums2 with a positive remaining count,
add it to the result and decrement the count.

Worked Example:
nums1 = [1, 2, 2, 1], nums2 = [2, 2]
Both 2s in nums2 find a remaining count in the map. Answer = [2, 2].

Time Complexity: O(m + n)
Space Complexity: O(min(m, n)) if the smaller array is counted
*/

public class P13_IntersectionOfTwoArraysIIHashing {
    public static List<Integer> intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int value : nums1) {
            count.merge(value, 1, Integer::sum);
        }
        List<Integer> result = new ArrayList<>();
        for (int value : nums2) {
            int remaining = count.getOrDefault(value, 0);
            if (remaining > 0) {
                result.add(value);
                count.put(value, remaining - 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 2, 1] & [2, 2] -> " + intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2}));
        System.out.println("[4, 9, 5] & [9, 4, 9, 8, 4] -> " + intersect(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}));
    }
}
