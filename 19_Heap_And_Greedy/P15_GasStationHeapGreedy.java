import java.util.*;

/*
Problem: Gas Station
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
There are n gas stations on a circular route. Station i provides gas[i] units of gas,
    and it costs cost[i] units of gas to drive from station i to station i + 1.
    You start with an empty tank at one of the stations.

    Return the index of the starting station from which you can drive around the circuit once in the clockwise
    direction, or -1 if it is impossible. If a solution exists, it is unique.

Why this pattern fits:
Trying every start costs O(n^2). Two observations make a single pass enough:
    if the total gas is less than the total cost, no start works; and if you run out of gas
    somewhere after starting at s, then no station between s and that point can be a valid start either,
    because you arrived at each of them with a tank that was at least empty.

Approach:
Scan the stations once, tracking the total surplus and the tank level since the current candidate start.
    When the tank goes negative, discard the candidate and every station visited since it:
    set the candidate to the next station and reset the tank to zero.
    At the end, the candidate is valid exactly when the total surplus is non-negative.

Worked Example:
Example:
    gas  = [1, 2, 3, 4, 5]
    cost = [3, 4, 5, 1, 2]
    Net gain per station: -2, -2, -2, +3, +3. Starting at 0, 1, or 2 fails immediately.
    Starting at 3: tank 3, then 6, then 4, then 2, then 0. The circuit completes. Answer = 3.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P15_GasStationHeapGreedy {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalSurplus = 0;
        int tank = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            int net = gas[i] - cost[i];
            totalSurplus += net;
            tank += net;

            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        return totalSurplus >= 0 ? start : -1;
    }

    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        System.out.println(canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3}));
        System.out.println(canCompleteCircuit(new int[]{5, 1, 2, 3, 4}, new int[]{4, 4, 1, 5, 1}));
    }
}
