import java.util.*;

/*
Problem: Parallel Courses
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
You are given an integer n, the number of courses labeled from 1 to n,
    and an array relations where relations[i] = [prev, next] means course prev has to be taken before course next.
    In one semester, you can take any number of courses as long as you have taken all their prerequisites
    in earlier semesters. Return the minimum number of semesters needed to take all courses,
    or -1 if there is no way to take all the courses.

Why this pattern fits:
Taking every available course each semester is optimal, and "available" means in-degree zero.
    That is Kahn's topological sort processed level by level: each BFS level is one semester.

Approach:
Compute the in-degree of every course and enqueue all courses with in-degree 0.
    Process the queue one level at a time. For each course taken, decrease the in-degree of the courses that depend on it,
    and enqueue any that reach 0 for the next semester.
    Count the levels. If fewer than n courses were taken, a cycle exists, so return -1.

Worked Example:
Example:
    n = 3, relations = [[1, 3], [2, 3]]
    Semester 1: courses 1 and 2. Semester 2: course 3. Answer = 2.

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class P24_ParallelCoursesBFSGraph {
    public static int minimumSemesters(int n, int[][] relations) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[n + 1];
        for (int[] relation : relations) {
            graph.get(relation[0]).add(relation[1]);
            indegree[relation[1]]++;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int course = 1; course <= n; course++) {
            if (indegree[course] == 0) {
                queue.offer(course);
            }
        }

        int semesters = 0;
        int taken = 0;
        while (!queue.isEmpty()) {
            semesters++;
            for (int size = queue.size(); size > 0; size--) {
                int course = queue.poll();
                taken++;
                for (int next : graph.get(course)) {
                    if (--indegree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
        }
        return taken == n ? semesters : -1;
    }

    public static void main(String[] args) {
        System.out.println(minimumSemesters(3, new int[][]{{1, 3}, {2, 3}}));
        System.out.println(minimumSemesters(3, new int[][]{{1, 2}, {2, 3}, {3, 1}}));
        System.out.println(minimumSemesters(4, new int[][]{}));
    }
}
