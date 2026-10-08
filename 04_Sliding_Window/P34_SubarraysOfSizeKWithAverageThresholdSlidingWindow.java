import java.util.*;

/*
Problem: Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Given an array of integers arr and two integers k and threshold,
    return the number of subarrays of size k whose average is greater than or equal to threshold.

Why this pattern fits:
The window length is fixed at k, and consecutive windows share k - 1 elements.
    Maintaining the running sum avoids recomputing each window from scratch.

Approach:
An average of at least threshold is the same as a sum of at least k * threshold, which avoids division.
    Add each element to the running sum. Once the window has grown past k elements, subtract the one that left.
    Whenever the window holds exactly k elements, compare its sum with the required sum.

Worked Example:
Example:
    arr = [2, 2, 2, 2, 5, 5, 5, 8], k = 3, threshold = 4
    Required sum = 12. Window sums: 6, 6, 9, 12, 15, 18. Three of them reach 12. Answer = 3.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P34_SubarraysOfSizeKWithAverageThresholdSlidingWindow {
    public static int numOfSubarrays(int[] arr, int k, int threshold) {
        int required = k * threshold;
        int sum = 0;
        int count = 0;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];
            if (right >= k) {
                sum -= arr[right - k];
            }
            if (right >= k - 1 && sum >= required) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(numOfSubarrays(new int[]{2, 2, 2, 2, 5, 5, 5, 8}, 3, 4));
        System.out.println(numOfSubarrays(new int[]{11, 13, 17, 23, 29, 31, 7, 5, 2, 3}, 3, 5));
    }
}
