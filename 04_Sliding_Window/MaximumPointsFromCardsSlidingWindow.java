import java.util.*;

/*
Problem: Maximum Points You Can Obtain from Cards
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
Cards are arranged in a row, and each card has a point value.
    In one step you can take one card from the beginning or from the end of the row.
    You must take exactly k cards. Return the maximum total score you can obtain.

Why this pattern fits:
Taking k cards from the two ends leaves a contiguous block of n - k cards in the middle.
    Maximizing what you take is the same as minimizing that middle block, which is a fixed-size window.

Approach:
Compute the total sum of all cards.
    Slide a window of length n - k across the array and find its minimum sum.
    The answer is total - minimum window sum.

Worked Example:
Example:
    cardPoints = [1, 2, 3, 4, 5, 6, 1], k = 3
    Total = 22. The minimum window of length 4 is [1, 2, 3, 4] with sum 10.
    Answer = 22 - 10 = 12, which corresponds to taking 5, 6, and 1 from the right.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class MaximumPointsFromCardsSlidingWindow {
    public static int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int windowSize = n - k;

        int total = 0;
        int windowSum = 0;
        int minWindow = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            total += cardPoints[right];
            windowSum += cardPoints[right];
            if (right >= windowSize) {
                windowSum -= cardPoints[right - windowSize];
            }
            if (right >= windowSize - 1) {
                minWindow = Math.min(minWindow, windowSum);
            }
        }
        return total - minWindow;
    }

    private static void runDemo(int[] cardPoints, int k) {
        System.out.println("Cards: " + Arrays.toString(cardPoints) + ", k = " + k);
        System.out.println("Score: " + maxScore(cardPoints, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 3, 4, 5, 6, 1}, 3);
        runDemo(new int[]{2, 2, 2}, 2);
        runDemo(new int[]{9, 7, 7, 9, 7, 7, 9}, 7);
    }
}
