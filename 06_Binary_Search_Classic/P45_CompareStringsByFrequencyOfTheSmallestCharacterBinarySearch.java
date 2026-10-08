import java.util.*;

/*
Problem: Compare Strings by Frequency of the Smallest Character
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
Let f(s) be the frequency of the lexicographically smallest character of s.
For each query string, count the words W such that f(query) < f(W).

Why this pattern fits:
After mapping strings to numbers, each query asks "how many values are greater than x"
in a fixed collection - sort once, then one upper-bound search per query.

Approach:
Compute f for every word and sort those values.
For each query, find the first index whose value is greater than f(query); the count is n - index.

Worked Example:
queries = ["bbb", "cc"], words = ["a", "aa", "aaa", "aaaa"] -> f values 3 and 2 versus [1, 2, 3, 4]. Answer = [1, 2].

Time Complexity: O((n + q) * (L + log n))
Space Complexity: O(n)
*/

public class P45_CompareStringsByFrequencyOfTheSmallestCharacterBinarySearch {
    private static int f(String s) {
        char smallest = 'z';
        int count = 0;
        for (char ch : s.toCharArray()) {
            if (ch < smallest) {
                smallest = ch;
                count = 1;
            } else if (ch == smallest) {
                count++;
            }
        }
        return count;
    }

    public static int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] values = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            values[i] = f(words[i]);
        }
        Arrays.sort(values);
        int[] answer = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int target = f(queries[q]);
            int low = 0;
            int high = values.length;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (values[mid] <= target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            answer[q] = values.length - low;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println("[cbd] vs [zaaaz] -> "
                + Arrays.toString(numSmallerByFrequency(new String[]{"cbd"}, new String[]{"zaaaz"})));
        System.out.println("[bbb, cc] vs [a, aa, aaa, aaaa] -> "
                + Arrays.toString(numSmallerByFrequency(new String[]{"bbb", "cc"}, new String[]{"a", "aa", "aaa", "aaaa"})));
    }
}
