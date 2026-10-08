import java.util.*;

/*
Problem: Meeting Rooms III
Pattern: 19. Heap & Greedy
Difficulty: Hard

Interview Prompt:
There are n rooms numbered 0..n - 1. Meetings (with unique start times) are handled in order of start:
each takes the lowest-numbered free room; if none is free it is delayed until the earliest room frees up
(lowest number on ties) and keeps its duration. Return the room that held the most meetings (lowest number on ties).

Why this pattern fits:
Two different "minimum" questions are asked constantly: the smallest free room number,
and the busy room that finishes first. Each needs its own heap, and rooms move between them as time advances.

Approach:
Sort meetings by start. For each meeting: move every busy room whose end time <= start into the free heap.
If a room is free, use it until `end`. Otherwise pop the earliest-finishing room and book it from its end time
for the meeting's duration. Count the meetings per room.

Worked Example:
n = 2, meetings [0,10], [1,5], [2,7], [3,4] -> room 0 holds [0,10] and later [3,4] delayed to [10,11];
room 1 holds [1,5] and then [2,7] delayed to [5,10]. Both rooms host two meetings, so return 0.

Time Complexity: O(m log m + m log n)
Space Complexity: O(n)
*/

public class P48_MeetingRoomsIIIHeapGreedy {
    public static int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> free = new PriorityQueue<>();
        for (int room = 0; room < n; room++) {
            free.add(room);
        }
        PriorityQueue<long[]> busy = new PriorityQueue<>(
                (a, b) -> a[0] != b[0] ? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1]));
        int[] count = new int[n];
        for (int[] meeting : meetings) {
            while (!busy.isEmpty() && busy.peek()[0] <= meeting[0]) {
                free.add((int) busy.poll()[1]);
            }
            if (!free.isEmpty()) {
                int room = free.poll();
                count[room]++;
                busy.add(new long[]{meeting[1], room});
            } else {
                long[] earliest = busy.poll();
                int room = (int) earliest[1];
                count[room]++;
                busy.add(new long[]{earliest[0] + meeting[1] - meeting[0], room});
            }
        }
        int best = 0;
        for (int room = 1; room < n; room++) {
            if (count[room] > count[best]) {
                best = room;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("n = 2 -> " + mostBooked(2, new int[][]{{0, 10}, {1, 5}, {2, 7}, {3, 4}}));
        System.out.println("n = 3 -> " + mostBooked(3, new int[][]{{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}}));
    }
}
