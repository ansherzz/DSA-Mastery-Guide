import java.util.*;

/*
Problem: Points That Intersect With Cars
Pattern: 05. Prefix Sum
Difficulty: Easy

Interview Prompt:
You are given a list of cars parked on a number line. Car i covers every integer point from start_i to end_i inclusive.
    Return the number of integer points on the line that are covered by at least one car.

Why this pattern fits:
Each car is a range update: add 1 to every point in [start, end].
    A difference array records each range update in O(1), and a prefix sum over it recovers the coverage of every point.

Approach:
For each car, do diff[start] += 1 and diff[end + 1] -= 1.
    Sweep the line while accumulating the running sum; the running sum at a point is the number of cars covering it.
    Count the points where the running sum is positive.

Worked Example:
Example:
    cars = [[3, 6], [1, 5], [4, 7]]
    Every point from 1 to 7 is covered by at least one car. Answer = 7.

Time Complexity: O(n + M), where M is the largest coordinate
Space Complexity: O(M)
*/

public class P11_PointsThatIntersectWithCarsPrefixSum {
    public static int numberOfPoints(int[][] cars) {
        int maxEnd = 0;
        for (int[] car : cars) {
            maxEnd = Math.max(maxEnd, car[1]);
        }

        int[] diff = new int[maxEnd + 2];
        for (int[] car : cars) {
            diff[car[0]]++;
            diff[car[1] + 1]--;
        }

        int covered = 0;
        int running = 0;
        for (int point = 0; point <= maxEnd; point++) {
            running += diff[point];
            if (running > 0) {
                covered++;
            }
        }
        return covered;
    }

    public static void main(String[] args) {
        System.out.println(numberOfPoints(new int[][]{{3, 6}, {1, 5}, {4, 7}}));
        System.out.println(numberOfPoints(new int[][]{{1, 3}, {5, 8}}));
    }
}
