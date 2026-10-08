import java.util.*;

/*
Problem: Two Best Non-Overlapping Events
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
events[i] = [start, end, value]. Attend at most two events that do not overlap
(an event ending at t and another starting at t do overlap). Return the maximum total value.

Why this pattern fits:
After choosing a first event, the best partner is "the most valuable event that starts after it ends".
Sorting by start time makes those partners a suffix, found by binary search and summarized by a suffix maximum.

Approach:
Sort by start. suffixBest[i] = max value among events i..n-1.
For each event, binary search the first index whose start > its end and add suffixBest of that index.

Worked Example:
events = [[1, 3, 2], [4, 5, 2], [2, 4, 3]] -> take the events with values 2 and 2 (days 1-3 and 4-5). Answer = 4.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P23_TwoBestNonOverlappingEventsAdvanced {
    public static int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int n = events.length;
        int[] suffixBest = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffixBest[i] = Math.max(suffixBest[i + 1], events[i][2]);
        }
        int best = 0;
        for (int[] event : events) {
            int low = 0;
            int high = n;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (events[mid][0] > event[1]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            best = Math.max(best, event[2] + suffixBest[low]);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[[1,3,2],[4,5,2],[2,4,3]] -> " + maxTwoEvents(new int[][]{{1, 3, 2}, {4, 5, 2}, {2, 4, 3}}));
        System.out.println("[[1,3,2],[4,5,2],[1,5,5]] -> " + maxTwoEvents(new int[][]{{1, 3, 2}, {4, 5, 2}, {1, 5, 5}}));
        System.out.println("[[1,5,3],[1,5,1],[6,6,5]] -> " + maxTwoEvents(new int[][]{{1, 5, 3}, {1, 5, 1}, {6, 6, 5}}));
    }
}
