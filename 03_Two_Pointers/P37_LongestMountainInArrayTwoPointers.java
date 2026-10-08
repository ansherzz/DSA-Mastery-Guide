import java.util.*;

/*
Problem: Longest Mountain in Array
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
A mountain is a subarray of length at least 3 that strictly increases to a peak and then strictly decreases.
Return the length of the longest mountain, or 0 if there is none.

Why this pattern fits:
Every mountain is identified by its peak. From a peak, a left pointer and a right pointer extend down the slopes.

Approach:
For each index that is higher than both neighbors, move left while the values keep falling to the left
and right while they keep falling to the right. Record right - left + 1 and continue scanning after `right`.

Worked Example:
arr = [2, 1, 4, 7, 3, 2, 5]
Peak 7 extends left to 1 and right to 2: [1, 4, 7, 3, 2]. Answer = 5.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P37_LongestMountainInArrayTwoPointers {
    public static int longestMountain(int[] arr) {
        int best = 0;
        int i = 1;
        while (i < arr.length - 1) {
            if (arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
                int left = i;
                int right = i;
                while (left > 0 && arr[left - 1] < arr[left]) {
                    left--;
                }
                while (right < arr.length - 1 && arr[right + 1] < arr[right]) {
                    right++;
                }
                best = Math.max(best, right - left + 1);
                i = right + 1;
            } else {
                i++;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[2, 1, 4, 7, 3, 2, 5] -> " + longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5}));
        System.out.println("[2, 2, 2] -> " + longestMountain(new int[]{2, 2, 2}));
    }
}
