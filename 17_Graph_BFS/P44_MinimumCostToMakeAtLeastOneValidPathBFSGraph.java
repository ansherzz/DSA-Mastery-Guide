import java.util.*;

/*
Problem: Minimum Cost to Make at Least One Valid Path in a Grid
Pattern: 17. Graph BFS
Difficulty: Hard

Interview Prompt:
Given an m x n grid, each cell has a sign pointing to the next cell you should visit:
    1 means go right, 2 means go left, 3 means go down, and 4 means go up.
    Some signs may point outside the grid. You start at the upper-left cell and follow the signs.
    You can modify the sign on a cell at a cost of 1, and each cell's sign can be modified only once.
    Return the minimum cost to make the grid have at least one valid path from the upper-left cell to the bottom-right cell.

Why this pattern fits:
Each cell has four outgoing edges: the one matching its sign costs 0, and the other three cost 1.
    When all edge weights are 0 or 1, Dijkstra's heap can be replaced by a deque (0-1 BFS):
    zero-cost moves go to the front, and one-cost moves go to the back, which keeps the deque sorted by distance.

Approach:
Keep cost[r][c], the cheapest known cost to reach each cell, starting with 0 at the upper-left cell.
    Poll a cell from the front of the deque and try all four directions.
    The move costs 0 if the direction matches the cell's sign, otherwise 1.
    If the new cost improves the neighbor, record it and add the neighbor to the front (cost 0) or back (cost 1).
    The cost stored for the bottom-right cell is the answer.

Worked Example:
Example:
    grid = [[1, 1, 1, 1],
            [2, 2, 2, 2],
            [1, 1, 1, 1],
            [2, 2, 2, 2]]
    Follow row 0 to the right, pay 1 to go down, follow row 1 to the left, pay 1 to go down,
    follow row 2 to the right, pay 1 to go down. Answer = 3.

Time Complexity: O(m * n)
Space Complexity: O(m * n)
*/

public class P44_MinimumCostToMakeAtLeastOneValidPathBFSGraph {
    // Index i holds the move for sign i + 1: right, left, down, up.
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static int minCost(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] cost = new int[rows][cols];
        for (int[] row : cost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        cost[0][0] = 0;

        Deque<int[]> deque = new ArrayDeque<>();
        deque.offerFirst(new int[]{0, 0});
        while (!deque.isEmpty()) {
            int[] cell = deque.pollFirst();
            int r = cell[0];
            int c = cell[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + DIRECTIONS[d][0];
                int nc = c + DIRECTIONS[d][1];
                if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) {
                    continue;
                }

                int step = grid[r][c] == d + 1 ? 0 : 1;
                if (cost[r][c] + step < cost[nr][nc]) {
                    cost[nr][nc] = cost[r][c] + step;
                    if (step == 0) {
                        deque.offerFirst(new int[]{nr, nc});
                    } else {
                        deque.offerLast(new int[]{nr, nc});
                    }
                }
            }
        }
        return cost[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        System.out.println(minCost(new int[][]{{1, 1, 1, 1}, {2, 2, 2, 2}, {1, 1, 1, 1}, {2, 2, 2, 2}}));
        System.out.println(minCost(new int[][]{{1, 1, 3}, {3, 2, 2}, {1, 1, 4}}));
        System.out.println(minCost(new int[][]{{1, 2}, {4, 3}}));
    }
}
