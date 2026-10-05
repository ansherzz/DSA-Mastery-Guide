import java.util.*;

/*
Problem: Find Right Interval
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
You are given intervals where intervals[i] = [start, end] and every start is unique.
    The right interval of interval i is the interval j whose start is the smallest value that is >= end of i.
    Note that j may equal i.

    Return an array where answer[i] is the index of the right interval of interval i, or -1 if none exists.

Why this pattern fits:
The input is not sorted, but each query is "smallest start that is at least X".
    If you sort the starts once while remembering their original indices,
    every query becomes a lower-bound binary search.

Approach:
Build an array of [start, originalIndex] pairs and sort it by start.
    For each interval, binary search the sorted starts for the first one >= the interval's end.
    If found, record the stored original index; otherwise record -1.

Worked Example:
Example:
    intervals = [[3, 4], [2, 3], [1, 2]]
    Sorted starts: 1 (index 2), 2 (index 1), 3 (index 0).
    Interval [3, 4] needs a start >= 4: none, so -1.
    Interval [2, 3] needs a start >= 3: index 0.
    Interval [1, 2] needs a start >= 2: index 1.
    Answer = [-1, 0, 1]

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class FindRightIntervalAdvanced {
    public static int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[][] starts = new int[n][2];
        for (int i = 0; i < n; i++) {
            starts[i][0] = intervals[i][0];
            starts[i][1] = i;
        }
        Arrays.sort(starts, (x, y) -> Integer.compare(x[0], y[0]));

        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int end = intervals[i][1];

            int left = 0;
            int right = n;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (starts[mid][0] < end) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            answer[i] = left == n ? -1 : starts[left][1];
        }
        return answer;
    }

    private static void runDemo(int[][] intervals) {
        System.out.println("Intervals : " + Arrays.deepToString(intervals));
        System.out.println("Answer    : " + Arrays.toString(findRightInterval(intervals)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{1, 2}});
        runDemo(new int[][]{{3, 4}, {2, 3}, {1, 2}});
        runDemo(new int[][]{{1, 4}, {2, 3}, {3, 4}});
    }
}
