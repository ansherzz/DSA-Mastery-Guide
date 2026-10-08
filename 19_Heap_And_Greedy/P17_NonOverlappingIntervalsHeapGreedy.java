import java.util.*;

/*
Problem: Non-overlapping Intervals
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
Given an array of intervals where intervals[i] = [start, end], return the minimum number of intervals
    you need to remove so that the remaining intervals do not overlap.
    Intervals that only touch at an endpoint, such as [1, 2] and [2, 3], do not overlap.

Why this pattern fits:
Removing as few as possible is the same as keeping as many as possible, which is the classic
    activity selection problem. The greedy rule is to always keep the interval that ends earliest,
    because it leaves the most room for everything that follows.

Approach:
Sort the intervals by end time.
    Keep the first one. For each later interval, keep it if its start is at or after the end of the last kept interval;
    otherwise count it as removed.

Worked Example:
Example:
    intervals = [[1, 2], [2, 3], [3, 4], [1, 3]]
    Sorted by end: [1, 2], [2, 3], [1, 3], [3, 4].
    Keep [1, 2] and [2, 3]. [1, 3] starts before 3, so remove it. Keep [3, 4].
    Answer = 1.

Time Complexity: O(n log n)
Space Complexity: O(n) for the sorted copy
*/

public class P17_NonOverlappingIntervalsHeapGreedy {
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        int[][] sorted = intervals.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[1], b[1]));

        int removed = 0;
        int lastEnd = sorted[0][1];
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i][0] < lastEnd) {
                removed++;
            } else {
                lastEnd = sorted[i][1];
            }
        }
        return removed;
    }

    private static void runDemo(int[][] intervals) {
        System.out.println(Arrays.deepToString(intervals) + " -> remove " + eraseOverlapIntervals(intervals));
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}});
        runDemo(new int[][]{{1, 2}, {1, 2}, {1, 2}});
        runDemo(new int[][]{{1, 2}, {2, 3}});
    }
}
