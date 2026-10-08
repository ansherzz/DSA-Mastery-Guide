import java.util.*;

/*
Problem: K-Similar Strings
Pattern: 11. Queue / BFS
Difficulty: Hard

Interview Prompt:
Strings s1 and s2 are anagrams. Return the smallest k such that s1 can be turned into s2
by swapping two letters of s1 exactly k times.

Why this pattern fits:
Each string is a state and each swap is an edge, so the answer is a shortest path.
The state space is huge unless moves are pruned: always repair the first mismatch,
and only swap in a letter that is itself out of place.

Approach:
BFS from s1. For a state, find the first index i where it differs from s2.
For every j > i with state[j] == s2[i] and state[j] != s2[j], swap i and j and enqueue the new string if unseen.

Worked Example:
s1 = "abc", s2 = "bca" -> "abc" -> "bac" -> "bca". Answer = 2.

Time Complexity: Exponential in the worst case, heavily pruned in practice
Space Complexity: O(number of states visited)
*/

public class P49_KSimilarStringsBFS {
    public static int kSimilarity(String s1, String s2) {
        Deque<String> queue = new ArrayDeque<>();
        Set<String> seen = new HashSet<>();
        queue.add(s1);
        seen.add(s1);
        int swaps = 0;
        while (!queue.isEmpty()) {
            for (int size = queue.size(); size > 0; size--) {
                String current = queue.poll();
                if (current.equals(s2)) {
                    return swaps;
                }
                int i = 0;
                while (current.charAt(i) == s2.charAt(i)) {
                    i++;
                }
                char[] chars = current.toCharArray();
                for (int j = i + 1; j < chars.length; j++) {
                    if (chars[j] == s2.charAt(i) && chars[j] != s2.charAt(j)) {
                        char temp = chars[i];
                        chars[i] = chars[j];
                        chars[j] = temp;
                        String next = new String(chars);
                        if (seen.add(next)) {
                            queue.add(next);
                        }
                        chars[j] = chars[i];
                        chars[i] = temp;
                    }
                }
            }
            swaps++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("ab, ba -> " + kSimilarity("ab", "ba"));
        System.out.println("abc, bca -> " + kSimilarity("abc", "bca"));
        System.out.println("aabc, abca -> " + kSimilarity("aabc", "abca"));
    }
}
