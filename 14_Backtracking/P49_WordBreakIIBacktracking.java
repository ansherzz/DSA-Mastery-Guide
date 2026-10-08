import java.util.*;

/*
Problem: Word Break II
Pattern: 14. Backtracking
Difficulty: Hard

Interview Prompt:
Given a string s and a dictionary, add spaces to s so that every piece is a dictionary word.
Return all possible sentences. Words may be reused.

Why this pattern fits:
All segmentations are required, so each valid prefix word opens a branch.
Without pruning, strings like "aaaa...b" explode; remembering which start indices cannot be completed removes that waste.

Approach:
dfs(start, path): at the end join the path into a sentence. For each end > start, if s[start..end) is a word,
add it and recurse from end. If a start index produced no sentence, mark it as a dead end and skip it next time.

Worked Example:
s = "catsanddog", dictionary = [cat, cats, and, sand, dog] -> "cat sand dog" and "cats and dog".

Time Complexity: O(2^n) in the worst case (the output itself can be exponential)
Space Complexity: O(n) besides the output
*/

public class P49_WordBreakIIBacktracking {
    private static boolean dfs(String s, int start, Set<String> words, boolean[] deadEnd,
                               Deque<String> path, List<String> result) {
        if (start == s.length()) {
            List<String> ordered = new ArrayList<>(path);
            Collections.reverse(ordered);
            result.add(String.join(" ", ordered));
            return true;
        }
        if (deadEnd[start]) {
            return false;
        }
        boolean found = false;
        for (int end = start + 1; end <= s.length(); end++) {
            String piece = s.substring(start, end);
            if (words.contains(piece)) {
                path.push(piece);
                found |= dfs(s, end, words, deadEnd, path, result);
                path.pop();
            }
        }
        if (!found) {
            deadEnd[start] = true;
        }
        return found;
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        dfs(s, 0, new HashSet<>(wordDict), new boolean[s.length()], new ArrayDeque<>(), result);
        return result;
    }

    public static void main(String[] args) {
        List<String> dictionary = Arrays.asList("cat", "cats", "and", "sand", "dog");
        System.out.println("catsanddog -> " + wordBreak("catsanddog", dictionary));
        System.out.println("catsandog -> " + wordBreak("catsandog", dictionary));
    }
}
