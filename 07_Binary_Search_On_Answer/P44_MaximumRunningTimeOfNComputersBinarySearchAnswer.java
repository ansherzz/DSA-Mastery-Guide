import java.util.*;

/*
Problem: Maximum Running Time of N Computers
Pattern: 07. Binary Search on Answer
Difficulty: Hard

Interview Prompt:
You have n computers and an array batteries where batteries[i] is how many minutes battery i can power a computer.
    Each computer needs exactly one battery at a time. At any moment you may swap batteries between computers
    instantly and as often as you like, but batteries cannot be recharged.

    Return the maximum number of minutes you can run all n computers simultaneously.

Why this pattern fits:
Scheduling the swaps directly is complicated, but checking a target time t is simple.
    A battery can contribute at most t minutes toward a run of length t (it cannot power two computers at once).
    If the total usable contribution is at least n * t, a schedule exists. Feasibility is monotonic in t.

Approach:
Search t between 0 and (sum of batteries / n).
    For a candidate t, add min(battery, t) over all batteries and compare against n * t.
    You want the largest feasible t, so use the upper-middle and move left up on success.

Worked Example:
Example:
    n = 2, batteries = [3, 3, 3]
    t = 4: usable = 3 + 3 + 3 = 9 and needed = 8. Feasible.
    t = 5: usable = 9 and needed = 10. Not feasible. Answer = 4.

Time Complexity: O(m log(sum / n)) where m is the number of batteries
Space Complexity: O(1)
*/

public class P44_MaximumRunningTimeOfNComputersBinarySearchAnswer {
    private static boolean canRun(int n, int[] batteries, long minutes) {
        long usable = 0;
        for (int battery : batteries) {
            usable += Math.min(battery, minutes);
        }
        return usable >= n * minutes;
    }

    public static long maxRunTime(int n, int[] batteries) {
        long total = 0;
        for (int battery : batteries) {
            total += battery;
        }

        long left = 0;
        long right = total / n;

        while (left < right) {
            long mid = left + (right - left + 1) / 2;
            if (canRun(n, batteries, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private static void runDemo(int n, int[] batteries) {
        System.out.println("Computers : " + n);
        System.out.println("Batteries : " + Arrays.toString(batteries));
        System.out.println("Minutes   : " + maxRunTime(n, batteries));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(2, new int[]{3, 3, 3});
        runDemo(2, new int[]{1, 1, 1, 1});
        runDemo(3, new int[]{10, 10, 3, 5});
    }
}
