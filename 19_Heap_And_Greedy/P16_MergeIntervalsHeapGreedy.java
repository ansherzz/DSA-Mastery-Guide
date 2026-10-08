import java.util.*;

/*
Problem: Merge Intervals
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
Given an array of intervals where intervals[i] = [start, end], merge all overlapping intervals
    and return an array of the non-overlapping intervals that cover the same ranges.
    Intervals that only touch at an endpoint, such as [1, 4] and [4, 5], count as overlapping.

Why this pattern fits:
After sorting by start time, an interval can only overlap the group that is currently being built.
    A single greedy decision per interval is enough: either extend the current group or close it and start a new one.

Approach:
Sort the intervals by start.
    Keep the last merged interval. For each next interval, if its start is at most the last interval's end,
    extend the end to the larger of the two ends. Otherwise append it as a new merged interval.

Worked Example:
Example:
    intervals = [[1, 3], [2, 6], [8, 10], [15, 18]]
    [1, 3] and [2, 6] overlap and become [1, 6]. The others stand alone.
    Answer = [[1, 6], [8, 10], [15, 18]]

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P16_MergeIntervalsHeapGreedy {
    public static int[][] merge(int[][] intervals) {
        int[][] sorted = intervals.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        for (int[] interval : sorted) {
            if (!merged.isEmpty() && interval[0] <= merged.get(merged.size() - 1)[1]) {
                int[] last = merged.get(merged.size() - 1);
                last[1] = Math.max(last[1], interval[1]);
            } else {
                merged.add(new int[]{interval[0], interval[1]});
            }
        }
        return merged.toArray(new int[0][]);
    }

    private static void runDemo(int[][] intervals) {
        System.out.println(Arrays.deepToString(intervals) + " -> " + Arrays.deepToString(merge(intervals)));
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        runDemo(new int[][]{{1, 4}, {4, 5}});
        runDemo(new int[][]{{1, 4}, {0, 4}, {2, 3}});
    }
}
