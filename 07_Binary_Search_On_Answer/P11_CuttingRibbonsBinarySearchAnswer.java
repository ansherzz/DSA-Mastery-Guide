import java.util.*;

/*
Problem: Cutting Ribbons
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
You are given an array ribbons where ribbons[i] is the length of the ith ribbon, and an integer k.
    You may cut any ribbon into any number of pieces with positive integer lengths, or leave it uncut.
    You want k ribbons that all have the same positive integer length. Leftover pieces can be thrown away.

    Return the maximum possible length of the k ribbons, or 0 if it cannot be done.

Why this pattern fits:
If you can obtain k pieces of length x, you can certainly obtain k pieces of any shorter length.
    So feasibility is true up to some length and false after it, and the task is to find the last true.

Approach:
Search the length between 0 and the longest ribbon.
    A ribbon of length r yields r / x pieces of length x.
    Use the upper-middle so that moving left up to mid always makes progress.

Worked Example:
Example:
    ribbons = [9, 7, 5], k = 3
    Length 5: pieces = 1 + 1 + 1 = 3. Feasible.
    Length 6: pieces = 1 + 1 + 0 = 2. Not feasible. Answer = 5.

Time Complexity: O(n log(max(ribbons)))
Space Complexity: O(1)
*/

public class P11_CuttingRibbonsBinarySearchAnswer {
    private static boolean canCut(int[] ribbons, int k, int length) {
        long pieces = 0;
        for (int ribbon : ribbons) {
            pieces += ribbon / length;
        }
        return pieces >= k;
    }

    public static int maxLength(int[] ribbons, int k) {
        int left = 0;
        int right = 0;
        for (int ribbon : ribbons) {
            right = Math.max(right, ribbon);
        }

        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (canCut(ribbons, k, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private static void runDemo(int[] ribbons, int k) {
        System.out.println("Ribbons : " + Arrays.toString(ribbons));
        System.out.println("k       : " + k);
        System.out.println("Length  : " + maxLength(ribbons, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{9, 7, 5}, 3);
        runDemo(new int[]{7, 5, 9}, 4);
        runDemo(new int[]{5, 7, 9}, 22);
    }
}
