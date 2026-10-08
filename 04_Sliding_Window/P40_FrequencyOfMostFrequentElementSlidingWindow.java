import java.util.*;

/*
Problem: Frequency of the Most Frequent Element
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
The frequency of an element is the number of times it occurs in an array.
    You are given an integer array nums and an integer k.
    In one operation, you can choose an index and increment its value by 1.
    Return the maximum possible frequency of an element after performing at most k operations.

Why this pattern fits:
Values can only go up, so the cheapest way to create copies of a value v is to raise the values just below it.
    After sorting, those values form a contiguous window ending at v.

Approach:
Sort the array. For a window [left, right], making every element equal to nums[right] costs
    nums[right] * windowLength - windowSum.
    Expand right, and shrink from the left while that cost exceeds k.
    The largest valid window length is the answer. Use long arithmetic to avoid overflow.

Worked Example:
Example:
    nums = [1, 2, 4], k = 5
    Raise 1 by three and 2 by two to get [4, 4, 4]. Cost = 4 * 3 - 7 = 5. Answer = 3.

Time Complexity: O(n log n)
Space Complexity: O(1) besides the sort
*/

public class P40_FrequencyOfMostFrequentElementSlidingWindow {
    public static int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        long windowSum = 0;
        int best = 0;

        for (int right = 0; right < nums.length; right++) {
            windowSum += nums[right];
            while ((long) nums[right] * (right - left + 1) - windowSum > k) {
                windowSum -= nums[left++];
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(maxFrequency(new int[]{1, 2, 4}, 5));
        System.out.println(maxFrequency(new int[]{1, 4, 8, 13}, 5));
        System.out.println(maxFrequency(new int[]{3, 9, 6}, 2));
    }
}
