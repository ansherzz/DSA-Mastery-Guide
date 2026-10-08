import java.util.*;

/*
Problem: Split a String Into the Max Number of Unique Substrings
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Given a string s, return the maximum number of unique substrings that the given string can be split into.
    You can split s into any list of non-empty substrings whose concatenation forms the original string,
    but all of the substrings in the split must be different from each other.

Why this pattern fits:
Each position offers a choice of where the next piece ends, and a choice is legal only if the piece is new.
    There is no greedy rule that works, so every legal split must be explored, with a set recording the pieces in use.

Approach:
Recurse on the start index of the next piece. Try every end index:
    if the piece is not in the set, add it, recurse from the end index, and remove it afterward.
    When the start index reaches the end of the string, update the best count.
    Prune: if the pieces chosen so far plus the remaining characters cannot beat the best count, stop exploring.

Worked Example:
Example:
    s = "ababccc"
    One best split is [a, b, ab, c, cc], which has 5 unique pieces.

Time Complexity: O(2^n * n) in the worst case
Space Complexity: O(n)
*/

public class P18_SplitStringIntoMaxUniqueSubstringsBacktracking {
    public static int maxUniqueSplit(String s) {
        int[] best = new int[1];
        backtrack(s, 0, new HashSet<>(), best);
        return best[0];
    }

    private static void backtrack(String s, int start, Set<String> used, int[] best) {
        if (used.size() + (s.length() - start) <= best[0]) {
            return;
        }
        if (start == s.length()) {
            best[0] = used.size();
            return;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String piece = s.substring(start, end);
            if (used.add(piece)) {
                backtrack(s, end, used, best);
                used.remove(piece);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(maxUniqueSplit("ababccc"));
        System.out.println(maxUniqueSplit("aba"));
        System.out.println(maxUniqueSplit("aa"));
    }
}
