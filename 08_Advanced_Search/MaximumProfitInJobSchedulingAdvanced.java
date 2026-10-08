import java.util.*;

/*
Problem: Maximum Profit in Job Scheduling
Pattern: 08. Advanced Search
Difficulty: Hard

Interview Prompt:
We have n jobs, where job i is scheduled from startTime[i] to endTime[i] and earns profit[i].
    Return the maximum profit you can take such that no two chosen jobs overlap in time.
    A job that ends at time X does not overlap with a job that starts at time X.

Why this pattern fits:
The take-or-skip recurrence is ordinary dynamic programming, but the "take" branch needs
    the last job that finishes before the current job starts.
    On jobs sorted by end time, that predecessor is found with an upper-bound search, turning O(n^2) into O(n log n).

Approach:
Sort the jobs by end time. Let best[i] be the maximum profit using only the first i jobs.
    For job i (1-indexed), binary search for the number of earlier jobs whose end time is <= its start time; call it p.
    Then best[i] = max(best[i - 1], best[p] + profit of job i).
    best[n] is the answer.

Worked Example:
Example:
    startTime = [1, 2, 3, 3], endTime = [3, 4, 5, 6], profit = [50, 10, 40, 70]
    Take the job [1, 3] for 50 and the job [3, 6] for 70. Answer = 120.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class MaximumProfitInJobSchedulingAdvanced {
    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[1], b[1]));

        int[] best = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int[] job = jobs[i - 1];

            int left = 0;
            int right = i - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (jobs[mid][1] <= job[0]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            best[i] = Math.max(best[i - 1], best[left] + job[2]);
        }
        return best[n];
    }

    public static void main(String[] args) {
        System.out.println(jobScheduling(
            new int[]{1, 2, 3, 3}, new int[]{3, 4, 5, 6}, new int[]{50, 10, 40, 70}));
        System.out.println(jobScheduling(
            new int[]{1, 2, 3, 4, 6}, new int[]{3, 5, 10, 6, 9}, new int[]{20, 20, 100, 70, 60}));
        System.out.println(jobScheduling(
            new int[]{1, 1, 1}, new int[]{2, 3, 4}, new int[]{5, 6, 4}));
    }
}
