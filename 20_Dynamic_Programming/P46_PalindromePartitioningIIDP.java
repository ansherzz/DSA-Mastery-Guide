import java.util.*;

/*
Problem: Palindrome Partitioning II
Pattern: 20. Dynamic Programming
Difficulty: Hard

Interview Prompt:
Partition a string so that every piece is a palindrome. Return the minimum number of cuts needed.

Why this pattern fits:
The last piece of an optimal partition is some palindrome s[j..i], and what precedes it is an optimal partition
of a shorter prefix. That is the partition-DP pattern, made fast by a palindrome table.

Approach:
isPalindrome[j][i] is true when s[j] == s[i] and the inside is a palindrome.
cuts[i] = 0 if s[0..i] is a palindrome, otherwise min(cuts[j - 1] + 1) over all j with s[j..i] a palindrome.

Worked Example:
s = "aab" -> "aa" | "b" needs 1 cut. s = "abccbc" -> "a" | "bccb" | "c" needs 2 cuts.

Time Complexity: O(n^2)
Space Complexity: O(n^2)
*/

public class P46_PalindromePartitioningIIDP {
    public static int minCut(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        int[] cuts = new int[n];
        for (int end = 0; end < n; end++) {
            cuts[end] = end;
            for (int start = 0; start <= end; start++) {
                if (s.charAt(start) == s.charAt(end) && (end - start < 2 || isPalindrome[start + 1][end - 1])) {
                    isPalindrome[start][end] = true;
                    cuts[end] = start == 0 ? 0 : Math.min(cuts[end], cuts[start - 1] + 1);
                }
            }
        }
        return cuts[n - 1];
    }

    public static void main(String[] args) {
        for (String s : new String[]{"aab", "a", "ab", "abccbc"}) {
            System.out.println(s + " -> " + minCut(s));
        }
    }
}
