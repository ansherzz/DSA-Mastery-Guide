import java.util.*;

/*
Problem: Find Median from Data Stream
Pattern: 19. Heap & Greedy
Difficulty: Hard

Interview Prompt:
Design a data structure that supports adding integers from a stream and reporting the median of all
    numbers added so far.
    - addNum(num) adds a number
    - findMedian() returns the median: the middle value, or the average of the two middle values
      when the count is even

Why this pattern fits:
The median depends only on the largest value of the lower half and the smallest value of the upper half.
    Two heaps expose exactly those two values: a max-heap for the lower half and a min-heap for the upper half.
    Keeping the halves balanced makes the median available in O(1).

Approach:
Maintain two invariants: every value in the lower heap is at most every value in the upper heap,
    and the lower heap has either the same number of values as the upper heap or exactly one more.
    To add a number, push it into the lower heap, move the lower heap's maximum to the upper heap,
    and if the upper heap is now larger, move its minimum back.
    The median is the lower heap's top, or the average of both tops when the sizes are equal.

Worked Example:
Example:
    addNum(1) -> lower [1], upper []          median 1.0
    addNum(2) -> lower [1], upper [2]         median 1.5
    addNum(3) -> lower [1, 2], upper [3]      median 2.0

Time Complexity: addNum: O(log n), findMedian: O(1)
Space Complexity: O(n)
*/

public class P42_FindMedianFromDataStreamHeapGreedy {
    static class MedianFinder {
        private final PriorityQueue<Integer> lowerHalf = new PriorityQueue<>(Collections.reverseOrder());
        private final PriorityQueue<Integer> upperHalf = new PriorityQueue<>();

        public void addNum(int num) {
            lowerHalf.offer(num);
            upperHalf.offer(lowerHalf.poll());
            if (upperHalf.size() > lowerHalf.size()) {
                lowerHalf.offer(upperHalf.poll());
            }
        }

        public double findMedian() {
            if (lowerHalf.size() > upperHalf.size()) {
                return lowerHalf.peek();
            }
            return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        for (int value : new int[]{1, 2, 3, 10, -4, 7}) {
            finder.addNum(value);
            System.out.println("addNum(" + value + ") -> median " + finder.findMedian());
        }
    }
}
