import java.util.*;

/*
Problem: Course Schedule using DFS Cycle Detection
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
There are numCourses labeled from 0 to numCourses - 1.
    Each prerequisite pair [a, b] means you must finish course b before course a.
    Return true if it is possible to finish all courses.

    This is cycle detection in a directed graph.

Why this pattern fits:
If the prerequisite graph contains a directed cycle, the courses in that cycle depend on each other forever.
    DFS with three-state visitation detects such cycles cleanly.

Approach:
Build an adjacency list from prerequisites.
    Use states: 0 = unvisited, 1 = visiting, 2 = visited.
    If DFS reaches a node currently marked visiting, a cycle exists.

Worked Example:
Example:
    numCourses = 2, prerequisites = [[1,0]]
    The answer is true.
    numCourses = 2, prerequisites = [[1,0],[0,1]]
    The answer is false.

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class CourseScheduleDFSGraph {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
        }

        int[] state = new int[numCourses];
        for (int course = 0; course < numCourses; course++) {
            if (state[course] == 0 && hasCycle(course, graph, state)) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasCycle(int node, List<List<Integer>> graph, int[] state) {
        state[node] = 1;

        for (int next : graph.get(node)) {
            if (state[next] == 1) {
                return true;
            }
            if (state[next] == 0 && hasCycle(next, graph, state)) {
                return true;
            }
        }

        state[node] = 2;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(canFinish(2, new int[][]{{1,0}}));
        System.out.println(canFinish(2, new int[][]{{1,0},{0,1}}));
    }
}
