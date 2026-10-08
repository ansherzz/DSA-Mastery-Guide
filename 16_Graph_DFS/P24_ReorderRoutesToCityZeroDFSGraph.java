import java.util.*;

/*
Problem: Reorder Routes to Make All Paths Lead to the City Zero
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
There are n cities numbered 0 to n - 1 and n - 1 one-way roads, arranged so that
    ignoring directions the road network is a tree. connections[i] = [a, b] is a road from city a to city b.

    An event is held in city 0. Reorient as few roads as possible so that every city can reach city 0.
    Return the minimum number of roads that must change direction.

Why this pattern fits:
Because the network is a tree, there is exactly one route between any city and city 0.
    So every road has one correct direction: toward city 0. Walking outward from city 0 with DFS,
    any road that points in the direction of travel points away from city 0 and must be flipped.

Approach:
Build an undirected adjacency list, but tag each entry with whether it follows the original direction.
    DFS from city 0, never returning to the parent.
    When moving from a city to a neighbor along an original-direction road, count one reversal.

Worked Example:
Example:
    n = 6, connections = [[0, 1], [1, 3], [2, 3], [4, 0], [4, 5]]
    Walking outward from 0: road 0 -> 1 points away (flip), road 1 -> 3 points away (flip),
    road 2 -> 3 points toward 3 and therefore toward 0 (keep), road 4 -> 0 is fine (keep),
    road 4 -> 5 points away (flip). Answer = 3.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P24_ReorderRoutesToCityZeroDFSGraph {
    public static int minReorder(int n, int[][] connections) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : connections) {
            graph.get(road[0]).add(new int[]{road[1], 1});
            graph.get(road[1]).add(new int[]{road[0], 0});
        }
        return dfs(graph, 0, -1);
    }

    private static int dfs(List<List<int[]>> graph, int city, int parent) {
        int reversals = 0;
        for (int[] edge : graph.get(city)) {
            int next = edge[0];
            if (next == parent) {
                continue;
            }
            reversals += edge[1] + dfs(graph, next, city);
        }
        return reversals;
    }

    public static void main(String[] args) {
        System.out.println(minReorder(6, new int[][]{{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}}));
        System.out.println(minReorder(5, new int[][]{{1, 0}, {1, 2}, {3, 2}, {3, 4}}));
        System.out.println(minReorder(3, new int[][]{{1, 0}, {2, 0}}));
    }
}
