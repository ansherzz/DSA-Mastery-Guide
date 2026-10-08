import java.util.*;

/*
Problem: Insert Interval
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
Given non-overlapping intervals sorted by start and a new interval, insert the new interval
and merge where necessary so the result is still sorted and non-overlapping.

Why this pattern fits:
Because the input is sorted, the intervals fall into three consecutive groups relative to the new one:
entirely before, overlapping, and entirely after. One left-to-right pass handles each group in turn.

Approach:
Add all intervals with end < newStart. While intervals have start <= newEnd, widen the new interval
to cover them. Add the merged interval, then add the remaining intervals.

Worked Example:
intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], new = [4, 8] -> [3,5], [6,7], [8,10] merge into [3, 10].

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P26_InsertIntervalHeapGreedy {
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i++]);
        }
        int start = newInterval[0];
        int end = newInterval[1];
        while (i < n && intervals[i][0] <= end) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        result.add(new int[]{start, end});
        while (i < n) {
            result.add(intervals[i++]);
        }
        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        System.out.println("insert [2, 5] -> " + Arrays.deepToString(insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5})));
        System.out.println("insert [4, 8] -> " + Arrays.deepToString(
                insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8})));
    }
}
