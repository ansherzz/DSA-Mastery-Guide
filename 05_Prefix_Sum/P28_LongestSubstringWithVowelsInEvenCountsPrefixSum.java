import java.util.*;

/*
Problem: Find the Longest Substring Containing Vowels in Even Counts
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Given a string s, return the size of the longest substring in which each of the vowels
    'a', 'e', 'i', 'o', and 'u' appears an even number of times. Zero occurrences count as even.

Why this pattern fits:
Only the parity of each vowel count matters, so the prefix state is 5 bits: one parity bit per vowel.
    A substring has all-even vowel counts exactly when the prefix states at its two ends are identical.
    This is the prefix-sum idea with XOR in place of addition.

Approach:
Keep a 5-bit mask and flip the matching bit whenever a vowel is read.
    Store the first index at which each of the 32 masks appears, with mask 0 seeded at index -1.
    When a mask is seen again, the substring since its first occurrence has even counts for every vowel.

Worked Example:
Example:
    s = "eleetminicoworoep"
    The longest valid substring is "leetminicowor" with length 13: e, i, and o each appear twice.

Time Complexity: O(n)
Space Complexity: O(1), a fixed 32-slot table
*/

public class P28_LongestSubstringWithVowelsInEvenCountsPrefixSum {
    public static int findTheLongestSubstring(String s) {
        int[] firstIndex = new int[32];
        Arrays.fill(firstIndex, Integer.MIN_VALUE);
        firstIndex[0] = -1;

        int mask = 0;
        int best = 0;
        for (int i = 0; i < s.length(); i++) {
            int vowel = "aeiou".indexOf(s.charAt(i));
            if (vowel >= 0) {
                mask ^= 1 << vowel;
            }

            if (firstIndex[mask] == Integer.MIN_VALUE) {
                firstIndex[mask] = i;
            } else {
                best = Math.max(best, i - firstIndex[mask]);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(findTheLongestSubstring("eleetminicoworoep"));
        System.out.println(findTheLongestSubstring("leetcodeisgreat"));
        System.out.println(findTheLongestSubstring("bcbcbc"));
    }
}
