import java.util.*;

/*
Problem: Longest Well-Performing Interval
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
A day is tiring if hours[i] > 8. An interval is well-performing if it has strictly more tiring days
than non-tiring days. Return the length of the longest well-performing interval.

Why this pattern fits:
Map tiring days to +1 and others to -1; the task becomes "longest subarray with positive sum".
Because the score changes by 1 each step, the earliest index with score - 1 is the best start.

Approach:
Keep the running score and the first index at which each score appeared.
If the score is positive, the whole prefix works. Otherwise, if score - 1 was seen at index j, the interval (j, i] works.

Worked Example:
hours = [9, 9, 6, 0, 6, 6, 9] -> [9, 9, 6] has two tiring days out of three. Answer = 3.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P35_LongestWellPerformingIntervalPrefixSum {
    public static int longestWPI(int[] hours) {
        Map<Integer, Integer> firstSeen = new HashMap<>();
        int score = 0;
        int best = 0;
        for (int i = 0; i < hours.length; i++) {
            score += hours[i] > 8 ? 1 : -1;
            if (score > 0) {
                best = i + 1;
            } else {
                firstSeen.putIfAbsent(score, i);
                Integer start = firstSeen.get(score - 1);
                if (start != null) {
                    best = Math.max(best, i - start);
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[9, 9, 6, 0, 6, 6, 9] -> " + longestWPI(new int[]{9, 9, 6, 0, 6, 6, 9}));
        System.out.println("[6, 6, 6] -> " + longestWPI(new int[]{6, 6, 6}));
    }
}
