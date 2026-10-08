import java.util.*;

/*
Problem: Segment Tree for Range Sum Queries
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Build a structure over an array supporting update(index, value) and query(left, right) (sum of a range),
both in O(log n).

Why this pattern fits:
A segment tree is divide and conquer frozen into a data structure: the answer for a range is the combination
of the answers for its two halves. Queries reuse the stored halves instead of recomputing them.

Approach:
build(node, low, high) recursively splits at mid and stores left + right.
update walks down to one leaf and recomputes the sums on the way back.
query returns the stored sum when the node's range is fully inside [left, right], 0 when it is outside,
and otherwise the sum of both children's answers.

Worked Example:
nums = [1, 3, 5, 7, 9, 11] -> query(1, 3) = 15. After update(1, 10): query(1, 3) = 22 and query(0, 5) = 43.

Time Complexity: O(n) build, O(log n) per update and query
Space Complexity: O(n)
*/

public class P36_SegmentTreeRangeSumQueryDivideConquer {
    static class SegmentTree {
        private final int[] tree;
        private final int n;

        SegmentTree(int[] nums) {
            n = nums.length;
            tree = new int[4 * n];
            build(nums, 1, 0, n - 1);
        }

        private void build(int[] nums, int node, int low, int high) {
            if (low == high) {
                tree[node] = nums[low];
                return;
            }
            int mid = (low + high) / 2;
            build(nums, 2 * node, low, mid);
            build(nums, 2 * node + 1, mid + 1, high);
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }

        public void update(int index, int value) {
            update(1, 0, n - 1, index, value);
        }

        private void update(int node, int low, int high, int index, int value) {
            if (low == high) {
                tree[node] = value;
                return;
            }
            int mid = (low + high) / 2;
            if (index <= mid) {
                update(2 * node, low, mid, index, value);
            } else {
                update(2 * node + 1, mid + 1, high, index, value);
            }
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }

        public int query(int left, int right) {
            return query(1, 0, n - 1, left, right);
        }

        private int query(int node, int low, int high, int left, int right) {
            if (right < low || high < left) {
                return 0;
            }
            if (left <= low && high <= right) {
                return tree[node];
            }
            int mid = (low + high) / 2;
            return query(2 * node, low, mid, left, right) + query(2 * node + 1, mid + 1, high, left, right);
        }
    }

    public static void main(String[] args) {
        SegmentTree tree = new SegmentTree(new int[]{1, 3, 5, 7, 9, 11});
        System.out.println("query(1, 3) -> " + tree.query(1, 3));
        tree.update(1, 10);
        System.out.println("after update(1, 10): query(1, 3) -> " + tree.query(1, 3));
        System.out.println("query(0, 5) -> " + tree.query(0, 5));
    }
}
