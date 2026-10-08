import java.util.*;

/*
Problem: Word Ladder
Pattern: 17. Graph BFS
Difficulty: Hard

Interview Prompt:
Given a begin word, an end word, and a word list, return the length of the shortest transformation sequence
    from begin to end such that only one letter changes at a time and every intermediate word is in the list.
    Return 0 if no transformation exists.

    This is BFS over word states.

Why this pattern fits:
Each word is a node, and an edge exists between words that differ by one character.
    Since each transformation costs 1, BFS finds the shortest chain.

Approach:
Put all dictionary words in a set.
    Start BFS from beginWord.
    For each position in the word, try replacing that character with 'a' to 'z' to generate neighbors.

Worked Example:
Example:
    beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
    The shortest sequence length is 5.

Time Complexity: O(N * L * 26) roughly, where L is word length
Space Complexity: O(N)
*/

public class P24_WordLadderBFSGraph {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dictionary = new HashSet<>(wordList);
        if (!dictionary.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int steps = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    return steps;
                }

                char[] chars = word.toCharArray();
                for (int pos = 0; pos < chars.length; pos++) {
                    char original = chars[pos];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[pos] = ch;
                        String next = new String(chars);
                        if (dictionary.contains(next) && visited.add(next)) {
                            queue.offer(next);
                        }
                    }
                    chars[pos] = original;
                }
            }
            steps++;
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(ladderLength(
            "hit",
            "cog",
            Arrays.asList("hot","dot","dog","lot","log","cog")
        ));
    }
}
