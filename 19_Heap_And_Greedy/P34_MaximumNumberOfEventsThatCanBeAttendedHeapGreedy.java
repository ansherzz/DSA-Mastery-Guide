import java.util.*;

/*
Problem: Maximum Number of Events That Can Be Attended
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
events[i] = [startDay, endDay]. You can attend an event on any single day within its range, and at most one event per day.
Return the maximum number of events you can attend.

Why this pattern fits:
On any day, among the events currently available, the one closing earliest is the most urgent;
the others can still be attended later. A min-heap of end days makes "most urgent" the top element.

Approach:
Sort events by start. For each day: add the end days of events starting today, drop events that already ended,
and if the heap is not empty attend (pop) the one with the smallest end day. Jump over days with nothing available.

Worked Example:
events = [[1, 2], [2, 3], [3, 4], [1, 2]] -> attend on days 1, 2, 3, 4. Answer = 4.

Time Complexity: O(n log n + D) where D is the range of days visited
Space Complexity: O(n)
*/

public class P34_MaximumNumberOfEventsThatCanBeAttendedHeapGreedy {
    public static int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> endDays = new PriorityQueue<>();
        int attended = 0;
        int i = 0;
        int day = 0;
        while (i < events.length || !endDays.isEmpty()) {
            if (endDays.isEmpty()) {
                day = events[i][0];
            }
            while (i < events.length && events[i][0] <= day) {
                endDays.add(events[i++][1]);
            }
            endDays.poll();
            attended++;
            day++;
            while (!endDays.isEmpty() && endDays.peek() < day) {
                endDays.poll();
            }
        }
        return attended;
    }

    public static void main(String[] args) {
        System.out.println("[[1,2],[2,3],[3,4]] -> " + maxEvents(new int[][]{{1, 2}, {2, 3}, {3, 4}}));
        System.out.println("[[1,2],[2,3],[3,4],[1,2]] -> " + maxEvents(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 2}}));
        System.out.println("[[1,4],[4,4],[2,2],[3,4],[1,1]] -> "
                + maxEvents(new int[][]{{1, 4}, {4, 4}, {2, 2}, {3, 4}, {1, 1}}));
    }
}
