import java.util.*;

/*
Problem: Minimum Score of a Path Between Two Cities
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
You are given a positive integer n representing n cities numbered from 1 to n,
    and a 2D array roads where roads[i] = [a, b, distance] is a bidirectional road between cities a and b.
    The score of a path between two cities is the minimum distance of a road in this path.
    A path may contain the same road multiple times, and it may visit cities 1 and n multiple times.
    Return the minimum possible score of a path between cities 1 and n. A path between them is guaranteed to exist.

Why this pattern fits:
Because roads and cities may be revisited freely, a path can detour to any road in the same connected component
    and come back. So the answer is simply the smallest road in the component that contains city 1.
    Grouping cities into components is what union-find does.

Approach:
Union the two endpoints of every road.
    Then scan the roads again: any road whose endpoint is in the same component as city 1 can be included in the path,
    so take the minimum distance among those roads.

Worked Example:
Example:
    n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
    All cities are connected. The smallest road is 2-4 with distance 5, and the path 1 -> 2 -> 4 uses it. Answer = 5.

Time Complexity: O((V + E) * alpha(V)), effectively linear
Space Complexity: O(V)
*/

public class P18_MinimumScoreOfPathBetweenTwoCitiesUnionFind {
    static class DSU {
        int[] parent;
        int[] size;
        int components;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            components = n;
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
            components--;
            return true;
        }
    }

    public static int minScore(int n, int[][] roads) {
        DSU dsu = new DSU(n + 1);
        for (int[] road : roads) {
            dsu.union(road[0], road[1]);
        }

        int root = dsu.find(1);
        int best = Integer.MAX_VALUE;
        for (int[] road : roads) {
            if (dsu.find(road[0]) == root) {
                best = Math.min(best, road[2]);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(minScore(4, new int[][]{{1, 2, 9}, {2, 3, 6}, {2, 4, 5}, {1, 4, 7}}));
        System.out.println(minScore(4, new int[][]{{1, 2, 2}, {1, 3, 4}, {3, 4, 7}}));
    }
}
