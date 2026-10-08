import java.util.*;

/*
Problem: Number of Flowers in Full Bloom
Pattern: 08. Advanced Search
Difficulty: Hard

Interview Prompt:
flowers[i] = [start, end] means the flower is in bloom on every day from start to end inclusive.
people[j] is the arrival day of person j. Return, for each person, the number of flowers in bloom on that day.

Why this pattern fits:
Intervals do not need to be handled together: a flower is in bloom at t if it has started and has not ended.
Sorting starts and ends separately turns each query into two counts on sorted arrays.

Approach:
Sort all starts and all ends. For time t: started = number of starts <= t (upper bound),
finished = number of ends < t (lower bound). Answer = started - finished.

Worked Example:
flowers = [[1, 6], [3, 7], [9, 12], [4, 13]], people = [2, 3, 7, 11] -> [1, 2, 2, 2].

Time Complexity: O((n + q) log n)
Space Complexity: O(n)
*/

public class P48_NumberOfFlowersInFullBloomAdvanced {
    private static int countLessThan(int[] sorted, int value) {
        int low = 0;
        int high = sorted.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (sorted[mid] < value) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int n = flowers.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = flowers[i][0];
            ends[i] = flowers[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int[] answer = new int[people.length];
        for (int i = 0; i < people.length; i++) {
            answer[i] = countLessThan(starts, people[i] + 1) - countLessThan(ends, people[i]);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println("people [2, 3, 7, 11] -> " + Arrays.toString(
                fullBloomFlowers(new int[][]{{1, 6}, {3, 7}, {9, 12}, {4, 13}}, new int[]{2, 3, 7, 11})));
        System.out.println("people [3, 3, 2] -> " + Arrays.toString(
                fullBloomFlowers(new int[][]{{1, 10}, {3, 3}}, new int[]{3, 3, 2})));
    }
}
