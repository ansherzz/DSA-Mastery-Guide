import java.util.*;

/*
Problem: Paint House
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
There is a row of houses, each painted red, blue, or green with given costs.
No two adjacent houses may have the same color. Return the minimum total cost.

Why this pattern fits:
The only thing the next house needs to know about the past is the color of the previous house.
So the DP carries one value per color - a tiny state updated house by house.

Approach:
For each house: newRed = cost[0] + min(blue, green), newBlue = cost[1] + min(red, green),
newGreen = cost[2] + min(red, blue). The answer is the minimum of the three at the end.

Worked Example:
costs = [[17, 2, 17], [16, 16, 5], [14, 3, 19]] -> blue (2), green (5), blue (3). Total = 10.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P32_PaintHouseDP {
    public static int minCost(int[][] costs) {
        int red = 0;
        int blue = 0;
        int green = 0;
        for (int[] cost : costs) {
            int newRed = cost[0] + Math.min(blue, green);
            int newBlue = cost[1] + Math.min(red, green);
            int newGreen = cost[2] + Math.min(red, blue);
            red = newRed;
            blue = newBlue;
            green = newGreen;
        }
        return Math.min(red, Math.min(blue, green));
    }

    public static void main(String[] args) {
        System.out.println("three houses -> " + minCost(new int[][]{{17, 2, 17}, {16, 16, 5}, {14, 3, 19}}));
        System.out.println("one house -> " + minCost(new int[][]{{7, 6, 2}}));
    }
}
