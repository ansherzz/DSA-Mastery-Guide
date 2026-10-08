import java.util.*;

/*
Problem: Maximize Score of Numbers in Ranges
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
Interval i is [start[i], start[i] + d]. Choose one integer from each interval.
The score is the minimum absolute difference between any two chosen integers. Return the maximum possible score.

Why this pattern fits:
If a gap g is achievable, any smaller gap is too. Checking a gap is a greedy placement over sorted intervals.

Approach:
Sort the starts. For a gap g, put the first number at start[0]; each later number goes at
max(start[i], previous + g) and must not exceed start[i] + d. Bisect the largest feasible g.

Worked Example:
start = [6, 0, 3], d = 2 -> choose 0, 4, 8 from [0, 2], [3, 5], [6, 8]. Answer = 4.

Time Complexity: O(n log n + n log(range))
Space Complexity: O(1) extra
*/

public class P23_MaximizeScoreOfNumbersInRangesBinarySearchAnswer {
    private static boolean feasible(int[] start, int d, long gap) {
        long previous = start[0];
        for (int i = 1; i < start.length; i++) {
            long position = Math.max(start[i], previous + gap);
            if (position > (long) start[i] + d) {
                return false;
            }
            previous = position;
        }
        return true;
    }

    public static int maxPossibleScore(int[] start, int d) {
        Arrays.sort(start);
        long low = 0;
        long high = (long) start[start.length - 1] + d - start[0];
        while (low < high) {
            long mid = low + (high - low + 1) / 2;
            if (feasible(start, d, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return (int) low;
    }

    public static void main(String[] args) {
        System.out.println("[6, 0, 3], d = 2 -> " + maxPossibleScore(new int[]{6, 0, 3}, 2));
        System.out.println("[2, 6, 13, 13], d = 5 -> " + maxPossibleScore(new int[]{2, 6, 13, 13}, 5));
    }
}
