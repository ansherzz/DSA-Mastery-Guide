import java.util.*;

/*
Problem: Minimum Jumps to Reach Home
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
A bug starts at 0 and wants to reach x. It can jump forward exactly a or backward exactly b,
but never backward twice in a row, never to a negative position, and never onto a forbidden position.
Return the minimum number of jumps, or -1.

Why this pattern fits:
The same position can be reached in two meaningfully different ways: one where a backward jump is still allowed
and one where it is not. So the BFS state must include that flag, and visited is tracked per state.

Approach:
BFS from (0, forward). From any state jump forward; jump backward only if the last jump was not backward.
Positions beyond 6000 are never needed for the given limits (a, b, x <= 2000).

Worked Example:
forbidden = [14, 4, 18, 1, 15], a = 3, b = 15, x = 9 -> 0 -> 3 -> 6 -> 9. Answer = 3.

Time Complexity: O(limit) states
Space Complexity: O(limit)
*/

public class P45_MinimumJumpsToReachHomeBFS {
    public static int minimumJumps(int[] forbidden, int a, int b, int x) {
        int limit = 6000;
        boolean[][] visited = new boolean[limit + 1][2];
        for (int position : forbidden) {
            visited[position][0] = true;
            visited[position][1] = true;
        }
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;
        int jumps = 0;
        while (!queue.isEmpty()) {
            for (int size = queue.size(); size > 0; size--) {
                int[] state = queue.poll();
                int position = state[0];
                if (position == x) {
                    return jumps;
                }
                int forward = position + a;
                if (forward <= limit && !visited[forward][0]) {
                    visited[forward][0] = true;
                    queue.add(new int[]{forward, 0});
                }
                int backward = position - b;
                if (state[1] == 0 && backward >= 0 && !visited[backward][1]) {
                    visited[backward][1] = true;
                    queue.add(new int[]{backward, 1});
                }
            }
            jumps++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("a = 3, b = 15, x = 9 -> " + minimumJumps(new int[]{14, 4, 18, 1, 15}, 3, 15, 9));
        System.out.println("a = 15, b = 13, x = 11 -> " + minimumJumps(new int[]{8, 3, 16, 6, 12, 20}, 15, 13, 11));
        System.out.println("a = 16, b = 9, x = 7 -> " + minimumJumps(new int[]{1, 6, 2, 14, 5, 17, 4}, 16, 9, 7));
    }
}
