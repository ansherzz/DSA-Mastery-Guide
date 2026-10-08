import java.util.*;

/*
Problem: Closest Pair of Points
Pattern: 15. Divide & Conquer
Difficulty: Hard

Interview Prompt:
Given n points in the plane (n >= 2), return the smallest Euclidean distance between any two of them.
    The brute-force solution checks all pairs in O(n^2). Find a faster algorithm.

Why this pattern fits:
Splitting the points by a vertical line gives two independent halves.
    The difficulty is the pairs that straddle the line, and the key geometric fact is that only a thin strip
    around the line matters, and inside the strip each point needs to be compared with only a few neighbors.

Approach:
Sort the points by x. Recursively find the closest distance d in the left half and in the right half.
    Collect the points whose x is within d of the dividing line, and sort this strip by y.
    For each strip point, compare it with the following strip points while their y difference is below d.
    A packing argument shows at most 7 such points exist, so the strip scan is linear.
    Ranges of 3 or fewer points are solved by brute force.
    Sorting the strip inside every call gives O(n log^2 n); merging by y on the way up would give O(n log n).

Worked Example:
Example:
    points = (2, 3), (12, 30), (40, 50), (5, 1), (12, 10), (3, 4)
    The closest pair is (2, 3) and (3, 4) with distance sqrt(2) = 1.41421.

Time Complexity: O(n log^2 n)
Space Complexity: O(n)
*/

public class P47_ClosestPairOfPointsDivideConquer {
    public static double closestPair(double[][] points) {
        double[][] sorted = points.clone();
        Arrays.sort(sorted, (a, b) -> Double.compare(a[0], b[0]));
        return solve(sorted, 0, sorted.length - 1);
    }

    private static double solve(double[][] points, int left, int right) {
        if (right - left < 3) {
            return bruteForce(points, left, right);
        }

        int mid = left + (right - left) / 2;
        double middleX = points[mid][0];
        double best = Math.min(solve(points, left, mid), solve(points, mid + 1, right));

        List<double[]> strip = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i][0] - middleX) < best) {
                strip.add(points[i]);
            }
        }
        strip.sort((a, b) -> Double.compare(a[1], b[1]));

        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && strip.get(j)[1] - strip.get(i)[1] < best; j++) {
                best = Math.min(best, distance(strip.get(i), strip.get(j)));
            }
        }
        return best;
    }

    private static double bruteForce(double[][] points, int left, int right) {
        double best = Double.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                best = Math.min(best, distance(points[i], points[j]));
            }
        }
        return best;
    }

    private static double distance(double[] a, double[] b) {
        return Math.hypot(a[0] - b[0], a[1] - b[1]);
    }

    public static void main(String[] args) {
        double[][] points = {{2, 3}, {12, 30}, {40, 50}, {5, 1}, {12, 10}, {3, 4}};
        System.out.printf("Divide and conquer: %.5f%n", closestPair(points));
        System.out.printf("Brute force check : %.5f%n", bruteForce(points, 0, points.length - 1));

        double[][] grid = new double[40][];
        for (int i = 0; i < grid.length; i++) {
            grid[i] = new double[]{(i * 37) % 101, (i * 53) % 89};
        }
        System.out.printf("Divide and conquer: %.5f%n", closestPair(grid));
        System.out.printf("Brute force check : %.5f%n", bruteForce(grid, 0, grid.length - 1));
    }
}
