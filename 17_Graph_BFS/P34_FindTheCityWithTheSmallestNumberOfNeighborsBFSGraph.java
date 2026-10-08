import java.util.*;

/*
Problem: Find the City With the Smallest Number of Neighbors at a Threshold Distance
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
Given a weighted undirected graph of n cities and a distance threshold, return the city with the smallest
number of cities reachable within the threshold. If several cities tie, return the one with the largest index.

Why this pattern fits:
Distances between every pair of cities are needed, and n is at most 100.
Floyd-Warshall's triple loop computes all pairs in O(n^3) with a few lines.

Approach:
dist[i][j] starts as the edge weight (0 on the diagonal, infinity otherwise).
For each intermediate k, relax dist[i][j] through k. Then count, per city, the others within the threshold.

Worked Example:
n = 4, edges 0-1 (3), 1-2 (1), 1-3 (4), 2-3 (1), threshold 4 -> cities 0 and 3 each reach two cities; return 3.

Time Complexity: O(n^3)
Space Complexity: O(n^2)
*/

public class P34_FindTheCityWithTheSmallestNumberOfNeighborsBFSGraph {
    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int infinity = Integer.MAX_VALUE / 2;
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], infinity);
            dist[i][i] = 0;
        }
        for (int[] edge : edges) {
            dist[edge[0]][edge[1]] = edge[2];
            dist[edge[1]][edge[0]] = edge[2];
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int bestCity = -1;
        int bestCount = Integer.MAX_VALUE;
        for (int city = 0; city < n; city++) {
            int count = 0;
            for (int other = 0; other < n; other++) {
                if (other != city && dist[city][other] <= distanceThreshold) {
                    count++;
                }
            }
            if (count <= bestCount) {
                bestCount = count;
                bestCity = city;
            }
        }
        return bestCity;
    }

    public static void main(String[] args) {
        System.out.println("n = 4, threshold 4 -> " + findTheCity(4,
                new int[][]{{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}}, 4));
        System.out.println("n = 5, threshold 2 -> " + findTheCity(5,
                new int[][]{{0, 1, 2}, {0, 4, 8}, {1, 2, 3}, {1, 4, 2}, {2, 3, 1}, {3, 4, 1}}, 2));
    }
}
