import java.util.*;

/*
Problem: Count Subarrays Where Max Element Appears at Least K Times
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Return the number of subarrays in which the maximum element of the whole array appears at least k times.

Why this pattern fits:
For "at least k" the valid starts for a fixed right end are 0..L for some boundary L that only moves right.

Approach:
Find the global maximum. Extend right, counting occurrences of it.
While the window holds k occurrences, move left forward (dropping occurrences as they leave).
After the loop, `left` is the number of valid starts for this right end, so add it.

Worked Example:
nums = [1, 3, 2, 3, 3], k = 2 -> six subarrays contain at least two 3s.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P36_CountSubarraysWhereMaxElementAppearsAtLeastKTimesSlidingWindow {
    public static long countSubarrays(int[] nums, int k) {
        int max = Arrays.stream(nums).max().getAsInt();
        long total = 0;
        int occurrences = 0;
        int left = 0;
        for (int value : nums) {
            if (value == max) {
                occurrences++;
            }
            while (occurrences >= k) {
                if (nums[left++] == max) {
                    occurrences--;
                }
            }
            total += left;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("[1, 3, 2, 3, 3], k = 2 -> " + countSubarrays(new int[]{1, 3, 2, 3, 3}, 2));
        System.out.println("[1, 4, 2, 1], k = 3 -> " + countSubarrays(new int[]{1, 4, 2, 1}, 3));
    }
}
