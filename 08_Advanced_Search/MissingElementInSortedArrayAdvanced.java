import java.util.*;

/*
Problem: Missing Element in Sorted Array
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
Given an integer array nums sorted in strictly ascending order and an integer k,
    return the kth missing number counting from the leftmost number of the array.

    Follow-up: find a logarithmic time solution.

Why this pattern fits:
The array stores what is present, but the question is about what is absent.
    The trick is to derive a hidden monotonic quantity: the number of values missing before index i is
    nums[i] - nums[0] - i. Binary search on that derived quantity finds where the kth gap falls.

Approach:
Define missing(i) = nums[i] - nums[0] - i.
    Find the first index where missing(i) >= k. The answer lies between the previous element and that one.
    If no such index exists, the answer is beyond the last element.
    In both cases the answer is nums[idx - 1] + (k - missing(idx - 1)).

Worked Example:
Example:
    nums = [4, 7, 9, 10], k = 3
    missing counts: 0, 2, 3, 3. The first index with count >= 3 is 2.
    Answer = nums[1] + (3 - 2) = 7 + 1 = 8. (The missing numbers are 5, 6, 8.)

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class MissingElementInSortedArrayAdvanced {
    private static int missingBefore(int[] nums, int index) {
        return nums[index] - nums[0] - index;
    }

    public static int missingElement(int[] nums, int k) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (missingBefore(nums, mid) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        int previous = left - 1;
        return nums[previous] + (k - missingBefore(nums, previous));
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Nums   : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("Answer : " + missingElement(nums, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{4, 7, 9, 10}, 1);
        runDemo(new int[]{4, 7, 9, 10}, 3);
        runDemo(new int[]{1, 2, 4}, 3);
    }
}
