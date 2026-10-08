import java.util.*;

/*
Problem: Course Schedule III
Pattern: 19. Heap & Greedy
Difficulty: Hard

Interview Prompt:
There are n different online courses, where courses[i] = [duration, lastDay] means the i-th course
    must be taken continuously for duration days and must be finished on or before lastDay.
    You start on day 1 and cannot take two or more courses simultaneously.
    Return the maximum number of courses that you can take.

Why this pattern fits:
Two greedy ideas combine. First, courses should be considered in order of deadline,
    because any feasible set can be reordered by deadline and stay feasible.
    Second, when a course does not fit, the best repair is to drop the longest course taken so far,
    which frees the most time without reducing the count. A max-heap provides that longest course.

Approach:
Sort the courses by lastDay. Keep the total time used and a max-heap of the durations of the chosen courses.
    For each course: add it (push its duration and add to the total).
    If the total now exceeds the course's lastDay, pop the longest duration from the heap and subtract it.
    That removes either the new course or a longer earlier one, and the schedule becomes feasible again.
    The heap size at the end is the answer.

Worked Example:
Example:
    courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
    Sorted by deadline: [100, 200], [1000, 1250], [200, 1300], [2000, 3200].
    Take 100 (total 100), 1000 (1100), 200 (1300). Adding 2000 gives 3300 > 3200, so drop the longest (2000).
    Answer = 3.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P46_CourseScheduleIIIHeapGreedy {
    public static int scheduleCourse(int[][] courses) {
        int[][] sorted = courses.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[1], b[1]));

        PriorityQueue<Integer> taken = new PriorityQueue<>(Collections.reverseOrder());
        int total = 0;
        for (int[] course : sorted) {
            taken.offer(course[0]);
            total += course[0];
            if (total > course[1]) {
                total -= taken.poll();
            }
        }
        return taken.size();
    }

    public static void main(String[] args) {
        System.out.println(scheduleCourse(new int[][]{{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}}));
        System.out.println(scheduleCourse(new int[][]{{1, 2}}));
        System.out.println(scheduleCourse(new int[][]{{3, 2}, {4, 3}}));
    }
}
