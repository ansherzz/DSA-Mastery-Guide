import java.util.*;

/*
Problem: Determine if Two Strings Are Close
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
Two strings are close if one can become the other using these operations any number of times:
1. swap any two characters; 2. swap every occurrence of one existing letter with every occurrence of another.
Return true if word1 and word2 are close.

Why this pattern fits:
Operation 1 makes order irrelevant. Operation 2 lets frequencies be reassigned among letters that already exist.
So only two frequency-level facts matter.

Approach:
The strings are close when they use exactly the same set of letters
and their frequency lists are equal after sorting.

Worked Example:
word1 = "cabbba", word2 = "abbccc"
Both use {a, b, c}. Frequencies are {1, 2, 3} for both. Answer = true.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P33_DetermineIfTwoStringsAreCloseHashing {
    public static boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int[] a = new int[26];
        int[] b = new int[26];
        for (int i = 0; i < word1.length(); i++) {
            a[word1.charAt(i) - 'a']++;
            b[word2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if ((a[i] == 0) != (b[i] == 0)) {
                return false;
            }
        }
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }

    public static void main(String[] args) {
        System.out.println("abc, bca -> " + closeStrings("abc", "bca"));
        System.out.println("a, aa -> " + closeStrings("a", "aa"));
        System.out.println("cabbba, abbccc -> " + closeStrings("cabbba", "abbccc"));
    }
}
