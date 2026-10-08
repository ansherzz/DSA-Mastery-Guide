import java.util.*;

/*
Problem: Contains Duplicate II
Pattern: 04. Sliding Window
Difficulty: Easy

Interview Prompt:
Given an integer array nums and an integer k, return true if there are two distinct indices i and j
    such that nums[i] == nums[j] and abs(i - j) <= k.

Why this pattern fits:
The distance limit means only the previous k elements matter for each index.
    That is a fixed-size window, and a HashSet can describe its contents.

Approach:
Keep a set holding the values of the last k elements.
    For each new element, if it is already in the set, a duplicate within distance k exists.
    Otherwise add it, and if the set now holds more than k values, remove the element that fell out of the window.

Worked Example:
Example:
    nums = [1, 2, 3, 1], k = 3
    When the second 1 arrives, the window holds {1, 2, 3}, so the answer is true.

Time Complexity: O(n) average
Space Complexity: O(min(n, k))
*/

public class P03_ContainsDuplicateIISlidingWindow {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> window = new HashSet<>();

        for (int right = 0; right < nums.length; right++) {
            if (!window.add(nums[right])) {
                return true;
            }
            if (window.size() > k) {
                window.remove(nums[right - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
        System.out.println(containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1));
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2));
    }
}
