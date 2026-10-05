import java.util.*;

/*
Problem: Maximum Candies Allocated to K Children
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
You are given piles of candies, where candies[i] is the size of pile i.
    You may split a pile into any number of smaller piles, but you cannot merge piles.
    There are k children. Each child must receive exactly one pile, and all children must receive the same number of candies.
    Some piles may go unused.

    Return the maximum number of candies each child can get. Return 0 if it is impossible to give every child a candy.

Why this pattern fits:
If every child can get x candies, then every child can also get x - 1.
    So feasibility is true for small x and false for large x, and you want the last true value.

Approach:
Search x between 0 and the largest pile.
    A pile of size c yields c / x piles of size x. If the total across all piles is at least k, x is feasible.
    Because you want the maximum feasible value, use the upper-middle and move left up on success.

Worked Example:
Example:
    candies = [5, 8, 6], k = 3
    x = 5: piles yield 1 + 1 + 1 = 3 children. Feasible.
    x = 6: piles yield 0 + 1 + 1 = 2 children. Not feasible. Answer = 5.

Time Complexity: O(n log(max(candies)))
Space Complexity: O(1)
*/

public class MaximumCandiesAllocatedToKChildrenBinarySearchAnswer {
    private static boolean canGive(int[] candies, long k, int perChild) {
        long children = 0;
        for (int pile : candies) {
            children += pile / perChild;
        }
        return children >= k;
    }

    public static int maximumCandies(int[] candies, long k) {
        int left = 0;
        int right = 0;
        for (int pile : candies) {
            right = Math.max(right, pile);
        }

        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (canGive(candies, k, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private static void runDemo(int[] candies, long k) {
        System.out.println("Candies : " + Arrays.toString(candies));
        System.out.println("k       : " + k);
        System.out.println("Answer  : " + maximumCandies(candies, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{5, 8, 6}, 3);
        runDemo(new int[]{2, 5}, 11);
    }
}
