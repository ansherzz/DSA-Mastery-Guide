import java.util.*;

/*
Problem: Minesweeper
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
Given a Minesweeper board ('M' mine, 'E' unrevealed empty) and a click position, return the board after the click:
a clicked mine becomes 'X'; an empty cell shows the number of adjacent mines, or 'B' if there are none,
in which case all of its unrevealed neighbors are revealed recursively.

Why this pattern fits:
The cascade of blank cells is a flood fill with a stopping rule:
cells that have a mine next to them are revealed as a digit but do not spread further.

Approach:
If the click is a mine, mark 'X'. Otherwise dfs(cell): count the mines among the 8 neighbors.
If the count is positive write the digit; else write 'B' and recurse into every neighbor that is still 'E'.

Worked Example:
Clicking the bottom-left corner of a 4 x 5 board with one mine reveals everything except the cells hidden behind the mine's numbers.

Time Complexity: O(rows * cols)
Space Complexity: O(rows * cols)
*/

public class P13_MinesweeperDFSGraph {
    private static void reveal(char[][] board, int row, int col) {
        int mines = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int r = row + dr;
                int c = col + dc;
                if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c] == 'M') {
                    mines++;
                }
            }
        }
        if (mines > 0) {
            board[row][col] = (char) ('0' + mines);
            return;
        }
        board[row][col] = 'B';
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int r = row + dr;
                int c = col + dc;
                if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c] == 'E') {
                    reveal(board, r, c);
                }
            }
        }
    }

    public static char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
        } else {
            reveal(board, click[0], click[1]);
        }
        return board;
    }

    private static String play(int row, int col) {
        char[][] board = {"EEEEE".toCharArray(), "EEMEE".toCharArray(), "EEEEE".toCharArray(), "EEEEE".toCharArray()};
        List<String> rows = new ArrayList<>();
        for (char[] line : updateBoard(board, new int[]{row, col})) {
            rows.add(new String(line));
        }
        return rows.toString();
    }

    public static void main(String[] args) {
        System.out.println("click (3, 0) -> " + play(3, 0));
        System.out.println("click the mine (1, 2) -> " + play(1, 2));
    }
}
