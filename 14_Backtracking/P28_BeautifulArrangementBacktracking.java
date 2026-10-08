import java.util.*;

/*
Problem: Beautiful Arrangement
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Suppose you have n integers labeled 1 through n. A permutation of those integers perm (1-indexed)
    is a beautiful arrangement if for every position i, either perm[i] is divisible by i or i is divisible by perm[i].
    Given an integer n, return the number of beautiful arrangements you can construct.

Why this pattern fits:
The answer is a count of permutations that satisfy a per-position rule.
    Building the permutation one position at a time lets you reject a number the moment it violates the rule,
    which prunes most of the n! permutations.

Approach:
Fill positions one by one. For the current position, try every unused number that is compatible with it.
    Mark the number as used, recurse to the next position, then unmark it.
    When every position has been filled, one beautiful arrangement has been found.
    Filling from position n down to 1 prunes faster, because large positions have very few compatible numbers.

Worked Example:
Example:
    n = 2
    [1, 2]: 1 is divisible by position 1, and 2 is divisible by position 2.
    [2, 1]: 2 is divisible by position 1, and position 2 is divisible by 1. Answer = 2.

Time Complexity: O(k), where k is the number of valid partial permutations (far below n!)
Space Complexity: O(n)
*/

public class P28_BeautifulArrangementBacktracking {
    public static int countArrangement(int n) {
        return place(n, new boolean[n + 1]);
    }

    private static int place(int position, boolean[] used) {
        if (position == 0) {
            return 1;
        }

        int count = 0;
        for (int number = 1; number < used.length; number++) {
            if (!used[number] && (number % position == 0 || position % number == 0)) {
                used[number] = true;
                count += place(position - 1, used);
                used[number] = false;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countArrangement(1));
        System.out.println(countArrangement(2));
        System.out.println(countArrangement(4));
        System.out.println(countArrangement(6));
    }
}
