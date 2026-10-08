import java.util.*;

/*
Problem: Maximum in a Bitonic Array
Pattern: 08. Advanced Search
Difficulty: Easy

Interview Prompt:
An array strictly increases and then strictly decreases (either part may be empty).
Return its maximum element.

Why this pattern fits:
The array is unimodal. The sign of nums[mid + 1] - nums[mid] is positive before the peak and negative after it,
which is a monotonic predicate even though the values themselves are not sorted.

Approach:
While low < high: if nums[mid] < nums[mid + 1] the peak is to the right, otherwise it is at mid or to the left.

Worked Example:
nums = [1, 3, 8, 12, 4, 2] -> the slope turns negative after 12. Answer = 12.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class P03_MaximumInBitonicArrayAdvanced {
    public static int findMaximum(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < nums[mid + 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }

    public static void main(String[] args) {
        System.out.println("[1, 3, 8, 12, 4, 2] -> " + findMaximum(new int[]{1, 3, 8, 12, 4, 2}));
        System.out.println("[10, 20, 30] -> " + findMaximum(new int[]{10, 20, 30}));
        System.out.println("[5, 4, 3] -> " + findMaximum(new int[]{5, 4, 3}));
    }
}
