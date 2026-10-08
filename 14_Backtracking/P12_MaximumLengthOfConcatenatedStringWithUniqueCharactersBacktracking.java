import java.util.*;

/*
Problem: Maximum Length of a Concatenated String with Unique Characters
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Choose a subsequence of the given strings whose concatenation has all unique characters.
Return the maximum possible length of such a concatenation.

Why this pattern fits:
Each string is either taken or skipped. A 26-bit mask makes the "no shared letters" test a single AND,
and words that repeat a letter internally can never be used.

Approach:
Convert each word to a mask (discard words with duplicate letters).
dfs(index, usedMask): try skipping the word, and try taking it when (mask & usedMask) == 0.
The answer is the largest bit count reached.

Worked Example:
["cha", "r", "act", "ers"] -> "chaers" or "acters" has length 6.

Time Complexity: O(2^n)
Space Complexity: O(n)
*/

public class P12_MaximumLengthOfConcatenatedStringWithUniqueCharactersBacktracking {
    private static int dfs(List<Integer> masks, int index, int used) {
        if (index == masks.size()) {
            return Integer.bitCount(used);
        }
        int best = dfs(masks, index + 1, used);
        if ((masks.get(index) & used) == 0) {
            best = Math.max(best, dfs(masks, index + 1, used | masks.get(index)));
        }
        return best;
    }

    public static int maxLength(List<String> arr) {
        List<Integer> masks = new ArrayList<>();
        for (String word : arr) {
            int mask = 0;
            boolean valid = true;
            for (char ch : word.toCharArray()) {
                int bit = 1 << (ch - 'a');
                if ((mask & bit) != 0) {
                    valid = false;
                }
                mask |= bit;
            }
            if (valid) {
                masks.add(mask);
            }
        }
        return dfs(masks, 0, 0);
    }

    public static void main(String[] args) {
        System.out.println("[un, iq, ue] -> " + maxLength(Arrays.asList("un", "iq", "ue")));
        System.out.println("[cha, r, act, ers] -> " + maxLength(Arrays.asList("cha", "r", "act", "ers")));
        System.out.println("[abcdefghijklmnopqrstuvwxyz] -> " + maxLength(Arrays.asList("abcdefghijklmnopqrstuvwxyz")));
        System.out.println("[aa, bb] -> " + maxLength(Arrays.asList("aa", "bb")));
    }
}
