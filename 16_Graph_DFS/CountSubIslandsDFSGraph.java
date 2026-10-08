import java.util.*;

/*
Problem: Count Sub Islands
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
You are given two m x n binary matrices grid1 and grid2 containing only 0s (water) and 1s (land).
    An island is a group of 1s connected 4-directionally.
    An island in grid2 is considered a sub-island if there is an island in grid1 that contains
    all the cells that make up this island in grid2. Return the number of islands in grid2 that are sub-islands.

Why this pattern fits:
Each island of grid2 must be examined as a whole, which is one DFS per island.
    The sub-island condition is a property of every cell of that island, so it is checked during the same traversal.

Approach:
For each unvisited land cell of grid2, run a DFS over its island, marking cells as visited by setting them to 0.
    The DFS returns whether every cell of the island is also land in grid1.
    Important: do not stop early when a cell fails the check. The whole island must still be consumed,
    otherwise its remaining cells would be counted later as a separate island.
    So combine the results with a non-short-circuit AND.

Worked Example:
Example:
    grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]]
    grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
    Three islands of grid2 lie entirely on land of grid1. Answer = 3.

Time Complexity: O(m * n)
Space Complexity: O(m * n) recursion stack in the worst case
*/

public class CountSubIslandsDFSGraph {
    public static int countSubIslands(int[][] grid1, int[][] grid2) {
        int count = 0;
        for (int r = 0; r < grid2.length; r++) {
            for (int c = 0; c < grid2[0].length; c++) {
                if (grid2[r][c] == 1 && isSubIsland(grid1, grid2, r, c)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isSubIsland(int[][] grid1, int[][] grid2, int r, int c) {
        if (r < 0 || c < 0 || r >= grid2.length || c >= grid2[0].length || grid2[r][c] == 0) {
            return true;
        }

        grid2[r][c] = 0;
        boolean covered = grid1[r][c] == 1;
        covered &= isSubIsland(grid1, grid2, r + 1, c);
        covered &= isSubIsland(grid1, grid2, r - 1, c);
        covered &= isSubIsland(grid1, grid2, r, c + 1);
        covered &= isSubIsland(grid1, grid2, r, c - 1);
        return covered;
    }

    public static void main(String[] args) {
        int[][] grid1 = {{1, 1, 1, 0, 0}, {0, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 1, 1}};
        int[][] grid2 = {{1, 1, 1, 0, 0}, {0, 0, 1, 1, 1}, {0, 1, 0, 0, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}};
        System.out.println(countSubIslands(grid1, grid2));

        int[][] grid3 = {{1, 0, 1, 0, 1}, {1, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {1, 0, 1, 0, 1}};
        int[][] grid4 = {{0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {0, 1, 0, 1, 0}, {0, 1, 0, 1, 0}, {1, 0, 0, 0, 1}};
        System.out.println(countSubIslands(grid3, grid4));
    }
}
