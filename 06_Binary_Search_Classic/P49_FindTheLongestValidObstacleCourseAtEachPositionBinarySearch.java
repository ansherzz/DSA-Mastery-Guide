import java.util.*;

/*
Problem: Find the Longest Valid Obstacle Course at Each Position
Pattern: 06. Binary Search - Classic Variations
Difficulty: Hard

Interview Prompt:
For each index i, find the length of the longest obstacle course that ends at obstacle i,
uses obstacles in their original order, and where each obstacle is at least as tall as the previous one.

Why this pattern fits:
This is "longest non-decreasing subsequence ending here" for every index.
The tails array of patience sorting gives each answer as the position where the value lands.

Approach:
tails[k] is the smallest possible last value of a non-decreasing subsequence of length k + 1.
For each height, find the first tail strictly greater than it (upper bound, because equal heights are allowed),
overwrite or append there, and report position + 1.

Worked Example:
obstacles = [3, 1, 5, 6, 4, 2] -> answers [1, 1, 2, 3, 2, 2].

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P49_FindTheLongestValidObstacleCourseAtEachPositionBinarySearch {
    public static int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int n = obstacles.length;
        int[] tails = new int[n];
        int size = 0;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int low = 0;
            int high = size;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (tails[mid] <= obstacles[i]) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            tails[low] = obstacles[i];
            if (low == size) {
                size++;
            }
            answer[i] = low + 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        for (int[] obstacles : new int[][]{{1, 2, 3, 2}, {2, 2, 1}, {3, 1, 5, 6, 4, 2}}) {
            System.out.println(Arrays.toString(obstacles) + " -> "
                    + Arrays.toString(longestObstacleCourseAtEachPosition(obstacles)));
        }
    }
}
