import java.util.*;

/*
Problem: Find the Highest Altitude
Pattern: 05. Prefix Sum
Difficulty: Easy

Interview Prompt:
A biker starts a road trip at altitude 0. The trip passes through n + 1 points.
    gain[i] is the net change in altitude between point i and point i + 1.
    Return the highest altitude reached at any point, including the starting point.

Why this pattern fits:
The altitude at each point is the sum of all gains before it, which is exactly a prefix sum.
    The answer is simply the largest prefix sum, with the empty prefix (altitude 0) included.

Approach:
Keep a running altitude starting at 0.
    Add each gain in order and compare the new altitude against the best seen so far.

Worked Example:
Example:
    gain = [-5, 1, 5, 0, -7]
    Altitudes: 0, -5, -4, 1, 1, -6. The highest is 1.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class FindHighestAltitudePrefixSum {
    public static int largestAltitude(int[] gain) {
        int altitude = 0;
        int highest = 0;

        for (int change : gain) {
            altitude += change;
            highest = Math.max(highest, altitude);
        }
        return highest;
    }

    private static void runDemo(int[] gain) {
        System.out.println("Gain    : " + Arrays.toString(gain));
        System.out.println("Highest : " + largestAltitude(gain));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{-5, 1, 5, 0, -7});
        runDemo(new int[]{-4, -3, -2, -1, 4, 3, 2});
    }
}
