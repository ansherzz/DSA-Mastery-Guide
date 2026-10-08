import java.util.*;

/*
Problem: Course Schedule IV
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
prerequisites[i] = [a, b] means course a must be taken before course b (directly).
For each query [u, v], return whether u is a direct or indirect prerequisite of v.

Why this pattern fits:
Many queries about "can u reach v" call for precomputing the transitive closure.
With at most 100 courses, one DFS per course fills the whole table.

Approach:
Build the directed graph. For each start course, DFS marking reachable[start][node] for every node reached.
Each query is then reachable[u][v].

Worked Example:
Edges 1 -> 2, 1 -> 0, 2 -> 0: course 1 reaches both 0 and 2. Queries [1, 0] and [1, 2] are both true.

Time Complexity: O(V * (V + E) + Q)
Space Complexity: O(V^2)
*/

public class P20_CourseScheduleIVDFSGraph {
    private static void dfs(List<List<Integer>> graph, int start, int node, boolean[][] reachable) {
        for (int next : graph.get(node)) {
            if (!reachable[start][next]) {
                reachable[start][next] = true;
                dfs(graph, start, next, reachable);
            }
        }
    }

    public static List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] p : prerequisites) {
            graph.get(p[0]).add(p[1]);
        }
        boolean[][] reachable = new boolean[numCourses][numCourses];
        for (int course = 0; course < numCourses; course++) {
            dfs(graph, course, course, reachable);
        }
        List<Boolean> answer = new ArrayList<>();
        for (int[] q : queries) {
            answer.add(reachable[q[0]][q[1]]);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println("three courses -> " + checkIfPrerequisite(3,
                new int[][]{{1, 2}, {1, 0}, {2, 0}}, new int[][]{{1, 0}, {1, 2}}));
        System.out.println("two courses -> " + checkIfPrerequisite(2, new int[][]{{1, 0}}, new int[][]{{0, 1}, {1, 0}}));
    }
}
