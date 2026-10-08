import java.util.*;

/*
Problem: Data Stream as Disjoint Intervals
Pattern: 08. Advanced Search
Difficulty: Hard

Interview Prompt:
Numbers arrive one at a time. Implement addNum(value) and getIntervals(), which returns
the numbers seen so far summarized as a sorted list of disjoint intervals.

Why this pattern fits:
A new value can only interact with the interval ending just before it and the interval starting just after it.
An ordered map finds both neighbors by key search.

Approach:
Map start -> end. On addNum(v): if the floor interval already covers v, do nothing.
Otherwise start a new interval [v, v]; extend it left if the floor interval ends at v - 1,
and extend it right (removing the old entry) if an interval starts at v + 1.

Worked Example:
add 1, 3, 7 -> [1, 1], [3, 3], [7, 7]. add 2 -> [1, 3], [7, 7]. add 6 -> [1, 3], [6, 7].

Time Complexity: O(log n) per addNum, O(n) per getIntervals
Space Complexity: O(n)
*/

public class P50_DataStreamAsDisjointIntervalsAdvanced {
    static class SummaryRanges {
        private final TreeMap<Integer, Integer> intervals = new TreeMap<>();

        public void addNum(int value) {
            Map.Entry<Integer, Integer> before = intervals.floorEntry(value);
            if (before != null && before.getValue() >= value) {
                return;
            }
            int start = value;
            int end = value;
            if (before != null && before.getValue() == value - 1) {
                start = before.getKey();
            }
            Integer afterEnd = intervals.get(value + 1);
            if (afterEnd != null) {
                end = afterEnd;
                intervals.remove(value + 1);
            }
            intervals.put(start, end);
        }

        public List<List<Integer>> getIntervals() {
            List<List<Integer>> result = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : intervals.entrySet()) {
                result.add(Arrays.asList(entry.getKey(), entry.getValue()));
            }
            return result;
        }
    }

    public static void main(String[] args) {
        SummaryRanges ranges = new SummaryRanges();
        ranges.addNum(1);
        ranges.addNum(3);
        ranges.addNum(7);
        System.out.println("after 1, 3, 7 -> " + ranges.getIntervals());
        ranges.addNum(2);
        System.out.println("after 2 -> " + ranges.getIntervals());
        ranges.addNum(6);
        System.out.println("after 6 -> " + ranges.getIntervals());
    }
}
