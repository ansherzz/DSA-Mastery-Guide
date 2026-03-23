import java.util.*;

/*
Problem: Magnetic Force Between Two Balls
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
You are given sorted or unsorted basket positions on a line and an integer m.
    Place m balls in the baskets so that the minimum distance between any two placed balls is as large as possible.
    Return that largest possible minimum distance.

    This is the 'maximize the minimum' version of binary search on answer.

Why this pattern fits:
If a minimum distance d is feasible, then every smaller distance is also feasible.
    That monotonicity lets us binary search for the largest feasible distance.

Approach:
Sort the positions.
    Binary search the distance. A greedy helper places balls from left to right whenever the next basket is far enough away.

Worked Example:
Example:
    positions = [1, 2, 3, 4, 7], m = 3
    The best minimum distance is 3.

Time Complexity: O(n log R), where R is the coordinate range
Space Complexity: O(1) extra beyond sorting
*/

public class MagneticForceBetweenTwoBallsBinarySearchAnswer {
    private static boolean canPlace(int[] position, int m, int force) {
        int count = 1;
        int last = position[0];

        for (int i = 1; i < position.length; i++) {
            if (position[i] - last >= force) {
                count++;
                last = position[i];
            }
        }

        return count >= m;
    }

    public static int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int left = 1;
        int right = position[position.length - 1] - position[0];

        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (canPlace(position, m, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private static void runDemo(int[] position, int m) {
        System.out.println("Position: " + Arrays.toString(position));
        System.out.println("m       : " + m);
        System.out.println("Answer  : " + maxDistance(position, m));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 3, 4, 7}, 3);
        runDemo(new int[]{5, 4, 3, 2, 1, 1000000000}, 2);
    }
}
