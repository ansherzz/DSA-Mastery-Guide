import java.util.*;

/*
Problem: K Closest Points to Origin
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
Given an array of points on the X-Y plane and an integer k, return the k points closest to the origin (0, 0).
    Distance is the Euclidean distance. The answer may be returned in any order.

Why this pattern fits:
Sorting all n points costs O(n log n), but only the k best are needed.
    A max-heap of size k keeps the k closest points seen so far with the farthest of them on top,
    so each new point is compared against the current worst and replaces it if it is closer.

Approach:
Compare points by squared distance x^2 + y^2, which avoids square roots and preserves the ordering.
    Push each point into a max-heap ordered by squared distance.
    When the heap grows beyond k, remove the farthest point.
    Whatever remains at the end is the answer.

Worked Example:
Example:
    points = [[3, 3], [5, -1], [-2, 4]], k = 2
    Squared distances: 18, 26, 20. The two smallest belong to [3, 3] and [-2, 4].

Time Complexity: O(n log k)
Space Complexity: O(k)
*/

public class P11_KClosestPointsToOriginHeapGreedy {
    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(squaredDistance(b), squaredDistance(a)));

        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] result = new int[maxHeap.size()][];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = maxHeap.poll();
        }
        return result;
    }

    private static int squaredDistance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    private static void runDemo(int[][] points, int k) {
        System.out.println("Points : " + Arrays.deepToString(points) + ", k = " + k);
        System.out.println("Closest: " + Arrays.deepToString(kClosest(points, k)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{1, 3}, {-2, 2}}, 1);
        runDemo(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2);
    }
}
