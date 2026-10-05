import java.util.*;

/*
Problem: Interval List Intersections
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
You are given two lists of closed intervals. Within each list the intervals are pairwise disjoint and sorted.
    Return the intersection of the two lists, as a list of closed intervals.

Why this pattern fits:
Both lists are sorted, so the earliest unprocessed interval of each list is the only pair that can overlap next.
    One pointer per list is enough, exactly like merging two sorted arrays.

Approach:
Look at the current interval from each list.
    Their overlap is [max(starts), min(ends)], and it is valid when the start is not after the end.
    Then advance the pointer whose interval ends first, because that interval cannot overlap anything later.

Worked Example:
Example:
    first  = [[0,2], [5,10], [13,23], [24,25]]
    second = [[1,5], [8,12], [15,24], [25,26]]
    Answer = [[1,2], [5,5], [8,10], [15,23], [24,24], [25,25]]

Time Complexity: O(n + m)
Space Complexity: O(1) extra, excluding the output
*/

public class IntervalListIntersectionsTwoPointers {
    public static int[][] intervalIntersection(int[][] first, int[][] second) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < first.length && j < second.length) {
            int start = Math.max(first[i][0], second[j][0]);
            int end = Math.min(first[i][1], second[j][1]);
            if (start <= end) {
                result.add(new int[]{start, end});
            }

            if (first[i][1] < second[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] first = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] second = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};

        System.out.println("First       : " + Arrays.deepToString(first));
        System.out.println("Second      : " + Arrays.deepToString(second));
        System.out.println("Intersection: " + Arrays.deepToString(intervalIntersection(first, second)));
    }
}
