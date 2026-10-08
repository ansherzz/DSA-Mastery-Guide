import java.util.*;

/*
Problem: Can Make Palindrome from Substring
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
For each query [left, right, k] you may rearrange s[left..right] and then replace up to k letters.
Return for every query whether the substring can be made a palindrome.

Why this pattern fits:
Only the parity of each letter count matters. A 26-bit prefix mask stores those parities,
and XOR of two prefixes gives the parities of the substring between them.

Approach:
prefix[i + 1] = prefix[i] ^ (1 << letter). For a query, odd = bitCount(prefix[right + 1] ^ prefix[left]).
Each replacement fixes two odd letters, so the query succeeds when odd / 2 <= k.

Worked Example:
s = "abcda", query [0, 3, 1]: a, b, c, d are all odd (4 letters), needing 2 replacements. Answer = false.
Query [0, 4, 1]: b, c, d are odd, needing 1 replacement. Answer = true.

Time Complexity: O(n + q)
Space Complexity: O(n)
*/

public class P44_CanMakePalindromeFromSubstringPrefixSum {
    public static List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int[] prefix = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            prefix[i + 1] = prefix[i] ^ (1 << (s.charAt(i) - 'a'));
        }
        List<Boolean> answer = new ArrayList<>();
        for (int[] q : queries) {
            int odd = Integer.bitCount(prefix[q[1] + 1] ^ prefix[q[0]]);
            answer.add(odd / 2 <= q[2]);
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] queries = {{3, 3, 0}, {1, 2, 0}, {0, 3, 1}, {0, 3, 2}, {0, 4, 1}};
        System.out.println("abcda -> " + canMakePaliQueries("abcda", queries));
    }
}
