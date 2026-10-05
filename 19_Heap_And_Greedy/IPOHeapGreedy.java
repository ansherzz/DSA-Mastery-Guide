import java.util.*;

/*
Problem: IPO
Pattern: 19. Heap & Greedy
Difficulty: Hard

Interview Prompt:
A company wants to maximize its capital before its IPO, but it can finish at most k distinct projects.
    Project i yields a pure profit profits[i] and requires a minimum capital capital[i] to start.
    The company starts with capital w. Finishing a project adds its profit to the company's capital.

    Return the maximum capital after finishing at most k projects.

Why this pattern fits:
Starting a project does not consume capital; it only requires having enough.
    So at every step the best move is simply the most profitable project that is currently affordable.
    The set of affordable projects only grows as capital grows, which suits a max-heap fed in order of required capital.

Approach:
Sort the projects by required capital.
    Repeat up to k times: move every project whose requirement is at most the current capital
    into a max-heap ordered by profit, then take the most profitable one and add its profit to the capital.
    If the heap is empty, nothing more can be started, so stop early.

Worked Example:
Example:
    k = 2, w = 0, profits = [1, 2, 3], capital = [0, 1, 1]
    With capital 0 only project 0 is affordable: capital becomes 1.
    Now projects 1 and 2 are affordable; take the profit of 3: capital becomes 4.
    Answer = 4.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class IPOHeapGreedy {
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }
        Arrays.sort(projects, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> affordableProfits = new PriorityQueue<>(Collections.reverseOrder());
        int next = 0;
        int current = w;

        for (int round = 0; round < k; round++) {
            while (next < n && projects[next][0] <= current) {
                affordableProfits.offer(projects[next][1]);
                next++;
            }
            if (affordableProfits.isEmpty()) {
                break;
            }
            current += affordableProfits.poll();
        }
        return current;
    }

    public static void main(String[] args) {
        System.out.println(findMaximizedCapital(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1}));
        System.out.println(findMaximizedCapital(3, 0, new int[]{1, 2, 3}, new int[]{0, 1, 2}));
        System.out.println(findMaximizedCapital(1, 0, new int[]{5, 6}, new int[]{3, 4}));
    }
}
