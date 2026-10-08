import java.util.*;

/*
Problem: Word Ladder II
Pattern: 17. Graph BFS
Difficulty: Hard

Interview Prompt:
Given beginWord, endWord, and a word list, return all shortest transformation sequences from beginWord to endWord
where each step changes one letter and every intermediate word is in the list.

Why this pattern fits:
BFS finds the shortest length, but all shortest paths are wanted. Storing every partial path during BFS explodes;
storing only distances and rebuilding paths backwards from the end visits just the useful words.

Approach:
BFS from beginWord over one-letter changes, recording distance[word]. If endWord is unreached return nothing.
DFS from endWord: move to any one-letter neighbor whose distance is exactly one less, until beginWord is reached.

Worked Example:
hit -> cog with [hot, dot, dog, lot, log, cog]: two shortest ladders of five words.

Time Complexity: O(N * L * 26) for the BFS plus the size of the output
Space Complexity: O(N * L)
*/

public class P50_WordLadderIIBFSGraph {
    private static List<String> neighbors(String word, Map<String, Integer> distance) {
        List<String> result = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char original = chars[i];
            for (char ch = 'a'; ch <= 'z'; ch++) {
                chars[i] = ch;
                String candidate = new String(chars);
                if (ch != original && distance.containsKey(candidate)) {
                    result.add(candidate);
                }
            }
            chars[i] = original;
        }
        return result;
    }

    private static void collect(String word, String beginWord, Map<String, Integer> distance,
                                Deque<String> path, List<List<String>> result) {
        path.push(word);
        if (word.equals(beginWord)) {
            result.add(new ArrayList<>(path));
        } else {
            for (String previous : neighbors(word, distance)) {
                if (distance.get(previous) == distance.get(word) - 1) {
                    collect(previous, beginWord, distance, path, result);
                }
            }
        }
        path.pop();
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        Map<String, Integer> distance = new HashMap<>();
        distance.put(beginWord, 0);
        Deque<String> queue = new ArrayDeque<>();
        queue.add(beginWord);
        while (!queue.isEmpty() && !distance.containsKey(endWord)) {
            for (int size = queue.size(); size > 0; size--) {
                String word = queue.poll();
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char original = chars[i];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;
                        String candidate = new String(chars);
                        if (words.contains(candidate) && !distance.containsKey(candidate)) {
                            distance.put(candidate, distance.get(word) + 1);
                            queue.add(candidate);
                        }
                    }
                    chars[i] = original;
                }
            }
        }
        List<List<String>> result = new ArrayList<>();
        if (distance.containsKey(endWord)) {
            collect(endWord, beginWord, distance, new ArrayDeque<>(), result);
        }
        result.sort((a, b) -> a.toString().compareTo(b.toString()));
        return result;
    }

    public static void main(String[] args) {
        System.out.println("hit to cog -> " + findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
        System.out.println("cog missing -> " + findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log")));
    }
}
