import java.util.*;

/*
Problem: Product of the Last K Numbers
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Design a class that accepts a stream of integers with add(num)
and returns the product of the last k numbers with getProduct(k).

Why this pattern fits:
A range product is a ratio of two prefix products, just as a range sum is a difference of two prefix sums.
Zero breaks division, but a zero also makes every product crossing it equal to 0.

Approach:
Keep a list starting with 1. On add(num): if num is 0, reset the list to [1]; otherwise append last * num.
On getProduct(k): if fewer than k numbers follow the last zero, return 0; otherwise divide the last prefix by the one k back.

Worked Example:
add 3, 0, 2, 5, 4 -> getProduct(2) = 20, getProduct(3) = 40, getProduct(4) = 0 (crosses the zero).
add 8 -> getProduct(2) = 32.

Time Complexity: O(1) per operation
Space Complexity: O(n)
*/

public class P15_ProductOfTheLastKNumbersPrefixSum {
    static class ProductOfNumbers {
        private final List<Integer> prefix = new ArrayList<>();

        ProductOfNumbers() {
            prefix.add(1);
        }

        public void add(int num) {
            if (num == 0) {
                prefix.clear();
                prefix.add(1);
            } else {
                prefix.add(prefix.get(prefix.size() - 1) * num);
            }
        }

        public int getProduct(int k) {
            int size = prefix.size();
            if (k >= size) {
                return 0;
            }
            return prefix.get(size - 1) / prefix.get(size - 1 - k);
        }
    }

    public static void main(String[] args) {
        ProductOfNumbers stream = new ProductOfNumbers();
        for (int num : new int[]{3, 0, 2, 5, 4}) {
            stream.add(num);
        }
        System.out.println("getProduct(2) -> " + stream.getProduct(2));
        System.out.println("getProduct(3) -> " + stream.getProduct(3));
        System.out.println("getProduct(4) -> " + stream.getProduct(4));
        stream.add(8);
        System.out.println("getProduct(2) -> " + stream.getProduct(2));
    }
}
