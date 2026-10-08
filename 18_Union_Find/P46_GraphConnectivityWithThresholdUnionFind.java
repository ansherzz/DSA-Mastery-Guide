import java.util.*;

/*
Problem: Graph Connectivity With Threshold
Pattern: 18. Union Find
Difficulty: Hard

Interview Prompt:
Cities are labeled 1..n. Two cities are directly connected if they share a common divisor strictly greater than threshold.
For each query [a, b], return whether the two cities are connected directly or indirectly.

Why this pattern fits:
Checking gcd for every pair is quadratic. If z > threshold divides both x and y, then x and y are both multiples of z,
so uniting each z with its multiples creates every required connection in harmonic-series time.

Approach:
For z from threshold + 1 to n, union z with 2z, 3z, ... up to n. Each query compares two roots.

Worked Example:
n = 6, threshold = 2 -> only divisor 3 matters: {3, 6} are connected. Queries (1,4), (2,5), (3,6) give false, false, true.

Time Complexity: O(n log n * alpha(n) + q)
Space Complexity: O(n)
*/

public class P46_GraphConnectivityWithThresholdUnionFind {
    static class UnionFind {
        final int[] parent;
        final int[] size;
        int components;

        UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            components = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
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

    public static List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        UnionFind sets = new UnionFind(n + 1);
        for (int divisor = threshold + 1; divisor <= n; divisor++) {
            for (int multiple = 2 * divisor; multiple <= n; multiple += divisor) {
                sets.union(divisor, multiple);
            }
        }
        List<Boolean> answer = new ArrayList<>();
        for (int[] query : queries) {
            answer.add(sets.find(query[0]) == sets.find(query[1]));
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println("n = 6, threshold 2 -> " + areConnected(6, 2, new int[][]{{1, 4}, {2, 5}, {3, 6}}));
        System.out.println("n = 6, threshold 0 -> " + areConnected(6, 0, new int[][]{{4, 5}, {3, 4}, {3, 2}, {2, 6}, {1, 3}}));
        System.out.println("n = 5, threshold 1 -> " + areConnected(5, 1, new int[][]{{4, 5}, {4, 5}, {3, 2}, {2, 3}, {3, 4}}));
    }
}
