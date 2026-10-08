import java.util.*;

/*
Problem: Minimum Number of Refueling Stops
Pattern: 19. Heap & Greedy
Difficulty: Hard

Interview Prompt:
A car travels from a starting position to a destination which is target miles east of the starting position.
    There are gas stations along the way, given as stations[i] = [position, fuel], sorted by position.
    The car starts with startFuel liters and uses one liter per mile. Its tank is infinitely large,
    and when it stops at a station it transfers all of that station's fuel into the car.
    Return the minimum number of refueling stops the car must make to reach its destination, or -1 if it cannot.
    Arriving at a station or at the destination with exactly 0 fuel left still counts as arriving.

Why this pattern fits:
The decision to stop at a station does not have to be made when the car passes it.
    Treat every passed station as fuel held in reserve, and only "cash in" a reserve when the car would otherwise run dry.
    When that happens, the largest reserve is the best one to use, so the reserves live in a max-heap.

Approach:
Track the farthest position reachable with the fuel taken so far (initially startFuel).
    Walk through the stations plus the target as a final checkpoint.
    While the next checkpoint is beyond reach: if the heap is empty, return -1;
    otherwise pop the largest passed station, extend the reach by its fuel, and count one stop.
    Once the checkpoint is reachable, push that station's fuel into the heap.

Worked Example:
Example:
    target = 100, startFuel = 10, stations = [[10, 60], [20, 30], [30, 30], [60, 40]]
    Reach 10 covers station 10. Station 20 is out of reach: use 60 -> reach 70 (1 stop).
    Stations 20, 30, 60 are passed. The target 100 is out of reach: use the largest reserve, 40 -> reach 110 (2 stops).

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P30_MinimumNumberOfRefuelingStopsHeapGreedy {
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> reserves = new PriorityQueue<>(Collections.reverseOrder());
        long reach = startFuel;
        int stops = 0;

        for (int i = 0; i <= stations.length; i++) {
            int checkpoint = i < stations.length ? stations[i][0] : target;
            while (reach < checkpoint) {
                if (reserves.isEmpty()) {
                    return -1;
                }
                reach += reserves.poll();
                stops++;
            }

            if (i < stations.length) {
                reserves.offer(stations[i][1]);
            }
        }
        return stops;
    }

    public static void main(String[] args) {
        System.out.println(minRefuelStops(1, 1, new int[][]{}));
        System.out.println(minRefuelStops(100, 1, new int[][]{{10, 100}}));
        System.out.println(minRefuelStops(100, 10, new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}}));
    }
}
