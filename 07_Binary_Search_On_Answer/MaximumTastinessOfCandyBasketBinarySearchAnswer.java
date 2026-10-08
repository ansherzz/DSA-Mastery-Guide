import java.util.*;

/*
Problem: Maximum Tastiness of Candy Basket
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
You are given an array price where price[i] is the price of the i-th candy, and a positive integer k.
    The store sells baskets of k distinct candies. The tastiness of a basket is the smallest absolute difference
    between the prices of any two candies in the basket.
    Return the maximum tastiness of a candy basket.

Why this pattern fits:
This is a "maximize the minimum gap" problem. If a basket with tastiness at least d exists,
    then one with tastiness at least d - 1 also exists, so feasibility is monotonic in d.

Approach:
Sort the prices. To check a gap d, pick candies greedily: take the cheapest one,
    then repeatedly take the next candy whose price is at least d above the last one taken.
    If at least k candies can be picked, d is feasible.
    Binary search for the largest feasible d between 0 and (max price - min price).

Worked Example:
Example:
    price = [13, 5, 1, 8, 21, 2], k = 3
    Sorted: [1, 2, 5, 8, 13, 21]. With d = 8, pick 1, 13, 21. With d = 9 only 1 and 13 can be picked. Answer = 8.

Time Complexity: O(n log n + n log(price range))
Space Complexity: O(1) besides the sort
*/

public class MaximumTastinessOfCandyBasketBinarySearchAnswer {
    public static int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int low = 0;
        int high = price[price.length - 1] - price[0];

        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (canPick(price, k, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private static boolean canPick(int[] price, int k, int gap) {
        int picked = 1;
        int last = price[0];
        for (int i = 1; i < price.length && picked < k; i++) {
            if (price[i] - last >= gap) {
                picked++;
                last = price[i];
            }
        }
        return picked >= k;
    }

    public static void main(String[] args) {
        System.out.println(maximumTastiness(new int[]{13, 5, 1, 8, 21, 2}, 3));
        System.out.println(maximumTastiness(new int[]{1, 3, 1}, 2));
        System.out.println(maximumTastiness(new int[]{7, 7, 7, 7}, 2));
    }
}
