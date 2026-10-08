import java.util.*;

/*
Problem: N-Queens II
Pattern: 14. Backtracking
Difficulty: Hard

Interview Prompt:
The n-queens puzzle is the problem of placing n queens on an n x n chessboard
    such that no two queens attack each other.
    Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Why this pattern fits:
Only the count is needed, so no board has to be built.
    The search places one queen per row, and the three attack conditions can each be tracked with a single bitmask,
    which makes every "is this square safe?" test a constant-time bit operation.

Approach:
Recurse row by row with three masks: columns in use, and the two diagonal directions in use as seen from the current row.
    The free squares of the row are the bits not set in any mask.
    Repeatedly take the lowest free bit, place a queen there, and recurse to the next row with:
    columns | bit, (diagonals | bit) shifted left by one, and (anti-diagonals | bit) shifted right by one.
    The shifts move each diagonal's attack one column sideways as the row advances.
    No explicit undo is needed because the masks are passed by value.

Worked Example:
Example:
    n = 4 has 2 solutions: queens in columns (1, 3, 0, 2) and (2, 0, 3, 1) for rows 0..3.
    n = 8 has 92 solutions.

Time Complexity: O(n!) in the worst case, with strong pruning
Space Complexity: O(n) recursion stack
*/

public class P29_NQueensIIBacktracking {
    public static int totalNQueens(int n) {
        return place(0, n, 0, 0, 0);
    }

    private static int place(int row, int n, int columns, int diagonals, int antiDiagonals) {
        if (row == n) {
            return 1;
        }

        int count = 0;
        int free = ~(columns | diagonals | antiDiagonals) & ((1 << n) - 1);
        while (free != 0) {
            int bit = free & -free;
            free ^= bit;
            count += place(row + 1, n, columns | bit, (diagonals | bit) << 1, (antiDiagonals | bit) >> 1);
        }
        return count;
    }

    public static void main(String[] args) {
        for (int n = 1; n <= 8; n++) {
            System.out.println("n = " + n + " -> " + totalNQueens(n));
        }
    }
}
