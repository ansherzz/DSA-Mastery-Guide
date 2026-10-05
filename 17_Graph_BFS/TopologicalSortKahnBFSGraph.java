import java.util.*;

/*
Problem: Course Schedule II using Kahn's Algorithm
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
There are numCourses courses labeled 0 to numCourses - 1.
    prerequisites[i] = [a, b] means course b must be taken before course a.
    Return any order in which all courses can be taken, or an empty array if it is impossible.

    The DFS version is in folder 16. Solve it here with BFS.

Why this pattern fits:
A course can be taken once all of its prerequisites are done, that is, when its in-degree drops to zero.
    Kahn's algorithm processes such "ready" nodes from a queue, which is BFS over the dependency graph.
    If some nodes are never ready, they are stuck in a cycle.

Approach:
Build the graph and count the in-degree of every course.
    Put all courses with in-degree 0 into a queue.
    Repeatedly take a course from the queue, append it to the order,
    and decrease the in-degree of every course that depends on it, enqueueing those that reach 0.
    If the order contains every course, return it; otherwise a cycle exists.

Worked Example:
Example:
    numCourses = 4, prerequisites = [[1, 0], [2, 0], [3, 1], [3, 2]]
    Only course 0 starts with in-degree 0. Taking it frees 1 and 2, and taking both frees 3.
    Order: [0, 1, 2, 3]

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class TopologicalSortKahnBFSGraph {
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
        }

        Queue<Integer> ready = new ArrayDeque<>();
        for (int course = 0; course < numCourses; course++) {
            if (indegree[course] == 0) {
                ready.offer(course);
            }
        }

        int[] order = new int[numCourses];
        int taken = 0;
        while (!ready.isEmpty()) {
            int course = ready.poll();
            order[taken++] = course;

            for (int next : graph.get(course)) {
                if (--indegree[next] == 0) {
                    ready.offer(next);
                }
            }
        }
        return taken == numCourses ? order : new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findOrder(2, new int[][]{{1, 0}})));
        System.out.println(Arrays.toString(findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
        System.out.println(Arrays.toString(findOrder(3, new int[][]{{0, 1}, {1, 2}, {2, 0}})));
    }
}
