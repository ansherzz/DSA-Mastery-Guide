import java.util.*;

/*
Problem: Minimum Number of Seconds to Make Mountain Height Zero
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
Workers reduce a mountain simultaneously. Worker i needs workerTimes[i] * (1 + 2 + ... + x) seconds
to reduce the height by x. Return the minimum number of seconds to bring the height to 0.

Why this pattern fits:
More time lets every worker remove at least as much, so "can they finish within T" is monotonic.

Approach:
For a time T, worker w can remove the largest x with w * x * (x + 1) / 2 <= T,
found from the quadratic formula and corrected for rounding. Feasible when the total is at least the height.

Worked Example:
height = 4, workerTimes = [2, 1, 1] -> in 3 seconds the workers remove 1, 2, and 2. Answer = 3.

Time Complexity: O(n log(maxTime))
Space Complexity: O(1)
*/

public class P17_MinimumSecondsToMakeMountainHeightZeroBinarySearchAnswer {
    private static long reducibleBy(long workerTime, long time) {
        long limit = 2 * time / workerTime;
        long x = (long) ((Math.sqrt(1 + 4.0 * limit) - 1) / 2);
        while ((x + 1) * (x + 2) <= limit) {
            x++;
        }
        while (x * (x + 1) > limit) {
            x--;
        }
        return x;
    }

    public static long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long fastest = Arrays.stream(workerTimes).min().getAsInt();
        long low = 0;
        long high = fastest * mountainHeight * (mountainHeight + 1L) / 2;
        while (low < high) {
            long mid = low + (high - low) / 2;
            long removed = 0;
            for (int w : workerTimes) {
                removed += reducibleBy(w, mid);
            }
            if (removed >= mountainHeight) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("height 4, [2, 1, 1] -> " + minNumberOfSeconds(4, new int[]{2, 1, 1}));
        System.out.println("height 10, [3, 2, 2, 4] -> " + minNumberOfSeconds(10, new int[]{3, 2, 2, 4}));
        System.out.println("height 5, [1] -> " + minNumberOfSeconds(5, new int[]{1}));
    }
}
