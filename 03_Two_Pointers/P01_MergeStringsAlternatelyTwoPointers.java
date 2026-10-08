import java.util.*;

/*
Problem: Merge Strings Alternately
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
Merge two strings by adding letters in alternating order, starting with word1.
If one string is longer, append its remaining letters at the end.

Why this pattern fits:
Two inputs consumed in lockstep is the simplest two-pointer shape: one index per sequence.

Approach:
Loop while either index is in range. Append word1[i] if available, then word2[j] if available.

Worked Example:
word1 = "ab", word2 = "pqrs" -> a p b q, then the leftover "rs". Answer = "apbqrs".

Time Complexity: O(m + n)
Space Complexity: O(m + n)
*/

public class P01_MergeStringsAlternatelyTwoPointers {
    public static String mergeAlternately(String word1, String word2) {
        StringBuilder merged = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < word1.length() || j < word2.length()) {
            if (i < word1.length()) {
                merged.append(word1.charAt(i++));
            }
            if (j < word2.length()) {
                merged.append(word2.charAt(j++));
            }
        }
        return merged.toString();
    }

    public static void main(String[] args) {
        System.out.println("abc, pqr -> " + mergeAlternately("abc", "pqr"));
        System.out.println("ab, pqrs -> " + mergeAlternately("ab", "pqrs"));
    }
}
