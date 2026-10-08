import java.util.*;

/*
Problem: Ways to Make a Fair Array
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Remove exactly one index from nums. The array is fair if the sum of the even-indexed values
equals the sum of the odd-indexed values afterwards. Return how many indices can be removed to make it fair.

Why this pattern fits:
After a removal, elements to the left keep their index parity and elements to the right swap it.
Prefix sums by parity on the left and suffix sums by parity on the right give the new totals in O(1).

Approach:
Keep evenLeft / oddLeft (before i) and evenRight / oddRight (after i).
New even sum = evenLeft + oddRight, new odd sum = oddLeft + evenRight. Count when they match.

Worked Example:
nums = [2, 1, 6, 4] -> removing index 1 leaves [2, 6, 4]: even sum 6, odd sum 6. Answer = 1.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P41_WaysToMakeAFairArrayPrefixSum {
    public static int waysToMakeFair(int[] nums) {
        int evenRight = 0;
        int oddRight = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                evenRight += nums[i];
            } else {
                oddRight += nums[i];
            }
        }
        int evenLeft = 0;
        int oddLeft = 0;
        int ways = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                evenRight -= nums[i];
            } else {
                oddRight -= nums[i];
            }
            if (evenLeft + oddRight == oddLeft + evenRight) {
                ways++;
            }
            if (i % 2 == 0) {
                evenLeft += nums[i];
            } else {
                oddLeft += nums[i];
            }
        }
        return ways;
    }

    public static void main(String[] args) {
        System.out.println("[2, 1, 6, 4] -> " + waysToMakeFair(new int[]{2, 1, 6, 4}));
        System.out.println("[1, 1, 1] -> " + waysToMakeFair(new int[]{1, 1, 1}));
        System.out.println("[1, 2, 3] -> " + waysToMakeFair(new int[]{1, 2, 3}));
    }
}
