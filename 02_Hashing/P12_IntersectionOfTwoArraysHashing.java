import java.util.*;

/*
Problem: Intersection of Two Arrays II
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Given two integer arrays, return their intersection.
    Each element in the result must appear as many times as it shows up in both arrays.
    The result may be returned in any order.

Why this pattern fits:
Duplicates matter, so a set is not enough. You need to know how many copies of each value are still available.
    A frequency map of one array gives exactly that.

Approach:
Count the values of the smaller array in a map.
    Scan the other array. Whenever a value still has a positive count, add it to the result and decrement the count.

Worked Example:
Example:
    nums1 = [1, 2, 2, 1], nums2 = [2, 2]
    Counts from nums2: {2: 2}. Scanning nums1 uses both copies of 2.
    Answer = [2, 2].

Time Complexity: O(n + m) average
Space Complexity: O(min(n, m))
*/

public class P12_IntersectionOfTwoArraysHashing {
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        Map<Integer, Integer> available = new HashMap<>();
        for (int value : nums1) {
            available.merge(value, 1, Integer::sum);
        }

        List<Integer> common = new ArrayList<>();
        for (int value : nums2) {
            int count = available.getOrDefault(value, 0);
            if (count > 0) {
                common.add(value);
                available.put(value, count - 1);
            }
        }

        int[] result = new int[common.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = common.get(i);
        }
        return result;
    }

    private static void runDemo(int[] nums1, int[] nums2) {
        System.out.println("nums1 : " + Arrays.toString(nums1));
        System.out.println("nums2 : " + Arrays.toString(nums2));
        System.out.println("Common: " + Arrays.toString(intersect(nums1, nums2)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 2, 1}, new int[]{2, 2});
        runDemo(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4});
    }
}
