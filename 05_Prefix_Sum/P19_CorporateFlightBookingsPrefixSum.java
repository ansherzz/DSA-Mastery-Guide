import java.util.*;

/*
Problem: Corporate Flight Bookings
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
There are n flights labeled 1 to n. You are given bookings where bookings[i] = [first, last, seats]
    means that `seats` seats were reserved on every flight from `first` through `last` inclusive.
    Return an array of length n with the total number of seats reserved on each flight.

Why this pattern fits:
Each booking is a range update. Applying it flight by flight costs O(n) per booking.
    A difference array records only where a range starts and where it stops,
    and a single prefix-sum pass afterwards converts those markers into final totals.

Approach:
Create a difference array. For each booking, add seats at index first - 1
    and subtract seats at index last (the position just past the range), if it exists.
    Take the running sum of the difference array to obtain the seats per flight.

Worked Example:
Example:
    bookings = [[1, 2, 10], [2, 3, 20], [2, 5, 25]], n = 5
    Difference array after all bookings: [10, 45, -10, -20, 0]
    Running sum: [10, 55, 45, 25, 25]

Time Complexity: O(n + number of bookings)
Space Complexity: O(n)
*/

public class P19_CorporateFlightBookingsPrefixSum {
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] seats = new int[n];

        for (int[] booking : bookings) {
            int first = booking[0] - 1;
            int last = booking[1];
            seats[first] += booking[2];
            if (last < n) {
                seats[last] -= booking[2];
            }
        }

        for (int i = 1; i < n; i++) {
            seats[i] += seats[i - 1];
        }
        return seats;
    }

    private static void runDemo(int[][] bookings, int n) {
        System.out.println("Bookings: " + Arrays.deepToString(bookings) + ", n = " + n);
        System.out.println("Seats   : " + Arrays.toString(corpFlightBookings(bookings, n)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}}, 5);
        runDemo(new int[][]{{1, 2, 10}, {2, 2, 15}}, 2);
    }
}
