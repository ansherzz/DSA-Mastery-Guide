import java.util.*;

/*
Problem: Sudoku Solver
Pattern: 14. Backtracking
Difficulty: Hard

Interview Prompt:
Write a program that solves a Sudoku puzzle by filling the empty cells, which are marked with '.'.
    A solution must place the digits 1-9 so that each digit appears exactly once in every row,
    every column, and every one of the nine 3 x 3 boxes. The input puzzle has exactly one solution.

Why this pattern fits:
Sudoku is a constraint satisfaction problem: each empty cell is a decision with up to nine options,
    and a wrong early decision is only discovered later. Backtracking places a digit, moves on,
    and returns to try the next digit when a later cell has no legal option.

Approach:
Precompute which digits are already used in each row, column, and box.
    Scan the cells in order. For an empty cell, try each digit that is not used in its row, column, or box.
    Place it, update the three usage tables, and recurse to the next cell.
    If the recursion fails, undo the placement and try the next digit. Reaching the end of the grid means success.

Worked Example:
Example:
    The classic puzzle starting with the row "53..7...." has a unique solution
    whose first row is 5 3 4 6 7 8 9 1 2.

Time Complexity: O(9^E) in the worst case, where E is the number of empty cells (far less in practice)
Space Complexity: O(E) recursion depth
*/

public class P25_SudokuSolverBacktracking {
    private static boolean[][] rowUsed;
    private static boolean[][] colUsed;
    private static boolean[][] boxUsed;

    public static void solveSudoku(char[][] board) {
        rowUsed = new boolean[9][10];
        colUsed = new boolean[9][10];
        boxUsed = new boolean[9][10];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') {
                    int digit = board[r][c] - '0';
                    rowUsed[r][digit] = true;
                    colUsed[c][digit] = true;
                    boxUsed[(r / 3) * 3 + c / 3][digit] = true;
                }
            }
        }
        solve(board, 0);
    }

    private static boolean solve(char[][] board, int cell) {
        if (cell == 81) {
            return true;
        }

        int r = cell / 9;
        int c = cell % 9;
        if (board[r][c] != '.') {
            return solve(board, cell + 1);
        }

        int box = (r / 3) * 3 + c / 3;
        for (int digit = 1; digit <= 9; digit++) {
            if (rowUsed[r][digit] || colUsed[c][digit] || boxUsed[box][digit]) {
                continue;
            }

            board[r][c] = (char) ('0' + digit);
            rowUsed[r][digit] = colUsed[c][digit] = boxUsed[box][digit] = true;

            if (solve(board, cell + 1)) {
                return true;
            }

            board[r][c] = '.';
            rowUsed[r][digit] = colUsed[c][digit] = boxUsed[box][digit] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        String[] rows = {
            "53..7....",
            "6..195...",
            ".98....6.",
            "8...6...3",
            "4..8.3..1",
            "7...2...6",
            ".6....28.",
            "...419..5",
            "....8..79"
        };
        char[][] board = new char[9][];
        for (int i = 0; i < 9; i++) {
            board[i] = rows[i].toCharArray();
        }

        solveSudoku(board);
        for (char[] row : board) {
            System.out.println(new String(row));
        }
    }
}
