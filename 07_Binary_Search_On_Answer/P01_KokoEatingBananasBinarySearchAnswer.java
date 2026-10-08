import java.util.*;

/*
Problem: Koko Eating Bananas
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
Koko has several banana piles. She can choose one pile per hour and eat up to k bananas from it.
    If the pile has fewer than k bananas, she eats the whole pile and stops for that hour.
    Return the minimum integer speed k that lets her finish all piles within h hours.

    The answer itself is a number, and feasibility is monotonic.

Why this pattern fits:
If Koko can finish with speed k, she can also finish with any speed larger than k.
    That monotonic yes/no check makes binary search on the answer possible.

Approach:
Search k between 1 and the largest pile.
    A helper function computes the total hours needed for a candidate speed.

Worked Example:
Example:
    piles = [3, 6, 7, 11], h = 8
    The minimum feasible speed is 4.

Time Complexity: O(n log M), where M is the maximum pile size
Space Complexity: O(1)
*/

public class P01_KokoEatingBananasBinarySearchAnswer {
    private static boolean canFinish(int[] piles, int h, int speed) {
        long hours = 0;
        for (int pile : piles) {
            hours += (pile + speed - 1) / speed;
        }
        return hours <= h;
    }

    public static int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(piles, h, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static void runDemo(int[] piles, int h) {
        System.out.println("Piles : " + Arrays.toString(piles));
        System.out.println("Hours : " + h);
        System.out.println("Speed : " + minEatingSpeed(piles, h));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{3, 6, 7, 11}, 8);
        runDemo(new int[]{30, 11, 23, 4, 20}, 5);
    }
}
