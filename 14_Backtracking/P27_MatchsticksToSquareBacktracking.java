import java.util.*;

/*
Problem: Matchsticks to Square
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Use every matchstick exactly once (without breaking any) to form a square.
Return true if it is possible.

Why this pattern fits:
Each stick must be assigned to one of four sides whose sums must all equal total / 4.
It is the bucket-assignment search, made practical by sorting and by skipping equivalent buckets.

Approach:
The total must be divisible by 4. Sort descending so large sticks fail early.
dfs(index): try the stick on each side that still has room, skipping a side whose current length equals
a side already tried (it would lead to the same search).

Worked Example:
[1, 1, 2, 2, 2] -> side length 2: sides are 2, 2, 2, and 1 + 1. Answer = true.

Time Complexity: O(4^n) worst case, far less with pruning
Space Complexity: O(n)
*/

public class P27_MatchsticksToSquareBacktracking {
    private static boolean place(int[] sticks, int index, int[] sides, int target) {
        if (index < 0) {
            return true;
        }
        for (int s = 0; s < 4; s++) {
            boolean sameAsEarlier = false;
            for (int earlier = 0; earlier < s; earlier++) {
                sameAsEarlier |= sides[earlier] == sides[s];
            }
            if (sameAsEarlier || sides[s] + sticks[index] > target) {
                continue;
            }
            sides[s] += sticks[index];
            if (place(sticks, index - 1, sides, target)) {
                return true;
            }
            sides[s] -= sticks[index];
        }
        return false;
    }

    public static boolean makesquare(int[] matchsticks) {
        int total = Arrays.stream(matchsticks).sum();
        if (total % 4 != 0) {
            return false;
        }
        Arrays.sort(matchsticks);
        return place(matchsticks, matchsticks.length - 1, new int[4], total / 4);
    }

    public static void main(String[] args) {
        System.out.println("[1, 1, 2, 2, 2] -> " + makesquare(new int[]{1, 1, 2, 2, 2}));
        System.out.println("[3, 3, 3, 3, 4] -> " + makesquare(new int[]{3, 3, 3, 3, 4}));
    }
}
