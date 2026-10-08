import java.util.*;

/*
Problem: Construct the Lexicographically Largest Valid Sequence
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Build a sequence of length 2n - 1 where 1 appears once, each integer from 2 to n appears twice,
and the two copies of i are exactly i positions apart. Return the lexicographically largest such sequence.

Why this pattern fits:
Placing a number fixes two positions at once, and a placement can make later slots impossible - so choices
must be reversible. Trying larger numbers first means the first complete sequence found is the largest.

Approach:
dfs(position): skip filled slots. For value from n down to 1 (if unused): place it at position
(and at position + value when value > 1, if in range and free), recurse, and undo on failure.

Worked Example:
n = 3 -> [3, 1, 2, 3, 2]: the 3s are 3 apart and the 2s are 2 apart.

Time Complexity: O(n!) worst case, fast in practice
Space Complexity: O(n)
*/

public class P29_ConstructTheLexicographicallyLargestValidSequenceBacktracking {
    private static boolean fill(int[] sequence, boolean[] used, int position, int n) {
        while (position < sequence.length && sequence[position] != 0) {
            position++;
        }
        if (position == sequence.length) {
            return true;
        }
        for (int value = n; value >= 1; value--) {
            if (used[value]) {
                continue;
            }
            int partner = value == 1 ? position : position + value;
            if (partner >= sequence.length || sequence[partner] != 0) {
                continue;
            }
            sequence[position] = value;
            sequence[partner] = value;
            used[value] = true;
            if (fill(sequence, used, position + 1, n)) {
                return true;
            }
            sequence[position] = 0;
            sequence[partner] = 0;
            used[value] = false;
        }
        return false;
    }

    public static int[] constructDistancedSequence(int n) {
        int[] sequence = new int[2 * n - 1];
        fill(sequence, new boolean[n + 1], 0, n);
        return sequence;
    }

    public static void main(String[] args) {
        System.out.println("n = 3 -> " + Arrays.toString(constructDistancedSequence(3)));
        System.out.println("n = 5 -> " + Arrays.toString(constructDistancedSequence(5)));
    }
}
