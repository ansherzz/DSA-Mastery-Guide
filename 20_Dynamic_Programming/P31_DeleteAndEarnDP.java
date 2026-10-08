import java.util.*;

/*
Problem: Delete and Earn
Pattern: 20. Dynamic Programming
Difficulty: Medium

Interview Prompt:
You are given an integer array nums. You want to maximize the number of points you get by performing
    the following operation any number of times:
    pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element
    equal to nums[i] - 1 and every element equal to nums[i] + 1.
    Return the maximum number of points you can earn.

Why this pattern fits:
If you take one copy of a value, you should take all copies of it, since they are never blocked by each other.
    So the real choice is per value: take value v (earning v * count) and lose v - 1 and v + 1, or skip it.
    Laid out on the number line, that is exactly House Robber.

Approach:
Build points[v] = v * (number of times v appears).
    Then run the House Robber recurrence over v = 0..max:
    best(v) = max(best(v - 1), best(v - 2) + points[v]).
    Two rolling variables hold best(v - 1) and best(v - 2).

Worked Example:
Example:
    nums = [2, 2, 3, 3, 3, 4]
    points: 2 -> 4, 3 -> 9, 4 -> 4. Taking 3 earns 9 and removes 2 and 4. Taking 2 and 4 earns only 8. Answer = 9.

Time Complexity: O(n + M), where M is the largest value
Space Complexity: O(M)
*/

public class P31_DeleteAndEarnDP {
    public static int deleteAndEarn(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        int[] points = new int[max + 1];
        for (int num : nums) {
            points[num] += num;
        }

        int skipPrevious = 0;
        int best = 0;
        for (int value = 0; value <= max; value++) {
            int current = Math.max(best, skipPrevious + points[value]);
            skipPrevious = best;
            best = current;
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(deleteAndEarn(new int[]{3, 4, 2}));
        System.out.println(deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4}));
        System.out.println(deleteAndEarn(new int[]{8, 10, 4, 9, 1, 3, 5, 9, 4, 10}));
    }
}
