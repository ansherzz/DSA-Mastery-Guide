import java.util.*;

/*
Problem: Jump Game III
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Given an array of non-negative integers arr, you are initially positioned at index start.
    When you are at index i, you can jump to i + arr[i] or i - arr[i].
    Return true if you can reach any index with value 0. You cannot jump outside of the array at any time.

Why this pattern fits:
Each index is a node with at most two outgoing edges, and the question is plain reachability.
    BFS with a visited array explores every reachable index exactly once and cannot loop forever.

Approach:
Start a queue with the start index and mark it visited.
    Poll an index; if its value is 0, return true.
    Otherwise compute the two jump targets and enqueue each one that is inside the array and not yet visited.
    If the queue empties, no zero is reachable.

Worked Example:
Example:
    arr = [4, 2, 3, 0, 3, 1, 2], start = 5
    5 -> 4 -> 1 -> 3, and arr[3] == 0. Answer: true.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class JumpGameIIIBFS {
    public static boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (arr[index] == 0) {
                return true;
            }

            for (int next : new int[]{index + arr[index], index - arr[index]}) {
                if (next >= 0 && next < arr.length && !visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 5));
        System.out.println(canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 0));
        System.out.println(canReach(new int[]{3, 0, 2, 1, 2}, 2));
    }
}
