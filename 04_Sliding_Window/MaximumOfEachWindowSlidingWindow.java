import java.util.*;

/*
Problem: Sliding Window Maximum
Pattern: 04. Sliding Window
Difficulty: Hard

Interview Prompt:
Given an integer array nums and a window size k, a window of size k slides from the far left
    to the far right, one position at a time.
    Return an array containing the maximum value of each window.

Why this pattern fits:
Recomputing the maximum for every window costs O(n * k).
    The key observation is that an element can never be a future maximum once a larger element enters after it.
    A deque that keeps only useful candidates gives each window's maximum in O(1) amortized time.

Approach:
Store indices in a deque so that their values are in decreasing order from front to back.
    For each new index: drop the front if it has left the window,
    drop every back index whose value is not larger than the new value, then push the new index.
    Once the first full window is formed, the front of the deque is that window's maximum.

Worked Example:
Example:
    nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
    Window maxima: [3, 3, 5, 5, 6, 7]

Time Complexity: O(n)
Space Complexity: O(k)
*/

public class MaximumOfEachWindowSlidingWindow {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> candidates = new ArrayDeque<>();

        for (int right = 0; right < nums.length; right++) {
            if (!candidates.isEmpty() && candidates.peekFirst() <= right - k) {
                candidates.pollFirst();
            }
            while (!candidates.isEmpty() && nums[candidates.peekLast()] <= nums[right]) {
                candidates.pollLast();
            }
            candidates.offerLast(right);

            if (right >= k - 1) {
                result[right - k + 1] = nums[candidates.peekFirst()];
            }
        }
        return result;
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Array : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("Maxima: " + Arrays.toString(maxSlidingWindow(nums, k)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        runDemo(new int[]{9, 8, 7, 6}, 2);
        runDemo(new int[]{1}, 1);
    }
}
