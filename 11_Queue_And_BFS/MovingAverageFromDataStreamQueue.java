import java.util.*;

/*
Problem: Moving Average from Data Stream
Pattern: 11. Queue / BFS
Difficulty: Easy

Interview Prompt:
Given a stream of integers and a window size, calculate the moving average of the values in the sliding window.
    Implement next(val), which adds a value to the stream and returns the average of the last `size` values
    (or of all values so far if fewer than `size` have arrived).

Why this pattern fits:
The window always drops its oldest value when a new one arrives.
    Oldest-out, newest-in is first-in-first-out, which is what a queue is for.

Approach:
Keep the window's values in a queue along with their running sum.
    On each new value, add it to the queue and the sum.
    If the queue is now larger than the window size, remove the oldest value and subtract it from the sum.
    Return sum / current queue size.

Worked Example:
Example with size 3:
    next(1)  -> 1 / 1 = 1.0
    next(10) -> (1 + 10) / 2 = 5.5
    next(3)  -> (1 + 10 + 3) / 3 = 4.666...
    next(5)  -> (10 + 3 + 5) / 3 = 6.0

Time Complexity: O(1) per call
Space Complexity: O(size)
*/

public class MovingAverageFromDataStreamQueue {
    static class MovingAverage {
        private final int size;
        private final Queue<Integer> window = new ArrayDeque<>();
        private long sum = 0;

        MovingAverage(int size) {
            this.size = size;
        }

        public double next(int val) {
            window.offer(val);
            sum += val;
            if (window.size() > size) {
                sum -= window.poll();
            }
            return (double) sum / window.size();
        }
    }

    public static void main(String[] args) {
        MovingAverage average = new MovingAverage(3);
        for (int value : new int[]{1, 10, 3, 5}) {
            System.out.printf("next(%d) -> %.4f%n", value, average.next(value));
        }
    }
}
