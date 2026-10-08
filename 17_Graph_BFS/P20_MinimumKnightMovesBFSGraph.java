import java.util.*;

/*
Problem: Minimum Knight Moves
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
A knight stands on square (0, 0) of an infinite chessboard.
    In one move a knight travels two squares in one direction and one square in a perpendicular direction.
    Return the minimum number of moves needed to reach the square (x, y). An answer always exists.

Why this pattern fits:
Squares are nodes, knight moves are edges, and every move costs one, so the fewest moves is a BFS distance.
    Because the board is infinite, the search needs bounds to stay small.

Approach:
The board is symmetric, so reaching (x, y) takes as many moves as reaching (|x|, |y|).
    Run BFS from (0, 0) toward the target in the first quadrant.
    Restrict the search to squares with coordinates between -2 and target + 2:
    an optimal route never needs to wander farther than that.
    Offset the coordinates by 2 so they can be used as array indices for the visited table.

Worked Example:
Example:
    Target (2, 1): one move reaches it directly.
    Target (5, 5): (0,0) -> (2,1) -> (4,2) -> (3,4) -> (5,5), which is 4 moves.

Time Complexity: O(|x| * |y|)
Space Complexity: O(|x| * |y|)
*/

public class P20_MinimumKnightMovesBFSGraph {
    private static final int[][] MOVES = {
        {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}
    };

    public static int minKnightMoves(int x, int y) {
        int targetX = Math.abs(x);
        int targetY = Math.abs(y);
        if (targetX == 0 && targetY == 0) {
            return 0;
        }

        int offset = 2;
        boolean[][] visited = new boolean[targetX + 2 * offset + 1][targetY + 2 * offset + 1];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        visited[offset][offset] = true;
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            moves++;

            for (int i = 0; i < size; i++) {
                int[] square = queue.poll();
                for (int[] move : MOVES) {
                    int nx = square[0] + move[0];
                    int ny = square[1] + move[1];

                    if (nx == targetX && ny == targetY) {
                        return moves;
                    }
                    if (nx < -offset || ny < -offset || nx > targetX + offset || ny > targetY + offset) {
                        continue;
                    }
                    if (visited[nx + offset][ny + offset]) {
                        continue;
                    }
                    visited[nx + offset][ny + offset] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return -1;
    }

    private static void runDemo(int x, int y) {
        System.out.println("Target (" + x + ", " + y + ") -> " + minKnightMoves(x, y) + " moves");
    }

    public static void main(String[] args) {
        runDemo(2, 1);
        runDemo(5, 5);
        runDemo(1, 1);
        runDemo(-3, 0);
    }
}
