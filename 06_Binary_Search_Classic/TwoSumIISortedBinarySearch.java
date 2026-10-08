import java.util.*;

/*
Problem: Two Sum II - Input Array Is Sorted
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
Given a 1-indexed array of integers numbers that is sorted in non-decreasing order,
    find two numbers that add up to a specific target.
    Return their indices as [index1, index2] with index1 < index2. Exactly one solution exists.

Why this pattern fits:
For each first number, the second number is fully determined: target - numbers[i].
    Because the array is sorted, that complement can be located by binary search in the part to the right of i.

Approach:
For each index i, binary search the range (i, n - 1] for target - numbers[i].
    Searching only to the right guarantees index1 < index2 and never reuses the same element.
    Trade-off: the two-pointer solution is O(n), while this one is O(n log n) but shows how a sorted order
    turns a lookup into a search.

Worked Example:
Example:
    numbers = [2, 7, 11, 15], target = 9
    For i = 0, the complement is 7, found at index 1. Answer (1-indexed): [1, 2].

Time Complexity: O(n log n)
Space Complexity: O(1)
*/

public class TwoSumIISortedBinarySearch {
    public static int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {
            int complement = target - numbers[i];
            int left = i + 1;
            int right = numbers.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (numbers[mid] == complement) {
                    return new int[]{i + 1, mid + 1};
                }
                if (numbers[mid] < complement) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSum(new int[]{2, 3, 4}, 6)));
        System.out.println(Arrays.toString(twoSum(new int[]{-1, 0}, -1)));
    }
}
