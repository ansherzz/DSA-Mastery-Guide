import java.util.*;

/*
Problem: Minimum Number of Taps to Open to Water a Garden
Pattern: 19. Heap & Greedy
Difficulty: Hard

Interview Prompt:
A garden spans [0, n] and tap i at position i waters [i - ranges[i], i + ranges[i]].
Return the minimum number of taps needed to water the whole garden, or -1 if impossible.

Why this pattern fits:
Covering a segment with the fewest intervals is the same greedy as the fewest jumps:
among all intervals starting within the area already covered, extend to the farthest right end.

Approach:
reach[left] = the farthest right end of any tap whose interval starts at left (clamped to 0).
Sweep positions with currentEnd and farthest: when the position reaches currentEnd, one more tap is needed
and currentEnd becomes farthest; if farthest did not move beyond the position, there is a gap.

Worked Example:
n = 5, ranges = [3, 4, 1, 1, 0, 0] -> tap 1 alone covers [-3, 5]. Answer = 1.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P49_MinimumNumberOfTapsToOpenToWaterAGardenHeapGreedy {
    public static int minTaps(int n, int[] ranges) {
        int[] reach = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int left = Math.max(0, i - ranges[i]);
            reach[left] = Math.max(reach[left], Math.min(n, i + ranges[i]));
        }
        int taps = 0;
        int currentEnd = 0;
        int farthest = 0;
        for (int position = 0; position < n; position++) {
            farthest = Math.max(farthest, reach[position]);
            if (position == currentEnd) {
                if (farthest <= position) {
                    return -1;
                }
                taps++;
                currentEnd = farthest;
            }
        }
        return taps;
    }

    public static void main(String[] args) {
        System.out.println("n = 5, [3, 4, 1, 1, 0, 0] -> " + minTaps(5, new int[]{3, 4, 1, 1, 0, 0}));
        System.out.println("n = 3, [0, 0, 0, 0] -> " + minTaps(3, new int[]{0, 0, 0, 0}));
    }
}
