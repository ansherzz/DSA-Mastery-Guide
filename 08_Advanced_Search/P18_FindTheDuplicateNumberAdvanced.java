import java.util.*;

/*
Problem: Find the Duplicate Number
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
Given an array nums containing n + 1 integers where each integer is in the range [1, n] inclusive,
    there is exactly one repeated number, which may appear more than twice. Return it.
    You must not modify the array and must use only constant extra space.

Why this pattern fits:
The array is not sorted and may not be sorted, so there is nothing to search by index.
    Instead, binary search the value range [1, n]: counting how many elements are <= x gives a monotonic signal.

Approach:
For a value x, count the elements that are <= x.
    Without a duplicate at or below x, that count is at most x. If the duplicate is <= x, the count exceeds x (pigeonhole).
    So "count(x) > x" is false below the duplicate and true from the duplicate onward.
    Binary search for the first x where it becomes true.

Worked Example:
Example:
    nums = [1, 3, 4, 2, 2]
    count(<= 1) = 1, count(<= 2) = 3 > 2, so the duplicate is 2.

Time Complexity: O(n log n)
Space Complexity: O(1)
*/

public class P18_FindTheDuplicateNumberAdvanced {
    public static int findDuplicate(int[] nums) {
        int low = 1;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }

            if (count > mid) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println(findDuplicate(new int[]{1, 3, 4, 2, 2}));
        System.out.println(findDuplicate(new int[]{3, 1, 3, 4, 2}));
        System.out.println(findDuplicate(new int[]{3, 3, 3, 3, 3}));
    }
}
