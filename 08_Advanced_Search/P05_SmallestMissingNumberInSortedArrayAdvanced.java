import java.util.*;

/*
Problem: Smallest Missing Number in a Sorted Array
Pattern: 08. Advanced Search
Difficulty: Easy

Interview Prompt:
Given a sorted array of distinct non-negative integers, return the smallest non-negative integer that is missing.

Why this pattern fits:
While nothing is missing, nums[i] == i. After the first gap, nums[i] > i forever.
The comparison of a value with its own index is the monotonic predicate.

Approach:
Binary search for the first index i with nums[i] != i. That index is the answer (n if every index matches).

Worked Example:
nums = [0, 1, 2, 6, 9] -> index 3 holds 6, so 3 is missing.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P05_SmallestMissingNumberInSortedArrayAdvanced {
    public static int smallestMissing(int[] nums) {
        int low = 0;
        int high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == mid) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("[0, 1, 2, 6, 9] -> " + smallestMissing(new int[]{0, 1, 2, 6, 9}));
        System.out.println("[4, 5, 10] -> " + smallestMissing(new int[]{4, 5, 10}));
        System.out.println("[0, 1, 2, 3] -> " + smallestMissing(new int[]{0, 1, 2, 3}));
    }
}
