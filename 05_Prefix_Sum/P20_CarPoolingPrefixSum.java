import java.util.*;

/*
Problem: Car Pooling
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
A car with `capacity` empty seats drives only east. You are given trips where
    trips[i] = [passengers, from, to] means that `passengers` people must be picked up at kilometer `from`
    and dropped off at kilometer `to`. Locations are between 0 and 1000.

    Return true if it is possible to complete every trip without ever exceeding the capacity.

Why this pattern fits:
Each trip adds passengers over a range of the road. Recording +passengers at the pickup point and
    -passengers at the drop-off point is a difference array, and its running sum is the load at each kilometer.

Approach:
Build a difference array over the possible locations.
    Add passengers at `from` and subtract them at `to` (they leave before new riders board at that point).
    Sweep with a running sum and return false as soon as the load exceeds the capacity.

Worked Example:
Example:
    trips = [[2, 1, 5], [3, 3, 7]], capacity = 4
    Between kilometers 3 and 5 the car carries 2 + 3 = 5 passengers, which exceeds 4. Answer = false.
    With capacity 5 the same trips are possible.

Time Complexity: O(n + maximum location)
Space Complexity: O(maximum location)
*/

public class P20_CarPoolingPrefixSum {
    public static boolean carPooling(int[][] trips, int capacity) {
        int[] change = new int[1001];
        for (int[] trip : trips) {
            change[trip[1]] += trip[0];
            change[trip[2]] -= trip[0];
        }

        int load = 0;
        for (int delta : change) {
            load += delta;
            if (load > capacity) {
                return false;
            }
        }
        return true;
    }

    private static void runDemo(int[][] trips, int capacity) {
        System.out.println("Trips: " + Arrays.deepToString(trips) + ", capacity = " + capacity);
        System.out.println("Possible? " + carPooling(trips, capacity));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{2, 1, 5}, {3, 3, 7}}, 4);
        runDemo(new int[][]{{2, 1, 5}, {3, 3, 7}}, 5);
        runDemo(new int[][]{{2, 1, 5}, {3, 5, 7}}, 3);
    }
}
