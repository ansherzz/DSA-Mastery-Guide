import java.util.*;

/*
Problem: The Earliest Moment When Everyone Become Friends
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
There are n people in a social group labeled 0 to n - 1.
    You are given logs where logs[i] = [timestamp, x, y] means x and y became friends at that time.
    Friendship is symmetric, and a person is acquainted with anyone connected to them through a chain of friends.

    Return the earliest time at which every person is acquainted with every other person,
    or -1 if that never happens.

Why this pattern fits:
The question is "when does the graph first become a single connected component?"
    as edges are added over time. Union Find supports exactly this: add edges in time order
    and watch the component count fall to one.

Approach:
Sort the logs by timestamp. Start with n components.
    Process each log in order, unioning the two people. Every successful union reduces the count.
    Return the timestamp of the union that brings the count down to 1.

Worked Example:
Example:
    n = 4, logs = [[0, 2, 0], [1, 0, 1], [3, 0, 3], [4, 1, 2], [7, 3, 1]]
    Time 0 joins {0, 2}. Time 1 joins {0, 1, 2}. Time 3 joins {0, 1, 2, 3}.
    Everyone is connected at time 3.

Time Complexity: O(m log m) for sorting the m logs
Space Complexity: O(n)
*/

public class EarliestMomentEveryoneBecomesFriendsUnionFind {
    static class DSU {
        int[] parent;
        int[] size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return false;
            }

            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }

            parent[rootB] = rootA;
            size[rootA] += size[rootB];
            return true;
        }
    }

    public static int earliestAcq(int[][] logs, int n) {
        int[][] sorted = logs.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));

        DSU dsu = new DSU(n);
        int components = n;

        for (int[] log : sorted) {
            if (dsu.union(log[1], log[2])) {
                components--;
                if (components == 1) {
                    return log[0];
                }
            }
        }
        return n == 1 ? 0 : -1;
    }

    public static void main(String[] args) {
        int[][] logs = {
            {20190101, 0, 1}, {20190104, 3, 4}, {20190107, 2, 3}, {20190211, 1, 5},
            {20190224, 2, 4}, {20190301, 0, 3}, {20190312, 1, 2}, {20190322, 4, 5}
        };
        System.out.println(earliestAcq(logs, 6));
        System.out.println(earliestAcq(new int[][]{{0, 2, 0}, {1, 0, 1}, {3, 0, 3}, {4, 1, 2}, {7, 3, 1}}, 4));
        System.out.println(earliestAcq(new int[][]{{5, 0, 1}}, 3));
    }
}
