import java.util.*;

/*
Problem: Parallel Courses III
Pattern: 16. Graph DFS
Difficulty: Hard

Interview Prompt:
There are n courses (1-indexed), prerequisite relations [prev, next], and time[i] months for course i + 1.
Any number of courses can be taken at once as long as their prerequisites are done.
Return the minimum number of months to finish all courses.

Why this pattern fits:
With unlimited parallelism, the answer is the longest weighted path in the prerequisite DAG.
The longest path ending at a node depends only on its predecessors, so DFS with memoization computes each node once.

Approach:
finish(course) = time[course] + max(finish(prerequisite)) over its prerequisites (0 if none), cached.
The answer is the maximum finish time over all courses.

Worked Example:
n = 3, relations 1 -> 3 and 2 -> 3, time = [3, 2, 5] -> course 3 starts at month 3 and ends at month 8.

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class P47_ParallelCoursesIIIDFSGraph {
    private static int finish(List<List<Integer>> prerequisites, int course, int[] time, int[] memo) {
        if (memo[course] != 0) {
            return memo[course];
        }
        int latest = 0;
        for (int before : prerequisites.get(course)) {
            latest = Math.max(latest, finish(prerequisites, before, time, memo));
        }
        memo[course] = latest + time[course];
        return memo[course];
    }

    public static int minimumTime(int n, int[][] relations, int[] time) {
        List<List<Integer>> prerequisites = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            prerequisites.add(new ArrayList<>());
        }
        for (int[] relation : relations) {
            prerequisites.get(relation[1] - 1).add(relation[0] - 1);
        }
        int[] memo = new int[n];
        int best = 0;
        for (int course = 0; course < n; course++) {
            best = Math.max(best, finish(prerequisites, course, time, memo));
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("n = 3 -> " + minimumTime(3, new int[][]{{1, 3}, {2, 3}}, new int[]{3, 2, 5}));
        System.out.println("n = 5 -> " + minimumTime(5,
                new int[][]{{1, 5}, {2, 5}, {3, 5}, {3, 4}, {4, 5}}, new int[]{1, 2, 3, 4, 5}));
    }
}
