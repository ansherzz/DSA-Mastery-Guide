import java.util.*;

/*
Problem: Divide Chocolate
Pattern: 07. Binary Search on Answer
Difficulty: Hard

Interview Prompt:
A chocolate bar is made of chunks in a row, and sweetness[i] is the sweetness of chunk i.
    You want to share it with k friends, so you make k cuts and get k + 1 pieces of consecutive chunks.
    Being generous, you keep the piece with the minimum total sweetness and give the rest away.

    Return the maximum total sweetness of the piece you can keep by cutting optimally.

Why this pattern fits:
This is "maximize the minimum", the mirror image of Split Array Largest Sum.
    If you can cut the bar so that every piece has sweetness at least x, you can also do it for any smaller x.
    So the largest feasible x can be binary searched.

Approach:
Search x between the smallest chunk and (total sweetness / (k + 1)).
    For a candidate x, greedily cut a piece as soon as its running sweetness reaches x.
    If you can form at least k + 1 pieces this way, x is feasible.
    Use the upper-middle because you are looking for the last feasible value.

Worked Example:
Example:
    sweetness = [1, 2, 3, 4, 5, 6, 7, 8, 9], k = 5
    Cutting into [1, 2, 3], [4, 5], [6], [7], [8], [9] gives piece sums 6, 9, 6, 7, 8, 9.
    The minimum is 6, and no cutting does better. Answer = 6.

Time Complexity: O(n log(total / (k + 1)))
Space Complexity: O(1)
*/

public class P46_DivideChocolateBinarySearchAnswer {
    private static boolean canCut(int[] sweetness, int pieces, int minSweetness) {
        int formed = 0;
        int running = 0;
        for (int chunk : sweetness) {
            running += chunk;
            if (running >= minSweetness) {
                formed++;
                running = 0;
            }
        }
        return formed >= pieces;
    }

    public static int maximizeSweetness(int[] sweetness, int k) {
        int left = Integer.MAX_VALUE;
        int total = 0;
        for (int chunk : sweetness) {
            left = Math.min(left, chunk);
            total += chunk;
        }
        int right = total / (k + 1);

        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (canCut(sweetness, k + 1, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private static void runDemo(int[] sweetness, int k) {
        System.out.println("Sweetness : " + Arrays.toString(sweetness));
        System.out.println("k         : " + k);
        System.out.println("Answer    : " + maximizeSweetness(sweetness, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 5);
        runDemo(new int[]{5, 6, 7, 8, 9, 1, 2, 3, 4}, 8);
        runDemo(new int[]{1, 2, 2, 1, 2, 2, 1, 2, 2}, 2);
    }
}
