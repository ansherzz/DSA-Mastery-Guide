import java.util.*;

/*
Problem: Maximize the Minimum Powered City
Pattern: 07. Binary Search on Answer
Difficulty: Hard

Interview Prompt:
stations[i] is the number of power stations in city i; each station powers every city within distance r.
You may build k more stations anywhere. Return the maximum possible value of the minimum power of any city.

Why this pattern fits:
"Maximize the minimum" with a budget is bisectable. For a target, scanning left to right,
a city that is short must be fixed now, and the best place for new stations is as far right as still reaches it.

Approach:
Compute each city's initial power with prefix sums. For a target, sweep the cities with a running "extra" value;
when a city is short by `need`, spend need stations at i + r, which adds to cities up to i + 2r
(recorded in a difference array). Fail if more than k stations are required.

Worked Example:
stations = [1, 2, 4, 5, 0], r = 1, k = 2 -> building both at city 1 gives powers [5, 9, 13, 9, 5]. Answer = 5.

Time Complexity: O(n log(sum + k))
Space Complexity: O(n)
*/

public class P47_MaximizeTheMinimumPoweredCityBinarySearchAnswer {
    private static boolean achievable(long[] power, int r, long k, long target) {
        int n = power.length;
        long[] expire = new long[n + 1];
        long extra = 0;
        for (int i = 0; i < n; i++) {
            extra -= expire[i];
            long current = power[i] + extra;
            if (current < target) {
                long need = target - current;
                if (need > k) {
                    return false;
                }
                k -= need;
                extra += need;
                expire[Math.min(n, i + 2 * r + 1)] += need;
            }
        }
        return true;
    }

    public static long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stations[i];
        }
        long[] power = new long[n];
        for (int i = 0; i < n; i++) {
            power[i] = prefix[Math.min(n, i + r + 1)] - prefix[Math.max(0, i - r)];
        }
        long low = 0;
        long high = prefix[n] + k;
        while (low < high) {
            long mid = low + (high - low + 1) / 2;
            if (achievable(power, r, k, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 4, 5, 0], r = 1, k = 2 -> " + maxPower(new int[]{1, 2, 4, 5, 0}, 1, 2));
        System.out.println("[4, 4, 4, 4], r = 0, k = 3 -> " + maxPower(new int[]{4, 4, 4, 4}, 0, 3));
    }
}
