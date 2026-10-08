import java.util.*;

/*
Problem: Max Points on a Line
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Hard

Interview Prompt:
Given points on a 2D plane, return the maximum number of points that lie on the same straight line.
There are at most 300 points.

Why this pattern fits:
Two points define a line, so enumerating every pair covers every candidate line.
With n <= 300 the O(n^3) enumeration is acceptable and avoids floating-point slopes entirely.

Approach:
For each pair (i, j), count the points k for which the cross product of (j - i) and (k - i) is zero.
Track the largest count. Use long arithmetic for the cross product.

Worked Example:
points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
The line through (3,2), (4,1), (2,3), (1,4) holds 4 points. Answer = 4.

Time Complexity: O(n^3)
Space Complexity: O(1)
*/

public class P49_MaxPointsOnALineBruteForce {
    public static int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        int best = 2;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long dx = points[j][0] - points[i][0];
                long dy = points[j][1] - points[i][1];
                int count = 2;
                for (int k = j + 1; k < n; k++) {
                    long cross = dx * (points[k][1] - points[i][1]) - dy * (points[k][0] - points[i][0]);
                    if (cross == 0) {
                        count++;
                    }
                }
                best = Math.max(best, count);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[[1,1],[2,2],[3,3]] -> " + maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
        System.out.println("[[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]] -> "
                + maxPoints(new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}}));
    }
}
