import java.util.*;

/*
Problem: Contains Duplicate II
Pattern: 02. Hashing
Difficulty: Easy

Interview Prompt:
Given an integer array nums and an integer k, return true if there are two distinct indices i and j
    such that nums[i] == nums[j] and the distance |i - j| is at most k.

Why this pattern fits:
You need to remember where each value was last seen so the current index can be compared against it.
    "Value -> most recent index" is a textbook hash map lookup.

Approach:
Scan the array once. For each value, check whether it has a stored previous index.
    If it does and the gap to the current index is at most k, return true.
    Always overwrite the stored index with the current one, because the most recent index gives the smallest gap.

Worked Example:
Example:
    nums = [1, 2, 3, 1], k = 3
    The two 1s are at indices 0 and 3, and 3 - 0 = 3 <= k, so the answer is true.

Time Complexity: O(n) average
Space Complexity: O(n)
*/

public class P04_ContainsDuplicateIIHashing {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> lastIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer previous = lastIndex.put(nums[i], i);
            if (previous != null && i - previous <= k) {
                return true;
            }
        }
        return false;
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Array : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("Nearby duplicate? " + containsNearbyDuplicate(nums, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 3, 1}, 3);
        runDemo(new int[]{1, 0, 1, 1}, 1);
        runDemo(new int[]{1, 2, 3, 1, 2, 3}, 2);
    }
}
