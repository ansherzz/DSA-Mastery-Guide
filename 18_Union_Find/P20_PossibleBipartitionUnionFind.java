import java.util.*;

/*
Problem: Possible Bipartition
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
We want to split a group of n people (labeled from 1 to n) into two groups of any size.
    Each person may dislike some other people, and they should not go into the same group.
    Given the integer n and the array dislikes where dislikes[i] = [a, b] indicates that a and b dislike each other,
    return true if it is possible to split everyone into two groups in this way.

Why this pattern fits:
With only two groups, "not in my group" forces "in the other group".
    So everyone a person dislikes must be together in the opposite group.
    That turns dislike edges into "must be together" constraints, which union-find tracks.

Approach:
Build the dislike adjacency list.
    For each person, union all of the people they dislike with each other (it is enough to union each with the first one).
    Then check: if the person ends up in the same set as someone they dislike, there is a contradiction.
    If no contradiction is found for anyone, a valid split exists.

Worked Example:
Example:
    n = 4, dislikes = [[1, 2], [1, 3], [2, 4]]
    Person 1 dislikes 2 and 3, so 2 and 3 are together. Person 2 dislikes 1 and 4, so 1 and 4 are together.
    Groups {1, 4} and {2, 3} work, so the answer is true.

Time Complexity: O((V + E) * alpha(V)), effectively linear
Space Complexity: O(V + E)
*/

public class P20_PossibleBipartitionUnionFind {
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

    public static boolean possibleBipartition(int n, int[][] dislikes) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] pair : dislikes) {
            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);
        }

        DSU dsu = new DSU(n + 1);
        for (int person = 1; person <= n; person++) {
            List<Integer> disliked = graph.get(person);
            for (int other : disliked) {
                if (dsu.find(person) == dsu.find(other)) {
                    return false;
                }
                dsu.union(disliked.get(0), other);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(possibleBipartition(4, new int[][]{{1, 2}, {1, 3}, {2, 4}}));
        System.out.println(possibleBipartition(3, new int[][]{{1, 2}, {1, 3}, {2, 3}}));
        System.out.println(possibleBipartition(5, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}}));
    }
}
