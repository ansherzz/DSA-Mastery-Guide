import java.util.*;

/*
Problem: Minimum Number of Operations to Make X and Y Equal
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
In one operation you may: divide x by 11 if it is a multiple of 11, divide x by 5 if it is a multiple of 5,
decrement x by 1, or increment x by 1. Return the minimum number of operations to make x equal to y.

Why this pattern fits:
Each integer is a state and each operation is an edge of cost 1, so the minimum number of operations
is a BFS distance. The state space is bounded because going far above x is never useful.

Approach:
If y >= x the only useful move is +1, so the answer is y - x.
Otherwise BFS from x over values in [0, x + 11], trying all valid operations and marking visited values.

Worked Example:
x = 26, y = 1 -> 26 - 1 = 25, 25 / 5 = 5, 5 / 5 = 1. Answer = 3.

Time Complexity: O(x)
Space Complexity: O(x)
*/

public class P28_MinimumOperationsToMakeXAndYEqualBFSGraph {
    public static int minimumOperationsToMakeEqual(int x, int y) {
        if (y >= x) {
            return y - x;
        }
        int limit = x + 11;
        boolean[] visited = new boolean[limit + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(x);
        visited[x] = true;
        int steps = 0;
        while (!queue.isEmpty()) {
            for (int size = queue.size(); size > 0; size--) {
                int value = queue.poll();
                if (value == y) {
                    return steps;
                }
                List<Integer> next = new ArrayList<>(Arrays.asList(value - 1, value + 1));
                if (value % 11 == 0) {
                    next.add(value / 11);
                }
                if (value % 5 == 0) {
                    next.add(value / 5);
                }
                for (int candidate : next) {
                    if (candidate >= 0 && candidate <= limit && !visited[candidate]) {
                        visited[candidate] = true;
                        queue.add(candidate);
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("26 to 1 -> " + minimumOperationsToMakeEqual(26, 1));
        System.out.println("54 to 2 -> " + minimumOperationsToMakeEqual(54, 2));
        System.out.println("25 to 30 -> " + minimumOperationsToMakeEqual(25, 30));
    }
}
