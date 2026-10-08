import java.util.*;

/*
Problem: Range Module
Pattern: 08. Advanced Search
Difficulty: Hard

Interview Prompt:
Design a structure that tracks half-open ranges [left, right) of real numbers with
addRange(left, right), queryRange(left, right) (is every number in the range tracked?), and removeRange(left, right).

Why this pattern fits:
The tracked set is a collection of disjoint sorted intervals.
Every operation only needs the interval just before a boundary, which an ordered map finds in O(log n).

Approach:
Map start -> end. add: merge with the interval covering `left` (if any) and absorb every interval starting within the new range.
query: the interval starting at or before left must extend to right.
remove: re-add the piece sticking out past `right`, trim the piece before `left`, and delete starts inside [left, right).

Worked Example:
addRange(10, 20), removeRange(14, 16) -> tracked [10, 14) and [16, 20).
queryRange(10, 14) = true, queryRange(13, 15) = false, queryRange(16, 17) = true.

Time Complexity: O(log n) amortized per operation
Space Complexity: O(n)
*/

public class P49_RangeModuleAdvanced {
    static class RangeModule {
        private final TreeMap<Integer, Integer> ranges = new TreeMap<>();

        public void addRange(int left, int right) {
            Map.Entry<Integer, Integer> before = ranges.floorEntry(left);
            if (before != null && before.getValue() >= left) {
                left = before.getKey();
                right = Math.max(right, before.getValue());
            }
            Map.Entry<Integer, Integer> next = ranges.higherEntry(left);
            while (next != null && next.getKey() <= right) {
                right = Math.max(right, next.getValue());
                ranges.remove(next.getKey());
                next = ranges.higherEntry(left);
            }
            ranges.put(left, right);
        }

        public boolean queryRange(int left, int right) {
            Map.Entry<Integer, Integer> before = ranges.floorEntry(left);
            return before != null && before.getValue() >= right;
        }

        public void removeRange(int left, int right) {
            Map.Entry<Integer, Integer> crossingRight = ranges.lowerEntry(right);
            if (crossingRight != null && crossingRight.getValue() > right) {
                ranges.put(right, crossingRight.getValue());
            }
            Map.Entry<Integer, Integer> crossingLeft = ranges.lowerEntry(left);
            if (crossingLeft != null && crossingLeft.getValue() > left) {
                ranges.put(crossingLeft.getKey(), left);
            }
            ranges.subMap(left, right).clear();
        }
    }

    public static void main(String[] args) {
        RangeModule module = new RangeModule();
        module.addRange(10, 20);
        module.removeRange(14, 16);
        System.out.println("queryRange(10, 14) -> " + module.queryRange(10, 14));
        System.out.println("queryRange(13, 15) -> " + module.queryRange(13, 15));
        System.out.println("queryRange(16, 17) -> " + module.queryRange(16, 17));
    }
}
