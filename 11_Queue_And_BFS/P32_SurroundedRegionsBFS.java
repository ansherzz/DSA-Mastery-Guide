import java.util.*;

/*
Problem: Surrounded Regions (BFS from the Border)
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Given a board of 'X' and 'O', capture every region of 'O' that is fully surrounded by 'X'
by flipping it to 'X'. Regions touching the border are not captured.

Why this pattern fits:
Deciding whether a region is surrounded is awkward from the inside.
From the outside it is easy: an 'O' survives exactly when it is connected to a border 'O'.

Approach:
Enqueue every border 'O' and mark it '#'. BFS to mark all connected 'O' cells.
Finally turn the remaining 'O' cells into 'X' and restore '#' to 'O'.

Worked Example:
The 'O' at the bottom edge survives; the three interior 'O' cells are captured.

Time Complexity: O(rows * cols)
Space Complexity: O(rows * cols)
*/

public class P32_SurroundedRegionsBFS {
    public static void solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                boolean onBorder = r == 0 || c == 0 || r == rows - 1 || c == cols - 1;
                if (onBorder && board[r][c] == 'O') {
                    board[r][c] = '#';
                    queue.add(new int[]{r, c});
                }
            }
        }
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : directions) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r >= 0 && r < rows && c >= 0 && c < cols && board[r][c] == 'O') {
                    board[r][c] = '#';
                    queue.add(new int[]{r, c});
                }
            }
        }
        for (char[] row : board) {
            for (int c = 0; c < cols; c++) {
                row[c] = row[c] == '#' ? 'O' : 'X';
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = {"XXXX".toCharArray(), "XOOX".toCharArray(), "XXOX".toCharArray(), "XOXX".toCharArray()};
        solve(board);
        List<String> rows = new ArrayList<>();
        for (char[] row : board) {
            rows.add(new String(row));
        }
        System.out.println("[XXXX, XOOX, XXOX, XOXX] -> " + rows);
    }
}
