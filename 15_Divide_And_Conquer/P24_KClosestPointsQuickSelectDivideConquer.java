import java.util.*;

/*
Problem: K Closest Points to Origin
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Given an array of points where points[i] = [x, y] represents a point on the X-Y plane, and an integer k,
    return the k closest points to the origin (0, 0). The answer may be returned in any order.

Why this pattern fits:
The k closest points do not need to be sorted among themselves; they only need to be separated from the rest.
    Quickselect does exactly that: partition around a pivot, then continue only in the side that contains position k.
    Discarding one side each round is what brings the average cost down from O(n log n) to O(n).

Approach:
Compare points by squared distance, which avoids square roots.
    Partition a range around a pivot distance so that closer points are on the left.
    If the pivot's final index is k, the first k points are the answer.
    If it is greater than k, repeat on the left part; otherwise repeat on the right part.
    This demo uses the middle element as the pivot so the output is reproducible; a random pivot guards against bad inputs.

Worked Example:
Example:
    points = [[3, 3], [5, -1], [-2, 4]], k = 2
    Squared distances: 18, 26, 20. The two closest are [3, 3] and [-2, 4].

Time Complexity: O(n) on average, O(n^2) worst case
Space Complexity: O(1)
*/

public class P24_KClosestPointsQuickSelectDivideConquer {
    public static int[][] kClosest(int[][] points, int k) {
        int left = 0;
        int right = points.length - 1;

        while (left < right) {
            int pivotIndex = partition(points, left, right);
            if (pivotIndex == k) {
                break;
            }
            if (pivotIndex < k) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, k);
    }

    private static int partition(int[][] points, int left, int right) {
        swap(points, left + (right - left) / 2, right);
        long pivot = squaredDistance(points[right]);

        int store = left;
        for (int i = left; i < right; i++) {
            if (squaredDistance(points[i]) < pivot) {
                swap(points, store++, i);
            }
        }
        swap(points, store, right);
        return store;
    }

    private static long squaredDistance(int[] point) {
        return (long) point[0] * point[0] + (long) point[1] * point[1];
    }

    private static void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    private static void runDemo(int[][] points, int k) {
        int[][] closest = kClosest(points, k);
        Arrays.sort(closest, (a, b) -> Long.compare(squaredDistance(a), squaredDistance(b)));
        System.out.println(Arrays.deepToString(closest));
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{1, 3}, {-2, 2}}, 1);
        runDemo(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2);
        runDemo(new int[][]{{6, 0}, {1, 1}, {0, 5}, {2, 2}, {-1, 0}}, 3);
    }
}
