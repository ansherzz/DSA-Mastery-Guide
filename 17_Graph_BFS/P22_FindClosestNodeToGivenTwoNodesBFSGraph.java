import java.util.*;

/*
Problem: Find Closest Node to Given Two Nodes
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
Each node has at most one outgoing edge (edges[i] or -1). Given node1 and node2, return the node reachable
from both that minimizes the maximum of the two distances. Break ties by the smaller index; return -1 if none.

Why this pattern fits:
A question about distances from two sources is answered by two independent traversals
followed by a scan that combines the two distance arrays.

Approach:
Walk from node1 recording distances (stop on revisit or -1); do the same from node2.
For every node reached by both, compare max(dist1, dist2) and keep the best.

Worked Example:
edges = [2, 2, 3, -1], node1 = 0, node2 = 1 -> node 2 is at distance 1 from both. Answer = 2.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P22_FindClosestNodeToGivenTwoNodesBFSGraph {
    private static int[] distancesFrom(int[] edges, int start) {
        int[] dist = new int[edges.length];
        Arrays.fill(dist, -1);
        int steps = 0;
        for (int node = start; node != -1 && dist[node] == -1; node = edges[node]) {
            dist[node] = steps++;
        }
        return dist;
    }

    public static int closestMeetingNode(int[] edges, int node1, int node2) {
        int[] first = distancesFrom(edges, node1);
        int[] second = distancesFrom(edges, node2);
        int best = -1;
        int bestDistance = Integer.MAX_VALUE;
        for (int node = 0; node < edges.length; node++) {
            if (first[node] >= 0 && second[node] >= 0) {
                int distance = Math.max(first[node], second[node]);
                if (distance < bestDistance) {
                    bestDistance = distance;
                    best = node;
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[2, 2, 3, -1], 0 and 1 -> " + closestMeetingNode(new int[]{2, 2, 3, -1}, 0, 1));
        System.out.println("[1, 2, -1], 0 and 2 -> " + closestMeetingNode(new int[]{1, 2, -1}, 0, 2));
    }
}
