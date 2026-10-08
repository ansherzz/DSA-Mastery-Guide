import java.util.*;

/*
Problem: Alien Dictionary
Pattern: 17. Graph BFS
Difficulty: Hard

Interview Prompt:
An alien language uses the lowercase English letters, but in an unknown order.
    You are given a list of words that is sorted lexicographically according to the alien alphabet.
    Return a string of the letters that appear in the words, in an order consistent with that alphabet.
    If the given word order is impossible, return an empty string. If several orders are valid, return any of them.

Why this pattern fits:
Each adjacent pair of words reveals at most one fact: at the first position where they differ,
    the letter of the earlier word comes before the letter of the later word.
    Those facts are directed edges between letters, and a consistent alphabet is a topological order,
    which Kahn's BFS algorithm produces while also detecting contradictions (cycles).

Approach:
Mark every letter that appears. Compare each adjacent pair of words to find their first differing position
    and add an edge between those two letters. If a longer word comes before its own prefix, the input is invalid.
    Run Kahn's algorithm over the letters that appear.
    If the result does not include every letter, there is a cycle, so return "".

Worked Example:
Example:
    words = ["wrt", "wrf", "er", "ett", "rftt"]
    wrt vs wrf  -> t before f
    wrf vs er   -> w before e
    er vs ett   -> r before t
    ett vs rftt -> e before r
    Order: w, e, r, t, f -> "wertf"

Time Complexity: O(total characters in all words)
Space Complexity: O(1), since there are at most 26 letters
*/

public class P38_AlienDictionaryBFSGraph {
    public static String alienOrder(String[] words) {
        boolean[] present = new boolean[26];
        boolean[][] edge = new boolean[26][26];
        int[] indegree = new int[26];

        for (String word : words) {
            for (char ch : word.toCharArray()) {
                present[ch - 'a'] = true;
            }
        }

        for (int i = 0; i + 1 < words.length; i++) {
            String first = words[i];
            String second = words[i + 1];
            int common = Math.min(first.length(), second.length());

            int position = 0;
            while (position < common && first.charAt(position) == second.charAt(position)) {
                position++;
            }

            if (position == common) {
                if (first.length() > second.length()) {
                    return "";
                }
                continue;
            }

            int before = first.charAt(position) - 'a';
            int after = second.charAt(position) - 'a';
            if (!edge[before][after]) {
                edge[before][after] = true;
                indegree[after]++;
            }
        }

        Queue<Integer> ready = new ArrayDeque<>();
        int letterCount = 0;
        for (int letter = 0; letter < 26; letter++) {
            if (present[letter]) {
                letterCount++;
                if (indegree[letter] == 0) {
                    ready.offer(letter);
                }
            }
        }

        StringBuilder order = new StringBuilder();
        while (!ready.isEmpty()) {
            int letter = ready.poll();
            order.append((char) ('a' + letter));

            for (int next = 0; next < 26; next++) {
                if (edge[letter][next] && --indegree[next] == 0) {
                    ready.offer(next);
                }
            }
        }
        return order.length() == letterCount ? order.toString() : "";
    }

    private static void runDemo(String... words) {
        System.out.println(Arrays.toString(words) + " -> \"" + alienOrder(words) + "\"");
    }

    public static void main(String[] args) {
        runDemo("wrt", "wrf", "er", "ett", "rftt");
        runDemo("z", "x");
        runDemo("z", "x", "z");
        runDemo("abc", "ab");
    }
}
