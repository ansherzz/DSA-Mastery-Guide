import java.util.*;

/*
Problem: Scramble String
Pattern: 15. Divide & Conquer
Difficulty: Hard

Interview Prompt:
A string can be scrambled by splitting it into two non-empty parts, optionally swapping them,
and recursively scrambling each part. Return true if s2 is a scrambled version of s1.

Why this pattern fits:
The definition is itself divide and conquer: s1 is cut at some position, and each half must match
a half of s2, either in the same order or crossed. Memoization removes repeated sub-problems.

Approach:
isScramble(a, b): equal strings match; strings with different letter counts cannot.
For each split i: (a[0..i) ~ b[0..i) and a[i..) ~ b[i..)) or (a[0..i) ~ b[n-i..) and a[i..) ~ b[0..n-i)).

Worked Example:
"great" -> "gr" + "eat" -> "rg" + "eat" = "rgeat". Answer = true.

Time Complexity: O(n^4) with memoization
Space Complexity: O(n^3) memo entries
*/

public class P48_ScrambleStringDivideConquer {
    private static final Map<String, Boolean> MEMO = new HashMap<>();

    public static boolean isScramble(String a, String b) {
        if (a.equals(b)) {
            return true;
        }
        String key = a + "#" + b;
        if (MEMO.containsKey(key)) {
            return MEMO.get(key);
        }
        int[] count = new int[26];
        for (int i = 0; i < a.length(); i++) {
            count[a.charAt(i) - 'a']++;
            count[b.charAt(i) - 'a']--;
        }
        boolean result = true;
        for (int value : count) {
            result &= value == 0;
        }
        if (result) {
            result = false;
            int n = a.length();
            for (int i = 1; i < n && !result; i++) {
                boolean straight = isScramble(a.substring(0, i), b.substring(0, i))
                        && isScramble(a.substring(i), b.substring(i));
                boolean crossed = isScramble(a.substring(0, i), b.substring(n - i))
                        && isScramble(a.substring(i), b.substring(0, n - i));
                result = straight || crossed;
            }
        }
        MEMO.put(key, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("great, rgeat -> " + isScramble("great", "rgeat"));
        System.out.println("abcde, caebd -> " + isScramble("abcde", "caebd"));
        System.out.println("a, a -> " + isScramble("a", "a"));
    }
}
