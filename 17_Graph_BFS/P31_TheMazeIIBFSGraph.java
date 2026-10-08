import java.util.*;

/*
Problem: The Maze II
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
A ball in a maze rolls in one of four directions until it hits a wall, then chooses a new direction.
Return the shortest distance (number of empty cells travelled) for the ball to stop at the destination, or -1.

Why this pattern fits:
The nodes are the cells where the ball can stop, and a roll connects two of them with a cost equal to its length.
Edges have different weights, so plain BFS is wrong and Dijkstra's priority queue is required.

Approach:
Dijkstra from the start cell. For each popped cell roll in all four directions, counting steps until a wall.
Relax the stopping cell if the new distance is smaller.

Worked Example:
In the classic 5 x 5 maze, rolling left, down, left, down, right, down, right from (0, 4) reaches (4, 4) in 12 steps.

Time Complexity: O(rows * cols * max(rows, cols) * log(rows * cols))
Space Complexity: O(rows * cols)
*/

public class P31_TheMazeIIBFSGraph {
    public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length;
        int cols = maze[0].length;
        int[][] dist = new int[rows][cols];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        heap.add(new int[]{start[0], start[1], 0});
        dist[start[0]][start[1]] = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!heap.isEmpty()) {
            int[] current = heap.poll();
            if (current[2] > dist[current[0]][current[1]]) {
                continue;
            }
            if (current[0] == destination[0] && current[1] == destination[1]) {
                return current[2];
            }
            for (int[] d : directions) {
                int r = current[0];
                int c = current[1];
                int steps = current[2];
                while (r + d[0] >= 0 && r + d[0] < rows && c + d[1] >= 0 && c + d[1] < cols
                        && maze[r + d[0]][c + d[1]] == 0) {
                    r += d[0];
                    c += d[1];
                    steps++;
                }
                if (steps < dist[r][c]) {
                    dist[r][c] = steps;
                    heap.add(new int[]{r, c, steps});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] maze = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        System.out.println("to (4, 4) -> " + shortestDistance(maze, new int[]{0, 4}, new int[]{4, 4}));
        System.out.println("to (3, 2) -> " + shortestDistance(maze, new int[]{0, 4}, new int[]{3, 2}));
    }
}
