import java.util.*;

/*
Problem: Number of Matching Subsequences
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
Given a string s and an array of words, return how many words are subsequences of s.
s can be long and there can be many words.

Why this pattern fits:
Scanning s for every word is too slow. Pre-indexing the positions of each letter turns
"next occurrence of this letter after position p" into an upper-bound search.

Approach:
Store the sorted positions of each letter of s. For a word, keep the last matched position (start at -1)
and, for each letter, binary search the first position greater than it. If none exists, the word fails.

Worked Example:
s = "abcde", words = ["a", "bb", "acd", "ace"] -> "bb" fails because there is no second b. Answer = 3.

Time Complexity: O(|s| + total word length * log |s|)
Space Complexity: O(|s|)
*/

public class P37_NumberOfMatchingSubsequencesBinarySearch {
    public static int numMatchingSubseq(String s, String[] words) {
        List<List<Integer>> positions = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            positions.add(new ArrayList<>());
        }
        for (int i = 0; i < s.length(); i++) {
            positions.get(s.charAt(i) - 'a').add(i);
        }
        int matched = 0;
        for (String word : words) {
            int previous = -1;
            boolean ok = true;
            for (int i = 0; i < word.length() && ok; i++) {
                List<Integer> list = positions.get(word.charAt(i) - 'a');
                int low = 0;
                int high = list.size();
                while (low < high) {
                    int mid = low + (high - low) / 2;
                    if (list.get(mid) <= previous) {
                        low = mid + 1;
                    } else {
                        high = mid;
                    }
                }
                if (low == list.size()) {
                    ok = false;
                } else {
                    previous = list.get(low);
                }
            }
            if (ok) {
                matched++;
            }
        }
        return matched;
    }

    public static void main(String[] args) {
        System.out.println("abcde -> " + numMatchingSubseq("abcde", new String[]{"a", "bb", "acd", "ace"}));
        System.out.println("dsahjpjauf -> "
                + numMatchingSubseq("dsahjpjauf", new String[]{"ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax"}));
    }
}
