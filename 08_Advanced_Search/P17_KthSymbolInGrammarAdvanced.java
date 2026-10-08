import java.util.*;

/*
Problem: K-th Symbol in Grammar
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
Row 1 is "0". Each next row replaces every 0 with 01 and every 1 with 10.
Return the k-th (1-indexed) symbol in row n without building the row.

Why this pattern fits:
Row n has 2^(n-1) symbols, far too many to generate. Its structure is recursive:
the first half equals row n - 1 and the second half is its complement, so each step discards half of the row.

Approach:
If n == 1 return 0. Let half = 2^(n-2). If k <= half, answer = solve(n - 1, k).
Otherwise answer = 1 - solve(n - 1, k - half).

Worked Example:
Row 4 is 0 1 1 0 1 0 0 1. For k = 5 we are in the second half, so the answer is 1 - (row 3, k = 1) = 1 - 0 = 1.

Time Complexity: O(n)
Space Complexity: O(n) recursion depth
*/

public class P17_KthSymbolInGrammarAdvanced {
    public static int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }
        int half = 1 << (n - 2);
        if (k <= half) {
            return kthGrammar(n - 1, k);
        }
        return 1 - kthGrammar(n - 1, k - half);
    }

    public static void main(String[] args) {
        System.out.println("n = 1, k = 1 -> " + kthGrammar(1, 1));
        System.out.println("n = 2, k = 2 -> " + kthGrammar(2, 2));
        System.out.println("n = 4, k = 5 -> " + kthGrammar(4, 5));
    }
}
