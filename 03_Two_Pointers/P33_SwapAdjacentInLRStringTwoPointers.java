import java.util.*;

/*
Problem: Swap Adjacent in LR String
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
In a string of 'L', 'R', and 'X', a move replaces "XL" with "LX" or "RX" with "XR".
Return true if `start` can be transformed into `end` with some sequence of moves.

Why this pattern fits:
L pieces only move left, R pieces only move right, and pieces never jump over each other.
So the non-X characters must appear in the same order, with position constraints checked pairwise.

Approach:
Walk both strings with one pointer each, skipping X.
The next pieces must be the same letter. An L in start at i must land at j <= i; an R must land at j >= i.
Both pointers must run out together.

Worked Example:
start = "RXXLRXRXL", end = "XRLXXRRLX"
Pieces are R L R R L in both. Every R moved right and every L moved left. Answer = true.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P33_SwapAdjacentInLRStringTwoPointers {
    public static boolean canTransform(String start, String end) {
        int n = start.length();
        if (end.length() != n) {
            return false;
        }
        int i = 0;
        int j = 0;
        while (true) {
            while (i < n && start.charAt(i) == 'X') {
                i++;
            }
            while (j < n && end.charAt(j) == 'X') {
                j++;
            }
            if (i == n || j == n) {
                return i == n && j == n;
            }
            char piece = start.charAt(i);
            if (piece != end.charAt(j) || (piece == 'L' && j > i) || (piece == 'R' && j < i)) {
                return false;
            }
            i++;
            j++;
        }
    }

    public static void main(String[] args) {
        System.out.println("RXXLRXRXL, XRLXXRRLX -> " + canTransform("RXXLRXRXL", "XRLXXRRLX"));
        System.out.println("X, L -> " + canTransform("X", "L"));
        System.out.println("XL, LX -> " + canTransform("XL", "LX"));
    }
}
