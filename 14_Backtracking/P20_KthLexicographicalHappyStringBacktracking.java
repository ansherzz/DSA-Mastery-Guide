import java.util.*;

/*
Problem: The k-th Lexicographical String of All Happy Strings of Length n
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
A happy string uses only 'a', 'b', 'c' and has no two equal adjacent characters.
Return the k-th happy string of length n in lexicographic order, or "" if there are fewer than k.

Why this pattern fits:
Trying letters in alphabetical order makes the DFS produce strings already sorted,
so the k-th leaf reached is the answer and the search can stop early.

Approach:
dfs(builder): when the length is n, count one string and stop if the count is k.
Otherwise append each letter that differs from the last one, recurse, and remove it unless the answer was found.

Worked Example:
n = 3 -> aba, abc, aca, acb, bab, bac, bca, bcb, cab, ... The 9th is "cab".

Time Complexity: O(n * min(k, 3 * 2^(n-1)))
Space Complexity: O(n)
*/

public class P20_KthLexicographicalHappyStringBacktracking {
    private static int remaining;

    private static boolean dfs(StringBuilder current, int n) {
        if (current.length() == n) {
            return --remaining == 0;
        }
        for (char letter = 'a'; letter <= 'c'; letter++) {
            if (current.length() > 0 && current.charAt(current.length() - 1) == letter) {
                continue;
            }
            current.append(letter);
            if (dfs(current, n)) {
                return true;
            }
            current.deleteCharAt(current.length() - 1);
        }
        return false;
    }

    public static String getHappyString(int n, int k) {
        remaining = k;
        StringBuilder current = new StringBuilder();
        return dfs(current, n) ? current.toString() : "";
    }

    public static void main(String[] args) {
        System.out.println("n = 1, k = 3 -> \"" + getHappyString(1, 3) + "\"");
        System.out.println("n = 1, k = 4 -> \"" + getHappyString(1, 4) + "\"");
        System.out.println("n = 3, k = 9 -> \"" + getHappyString(3, 9) + "\"");
    }
}
