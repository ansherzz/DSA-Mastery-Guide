import java.util.*;

/*
Problem: Surrounded Regions
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
Given an m x n board containing 'X' and 'O', capture all regions that are surrounded by 'X'.
    A region of 'O' cells is captured by flipping every 'O' in it to 'X'.
    A region is surrounded only if none of its cells lies on the border of the board.

Why this pattern fits:
Deciding directly whether a region is surrounded is awkward, because one border cell anywhere saves the whole region.
    Reverse the question: the regions that survive are exactly those connected to a border 'O'.
    A DFS from every border 'O' marks all the survivors.

Approach:
Run DFS from each 'O' on the border and mark every reachable 'O' with a temporary symbol such as '#'.
    Then sweep the board: any remaining 'O' is surrounded and becomes 'X',
    and every '#' is restored to 'O'.

Worked Example:
Example:
    X X X X          X X X X
    X O O X    ->    X X X X
    X X O X          X X X X
    X O X X          X O X X
    The three inner O cells are surrounded and get captured.
    The O on the bottom border is safe.

Time Complexity: O(m * n)
Space Complexity: O(m * n) recursion stack in the worst case
*/

public class SurroundedRegionsDFSGraph {
    public static void solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        for (int r = 0; r < rows; r++) {
            markSafe(board, r, 0);
            markSafe(board, r, cols - 1);
        }
        for (int c = 0; c < cols; c++) {
            markSafe(board, 0, c);
            markSafe(board, rows - 1, c);
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X';
                } else if (board[r][c] == '#') {
                    board[r][c] = 'O';
                }
            }
        }
    }

    private static void markSafe(char[][] board, int r, int c) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] != 'O') {
            return;
        }

        board[r][c] = '#';
        markSafe(board, r + 1, c);
        markSafe(board, r - 1, c);
        markSafe(board, r, c + 1);
        markSafe(board, r, c - 1);
    }

    public static void main(String[] args) {
        String[] rows = {"XXXX", "XOOX", "XXOX", "XOXX"};
        char[][] board = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            board[i] = rows[i].toCharArray();
        }

        solve(board);
        for (char[] row : board) {
            System.out.println(new String(row));
        }
    }
}
