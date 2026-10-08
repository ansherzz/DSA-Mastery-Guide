import java.util.*;

/*
Problem: Range Sum Query - Mutable (Fenwick Tree)
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Design a structure over an integer array that supports update(index, val)
and sumRange(left, right), with many calls of both kinds interleaved.

Why this pattern fits:
A plain prefix array answers queries in O(1) but needs O(n) per update.
A Fenwick tree stores partial sums over power-of-two blocks so both operations cost O(log n).

Approach:
tree[i] covers the block of length lowbit(i) ending at i (1-indexed).
add(i, delta): walk upward with i += i & -i. prefix(i): walk downward with i -= i & -i.
update writes the difference between the new and old value; sumRange = prefix(right + 1) - prefix(left).

Worked Example:
nums = [1, 3, 5] -> sumRange(0, 2) = 9. update(1, 2) -> sumRange(0, 2) = 8 and sumRange(1, 2) = 7.

Time Complexity: O(log n) per operation, O(n log n) to build
Space Complexity: O(n)
*/

public class P45_RangeSumQueryMutablePrefixSum {
    static class NumArray {
        private final int[] nums;
        private final int[] tree;

        NumArray(int[] values) {
            nums = new int[values.length];
            tree = new int[values.length + 1];
            for (int i = 0; i < values.length; i++) {
                update(i, values[i]);
            }
        }

        public void update(int index, int val) {
            int delta = val - nums[index];
            nums[index] = val;
            for (int i = index + 1; i < tree.length; i += i & -i) {
                tree[i] += delta;
            }
        }

        private int prefix(int count) {
            int sum = 0;
            for (int i = count; i > 0; i -= i & -i) {
                sum += tree[i];
            }
            return sum;
        }

        public int sumRange(int left, int right) {
            return prefix(right + 1) - prefix(left);
        }
    }

    public static void main(String[] args) {
        NumArray array = new NumArray(new int[]{1, 3, 5});
        System.out.println("sumRange(0, 2) -> " + array.sumRange(0, 2));
        array.update(1, 2);
        System.out.println("after update(1, 2): sumRange(0, 2) -> " + array.sumRange(0, 2));
        System.out.println("sumRange(1, 2) -> " + array.sumRange(1, 2));
    }
}
