import java.util.*;

/*
Problem: Minimum Penalty for a Shop
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
You are given the customer visit log of a shop as a string customers of 'Y' and 'N' characters.
    'Y' at index i means customers come during hour i, and 'N' means no customers come.
    If the shop closes at hour j (0 <= j <= n), the penalty is:
    +1 for every hour the shop is open with no customers, and +1 for every hour it is closed while customers come.
    Return the earliest hour at which the shop should close to incur the minimum penalty.

Why this pattern fits:
The penalty for closing at hour j is (number of 'N' before j) + (number of 'Y' from j onward).
    Both terms are running counts, so moving j by one hour changes the penalty by exactly +1 or -1.

Approach:
Track the penalty relative to closing at hour 0.
    Moving the closing time past hour i changes the penalty by -1 if customers came ('Y', one fewer missed hour)
    and by +1 if they did not ('N', one more wasted hour).
    Scan once, and remember the earliest hour with the smallest running value.

Worked Example:
Example:
    customers = "YYNY"
    Relative penalties for closing at hours 0..4: 0, -1, -2, -1, -2.
    The minimum -2 first occurs at hour 2. Answer = 2.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P24_MinimumPenaltyForShopPrefixSum {
    public static int bestClosingTime(String customers) {
        int running = 0;
        int best = 0;
        int bestHour = 0;

        for (int i = 0; i < customers.length(); i++) {
            running += customers.charAt(i) == 'Y' ? -1 : 1;
            if (running < best) {
                best = running;
                bestHour = i + 1;
            }
        }
        return bestHour;
    }

    public static void main(String[] args) {
        System.out.println(bestClosingTime("YYNY"));
        System.out.println(bestClosingTime("NNNNN"));
        System.out.println(bestClosingTime("YYYY"));
    }
}
