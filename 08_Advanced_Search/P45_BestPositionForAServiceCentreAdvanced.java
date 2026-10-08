import java.util.*;

/*
Problem: Best Position for a Service Centre
Pattern: 08. Advanced Search
Difficulty: Hard

Interview Prompt:
Given customer positions on a 2D plane, choose a point (not necessarily at integer coordinates)
that minimizes the sum of Euclidean distances to all customers. Return that minimum sum.

Why this pattern fits:
A sum of distances is a convex function. A convex function of one variable has a single valley,
which ternary search finds; with two variables, the best y for each x is itself found by an inner ternary search.

Approach:
Ternary search x over [0, 100]. To evaluate an x, ternary search y over [0, 100] for the minimum sum.
Each search discards one third of its interval per step.

Worked Example:
positions = [[0, 1], [1, 0], [1, 2], [2, 1]] -> the centre (1, 1) is at distance 1 from each. Answer = 4.

Time Complexity: O(n * iterations^2)
Space Complexity: O(1)
*/

public class P45_BestPositionForAServiceCentreAdvanced {
    private static double totalDistance(int[][] positions, double x, double y) {
        double total = 0;
        for (int[] p : positions) {
            total += Math.sqrt((p[0] - x) * (p[0] - x) + (p[1] - y) * (p[1] - y));
        }
        return total;
    }

    private static double bestForX(int[][] positions, double x) {
        double low = 0;
        double high = 100;
        for (int step = 0; step < 60; step++) {
            double m1 = low + (high - low) / 3;
            double m2 = high - (high - low) / 3;
            if (totalDistance(positions, x, m1) < totalDistance(positions, x, m2)) {
                high = m2;
            } else {
                low = m1;
            }
        }
        return totalDistance(positions, x, low);
    }

    public static double getMinDistSum(int[][] positions) {
        double low = 0;
        double high = 100;
        for (int step = 0; step < 60; step++) {
            double m1 = low + (high - low) / 3;
            double m2 = high - (high - low) / 3;
            if (bestForX(positions, m1) < bestForX(positions, m2)) {
                high = m2;
            } else {
                low = m1;
            }
        }
        return bestForX(positions, low);
    }

    public static void main(String[] args) {
        System.out.println("[[0,1],[1,0],[1,2],[2,1]] -> "
                + String.format(Locale.ROOT, "%.5f", getMinDistSum(new int[][]{{0, 1}, {1, 0}, {1, 2}, {2, 1}})));
        System.out.println("[[1,1],[3,3]] -> "
                + String.format(Locale.ROOT, "%.5f", getMinDistSum(new int[][]{{1, 1}, {3, 3}})));
    }
}
