import java.util.*;

/*
Problem: Minimize Max Distance to Gas Station
Pattern: 07. Binary Search on Answer
Difficulty: Hard

Interview Prompt:
Gas stations are located at sorted positions along a straight road.
    You may add k new gas stations anywhere on the road, including non-integer positions.
    After adding them, let penalty be the maximum distance between adjacent gas stations.

    Return the smallest possible penalty. Answers within 10^-6 of the true value are accepted.

Why this pattern fits:
This is "minimize the maximum" over a continuous range.
    For a target gap d, you can count how many new stations are needed to make every gap at most d.
    Fewer stations are needed as d grows, so d can be binary searched on real numbers.

Approach:
Search d between 0 and the largest existing gap.
    A gap of length g needs ceil(g / d) - 1 additional stations.
    If the total needed is at most k, d is achievable, so shrink the upper bound; otherwise raise the lower bound.
    Repeat a fixed number of iterations so the interval becomes smaller than the required precision.

Worked Example:
Example:
    stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 9
    There are 9 gaps of length 1. Putting one new station in the middle of each gap makes every gap 0.5.
    Answer = 0.5.

Time Complexity: O(n * iterations)
Space Complexity: O(1)
*/

public class P42_MinimizeMaxDistanceToGasStationBinarySearchAnswer {
    private static boolean achievable(int[] stations, int k, double gapLimit) {
        int needed = 0;
        for (int i = 1; i < stations.length; i++) {
            double gap = stations[i] - stations[i - 1];
            needed += (int) Math.ceil(gap / gapLimit) - 1;
        }
        return needed <= k;
    }

    public static double minmaxGasDist(int[] stations, int k) {
        double left = 0;
        double right = 0;
        for (int i = 1; i < stations.length; i++) {
            right = Math.max(right, stations[i] - stations[i - 1]);
        }

        for (int iteration = 0; iteration < 60; iteration++) {
            double mid = (left + right) / 2;
            if (achievable(stations, k, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    private static void runDemo(int[] stations, int k) {
        System.out.println("Stations : " + Arrays.toString(stations));
        System.out.println("k        : " + k);
        System.out.printf("Penalty  : %.5f%n", minmaxGasDist(stations, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 9);
        runDemo(new int[]{23, 24, 36, 39, 46, 56, 57, 65, 84, 98}, 1);
    }
}
