import java.util.*;

/*
Problem: DI String Match
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
Given a string s of 'I' (increase) and 'D' (decrease) of length n, build a permutation of 0..n such that
perm[i] < perm[i + 1] when s[i] == 'I' and perm[i] > perm[i + 1] when s[i] == 'D'.

Why this pattern fits:
The remaining numbers form a range [low, high]. Using the smallest for 'I' guarantees the next is larger,
and using the largest for 'D' guarantees the next is smaller. Two pointers track the range ends.

Approach:
Keep low = 0 and high = n. For each character, write low++ on 'I' or high-- on 'D'.
Finally write the one number left (low == high).

Worked Example:
s = "IDID" -> 0 (I), 4 (D), 1 (I), 3 (D), then the last number 2. Answer = [0, 4, 1, 3, 2].

Time Complexity: O(n)
Space Complexity: O(1) extra
*/

public class P17_DIStringMatchTwoPointers {
    public static int[] diStringMatch(String s) {
        int low = 0;
        int high = s.length();
        int[] perm = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            perm[i] = s.charAt(i) == 'I' ? low++ : high--;
        }
        perm[s.length()] = low;
        return perm;
    }

    public static void main(String[] args) {
        for (String s : new String[]{"IDID", "III", "DDI"}) {
            System.out.println(s + " -> " + Arrays.toString(diStringMatch(s)));
        }
    }
}
