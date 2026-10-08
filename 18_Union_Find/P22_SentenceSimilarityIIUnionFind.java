import java.util.*;

/*
Problem: Sentence Similarity II
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
Two sentences are given as arrays of words, along with a list of similar word pairs.
    Similarity is symmetric and transitive: if "great" is similar to "good" and "good" is similar to "fine",
    then "great" is similar to "fine". A word is always similar to itself.

    Two sentences are similar if they have the same number of words and the words at each position are similar.
    Return true if the two sentences are similar.

Why this pattern fits:
A relation that is reflexive, symmetric, and transitive splits the words into equivalence classes.
    Union Find is the standard structure for building equivalence classes from pairs
    and then asking whether two items belong to the same class.

Approach:
Assign each distinct word in the pairs an integer id.
    Union the two ids of every pair.
    Then compare the sentences position by position: identical words are fine;
    otherwise both words must be known and must share the same root.

Worked Example:
Example:
    sentence1 = ["great", "acting", "skills"], sentence2 = ["fine", "drama", "talent"]
    pairs = [["great", "good"], ["fine", "good"], ["drama", "acting"], ["skills", "talent"]]
    great ~ good ~ fine, acting ~ drama, skills ~ talent. Every position matches. Answer = true.

Time Complexity: O((P + N) * alpha(P)) where P is the number of pairs and N is the sentence length
Space Complexity: O(P)
*/

public class P22_SentenceSimilarityIIUnionFind {
    static class DSU {
        int[] parent;
        int[] size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return false;
            }

            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }

            parent[rootB] = rootA;
            size[rootA] += size[rootB];
            return true;
        }
    }

    public static boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, String[][] similarPairs) {
        if (sentence1.length != sentence2.length) {
            return false;
        }

        Map<String, Integer> idByWord = new HashMap<>();
        for (String[] pair : similarPairs) {
            idByWord.putIfAbsent(pair[0], idByWord.size());
            idByWord.putIfAbsent(pair[1], idByWord.size());
        }

        DSU dsu = new DSU(idByWord.size());
        for (String[] pair : similarPairs) {
            dsu.union(idByWord.get(pair[0]), idByWord.get(pair[1]));
        }

        for (int i = 0; i < sentence1.length; i++) {
            String first = sentence1[i];
            String second = sentence2[i];
            if (first.equals(second)) {
                continue;
            }

            Integer firstId = idByWord.get(first);
            Integer secondId = idByWord.get(second);
            if (firstId == null || secondId == null || dsu.find(firstId) != dsu.find(secondId)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[][] pairs = {{"great", "good"}, {"fine", "good"}, {"drama", "acting"}, {"skills", "talent"}};

        System.out.println(areSentencesSimilarTwo(
            new String[]{"great", "acting", "skills"}, new String[]{"fine", "drama", "talent"}, pairs));
        System.out.println(areSentencesSimilarTwo(
            new String[]{"great", "acting", "skills"}, new String[]{"fine", "painting", "talent"}, pairs));
        System.out.println(areSentencesSimilarTwo(
            new String[]{"great"}, new String[]{"great", "good"}, pairs));
    }
}
