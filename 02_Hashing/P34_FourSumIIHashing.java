import java.util.*;

/*
Problem: 4Sum II
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
Given four integer arrays a, b, c, and d, all of length n, return the number of tuples (i, j, k, l)
    such that a[i] + b[j] + c[k] + d[l] == 0.

Why this pattern fits:
Four nested loops cost O(n^4). Splitting the equation into (a + b) = -(c + d) lets you
    precompute one half in a hash map and look up the other half, which is the meet-in-the-middle idea.

Approach:
Count every possible sum a[i] + b[j] in a map.
    Then for every pair c[k] + d[l], add the number of stored sums equal to -(c[k] + d[l]).

Worked Example:
Example:
    a = [1, 2], b = [-2, -1], c = [-1, 2], d = [0, 2]
    Sums of a + b: {-1: 1, 0: 2, 1: 1}
    Sums of c + d: -1, 1, 2, 4. Their negations are 1, -1, -2, -4.
    The map holds 1 once and -1 once, and has no entry for -2 or -4, so the answer is 1 + 1 = 2.

Time Complexity: O(n^2) average
Space Complexity: O(n^2)
*/

public class P34_FourSumIIHashing {
    public static int fourSumCount(int[] a, int[] b, int[] c, int[] d) {
        Map<Integer, Integer> pairSums = new HashMap<>();
        for (int x : a) {
            for (int y : b) {
                pairSums.merge(x + y, 1, Integer::sum);
            }
        }

        int count = 0;
        for (int x : c) {
            for (int y : d) {
                count += pairSums.getOrDefault(-(x + y), 0);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Tuples: " + fourSumCount(
            new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2}));
        System.out.println("Tuples: " + fourSumCount(
            new int[]{0}, new int[]{0}, new int[]{0}, new int[]{0}));
    }
}
