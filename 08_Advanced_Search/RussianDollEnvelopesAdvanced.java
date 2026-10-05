import java.util.*;

/*
Problem: Russian Doll Envelopes
Pattern: 08. Advanced Search
Difficulty: Hard

Interview Prompt:
You are given envelopes where envelopes[i] = [width, height].
    One envelope fits inside another only if both its width and its height are strictly smaller.
    Envelopes cannot be rotated.

    Return the maximum number of envelopes you can nest inside each other.

Why this pattern fits:
After sorting by width, the problem becomes a longest increasing subsequence on heights.
    The O(n log n) LIS algorithm keeps a sorted "tails" array and uses binary search to place each height,
    which is why this belongs with the advanced search problems.

Approach:
Sort by width ascending, and for equal widths by height descending.
    The descending tie-break prevents two envelopes of the same width from both being chosen.
    Then scan the heights. tails[len] holds the smallest possible ending height of an increasing run of length len + 1.
    For each height, binary search for the first tail >= height and replace it, or append if none exists.

Worked Example:
Example:
    envelopes = [[5, 4], [6, 4], [6, 7], [2, 3]]
    Sorted: [2, 3], [5, 4], [6, 7], [6, 4]. Heights: 3, 4, 7, 4.
    The longest strictly increasing run of heights is 3, 4, 7. Answer = 3.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class RussianDollEnvelopesAdvanced {
    public static int maxEnvelopes(int[][] envelopes) {
        int[][] sorted = envelopes.clone();
        Arrays.sort(sorted, (x, y) -> x[0] != y[0]
            ? Integer.compare(x[0], y[0])
            : Integer.compare(y[1], x[1]));

        int[] tails = new int[sorted.length];
        int size = 0;

        for (int[] envelope : sorted) {
            int height = envelope[1];

            int left = 0;
            int right = size;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < height) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            tails[left] = height;
            if (left == size) {
                size++;
            }
        }
        return size;
    }

    private static void runDemo(int[][] envelopes) {
        System.out.println("Envelopes : " + Arrays.deepToString(envelopes));
        System.out.println("Nested    : " + maxEnvelopes(envelopes));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}});
        runDemo(new int[][]{{1, 1}, {1, 1}, {1, 1}});
    }
}
