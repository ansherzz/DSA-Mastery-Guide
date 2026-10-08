import java.util.*;

/*
Problem: The Maze
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
There is a ball in a maze with empty spaces (0) and walls (1).
    The ball can roll up, down, left, or right, but it does not stop until it hits a wall.
    When the ball stops, it can choose the next direction.
    Given the maze, the ball's start position, and a destination, return true if the ball can stop at the destination.
    The borders of the maze are all walls.

Why this pattern fits:
The graph is not the grid of cells but the set of stopping positions.
    Each stopping position has up to four neighbors: the positions where a roll in each direction ends.
    Once the neighbors are defined that way, it is ordinary BFS reachability.

Approach:
BFS over stopping positions with a visited array.
    From a position, for each direction, keep moving while the next cell is inside the maze and is not a wall.
    The cell where the roll ends is the neighbor.
    Passing over the destination does not count; the ball must come to rest there, so only polled positions are tested.

Worked Example:
Example:
    maze = [[0, 0, 1, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 1, 0],
            [1, 1, 0, 1, 1],
            [0, 0, 0, 0, 0]], start = (0, 4)
    destination (4, 4): left, down, left, down, right, down, right reaches it, so true.
    destination (3, 2): the ball always rolls past this cell and never stops on it, so false.

Time Complexity: O(m * n * (m + n))
Space Complexity: O(m * n)
*/

public class TheMazeBFSGraph {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length;
        int cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            if (cell[0] == destination[0] && cell[1] == destination[1]) {
                return true;
            }

            for (int[] direction : DIRECTIONS) {
                int r = cell[0];
                int c = cell[1];
                while (r + direction[0] >= 0 && r + direction[0] < rows
                    && c + direction[1] >= 0 && c + direction[1] < cols
                    && maze[r + direction[0]][c + direction[1]] == 0) {
                    r += direction[0];
                    c += direction[1];
                }

                if (!visited[r][c]) {
                    visited[r][c] = true;
                    queue.offer(new int[]{r, c});
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] maze = {
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {1, 1, 0, 1, 1},
            {0, 0, 0, 0, 0}};

        System.out.println(hasPath(maze, new int[]{0, 4}, new int[]{4, 4}));
        System.out.println(hasPath(maze, new int[]{0, 4}, new int[]{3, 2}));
    }
}
