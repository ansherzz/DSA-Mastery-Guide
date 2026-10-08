import java.util.*;

/*
Problem: Minimum Operations to Convert Number
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Starting from x = start, one operation replaces x with x + nums[i], x - nums[i], or x ^ nums[i].
Operations may only be applied while 0 <= x <= 1000, but the result may leave that range.
Return the minimum number of operations to reach goal, or -1.

Why this pattern fits:
"Minimum number of operations" over a small set of reachable values is a shortest path
in an unweighted graph that is never built explicitly: BFS generates neighbors on the fly.

Approach:
BFS from start with a visited array for values 0..1000. For each value generate all three results for each number.
A result equal to goal ends the search; in-range unvisited results are enqueued; out-of-range ones are dropped.

Worked Example:
nums = [2, 4, 12], start = 2, goal = 12 -> 2 + 12 = 14, then 14 - 2 = 12. Answer = 2.

Time Complexity: O(1001 * n)
Space Complexity: O(1001)
*/

public class P34_MinimumOperationsToConvertNumberBFS {
    public static int minimumOperations(int[] nums, int start, int goal) {
        boolean[] visited = new boolean[1001];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        int steps = 0;
        while (!queue.isEmpty()) {
            steps++;
            for (int size = queue.size(); size > 0; size--) {
                int x = queue.poll();
                for (int num : nums) {
                    for (int next : new int[]{x + num, x - num, x ^ num}) {
                        if (next == goal) {
                            return steps;
                        }
                        if (next >= 0 && next <= 1000 && !visited[next]) {
                            visited[next] = true;
                            queue.add(next);
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("[2, 4, 12], 2 to 12 -> " + minimumOperations(new int[]{2, 4, 12}, 2, 12));
        System.out.println("[3, 5, 7], 0 to -4 -> " + minimumOperations(new int[]{3, 5, 7}, 0, -4));
        System.out.println("[2, 8, 16], 0 to 1 -> " + minimumOperations(new int[]{2, 8, 16}, 0, 1));
    }
}
