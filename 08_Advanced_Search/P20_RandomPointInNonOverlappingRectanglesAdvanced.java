import java.util.*;

/*
Problem: Random Point in Non-overlapping Rectangles
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
Given non-overlapping axis-aligned rectangles [x1, y1, x2, y2], implement pick() that returns
a uniformly random integer point lying inside one of the rectangles (borders included).

Why this pattern fits:
Uniform over points means rectangles must be chosen in proportion to how many points they contain.
A prefix sum of point counts plus binary search maps a random rank to its rectangle.

Approach:
prefix[i] = total points in rectangles 0..i. Draw rank in [0, total). The rectangle is the first one with prefix > rank.
The offset inside that rectangle decodes into (x, y) using its width.

Worked Example:
rects = [[-2, -2, 1, 1], [2, 2, 4, 6]] -> 16 and 15 points. Ranks 0..15 map to rectangle 0 and 16..30 to rectangle 1.

Time Complexity: O(log n) per pick
Space Complexity: O(n)
*/

public class P20_RandomPointInNonOverlappingRectanglesAdvanced {
    static class Solution {
        private final int[][] rects;
        private final int[] prefix;
        private final Random random = new Random();

        Solution(int[][] rects) {
            this.rects = rects;
            prefix = new int[rects.length];
            int total = 0;
            for (int i = 0; i < rects.length; i++) {
                total += (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
                prefix[i] = total;
            }
        }

        int locate(int rank) {
            int low = 0;
            int high = prefix.length - 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (prefix[mid] > rank) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            return low;
        }

        public int[] pick() {
            int rank = random.nextInt(prefix[prefix.length - 1]);
            int index = locate(rank);
            int[] rect = rects[index];
            int offset = rank - (index == 0 ? 0 : prefix[index - 1]);
            int width = rect[2] - rect[0] + 1;
            return new int[]{rect[0] + offset % width, rect[1] + offset / width};
        }
    }

    public static void main(String[] args) {
        int[][] rects = {{-2, -2, 1, 1}, {2, 2, 4, 6}};
        Solution solution = new Solution(rects);
        for (int rank : new int[]{0, 15, 16, 30}) {
            System.out.println("rank " + rank + " lies in rectangle -> " + solution.locate(rank));
        }
        boolean allInside = true;
        for (int i = 0; i < 1000; i++) {
            int[] p = solution.pick();
            boolean inside = false;
            for (int[] r : rects) {
                inside |= p[0] >= r[0] && p[0] <= r[2] && p[1] >= r[1] && p[1] <= r[3];
            }
            allInside &= inside;
        }
        System.out.println("1000 picks all inside a rectangle -> " + allInside);
    }
}
