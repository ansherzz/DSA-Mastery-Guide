import java.util.*;

/*
Problem: Course Schedule (Kahn's Algorithm)
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
There are numCourses courses and prerequisites [a, b] meaning b must be taken before a.
Return true if all courses can be finished.

Why this pattern fits:
Finishing all courses is possible exactly when the prerequisite graph has no cycle.
Kahn's algorithm detects a cycle without recursion: nodes on a cycle never reach in-degree zero.

Approach:
Compute in-degrees. Queue all courses with in-degree 0. Pop a course, count it,
and decrement the in-degree of its dependents, queueing those that reach 0.
All courses can be finished if the count equals numCourses.

Worked Example:
2 courses with [1, 0]: take 0 then 1 (true). With [1, 0] and [0, 1] neither can start (false).

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class P10_CourseScheduleKahnBFSGraph {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];
        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]);
            indegree[p[0]]++;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int course = 0; course < numCourses; course++) {
            if (indegree[course] == 0) {
                queue.add(course);
            }
        }
        int taken = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            taken++;
            for (int next : graph.get(course)) {
                if (--indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return taken == numCourses;
    }

    public static void main(String[] args) {
        System.out.println("[[1, 0]] -> " + canFinish(2, new int[][]{{1, 0}}));
        System.out.println("[[1, 0], [0, 1]] -> " + canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }
}
