import java.util.*;

/*
Problem: Longest Turbulent Subarray
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
A subarray is turbulent if the comparison sign flips between every adjacent pair
(for example a < b > c < d). Return the length of the longest turbulent subarray.

Why this pattern fits:
Validity depends only on neighboring comparisons, so when the pattern breaks the window
does not shrink step by step: it restarts at the break.

Approach:
Track the window start. For each i, compare arr[i - 1] with arr[i].
If they are equal, restart at i. Otherwise, if i is the last index or the next comparison does not flip,
the window [start, i] is complete: record its length and restart at i.

Worked Example:
arr = [9, 4, 2, 10, 7, 8, 8, 1, 9] -> [4, 2, 10, 7, 8] alternates > < > <. Answer = 5.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P25_LongestTurbulentSubarraySlidingWindow {
    public static int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int best = 1;
        int start = 0;
        for (int i = 1; i < n; i++) {
            int sign = Integer.compare(arr[i - 1], arr[i]);
            if (sign == 0) {
                start = i;
            } else if (i == n - 1 || sign * Integer.compare(arr[i], arr[i + 1]) != -1) {
                best = Math.max(best, i - start + 1);
                start = i;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[9, 4, 2, 10, 7, 8, 8, 1, 9] -> " + maxTurbulenceSize(new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9}));
        System.out.println("[4, 8, 12, 16] -> " + maxTurbulenceSize(new int[]{4, 8, 12, 16}));
        System.out.println("[100] -> " + maxTurbulenceSize(new int[]{100}));
    }
}
