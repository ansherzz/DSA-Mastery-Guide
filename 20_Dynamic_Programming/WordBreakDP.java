import java.util.*;

/*
Problem: Word Break
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
Given a string s and a dictionary of words, return true if s can be segmented into
    a sequence of one or more dictionary words. The same dictionary word may be reused any number of times.

Why this pattern fits:
If the string can be split, there is a last word. Removing it leaves a shorter prefix
    that must also be splittable. So the answer for each prefix depends on answers for shorter prefixes,
    and a greedy choice of the longest or shortest word does not always work.

Approach:
State: canBreak[i] is true if the first i characters can be segmented. canBreak[0] is true for the empty prefix.
    Transition: canBreak[i] is true if there is some j < i with canBreak[j] true
    and s[j..i) present in the dictionary.
    Limit j so that the candidate word is never longer than the longest dictionary word.

Worked Example:
Example:
    s = "leetcode", dictionary = ["leet", "code"]
    canBreak[4] is true because "leet" is a word. canBreak[8] is true because canBreak[4] is true and "code" is a word.

    s = "catsandog", dictionary = ["cats", "dog", "sand", "and", "cat"]
    Every split leaves "og" or "andog" unmatched, so the answer is false.

Time Complexity: O(n * L) substring checks, where L is the longest word length
Space Complexity: O(n + dictionary size)
*/

public class WordBreakDP {
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        int longest = 0;
        for (String word : wordDict) {
            longest = Math.max(longest, word.length());
        }

        boolean[] canBreak = new boolean[s.length() + 1];
        canBreak[0] = true;

        for (int end = 1; end <= s.length(); end++) {
            for (int start = Math.max(0, end - longest); start < end; start++) {
                if (canBreak[start] && words.contains(s.substring(start, end))) {
                    canBreak[end] = true;
                    break;
                }
            }
        }
        return canBreak[s.length()];
    }

    private static void runDemo(String s, String... dictionary) {
        System.out.println("s = " + s + ", dictionary = " + Arrays.toString(dictionary)
            + " -> " + wordBreak(s, Arrays.asList(dictionary)));
    }

    public static void main(String[] args) {
        runDemo("leetcode", "leet", "code");
        runDemo("applepenapple", "apple", "pen");
        runDemo("catsandog", "cats", "dog", "sand", "and", "cat");
    }
}
