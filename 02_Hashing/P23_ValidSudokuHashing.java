import java.util.*;

/*
Problem: Valid Sudoku
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
Determine whether a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated:
    - each row must contain the digits 1-9 without repetition
    - each column must contain the digits 1-9 without repetition
    - each of the nine 3 x 3 boxes must contain the digits 1-9 without repetition

    Empty cells are marked with '.'. The board does not need to be solvable.

Why this pattern fits:
Every rule is a "have I seen this digit in this group before?" question.
    Encoding (group, digit) as a key lets one hash set answer all three rules in a single pass.

Approach:
Visit every filled cell once.
    Build three keys for it: digit-in-row, digit-in-column, and digit-in-box.
    If adding any key to the set fails, the digit is repeated in that group and the board is invalid.

Worked Example:
Example:
    If '8' appears at (0, 0) and again at (2, 2), both cells create the key "8 in box 0".
    The second insert fails, so the board is invalid.

Time Complexity: O(1) because the board is always 81 cells
Space Complexity: O(1)
*/

public class P23_ValidSudokuHashing {
    public static boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char digit = board[row][col];
                if (digit == '.') {
                    continue;
                }

                int box = (row / 3) * 3 + col / 3;
                if (!seen.add(digit + " in row " + row)
                        || !seen.add(digit + " in col " + col)
                        || !seen.add(digit + " in box " + box)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static char[][] parse(String... rows) {
        char[][] board = new char[9][];
        for (int i = 0; i < 9; i++) {
            board[i] = rows[i].toCharArray();
        }
        return board;
    }

    public static void main(String[] args) {
        char[][] valid = parse(
            "53..7....",
            "6..195...",
            ".98....6.",
            "8...6...3",
            "4..8.3..1",
            "7...2...6",
            ".6....28.",
            "...419..5",
            "....8..79");
        System.out.println("First board valid?  " + isValidSudoku(valid));

        char[][] invalid = parse(
            "83..7....",
            "6..195...",
            ".98....6.",
            "8...6...3",
            "4..8.3..1",
            "7...2...6",
            ".6....28.",
            "...419..5",
            "....8..79");
        System.out.println("Second board valid? " + isValidSudoku(invalid));
    }
}
