import java.util.*;

/*
Problem: Word Search
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Given an m x n grid of characters and a word, return true if the word exists in the grid.
    The word must be built from letters of sequentially adjacent cells (horizontally or vertically neighboring),
    and the same cell may not be used more than once.

Why this pattern fits:
A path through the grid is a sequence of choices (which neighbor comes next),
    with the constraint that cells on the current path cannot be reused.
    Backtracking marks a cell when it joins the path and unmarks it when the path retreats.

Approach:
Try every cell as a starting point.
    The DFS checks that the cell is in bounds and matches the current letter of the word.
    Temporarily overwrite the cell with a marker, explore the four neighbors for the next letter,
    and restore the cell before returning.

Worked Example:
Example:
    board = A B C E
            S F C S
            A D E E
    "ABCCED" exists: A(0,0) B(0,1) C(0,2) C(1,2) E(2,2) D(2,1).
    "ABCB" does not: the only B would have to be used twice.

Time Complexity: O(m * n * 3^L) where L is the word length
Space Complexity: O(L) recursion depth
*/

public class WordSearchBacktracking {
    public static boolean exist(char[][] board, String word) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (search(board, word, r, c, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean search(char[][] board, String word, int r, int c, int index) {
        if (index == word.length()) {
            return true;
        }
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] != word.charAt(index)) {
            return false;
        }

        char saved = board[r][c];
        board[r][c] = '#';

        boolean found = search(board, word, r + 1, c, index + 1)
            || search(board, word, r - 1, c, index + 1)
            || search(board, word, r, c + 1, index + 1)
            || search(board, word, r, c - 1, index + 1);

        board[r][c] = saved;
        return found;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };

        System.out.println("ABCCED -> " + exist(board, "ABCCED"));
        System.out.println("SEE    -> " + exist(board, "SEE"));
        System.out.println("ABCB   -> " + exist(board, "ABCB"));
    }
}
