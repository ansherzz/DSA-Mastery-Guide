import java.util.*;

/*
Problem: Minimum Number of Arrows to Burst Balloons
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
There are some spherical balloons taped onto a flat wall that represents the XY-plane.
    points[i] = [xStart, xEnd] denotes a balloon whose horizontal diameter stretches between xStart and xEnd.
    An arrow shot straight up at position x bursts every balloon with xStart <= x <= xEnd.
    Return the minimum number of arrows that must be shot to burst all balloons.

Why this pattern fits:
This is interval scheduling. The balloon that ends first must be burst by some arrow,
    and shooting at its right end is the choice that also hits as many later balloons as possible.
    Repeating that choice is optimal.

Approach:
Sort the balloons by their end coordinate.
    Shoot the first arrow at the end of the first balloon.
    Every following balloon that starts at or before the arrow's position is already burst.
    When a balloon starts after the arrow's position, shoot a new arrow at that balloon's end.
    Compare with Integer.compare instead of subtraction, because the coordinates can be near the int limits.

Worked Example:
Example:
    points = [[10, 16], [2, 8], [1, 6], [7, 12]]
    Sorted by end: [1, 6], [2, 8], [7, 12], [10, 16].
    Arrow at x = 6 bursts the first two. Arrow at x = 12 bursts the other two. Answer = 2.

Time Complexity: O(n log n)
Space Complexity: O(1) besides the sort
*/

public class P31_MinimumNumberOfArrowsToBurstBalloonsHeapGreedy {
    public static int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
        int arrows = 1;
        int arrowAt = points[0][1];

        for (int[] balloon : points) {
            if (balloon[0] > arrowAt) {
                arrows++;
                arrowAt = balloon[1];
            }
        }
        return arrows;
    }

    public static void main(String[] args) {
        System.out.println(findMinArrowShots(new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}}));
        System.out.println(findMinArrowShots(new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}}));
        System.out.println(findMinArrowShots(new int[][]{{-2147483646, -2147483645}, {2147483646, 2147483647}}));
    }
}
