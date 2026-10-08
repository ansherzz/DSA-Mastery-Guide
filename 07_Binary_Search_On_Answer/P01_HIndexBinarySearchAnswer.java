import java.util.*;

/*
Problem: H-Index
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
citations[i] is the number of citations of a researcher's i-th paper (unsorted).
Return the h-index: the maximum h such that at least h papers have at least h citations each.

Why this pattern fits:
If h works, every smaller h also works. A yes...yes no...no pattern over the answer range [0, n]
means the largest feasible h can be bisected, with a simple counting check.

Approach:
Search h in [0, n]. For a candidate, count the papers with citations >= h.
If the count is at least h, try larger; otherwise try smaller.

Worked Example:
citations = [3, 0, 6, 1, 5] -> h = 3 works (3, 6, 5) but h = 4 does not (only 6 and 5). Answer = 3.

Time Complexity: O(n log n)
Space Complexity: O(1)
*/

public class P01_HIndexBinarySearchAnswer {
    public static int hIndex(int[] citations) {
        int low = 0;
        int high = citations.length;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            int count = 0;
            for (int c : citations) {
                if (c >= mid) {
                    count++;
                }
            }
            if (count >= mid) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("[3, 0, 6, 1, 5] -> " + hIndex(new int[]{3, 0, 6, 1, 5}));
        System.out.println("[1, 3, 1] -> " + hIndex(new int[]{1, 3, 1}));
        System.out.println("[0] -> " + hIndex(new int[]{0}));
    }
}
