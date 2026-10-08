import java.util.*;

/*
Problem: Strobogrammatic Number II
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Return all strobogrammatic numbers of length n (numbers that look the same when rotated 180 degrees).
Numbers with more than one digit may not start with 0.

Why this pattern fits:
A strobogrammatic number of length n is a valid outer pair (0-0, 1-1, 6-9, 8-8, 9-6)
around a strobogrammatic string of length n - 2. The problem reduces to a smaller copy of itself.

Approach:
build(length): base cases are [""] for 0 and ["0", "1", "8"] for 1.
Otherwise wrap each inner string with every pair, using the 0-0 pair only when this is not the outermost level.

Worked Example:
n = 2 -> wrap the empty string: 11, 69, 88, 96.

Time Complexity: O(5^(n/2))
Space Complexity: O(5^(n/2))
*/

public class P33_StrobogrammaticNumberIIDivideConquer {
    private static List<String> build(int length, int total) {
        if (length == 0) {
            return new ArrayList<>(Arrays.asList(""));
        }
        if (length == 1) {
            return new ArrayList<>(Arrays.asList("0", "1", "8"));
        }
        List<String> result = new ArrayList<>();
        for (String inner : build(length - 2, total)) {
            if (length != total) {
                result.add("0" + inner + "0");
            }
            result.add("1" + inner + "1");
            result.add("6" + inner + "9");
            result.add("8" + inner + "8");
            result.add("9" + inner + "6");
        }
        return result;
    }

    public static List<String> findStrobogrammatic(int n) {
        return build(n, n);
    }

    public static void main(String[] args) {
        for (int n : new int[]{1, 2, 3}) {
            System.out.println("n = " + n + " -> " + findStrobogrammatic(n));
        }
    }
}
