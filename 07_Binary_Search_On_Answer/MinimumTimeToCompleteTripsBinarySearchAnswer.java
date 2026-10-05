import java.util.*;

/*
Problem: Minimum Time to Complete Trips
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
You are given an array time where time[i] is the time bus i needs to complete one trip.
    Each bus can make trips back to back, and all buses operate independently.
    Return the minimum amount of time required for all buses together to complete at least totalTrips trips.

Why this pattern fits:
Given a time limit t, the number of completed trips is easy to compute: sum of t / time[i].
    That count never decreases as t increases, so the smallest sufficient t can be binary searched.

Approach:
Search t between 1 and (fastest bus time * totalTrips), which is enough even if only the fastest bus worked.
    For a candidate t, count trips and stop early once the count reaches totalTrips.
    Shrink toward the smallest feasible t.

Worked Example:
Example:
    time = [1, 2, 3], totalTrips = 5
    At t = 2: trips = 2 + 1 + 0 = 3. Not enough.
    At t = 3: trips = 3 + 1 + 1 = 5. Enough. Answer = 3.

Time Complexity: O(n log(minTime * totalTrips))
Space Complexity: O(1)
*/

public class MinimumTimeToCompleteTripsBinarySearchAnswer {
    private static boolean enoughTrips(int[] time, int totalTrips, long limit) {
        long trips = 0;
        for (int tripTime : time) {
            trips += limit / tripTime;
            if (trips >= totalTrips) {
                return true;
            }
        }
        return false;
    }

    public static long minimumTime(int[] time, int totalTrips) {
        int fastest = Integer.MAX_VALUE;
        for (int tripTime : time) {
            fastest = Math.min(fastest, tripTime);
        }

        long left = 1;
        long right = (long) fastest * totalTrips;

        while (left < right) {
            long mid = left + (right - left) / 2;
            if (enoughTrips(time, totalTrips, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static void runDemo(int[] time, int totalTrips) {
        System.out.println("Trip times : " + Arrays.toString(time));
        System.out.println("Trips      : " + totalTrips);
        System.out.println("Min time   : " + minimumTime(time, totalTrips));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 3}, 5);
        runDemo(new int[]{2}, 1);
        runDemo(new int[]{10000000}, 10000000);
    }
}
