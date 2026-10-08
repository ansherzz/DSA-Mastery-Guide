import java.util.*;

/*
Problem: Minimized Maximum of Products Distributed to Any Store
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
There are n retail stores and several product types. quantities[i] is the number of items of product type i.
    Distribute all items to the stores with these rules:
    - a store can receive at most one product type, in any amount
    - a store may receive nothing

    Let x be the maximum number of items given to any single store. Return the minimum possible x.

Why this pattern fits:
"Minimize the maximum" is the classic signal for binary search on the answer.
    If a cap x works, any larger cap also works, so feasibility is monotonic.

Approach:
Search x between 1 and the largest quantity.
    For a candidate x, product type i needs ceil(quantities[i] / x) stores.
    If the total number of stores needed is at most n, the cap is feasible, so try smaller.

Worked Example:
Example:
    n = 6, quantities = [11, 6]
    With x = 3: 11 needs 4 stores and 6 needs 2 stores, total 6. Feasible.
    With x = 2: 11 needs 6 stores and 6 needs 3 stores, total 9. Not feasible. Answer = 3.

Time Complexity: O(m log(max(quantities)))
Space Complexity: O(1)
*/

public class P06_MinimizedMaximumOfProductsBinarySearchAnswer {
    private static boolean canDistribute(int n, int[] quantities, int cap) {
        long storesNeeded = 0;
        for (int quantity : quantities) {
            storesNeeded += (quantity + cap - 1) / cap;
        }
        return storesNeeded <= n;
    }

    public static int minimizedMaximum(int n, int[] quantities) {
        int left = 1;
        int right = 0;
        for (int quantity : quantities) {
            right = Math.max(right, quantity);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canDistribute(n, quantities, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static void runDemo(int n, int[] quantities) {
        System.out.println("Stores     : " + n);
        System.out.println("Quantities : " + Arrays.toString(quantities));
        System.out.println("Answer     : " + minimizedMaximum(n, quantities));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(6, new int[]{11, 6});
        runDemo(7, new int[]{15, 10, 10});
        runDemo(1, new int[]{100000});
    }
}
