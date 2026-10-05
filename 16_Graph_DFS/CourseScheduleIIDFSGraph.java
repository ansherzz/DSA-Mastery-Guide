import java.util.*;

/*
Problem: Course Schedule II (Topological Order with DFS)
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
There are numCourses courses labeled 0 to numCourses - 1.
    prerequisites[i] = [a, b] means you must take course b before course a.
    Return an order in which you can take all the courses. If several orders are valid, return any of them.
    If it is impossible to finish all courses, return an empty array.

Why this pattern fits:
The prerequisites form a directed graph, and a valid order is a topological sort.
    DFS produces one through finishing times: a course is finished only after everything that depends on it,
    so reversing the finish order gives a valid schedule. The same DFS detects cycles with three states.

Approach:
Build edges from prerequisite to dependent course.
    Use states: 0 = unvisited, 1 = on the current DFS path, 2 = finished.
    Reaching a node in state 1 means a cycle, so no order exists.
    When a node finishes, write it into the result array from the back toward the front.

Worked Example:
Example:
    numCourses = 4, prerequisites = [[1, 0], [2, 0], [3, 1], [3, 2]]
    Course 0 unlocks 1 and 2, and both are needed for 3.
    One valid order is [0, 2, 1, 3]; [0, 1, 2, 3] is equally valid.

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class CourseScheduleIIDFSGraph {
    private static int writeIndex;

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
        }

        int[] state = new int[numCourses];
        int[] order = new int[numCourses];
        writeIndex = numCourses - 1;

        for (int course = 0; course < numCourses; course++) {
            if (state[course] == 0 && hasCycle(course, graph, state, order)) {
                return new int[0];
            }
        }
        return order;
    }

    private static boolean hasCycle(int node, List<List<Integer>> graph, int[] state, int[] order) {
        state[node] = 1;

        for (int next : graph.get(node)) {
            if (state[next] == 1) {
                return true;
            }
            if (state[next] == 0 && hasCycle(next, graph, state, order)) {
                return true;
            }
        }

        state[node] = 2;
        order[writeIndex--] = node;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findOrder(2, new int[][]{{1, 0}})));
        System.out.println(Arrays.toString(findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
        System.out.println(Arrays.toString(findOrder(2, new int[][]{{1, 0}, {0, 1}})));
    }
}
