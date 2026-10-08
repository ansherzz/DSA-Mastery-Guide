import java.util.*;

/*
Problem: Longest Cycle in a Graph
Pattern: 16. Graph DFS
Difficulty: Hard

Interview Prompt:
Each node has at most one outgoing edge: edges[i] is the node that i points to, or -1.
Return the length of the longest cycle, or -1 if there is none.

Why this pattern fits:
With out-degree at most one, a DFS from any node is just a walk along a chain.
If the walk reaches a node stamped during the same walk, the difference of timestamps is the cycle length.

Approach:
Keep a global clock and visitTime[]. For each unvisited start, remember the clock value at the start of the walk,
then follow edges stamping nodes. If the walk ends on a node stamped at or after the walk's start time,
the cycle length is the current clock minus that node's stamp.

Worked Example:
edges = [3, 3, 4, 2, 3] -> the cycle 2 -> 4 -> 3 -> 2 has length 3.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P43_LongestCycleInAGraphDFSGraph {
    public static int longestCycle(int[] edges) {
        int n = edges.length;
        int[] visitTime = new int[n];
        int clock = 1;
        int best = -1;
        for (int start = 0; start < n; start++) {
            if (visitTime[start] != 0) {
                continue;
            }
            int walkStart = clock;
            int node = start;
            while (node != -1 && visitTime[node] == 0) {
                visitTime[node] = clock++;
                node = edges[node];
            }
            if (node != -1 && visitTime[node] >= walkStart) {
                best = Math.max(best, clock - visitTime[node]);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[3, 3, 4, 2, 3] -> " + longestCycle(new int[]{3, 3, 4, 2, 3}));
        System.out.println("[2, -1, 3, 1] -> " + longestCycle(new int[]{2, -1, 3, 1}));
    }
}
