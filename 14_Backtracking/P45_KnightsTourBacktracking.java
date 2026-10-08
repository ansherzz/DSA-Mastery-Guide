import java.util.*;

/*
Problem: Knight's Tour
Pattern: 14. Backtracking
Difficulty: Hard

Interview Prompt:
Starting from the top-left corner of an n x n chessboard, find a sequence of knight moves that visits
every square exactly once. Return the board with each square's visit order (0-based), or null if no tour exists.

Why this pattern fits:
The knight must cover the entire board, so a wrong turn is only discovered many moves later.
The search must be able to take back moves, which is exactly what backtracking provides.

Approach:
dfs(row, col, step): write the step number in the square. If it was the last square, succeed.
Otherwise try the eight knight moves to unvisited squares; if none completes the tour, clear the square and report failure.

Worked Example:
A tour exists on the 5 x 5 board from the corner, but no tour of any kind exists on the 4 x 4 board.

Time Complexity: O(8^(n*n)) worst case
Space Complexity: O(n*n)
*/

public class P45_KnightsTourBacktracking {
    private static final int[][] MOVES = {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};

    private static boolean dfs(int[][] board, int row, int col, int step) {
        int n = board.length;
        board[row][col] = step;
        if (step == n * n - 1) {
            return true;
        }
        for (int[] move : MOVES) {
            int r = row + move[0];
            int c = col + move[1];
            if (r >= 0 && r < n && c >= 0 && c < n && board[r][c] == -1 && dfs(board, r, c, step + 1)) {
                return true;
            }
        }
        board[row][col] = -1;
        return false;
    }

    public static int[][] knightsTour(int n) {
        int[][] board = new int[n][n];
        for (int[] row : board) {
            Arrays.fill(row, -1);
        }
        return dfs(board, 0, 0, 0) ? board : null;
    }

    private static boolean isValidTour(int[][] board) {
        int n = board.length;
        int[][] position = new int[n * n][];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                position[board[r][c]] = new int[]{r, c};
            }
        }
        for (int step = 1; step < n * n; step++) {
            int dr = Math.abs(position[step][0] - position[step - 1][0]);
            int dc = Math.abs(position[step][1] - position[step - 1][1]);
            if (dr * dc != 2) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] tour = knightsTour(5);
        for (int[] row : tour) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("5x5 tour found and valid -> " + isValidTour(tour));
        System.out.println("4x4 tour exists -> " + (knightsTour(4) != null));
    }
}
