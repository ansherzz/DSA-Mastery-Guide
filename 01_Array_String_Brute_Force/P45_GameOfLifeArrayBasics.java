import java.util.*;

/*
Problem: Game of Life
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Medium

Interview Prompt:
Given a board of live (1) and dead (0) cells, compute the next generation in place.
A live cell survives with 2 or 3 live neighbors; a dead cell becomes live with exactly 3 live neighbors.
All cells update simultaneously.

Why this pattern fits:
The straightforward solution copies the board. The interview follow-up is to do it in place,
which requires encoding old and new state in the same cell.

Approach:
Use bit 0 for the current state and bit 1 for the next state.
Count live neighbors using only bit 0, set bit 1 when the cell will be alive, then shift every cell right by one.

Worked Example:
board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
Next generation = [[0,0,0],[1,0,1],[0,1,1],[0,1,0]].

Time Complexity: O(m * n)
Space Complexity: O(1) extra
*/

public class P45_GameOfLifeArrayBasics {
    public static void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int live = 0;
                for (int dr = -1; dr <= 1; dr++) {
                    for (int dc = -1; dc <= 1; dc++) {
                        int nr = r + dr;
                        int nc = c + dc;
                        if ((dr != 0 || dc != 0) && nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                            live += board[nr][nc] & 1;
                        }
                    }
                }
                boolean alive = (board[r][c] & 1) == 1;
                if (live == 3 || (alive && live == 2)) {
                    board[r][c] |= 2;
                }
            }
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] >>= 1;
            }
        }
    }

    public static void main(String[] args) {
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        String before = Arrays.deepToString(board);
        gameOfLife(board);
        System.out.println(before + " -> " + Arrays.deepToString(board));
    }
}
