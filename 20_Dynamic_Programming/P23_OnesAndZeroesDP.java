import java.util.*;

/*
Problem: Ones and Zeroes
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Given binary strings and two integers m and n, return the size of the largest subset of the strings
that contains at most m zeros and at most n ones in total.

Why this pattern fits:
Each string is either taken or not (0/1), costs some zeros and some ones, and is worth 1.
That is a knapsack whose capacity has two dimensions.

Approach:
dp[i][j] = the most strings using at most i zeros and j ones. For each string with (z, o),
update i from m down to z and j from n down to o: dp[i][j] = max(dp[i][j], dp[i - z][j - o] + 1).
Iterating downward keeps each string from being used twice.

Worked Example:
strs = [10, 0001, 111001, 1, 0], m = 5, n = 3 -> {10, 0001, 1, 0} uses 5 zeros and 3 ones. Answer = 4.

Time Complexity: O(count * m * n)
Space Complexity: O(m * n)
*/

public class P23_OnesAndZeroesDP {
    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            int zeros = 0;
            for (char ch : s.toCharArray()) {
                if (ch == '0') {
                    zeros++;
                }
            }
            int ones = s.length() - zeros;
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println("m = 5, n = 3 -> " + findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
        System.out.println("m = 1, n = 1 -> " + findMaxForm(new String[]{"10", "0", "1"}, 1, 1));
    }
}
