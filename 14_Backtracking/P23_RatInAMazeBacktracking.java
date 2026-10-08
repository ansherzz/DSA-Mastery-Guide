import java.util.*;

/*
Problem: Rat in a Maze
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
A rat starts at the top-left of an n x n maze and wants to reach the bottom-right.
Cells with 1 are open and cells with 0 are blocked. It may move D, L, R, U and may not visit a cell twice on one path.
Return every path as a string of moves, in lexicographic order.

Why this pattern fits:
All simple paths are required, not just one or the shortest, so every route must be explored.
The visited marks must be undone when returning so that other paths may reuse the cell.

Approach:
dfs(row, col, path): at the destination record the path. Otherwise mark the cell,
try D, L, R, U in that order (which yields sorted output), and unmark the cell.

Worked Example:
For the 4 x 4 example maze there are exactly two paths: DDRDRR and DRDDRR.

Time Complexity: O(4^(n*n)) in the worst case
Space Complexity: O(n*n)
*/

public class P23_RatInAMazeBacktracking {
    private static final int[][] MOVES = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    private static final char[] NAMES = {'D', 'L', 'R', 'U'};

    private static void dfs(int[][] maze, int row, int col, StringBuilder path, List<String> result) {
        int n = maze.length;
        if (row == n - 1 && col == n - 1) {
            result.add(path.toString());
            return;
        }
        maze[row][col] = 0;
        for (int m = 0; m < 4; m++) {
            int r = row + MOVES[m][0];
            int c = col + MOVES[m][1];
            if (r >= 0 && r < n && c >= 0 && c < n && maze[r][c] == 1) {
                path.append(NAMES[m]);
                dfs(maze, r, c, path, result);
                path.deleteCharAt(path.length() - 1);
            }
        }
        maze[row][col] = 1;
    }

    public static List<String> findPaths(int[][] maze) {
        List<String> result = new ArrayList<>();
        int n = maze.length;
        if (maze[0][0] == 1 && maze[n - 1][n - 1] == 1) {
            dfs(maze, 0, 0, new StringBuilder(), result);
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] maze = {{1, 0, 0, 0}, {1, 1, 0, 1}, {1, 1, 0, 0}, {0, 1, 1, 1}};
        System.out.println("4x4 maze -> " + findPaths(maze));
        System.out.println("blocked maze -> " + findPaths(new int[][]{{1, 0}, {0, 1}}));
    }
}
