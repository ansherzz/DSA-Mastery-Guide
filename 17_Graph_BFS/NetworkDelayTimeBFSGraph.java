import java.util.*;

/*
Problem: Network Delay Time
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
You are given a network of n nodes labeled 1 to n and a list of travel times as directed edges
    times[i] = [u, v, w], meaning a signal takes w time units to travel from u to v.
    A signal is sent from node k. Return the minimum time for all n nodes to receive the signal,
    or -1 if some node can never receive it.

Why this pattern fits:
Plain BFS finds shortest paths only when every edge costs the same.
    With different edge weights, the queue must release the closest node first instead of the oldest one.
    Replacing the FIFO queue with a min-heap turns BFS into Dijkstra's algorithm.

Approach:
Keep the best known distance to each node, initially infinity except for the source.
    Repeatedly take the node with the smallest distance from a priority queue.
    Skip the entry if a shorter distance was already finalized for that node.
    Otherwise relax each outgoing edge and push improved distances.
    The answer is the largest finalized distance, or -1 if any node is unreachable.

Worked Example:
Example:
    times = [[2, 1, 1], [2, 3, 1], [3, 4, 1]], n = 4, k = 2
    Node 2 reaches 1 and 3 at time 1, and node 3 reaches 4 at time 2.
    All nodes have the signal after 2 time units.

Time Complexity: O(E log V)
Space Complexity: O(V + E)
*/

public class NetworkDelayTimeBFSGraph {
    public static int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : times) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[k] = 0;

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        heap.offer(new int[]{k, 0});

        while (!heap.isEmpty()) {
            int[] current = heap.poll();
            int node = current[0];
            int time = current[1];
            if (time > distance[node]) {
                continue;
            }

            for (int[] edge : graph.get(node)) {
                int arrival = time + edge[1];
                if (arrival < distance[edge[0]]) {
                    distance[edge[0]] = arrival;
                    heap.offer(new int[]{edge[0], arrival});
                }
            }
        }

        int latest = 0;
        for (int node = 1; node <= n; node++) {
            if (distance[node] == Integer.MAX_VALUE) {
                return -1;
            }
            latest = Math.max(latest, distance[node]);
        }
        return latest;
    }

    public static void main(String[] args) {
        System.out.println(networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
        System.out.println(networkDelayTime(new int[][]{{1, 2, 1}}, 2, 1));
        System.out.println(networkDelayTime(new int[][]{{1, 2, 1}}, 2, 2));
    }
}
