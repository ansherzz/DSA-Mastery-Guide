import java.util.*;

/*
Problem: Nearest Exit from Entrance in Maze
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
You are given a maze as a grid of characters, where '.' is an empty cell and '+' is a wall,
    and the coordinates of the entrance, which is an empty cell.
    In one step you can move up, down, left, or right to an empty cell.

    An exit is any empty cell on the border of the maze. The entrance itself does not count as an exit.
    Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if there is none.

Why this pattern fits:
Every move costs one step, so the grid is an unweighted graph.
    BFS explores cells in order of increasing step count, which makes the first exit it finds the nearest one.

Approach:
Start BFS from the entrance and mark it visited.
    Expand level by level, counting steps.
    When a newly reached empty cell lies on the border, return the current step count.
    If the queue empties without finding a border cell, return -1.

Worked Example:
Example:
    maze = ["++.+",
            "...+",
            "+++."], entrance = (1, 2)
    Moving up one step reaches (0, 2), which is on the border. Answer = 1.

Time Complexity: O(m * n)
Space Complexity: O(m * n)
*/

public class P21_NearestExitFromEntranceInMazeBFS {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length;
        int cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(entrance);
        visited[entrance[0]][entrance[1]] = true;
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;

            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                for (int[] direction : DIRECTIONS) {
                    int row = cell[0] + direction[0];
                    int col = cell[1] + direction[1];

                    if (row < 0 || col < 0 || row >= rows || col >= cols) {
                        continue;
                    }
                    if (maze[row][col] == '+' || visited[row][col]) {
                        continue;
                    }
                    if (row == 0 || col == 0 || row == rows - 1 || col == cols - 1) {
                        return steps;
                    }
                    visited[row][col] = true;
                    queue.offer(new int[]{row, col});
                }
            }
        }
        return -1;
    }

    private static char[][] parse(String... rows) {
        char[][] maze = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            maze[i] = rows[i].toCharArray();
        }
        return maze;
    }

    public static void main(String[] args) {
        System.out.println("Steps: " + nearestExit(parse("++.+", "...+", "+++."), new int[]{1, 2}));
        System.out.println("Steps: " + nearestExit(parse("+++", "...", "+++"), new int[]{1, 0}));
        System.out.println("Steps: " + nearestExit(parse(".+"), new int[]{0, 0}));
    }
}
