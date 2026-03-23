import java.util.*;

/*
Problem: Number of Provinces using DFS
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
You are given an adjacency matrix where isConnected[i][j] = 1 means city i and city j are directly connected.
    A province is a group of directly or indirectly connected cities.
    Return the number of provinces.

    This is connected-components counting on a graph.

Why this pattern fits:
Every unvisited city starts a DFS that marks its entire connected component.
    The number of such DFS launches is the number of provinces.

Approach:
Scan all cities.
    When a city has not been visited, increment the province count and DFS through all reachable cities.

Worked Example:
Example:
    isConnected = [[1,1,0],[1,1,0],[0,0,1]]
    The answer is 2.

Time Complexity: O(n^2)
Space Complexity: O(n)
*/

public class NumberOfProvincesDFSGraph {
    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for (int city = 0; city < n; city++) {
            if (!visited[city]) {
                provinces++;
                dfs(city, isConnected, visited);
            }
        }

        return provinces;
    }

    private static void dfs(int city, int[][] graph, boolean[] visited) {
        visited[city] = true;
        for (int next = 0; next < graph.length; next++) {
            if (graph[city][next] == 1 && !visited[next]) {
                dfs(next, graph, visited);
            }
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
            {1,1,0},
            {1,1,0},
            {0,0,1}
        };
        System.out.println("Provinces: " + findCircleNum(graph));
    }
}
