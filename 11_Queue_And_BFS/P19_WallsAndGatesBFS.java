import java.util.*;

/*
Problem: Walls and Gates
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
You are given an m x n grid where each cell is one of:
    -1          a wall or obstacle
     0          a gate
     2147483647 an empty room (INF)

    Fill each empty room with the distance to its nearest gate.
    If a room cannot reach any gate, leave it as INF. Movement is allowed in four directions.

Why this pattern fits:
Running a separate BFS from every room would repeat a lot of work.
    Multi-source BFS flips the question: start from all gates at once and expand outward.
    The first time a room is reached, it is reached from its nearest gate.

Approach:
Put every gate into the queue at distance 0.
    Process the queue normally. For each polled cell, look at its four neighbors.
    A neighbor that is still INF has not been reached yet, so set it to the current distance + 1 and enqueue it.
    Walls and already-filled rooms are skipped automatically because they are not INF.

Worked Example:
Example:
    INF  -1    0  INF          3  -1   0   1
    INF INF  INF   -1    ->    2   2   1  -1
    INF  -1  INF   -1          1  -1   2  -1
      0  -1  INF  INF          0  -1   3   4

Time Complexity: O(m * n)
Space Complexity: O(m * n)
*/

public class P19_WallsAndGatesBFS {
    private static final int INF = Integer.MAX_VALUE;
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void wallsAndGates(int[][] rooms) {
        Queue<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < rooms.length; r++) {
            for (int c = 0; c < rooms[0].length; c++) {
                if (rooms[r][c] == 0) {
                    queue.offer(new int[]{r, c});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] direction : DIRECTIONS) {
                int row = cell[0] + direction[0];
                int col = cell[1] + direction[1];

                if (row < 0 || col < 0 || row >= rooms.length || col >= rooms[0].length) {
                    continue;
                }
                if (rooms[row][col] != INF) {
                    continue;
                }
                rooms[row][col] = rooms[cell[0]][cell[1]] + 1;
                queue.offer(new int[]{row, col});
            }
        }
    }

    public static void main(String[] args) {
        int[][] rooms = {
            {INF, -1, 0, INF},
            {INF, INF, INF, -1},
            {INF, -1, INF, -1},
            {0, -1, INF, INF}
        };

        wallsAndGates(rooms);
        for (int[] row : rooms) {
            System.out.println(Arrays.toString(row));
        }
    }
}
