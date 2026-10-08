import java.util.*;

/*
Problem: Longest String Chain
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
wordA is a predecessor of wordB if inserting exactly one letter anywhere in wordA makes it equal to wordB.
Return the length of the longest possible chain of words chosen from the given list.

Why this pattern fits:
A chain goes strictly from shorter to longer words, so sorting by length gives a valid processing order.
The predecessors of a word are found by deleting each letter, and a hash map stores the best chain per word.

Approach:
Sort by length. For each word, try removing each character; best = 1 + max(dp[shorter]) over shorter words in the map.
Store dp[word] and track the overall maximum.

Worked Example:
words = [a, b, ba, bca, bda, bdca] -> a -> ba -> bda -> bdca has length 4.

Time Complexity: O(n log n + n * L^2)
Space Complexity: O(n * L)
*/

public class P21_LongestStringChainDP {
    public static int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        Map<String, Integer> dp = new HashMap<>();
        int best = 0;
        for (String word : words) {
            int chain = 1;
            for (int i = 0; i < word.length(); i++) {
                String shorter = word.substring(0, i) + word.substring(i + 1);
                chain = Math.max(chain, dp.getOrDefault(shorter, 0) + 1);
            }
            dp.put(word, chain);
            best = Math.max(best, chain);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[a, b, ba, bca, bda, bdca] -> " + longestStrChain(new String[]{"a", "b", "ba", "bca", "bda", "bdca"}));
        System.out.println("[xbc, pcxbcf, xb, cxbc, pcxbc] -> "
                + longestStrChain(new String[]{"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"}));
        System.out.println("[abcd, dbqca] -> " + longestStrChain(new String[]{"abcd", "dbqca"}));
    }
}
