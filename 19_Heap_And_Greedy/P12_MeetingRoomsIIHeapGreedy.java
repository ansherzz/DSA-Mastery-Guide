import java.util.*;

/*
Problem: Meeting Rooms II
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
Given a list of meeting intervals [start, end], return the minimum number of meeting rooms required.

    This problem combines sorting with a heap of current end times.

Why this pattern fits:
Sort meetings by start time.
    A min-heap of end times tells you which room becomes free first.
    If the earliest-ending meeting finishes before the next meeting starts, that room can be reused.

Approach:
Sort intervals by start.
    For each interval, compare its start time with the smallest end time in the heap.
    Reuse a room if possible; otherwise allocate a new one.

Worked Example:
Example:
    intervals = [[0,30],[5,10],[15,20]]
    The answer is 2.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P12_MeetingRoomsIIHeapGreedy {
    public static int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> minEndTimes = new PriorityQueue<>();

        for (int[] interval : intervals) {
            if (!minEndTimes.isEmpty() && minEndTimes.peek() <= interval[0]) {
                minEndTimes.poll();
            }
            minEndTimes.offer(interval[1]);
        }

        return minEndTimes.size();
    }

    public static void main(String[] args) {
        System.out.println(minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}));
        System.out.println(minMeetingRooms(new int[][]{{7,10},{2,4}}));
    }
}
