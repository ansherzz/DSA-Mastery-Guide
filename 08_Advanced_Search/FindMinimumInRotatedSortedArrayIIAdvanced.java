import java.util.*;

/*
Problem: Find Minimum in Rotated Sorted Array II
Pattern: 08. Advanced Search
Difficulty: Hard

Interview Prompt:
An array sorted in ascending order was rotated some number of times. The array may contain duplicates.
    Return the minimum element of the array. Reduce the number of operations as much as possible.

Why this pattern fits:
Without duplicates, comparing nums[mid] with nums[right] always tells you which half holds the minimum.
    Duplicates break that when the two values are equal, so the search needs a safe fallback
    that still makes progress without discarding the answer.

Approach:
Compare nums[mid] with nums[right].
    If nums[mid] > nums[right], the minimum is strictly to the right of mid.
    If nums[mid] < nums[right], the minimum is at mid or to its left.
    If they are equal, you cannot tell, but nums[right] has a duplicate at mid, so dropping right is safe.

Worked Example:
Example:
    nums = [2, 2, 2, 0, 1]
    mid = 2: nums[2] = 2 > nums[4] = 1, so search [3, 4].
    mid = 3: nums[3] = 0 < nums[4] = 1, so search [3, 3]. Answer = 0.

Time Complexity: O(log n) on average, O(n) in the worst case with many duplicates
Space Complexity: O(1)
*/

public class FindMinimumInRotatedSortedArrayIIAdvanced {
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return nums[left];
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums   : " + Arrays.toString(nums));
        System.out.println("Min    : " + findMin(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 3, 5});
        runDemo(new int[]{2, 2, 2, 0, 1});
        runDemo(new int[]{3, 3, 1, 3});
    }
}
