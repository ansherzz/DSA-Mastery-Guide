import java.util.*;

/*
Problem: Task Scheduler
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
You are given tasks represented by uppercase letters and a non-negative cooldown n.
    The same task must be separated by at least n intervals.
    Return the least number of intervals needed to finish all tasks.

    This problem blends greedy frequency reasoning with a max-heap simulation.

Why this pattern fits:
The most frequent tasks create the scheduling pressure.
    A max-heap lets you repeatedly choose the currently most urgent remaining task.

Approach:
Count task frequencies.
    Use a max-heap and process work in cycles of length n + 1.
    In each cycle, execute up to n + 1 different tasks, decrement their counts, and push unfinished tasks back.

Worked Example:
Example:
    tasks = ['A','A','A','B','B','B'], n = 2
    The least number of intervals is 8.

Time Complexity: O(T log 26), effectively O(T)
Space Complexity: O(26)
*/

public class P14_TaskSchedulerHeapGreedy {
    public static int leastInterval(char[] tasks, int n) {
        int[] frequency = new int[26];
        for (char task : tasks) {
            frequency[task - 'A']++;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int count : frequency) {
            if (count > 0) {
                maxHeap.offer(count);
            }
        }

        int time = 0;
        while (!maxHeap.isEmpty()) {
            List<Integer> usedThisCycle = new ArrayList<>();
            int slots = n + 1;

            while (slots > 0 && !maxHeap.isEmpty()) {
                int count = maxHeap.poll();
                if (count - 1 > 0) {
                    usedThisCycle.add(count - 1);
                }
                time++;
                slots--;
            }

            for (int remaining : usedThisCycle) {
                maxHeap.offer(remaining);
            }

            if (maxHeap.isEmpty()) {
                break;
            }

            time += slots;
        }

        return time;
    }

    public static void main(String[] args) {
        System.out.println(leastInterval(new char[]{'A','A','A','B','B','B'}, 2));
        System.out.println(leastInterval(new char[]{'A','A','A','B','B','B'}, 0));
    }
}
