import java.util.*;

/*
Problem: Car Fleet
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
There are n cars driving toward a destination that is target miles away along a one-lane road.
    Car i starts at position[i] and drives at speed[i] miles per hour.
    A car can never pass another car ahead of it, but it can catch up and then drive bumper to bumper at the slower speed.
    A car fleet is a non-empty set of cars driving at the same position and speed. A single car is also a fleet.
    If a car catches up to a fleet exactly at the destination, it is still part of that fleet.
    Return the number of car fleets that will arrive at the destination.

Why this pattern fits:
Process the cars from the one closest to the target backward. Each car either catches the fleet in front of it
    or starts a new fleet. Only the most recent fleet matters, so the fleets form a stack of arrival times.

Approach:
Sort the cars by starting position, closest to the target first.
    Compute each car's unobstructed arrival time (target - position) / speed.
    If that time is greater than the arrival time of the fleet ahead (the top of the stack), the car cannot catch up,
    so it becomes a new fleet and its time is pushed.
    Otherwise it merges into the fleet ahead and adopts that fleet's time, so nothing is pushed.
    The stack size is the number of fleets.

Worked Example:
Example:
    target = 12, position = [10, 8, 0, 5, 3], speed = [2, 4, 1, 1, 3]
    Arrival times from closest to farthest: 1, 1, 7, 3, 12.
    Fleets: {10, 8} arrive at 1, {5, 3} arrive at 7, {0} arrives at 12. Answer = 3.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P41_CarFleetStack {
    public static int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> position[b] - position[a]);

        Deque<Double> fleetTimes = new ArrayDeque<>();
        for (int car : order) {
            double time = (double) (target - position[car]) / speed[car];
            if (fleetTimes.isEmpty() || time > fleetTimes.peek()) {
                fleetTimes.push(time);
            }
        }
        return fleetTimes.size();
    }

    public static void main(String[] args) {
        System.out.println(carFleet(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3}));
        System.out.println(carFleet(10, new int[]{3}, new int[]{3}));
        System.out.println(carFleet(100, new int[]{0, 2, 4}, new int[]{4, 2, 1}));
    }
}
