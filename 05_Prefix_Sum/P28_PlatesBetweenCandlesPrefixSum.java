import java.util.*;

/*
Problem: Plates Between Candles
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
s contains plates '*' and candles '|'. For each query [left, right], return the number of plates
that lie between two candles inside the substring s[left..right].

Why this pattern fits:
Each query is a range count on a static string. Prefix counts give the plates in any range,
and precomputed nearest-candle arrays find the range that actually counts.

Approach:
Precompute plates[i] (plates before index i), nextCandle[i] (first candle at or after i),
and prevCandle[i] (last candle at or before i). For a query, a = nextCandle[left] and b = prevCandle[right];
if a < b the answer is plates[b] - plates[a], otherwise 0.

Worked Example:
s = "**|**|***|", query [2, 5] -> candles at 2 and 5 enclose 2 plates. Query [5, 9] -> 3 plates.

Time Complexity: O(n + q)
Space Complexity: O(n)
*/

public class P28_PlatesBetweenCandlesPrefixSum {
    public static int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] plates = new int[n + 1];
        int[] prevCandle = new int[n];
        int[] nextCandle = new int[n];
        int last = -1;
        for (int i = 0; i < n; i++) {
            plates[i + 1] = plates[i] + (s.charAt(i) == '*' ? 1 : 0);
            if (s.charAt(i) == '|') {
                last = i;
            }
            prevCandle[i] = last;
        }
        last = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '|') {
                last = i;
            }
            nextCandle[i] = last;
        }
        int[] answer = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int a = nextCandle[queries[q][0]];
            int b = prevCandle[queries[q][1]];
            if (a != -1 && b != -1 && a < b) {
                answer[q] = plates[b] - plates[a];
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println("**|**|***| -> " + Arrays.toString(platesBetweenCandles("**|**|***|", new int[][]{{2, 5}, {5, 9}})));
        System.out.println("***|**|*****|**||**|* -> " + Arrays.toString(platesBetweenCandles("***|**|*****|**||**|*",
                new int[][]{{1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}})));
    }
}
