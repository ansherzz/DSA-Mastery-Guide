import java.util.*;

/*
Problem: Number of Ways to Arrive at Destination
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
You are in a city with n intersections numbered from 0 to n - 1 and bi-directional roads between some of them.
    roads[i] = [u, v, time] means there is a road between u and v that takes time minutes to travel.
    You want to know in how many ways you can travel from intersection 0 to intersection n - 1
    in the shortest amount of time. Return the number of ways modulo 10^9 + 7.

Why this pattern fits:
The shortest time is a Dijkstra question. Counting the shortest paths rides along with it:
    Dijkstra finalizes nodes in order of distance, so by the time a node is processed,
    the number of shortest paths into it is complete and can be pushed to its neighbors.

Approach:
Keep dist[] and ways[], with dist[0] = 0 and ways[0] = 1.
    When relaxing an edge from node to next with a new distance d:
    - if d < dist[next], a better route was found: set dist[next] = d and ways[next] = ways[node].
    - if d == dist[next], another equally short route was found: add ways[node] to ways[next].
    Skip stale heap entries so each node distributes its count once. Use long distances.

Worked Example:
Example:
    n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
    The shortest time from 0 to 6 is 7 minutes, and there are 4 ways:
    0-6, 0-4-6, 0-1-2-5-6, and 0-1-3-5-6.

Time Complexity: O(E log V)
Space Complexity: O(V + E)
*/

public class P23_NumberOfWaysToArriveAtDestinationBFSGraph {
    public static int countPaths(int n, int[][] roads) {
        final long mod = 1_000_000_007L;
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            graph.get(road[0]).add(new int[]{road[1], road[2]});
            graph.get(road[1]).add(new int[]{road[0], road[2]});
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        long[] ways = new long[n];
        dist[0] = 0;
        ways[0] = 1;

        PriorityQueue<long[]> heap = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        heap.offer(new long[]{0, 0});
        while (!heap.isEmpty()) {
            long[] current = heap.poll();
            int node = (int) current[0];
            if (current[1] > dist[node]) {
                continue;
            }

            for (int[] edge : graph.get(node)) {
                int next = edge[0];
                long candidate = current[1] + edge[1];
                if (candidate < dist[next]) {
                    dist[next] = candidate;
                    ways[next] = ways[node];
                    heap.offer(new long[]{next, candidate});
                } else if (candidate == dist[next]) {
                    ways[next] = (ways[next] + ways[node]) % mod;
                }
            }
        }
        return (int) ways[n - 1];
    }

    public static void main(String[] args) {
        int[][] roads = {{0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3}, {6, 3, 3},
            {3, 5, 1}, {6, 5, 1}, {2, 5, 1}, {0, 4, 5}, {4, 6, 2}};
        System.out.println(countPaths(7, roads));
        System.out.println(countPaths(2, new int[][]{{1, 0, 10}}));
    }
}
