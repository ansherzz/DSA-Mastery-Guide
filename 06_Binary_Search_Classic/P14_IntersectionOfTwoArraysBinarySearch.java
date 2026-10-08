import java.util.*;

/*
Problem: Intersection of Two Arrays
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Given two integer arrays nums1 and nums2, return an array of their intersection.
    Each element in the result must be unique. Here the result is returned in increasing order.

Why this pattern fits:
The task is a series of membership tests. When hashing is not allowed or memory is tight,
    sorting one array and binary searching it gives O(log n) membership with no extra structure.

Approach:
Sort both arrays. Walk through nums1, skipping values equal to the previous one so each value is tested once.
    Binary search each distinct value in nums2 and add it to the result when found.
    Sorting nums1 is what makes the duplicate skipping a single comparison.

Worked Example:
Example:
    nums1 = [4, 9, 5], nums2 = [9, 4, 9, 8, 4]
    Sorted: [4, 5, 9] and [4, 4, 8, 9, 9]. 4 is found, 5 is not, 9 is found. Result: [4, 9].

Time Complexity: O(n log n + m log m)
Space Complexity: O(1) besides the sorts and the result
*/

public class P14_IntersectionOfTwoArraysBinarySearch {
    public static List<Integer> intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums1.length; i++) {
            if (i > 0 && nums1[i] == nums1[i - 1]) {
                continue;
            }
            if (contains(nums2, nums1[i])) {
                result.add(nums1[i]);
            }
        }
        return result;
    }

    private static boolean contains(int[] sorted, int target) {
        int left = 0;
        int right = sorted.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sorted[mid] == target) {
                return true;
            }
            if (sorted[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2}));
        System.out.println(intersection(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}));
    }
}
