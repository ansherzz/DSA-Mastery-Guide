import java.util.*;

/*
Problem: Longest Repeating Substring
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
Given a string s, return the length of the longest substring that occurs at least twice
(occurrences may overlap). Return 0 if there is none.

Why this pattern fits:
If a substring of length L repeats, its prefix of length L - 1 repeats too.
So feasibility is monotonic in the length, and each length is checked with a set of substrings.

Approach:
Search L in [0, n - 1]. For a candidate, insert every substring of length L into a hash set
and report success on the first duplicate.

Worked Example:
s = "aabcaabdaab" -> "aab" occurs three times and no length-4 substring repeats. Answer = 3.

Time Complexity: O(n^2 log n) with plain substring hashing (O(n log n) with a rolling hash)
Space Complexity: O(n^2) for the set of substrings
*/

public class P28_LongestRepeatingSubstringBinarySearchAnswer {
    private static boolean hasRepeat(String s, int length) {
        Set<String> seen = new HashSet<>();
        for (int i = 0; i + length <= s.length(); i++) {
            if (!seen.add(s.substring(i, i + length))) {
                return true;
            }
        }
        return false;
    }

    public static int longestRepeatingSubstring(String s) {
        int low = 0;
        int high = s.length() - 1;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (hasRepeat(s, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        for (String s : new String[]{"abcd", "abbaba", "aabcaabdaab"}) {
            System.out.println(s + " -> " + longestRepeatingSubstring(s));
        }
    }
}
