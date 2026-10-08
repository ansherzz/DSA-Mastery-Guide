import java.util.*;

/*
Problem: Exponential Search
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
Given a sorted array and a target, return the index of the target or -1 if it is absent.
    The target is expected to be near the front of a very large array,
    so the running time should depend on the target's position p rather than on the array length n.

Why this pattern fits:
Plain binary search always costs O(log n), even when the target sits at index 3 of a billion elements.
    Exponential (galloping) search first discovers a small range that must contain the target,
    then runs binary search only inside that range.

Approach:
Check index 0. Then double a bound (1, 2, 4, 8, ...) while the bound is inside the array
    and the value at the bound is smaller than the target.
    When the doubling stops, the target can only be in (bound / 2, bound].
    Binary search that range. The bound overshoots the target position by at most a factor of 2,
    so both phases take O(log p) steps.

Worked Example:
Example:
    nums = [2, 3, 5, 8, 13, 21, 34, 55, 89, 144], target = 21
    Bounds visited: 1 (3), 2 (5), 4 (13), 8 (89). 89 >= 21, so search indices 4..8 and find 21 at index 5.

Time Complexity: O(log p), where p is the position of the target
Space Complexity: O(1)
*/

public class P28_ExponentialSearchAdvanced {
    public static int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums[0] == target) {
            return 0;
        }

        int bound = 1;
        while (bound < nums.length && nums[bound] < target) {
            bound *= 2;
        }

        int left = bound / 2;
        int right = Math.min(bound, nums.length - 1);
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 5, 8, 13, 21, 34, 55, 89, 144};
        System.out.println(search(nums, 21));
        System.out.println(search(nums, 2));
        System.out.println(search(nums, 144));
        System.out.println(search(nums, 4));
        System.out.println(search(nums, 200));
    }
}
