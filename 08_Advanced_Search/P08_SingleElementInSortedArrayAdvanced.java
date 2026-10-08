import java.util.*;

/*
Problem: Single Element in a Sorted Array
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
In a sorted array, every element appears exactly twice except one element that appears once.
    Return that single element.

    Binary search works because pairs occupy predictable even/odd positions before and after the single element.

Why this pattern fits:
Before the single element, pairs start at even indices.
    After the single element, the pairing pattern shifts.
    That index pattern is a hidden monotonic structure.

Approach:
Force mid to be even.
    If nums[mid] == nums[mid + 1], the single element is to the right.
    Otherwise it is at mid or to the left.

Worked Example:
Example:
    nums = [1, 1, 2, 3, 3, 4, 4, 8, 8]
    The answer is 2.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P08_SingleElementInSortedArrayAdvanced {
    public static int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid % 2 == 1) {
                mid--;
            }

            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums   : " + Arrays.toString(nums));
        System.out.println("Single : " + singleNonDuplicate(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8});
        runDemo(new int[]{3, 3, 7, 7, 10, 11, 11});
    }
}
