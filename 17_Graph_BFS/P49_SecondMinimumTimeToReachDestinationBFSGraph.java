import java.util.*;

/*
Problem: Second Minimum Time to Reach Destination
Pattern: 17. Graph BFS
Difficulty: Hard

Interview Prompt:
A city graph has n vertices; every edge takes `time` minutes. Every vertex has a traffic signal that turns green
at time 0 and switches every `change` minutes; you may only leave a vertex when it is green and cannot wait otherwise.
Return the second minimum time (strictly larger than the minimum) to travel from vertex 1 to vertex n.

Why this pattern fits:
Travel time depends only on the number of edges used, so the task is the second-shortest path in hops.
Ordinary BFS keeps one distance per node; here each node keeps two, so it may enter the queue twice.

Approach:
dist1 and dist2 per node. When a neighbor is reached with d + 1 hops: set dist1 if unset; otherwise, if d + 1 is larger
than dist1 and dist2 is unset, set dist2. Each successful update enqueues the node.
Finally simulate dist2[n] hops, waiting for green whenever the current time falls in a red phase.

Worked Example:
n = 5, time = 3, change = 5: the shortest route has 2 hops (6 minutes); the second has 3 hops and one red-light wait: 13 minutes.

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class P49_SecondMinimumTimeToReachDestinationBFSGraph {
    public static int secondMinimum(int n, int[][] edges, int time, int change) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int[] dist1 = new int[n + 1];
        int[] dist2 = new int[n + 1];
        Arrays.fill(dist1, -1);
        Arrays.fill(dist2, -1);
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{1, 0});
        dist1[1] = 0;
        while (!queue.isEmpty() && dist2[n] == -1) {
            int[] state = queue.poll();
            int hops = state[1] + 1;
            for (int next : graph.get(state[0])) {
                if (dist1[next] == -1) {
                    dist1[next] = hops;
                    queue.add(new int[]{next, hops});
                } else if (dist2[next] == -1 && hops > dist1[next]) {
                    dist2[next] = hops;
                    queue.add(new int[]{next, hops});
                }
            }
        }
        int elapsed = 0;
        for (int hop = 0; hop < dist2[n]; hop++) {
            if ((elapsed / change) % 2 == 1) {
                elapsed = (elapsed / change + 1) * change;
            }
            elapsed += time;
        }
        return elapsed;
    }

    public static void main(String[] args) {
        System.out.println("n = 5 -> " + secondMinimum(5, new int[][]{{1, 2}, {1, 3}, {1, 4}, {3, 4}, {4, 5}}, 3, 5));
        System.out.println("n = 2 -> " + secondMinimum(2, new int[][]{{1, 2}}, 3, 2));
    }
}
