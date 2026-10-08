import java.util.*;

/*
Problem: Shortest Common Supersequence
Pattern: 20. Dynamic Programming
Difficulty: Hard

Interview Prompt:
Given two strings, return the shortest string that has both of them as subsequences.

Why this pattern fits:
The shortest supersequence merges the two strings while sharing as many letters as possible,
and the letters that can be shared are exactly a longest common subsequence.

Approach:
Fill the LCS table. Starting from the bottom-right corner: if the two characters match, emit it and move diagonally;
otherwise move toward the larger neighbor, emitting the character that is left behind.
Emit whatever remains of either string and reverse the result.

Worked Example:
str1 = "abac", str2 = "cab" -> LCS "ab" gives a supersequence of length 4 + 3 - 2 = 5, such as "cabac".

Time Complexity: O(m * n)
Space Complexity: O(m * n)
*/

public class P50_ShortestCommonSupersequenceDP {
    public static String shortestCommonSupersequence(String a, String b) {
        int m = a.length();
        int n = b.length();
        int[][] lcs = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                lcs[i][j] = a.charAt(i - 1) == b.charAt(j - 1)
                        ? lcs[i - 1][j - 1] + 1
                        : Math.max(lcs[i - 1][j], lcs[i][j - 1]);
            }
        }
        StringBuilder result = new StringBuilder();
        int i = m;
        int j = n;
        while (i > 0 && j > 0) {
            if (a.charAt(i - 1) == b.charAt(j - 1)) {
                result.append(a.charAt(i - 1));
                i--;
                j--;
            } else if (lcs[i - 1][j] >= lcs[i][j - 1]) {
                result.append(a.charAt(--i));
            } else {
                result.append(b.charAt(--j));
            }
        }
        while (i > 0) {
            result.append(a.charAt(--i));
        }
        while (j > 0) {
            result.append(b.charAt(--j));
        }
        return result.reverse().toString();
    }

    private static boolean isSubsequence(String small, String large) {
        int matched = 0;
        for (int i = 0; i < large.length() && matched < small.length(); i++) {
            if (large.charAt(i) == small.charAt(matched)) {
                matched++;
            }
        }
        return matched == small.length();
    }

    private static void runDemo(String a, String b) {
        String result = shortestCommonSupersequence(a, b);
        System.out.println(a + " and " + b + " give " + result);
        System.out.println("length -> " + result.length());
        System.out.println("contains both as subsequences -> " + (isSubsequence(a, result) && isSubsequence(b, result)));
    }

    public static void main(String[] args) {
        runDemo("abac", "cab");
        runDemo("aaaaaaaa", "aaaaaaaa");
    }
}
