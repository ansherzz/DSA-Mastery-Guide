import java.util.*;

/*
Problem: Single-Threaded CPU
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
You are given n tasks labeled from 0 to n - 1, where tasks[i] = [enqueueTime, processingTime].
    Task i becomes available at enqueueTime and takes processingTime to finish.
    A single-threaded CPU processes at most one task at a time and behaves as follows:
    - If the CPU is idle and no tasks are available, it stays idle.
    - If the CPU is idle and tasks are available, it picks the one with the shortest processing time,
      breaking ties by the smallest index.
    - Once it starts a task, it processes the entire task without stopping.
    Return the order in which the CPU will process the tasks.

Why this pattern fits:
This is an event simulation with a repeated "pick the best of what is currently available" step.
    The available set grows as time passes and shrinks by one each time the CPU picks, which is a priority queue workload.

Approach:
Sort the task indices by enqueue time. Keep a min-heap of available tasks ordered by (processing time, index).
    Loop until every task is processed:
    - Move every task whose enqueue time is <= the current time into the heap.
    - If the heap is empty, jump the clock forward to the next task's enqueue time.
    - Otherwise pop the best task, append it to the order, and advance the clock by its processing time.

Worked Example:
Example:
    tasks = [[1, 2], [2, 4], [3, 2], [4, 1]]
    Time 1: run task 0 until 3. Tasks 1 and 2 are available; task 2 is shorter, run it until 5.
    Tasks 1 and 3 are available; task 3 is shorter, run it until 6. Then task 1. Order: [0, 2, 3, 1].

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class SingleThreadedCPUHeapGreedy {
    public static int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        Integer[] byEnqueue = new Integer[n];
        for (int i = 0; i < n; i++) {
            byEnqueue[i] = i;
        }
        Arrays.sort(byEnqueue, (a, b) -> Integer.compare(tasks[a][0], tasks[b][0]));

        PriorityQueue<Integer> available = new PriorityQueue<>((a, b) ->
            tasks[a][1] != tasks[b][1] ? Integer.compare(tasks[a][1], tasks[b][1]) : Integer.compare(a, b));

        int[] order = new int[n];
        int done = 0;
        int next = 0;
        long time = 0;

        while (done < n) {
            while (next < n && tasks[byEnqueue[next]][0] <= time) {
                available.offer(byEnqueue[next++]);
            }

            if (available.isEmpty()) {
                time = tasks[byEnqueue[next]][0];
                continue;
            }

            int task = available.poll();
            order[done++] = task;
            time += tasks[task][1];
        }
        return order;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getOrder(new int[][]{{1, 2}, {2, 4}, {3, 2}, {4, 1}})));
        System.out.println(Arrays.toString(getOrder(new int[][]{{7, 10}, {7, 12}, {7, 5}, {7, 4}, {7, 2}})));
    }
}
