import java.util.*;

/*
Problem: 24 Game
Pattern: 14. Backtracking
Difficulty: Hard

Interview Prompt:
Given four cards with values 1 to 9, decide whether they can be combined with + - * / and parentheses
to make exactly 24. Division is real division, and every card must be used once.

Why this pattern fits:
Any parenthesized expression is built by repeatedly combining two available values into one.
Enumerating "which two, and which operation" covers every expression tree without generating strings.

Approach:
With a list of values: if one remains, check whether it is within a tiny tolerance of 24.
Otherwise for every ordered pair (i, j), remove both, and for each of a + b, a - b, a * b, a / b
add the result, recurse, and undo.

Worked Example:
[4, 1, 8, 7] -> (8 - 4) * (7 - 1) = 24. [1, 2, 1, 2] cannot make 24.

Time Complexity: O(1) for four cards (a bounded search of a few thousand states)
Space Complexity: O(1)
*/

public class P43_TwentyFourGameBacktracking {
    private static boolean solve(List<Double> values) {
        if (values.size() == 1) {
            return Math.abs(values.get(0) - 24) < 1e-6;
        }
        for (int i = 0; i < values.size(); i++) {
            for (int j = 0; j < values.size(); j++) {
                if (i == j) {
                    continue;
                }
                double a = values.get(i);
                double b = values.get(j);
                List<Double> rest = new ArrayList<>();
                for (int k = 0; k < values.size(); k++) {
                    if (k != i && k != j) {
                        rest.add(values.get(k));
                    }
                }
                List<Double> candidates = new ArrayList<>(Arrays.asList(a + b, a - b, a * b));
                if (Math.abs(b) > 1e-6) {
                    candidates.add(a / b);
                }
                for (double candidate : candidates) {
                    rest.add(candidate);
                    if (solve(rest)) {
                        return true;
                    }
                    rest.remove(rest.size() - 1);
                }
            }
        }
        return false;
    }

    public static boolean judgePoint24(int[] cards) {
        List<Double> values = new ArrayList<>();
        for (int card : cards) {
            values.add((double) card);
        }
        return solve(values);
    }

    public static void main(String[] args) {
        System.out.println("[4, 1, 8, 7] -> " + judgePoint24(new int[]{4, 1, 8, 7}));
        System.out.println("[1, 2, 1, 2] -> " + judgePoint24(new int[]{1, 2, 1, 2}));
    }
}
