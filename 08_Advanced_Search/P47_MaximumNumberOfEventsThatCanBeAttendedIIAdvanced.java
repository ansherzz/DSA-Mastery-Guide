import java.util.*;

/*
Problem: Maximum Number of Events That Can Be Attended II
Pattern: 08. Advanced Search
Difficulty: Hard

Interview Prompt:
events[i] = [startDay, endDay, value]. Attend at most k events that do not overlap
(you cannot start an event on the day another ends). Return the maximum total value.

Why this pattern fits:
Each event is either skipped or attended; attending jumps to the first event starting after it ends.
That jump target is a binary search on the sorted start days, which makes the DP O(n * k * log n).

Approach:
Sort by start. dp[i][j] = best value using events i.. with j picks left
= max(dp[i + 1][j], value[i] + dp[next(i)][j - 1]), where next(i) is the first index with start > end[i].

Worked Example:
events = [[1, 2, 4], [3, 4, 3], [2, 3, 1]], k = 2 -> attend the first two for 4 + 3 = 7.

Time Complexity: O(n * k + n log n)
Space Complexity: O(n * k)
*/

public class P47_MaximumNumberOfEventsThatCanBeAttendedIIAdvanced {
    public static int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int n = events.length;
        int[][] dp = new int[n + 1][k + 1];
        for (int i = n - 1; i >= 0; i--) {
            int low = i + 1;
            int high = n;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (events[mid][0] > events[i][1]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            for (int picks = 1; picks <= k; picks++) {
                dp[i][picks] = Math.max(dp[i + 1][picks], events[i][2] + dp[low][picks - 1]);
            }
        }
        return dp[0][k];
    }

    public static void main(String[] args) {
        System.out.println("[[1,2,4],[3,4,3],[2,3,1]], k = 2 -> " + maxValue(new int[][]{{1, 2, 4}, {3, 4, 3}, {2, 3, 1}}, 2));
        System.out.println("[[1,2,4],[3,4,3],[2,3,10]], k = 2 -> " + maxValue(new int[][]{{1, 2, 4}, {3, 4, 3}, {2, 3, 10}}, 2));
        System.out.println("[[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3 -> "
                + maxValue(new int[][]{{1, 1, 1}, {2, 2, 2}, {3, 3, 3}, {4, 4, 4}}, 3));
    }
}
