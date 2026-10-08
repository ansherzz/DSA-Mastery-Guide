import java.util.*;

/*
Problem: Substring with Concatenation of All Words
Pattern: 04. Sliding Window
Difficulty: Hard

Interview Prompt:
You are given a string s and an array of strings words. All the strings of words have the same length.
    A concatenated substring is a substring that contains every string of words exactly once,
    in any order, with nothing in between.
    Return the starting indices of all concatenated substrings in s, in any order.

Why this pattern fits:
Because every word has the same length L, the string can be read as a sequence of L-sized tokens.
    The problem then becomes "find all windows of tokens that are a permutation of words",
    which is the Find All Anagrams window with words in place of characters.

Approach:
There are L possible alignments, one per starting offset in [0, L).
    For each offset, slide a window over tokens of length L:
    - If the token is not a required word, clear the window and restart after it.
    - Otherwise add it; while that word is now over its required count, drop tokens from the left.
    - When the window holds exactly words.length tokens, its left edge is an answer.

Worked Example:
Example:
    s = "barfoothefoobarman", words = [foo, bar]
    "barfoo" starts at index 0 and "foobar" starts at index 9. Answer: [0, 9].

Time Complexity: O(n * L), since each of the L offsets scans n / L tokens of length L
Space Complexity: O(number of words * L)
*/

public class P30_SubstringWithConcatenationOfAllWordsSlidingWindow {
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        int wordLength = words[0].length();
        int wordCount = words.length;

        Map<String, Integer> need = new HashMap<>();
        for (String word : words) {
            need.merge(word, 1, Integer::sum);
        }

        for (int offset = 0; offset < wordLength; offset++) {
            Map<String, Integer> window = new HashMap<>();
            int left = offset;
            int used = 0;

            for (int right = offset; right + wordLength <= s.length(); right += wordLength) {
                String token = s.substring(right, right + wordLength);
                if (!need.containsKey(token)) {
                    window.clear();
                    used = 0;
                    left = right + wordLength;
                    continue;
                }

                window.merge(token, 1, Integer::sum);
                used++;
                while (window.get(token) > need.get(token)) {
                    String dropped = s.substring(left, left + wordLength);
                    window.merge(dropped, -1, Integer::sum);
                    used--;
                    left += wordLength;
                }

                if (used == wordCount) {
                    result.add(left);
                }
            }
        }

        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
        System.out.println(findSubstring("wordgoodgoodgoodbestword",
            new String[]{"word", "good", "best", "word"}));
        System.out.println(findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"}));
    }
}
