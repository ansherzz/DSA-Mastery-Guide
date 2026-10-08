import java.util.*;

/*
Problem: Count Vowel Strings in Ranges
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
You are given an array of lowercase words and a list of queries where queries[i] = [left, right].
    For each query, count how many words with indices in [left, right] both start and end with a vowel.
    Return the answers as an array.

Why this pattern fits:
Each word either qualifies (1) or does not (0), independent of the query.
    Counting qualifying words in a range is a range sum over that 0/1 array, so prefix sums answer each query in O(1).

Approach:
Build prefix where prefix[i] is the number of qualifying words among the first i words.
    The answer to [left, right] is prefix[right + 1] - prefix[left].

Worked Example:
Example:
    words = ["aba", "bcb", "ece", "aa", "e"]
    Qualifying flags: 1, 0, 1, 1, 1 -> prefix = [0, 1, 1, 2, 3, 4]
    Query [0, 2] -> 2, query [1, 4] -> 3, query [1, 1] -> 0

Time Complexity: O(n + q)
Space Complexity: O(n)
*/

public class P27_CountVowelStringsInRangesPrefixSum {
    public static int[] vowelStrings(String[] words, int[][] queries) {
        int[] prefix = new int[words.length + 1];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            boolean qualifies = isVowel(word.charAt(0)) && isVowel(word.charAt(word.length() - 1));
            prefix[i + 1] = prefix[i] + (qualifies ? 1 : 0);
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            answer[i] = prefix[queries[i][1] + 1] - prefix[queries[i][0]];
        }
        return answer;
    }

    private static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public static void main(String[] args) {
        String[] words = {"aba", "bcb", "ece", "aa", "e"};
        int[][] queries = {{0, 2}, {1, 4}, {1, 1}};

        System.out.println("Words   : " + Arrays.toString(words));
        System.out.println("Queries : " + Arrays.deepToString(queries));
        System.out.println("Answers : " + Arrays.toString(vowelStrings(words, queries)));
    }
}
