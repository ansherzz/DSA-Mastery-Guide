import java.util.*;

/*
Problem: Perfect Squares
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Given a positive integer n, return the least number of perfect square numbers (1, 4, 9, 16, ...) that sum to n.

Why this pattern fits:
This does not look like a graph problem, but it can be modeled as one.
    Treat every number from 0 to n as a node, and connect x to x - s for every perfect square s <= x.
    The fewest squares that sum to n is then the shortest path from n to 0, and shortest paths in
    an unweighted graph are found with BFS.

Approach:
Start BFS from n with step count 0.
    At each level, for every number in the queue, subtract each perfect square that fits.
    If the result is 0, return the current level + 1. Otherwise enqueue it if it has not been seen before.

Worked Example:
Example:
    n = 12
    Level 1: 12 - 1 = 11, 12 - 4 = 8, 12 - 9 = 3
    Level 2: from 8 we reach 4 (8 - 4)
    Level 3: from 4 we reach 0 (4 - 4)
    Answer = 3, because 12 = 4 + 4 + 4.

Time Complexity: O(n * sqrt(n))
Space Complexity: O(n)
*/

public class PerfectSquaresBFS {
    public static int numSquares(int n) {
        boolean[] seen = new boolean[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(n);
        seen[n] = true;
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;

            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                for (int root = 1; root * root <= current; root++) {
                    int next = current - root * root;
                    if (next == 0) {
                        return steps;
                    }
                    if (!seen[next]) {
                        seen[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }
        return steps;
    }

    private static void runDemo(int n) {
        System.out.println("n = " + n + " -> " + numSquares(n) + " squares");
    }

    public static void main(String[] args) {
        runDemo(12);
        runDemo(13);
        runDemo(1);
        runDemo(7);
    }
}
