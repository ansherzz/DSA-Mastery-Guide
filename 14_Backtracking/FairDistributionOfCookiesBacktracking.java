import java.util.*;

/*
Problem: Fair Distribution of Cookies
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
You are given an integer array cookies, where cookies[i] is the number of cookies in the i-th bag,
    and an integer k for the number of children. All bags must be handed out, and a bag cannot be split.
    The unfairness of a distribution is the maximum total number of cookies obtained by a single child.
    Return the minimum unfairness over all distributions.

Why this pattern fits:
Each bag makes one decision: which child receives it. That is k choices per bag over a small number of bags,
    and no greedy assignment is guaranteed to be optimal, so the assignments must be searched.

Approach:
Assign bags one at a time. For the current bag, try giving it to each child, recurse, and take it back.
    Two prunings keep the search small:
    - If a child's total already reaches the best unfairness found so far, that branch cannot improve the answer.
    - If a child currently has zero cookies, giving the bag to any other empty child is symmetric,
      so stop after trying the first empty child.

Worked Example:
Example:
    cookies = [8, 15, 10, 20, 8], k = 2
    One child gets 8 + 15 + 8 = 31 and the other gets 10 + 20 = 30. The unfairness is 31, which is the minimum.

Time Complexity: O(k^n) worst case, heavily pruned
Space Complexity: O(n + k)
*/

public class FairDistributionOfCookiesBacktracking {
    public static int distributeCookies(int[] cookies, int k) {
        int[] best = {Integer.MAX_VALUE};
        assign(cookies, 0, new int[k], best);
        return best[0];
    }

    private static void assign(int[] cookies, int bag, int[] totals, int[] best) {
        if (bag == cookies.length) {
            int unfairness = 0;
            for (int total : totals) {
                unfairness = Math.max(unfairness, total);
            }
            best[0] = Math.min(best[0], unfairness);
            return;
        }

        for (int child = 0; child < totals.length; child++) {
            if (totals[child] + cookies[bag] < best[0]) {
                totals[child] += cookies[bag];
                assign(cookies, bag + 1, totals, best);
                totals[child] -= cookies[bag];
            }
            if (totals[child] == 0) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(distributeCookies(new int[]{8, 15, 10, 20, 8}, 2));
        System.out.println(distributeCookies(new int[]{6, 1, 3, 2, 2, 4, 1, 2}, 3));
    }
}
