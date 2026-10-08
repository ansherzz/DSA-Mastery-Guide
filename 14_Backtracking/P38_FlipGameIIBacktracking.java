import java.util.*;

/*
Problem: Flip Game II
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Two players take turns flipping two consecutive "++" into "--". The player who cannot move loses.
Given the starting string, return true if the first player can guarantee a win.

Why this pattern fits:
Game outcomes are defined recursively: try every move, and ask whether the opponent loses from the result.
That is backtracking over moves, with the board restored after each trial.

Approach:
canWin(state): for each index with "++", flip it to "--" and recurse.
If the recursive call returns false (the opponent loses), restore and return true.
If no move wins, return false.

Worked Example:
"++++" -> flipping the middle pair gives "+--+", where the opponent has no move. Answer = true.

Time Complexity: Exponential without memoization (O(n!!)); memoizing states reduces it sharply
Space Complexity: O(n) recursion depth
*/

public class P38_FlipGameIIBacktracking {
    private static boolean canWin(char[] state) {
        for (int i = 0; i + 1 < state.length; i++) {
            if (state[i] == '+' && state[i + 1] == '+') {
                state[i] = '-';
                state[i + 1] = '-';
                boolean opponentWins = canWin(state);
                state[i] = '+';
                state[i + 1] = '+';
                if (!opponentWins) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean canWin(String currentState) {
        return canWin(currentState.toCharArray());
    }

    public static void main(String[] args) {
        for (String state : new String[]{"++++", "+++++", "+"}) {
            System.out.println(state + " -> " + canWin(state));
        }
    }
}
