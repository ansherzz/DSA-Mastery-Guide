import java.util.*;

/*
Problem: N-Queens
Pattern: 14. Backtracking
Difficulty: Hard

Interview Prompt:
Place n queens on an n x n chessboard so that no two queens attack each other.
    Return all valid board configurations.

    This is a classic constraint-satisfaction backtracking problem with strong pruning.

Why this pattern fits:
Each row chooses one column, but the choice must respect used columns and diagonals.
    The constraints are local enough to prune early, which is exactly what backtracking needs.

Approach:
Place queens row by row.
    Track occupied columns, main diagonals, and anti-diagonals in sets.
    When you reach row n, record the current board.

Worked Example:
Example:
    n = 4
    There are 2 valid solutions.

Time Complexity: Roughly O(n!) with pruning
Space Complexity: O(n)
*/

public class P24_NQueensBacktracking {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> answer = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        Set<Integer> cols = new HashSet<>();
        Set<Integer> diag1 = new HashSet<>();
        Set<Integer> diag2 = new HashSet<>();

        backtrack(0, board, cols, diag1, diag2, answer);
        return answer;
    }

    private static void backtrack(int row, char[][] board, Set<Integer> cols, Set<Integer> diag1, Set<Integer> diag2, List<List<String>> answer) {
        int n = board.length;
        if (row == n) {
            List<String> snapshot = new ArrayList<>();
            for (char[] r : board) {
                snapshot.add(new String(r));
            }
            answer.add(snapshot);
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col;
            int d2 = row + col;
            if (cols.contains(col) || diag1.contains(d1) || diag2.contains(d2)) {
                continue;
            }

            cols.add(col);
            diag1.add(d1);
            diag2.add(d2);
            board[row][col] = 'Q';

            backtrack(row + 1, board, cols, diag1, diag2, answer);

            board[row][col] = '.';
            cols.remove(col);
            diag1.remove(d1);
            diag2.remove(d2);
        }
    }

    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }
}
