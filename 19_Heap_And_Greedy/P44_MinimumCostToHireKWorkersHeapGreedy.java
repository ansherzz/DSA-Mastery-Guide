import java.util.*;

/*
Problem: Minimum Cost to Hire K Workers
Pattern: 19. Heap & Greedy
Difficulty: Hard

Interview Prompt:
Hire exactly k workers. Every hired worker must be paid in proportion to their quality,
and at least their minimum wage expectation. Return the least total amount of money needed.

Why this pattern fits:
With a common pay rate r per unit of quality, the cost is r * (sum of qualities), and r must be at least the
largest wage / quality ratio in the group. Fixing that largest ratio (by sorting), the best group is the k smallest qualities.

Approach:
Sort workers by ratio. Sweep in that order with a max-heap of qualities and a running sum.
When the heap exceeds k, drop the largest quality. Whenever it holds k workers, cost = sum * current ratio.

Worked Example:
quality = [10, 20, 5], wage = [70, 50, 30], k = 2 -> hire workers 0 and 2 at rate 7: 70 + 35 = 105.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P44_MinimumCostToHireKWorkersHeapGreedy {
    public static double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Double.compare((double) wage[a] / quality[a], (double) wage[b] / quality[b]));
        PriorityQueue<Integer> largestQualities = new PriorityQueue<>(Collections.reverseOrder());
        int qualitySum = 0;
        double best = Double.MAX_VALUE;
        for (int worker : order) {
            largestQualities.add(quality[worker]);
            qualitySum += quality[worker];
            if (largestQualities.size() > k) {
                qualitySum -= largestQualities.poll();
            }
            if (largestQualities.size() == k) {
                best = Math.min(best, qualitySum * ((double) wage[worker] / quality[worker]));
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("k = 2 -> " + String.format(Locale.ROOT, "%.5f",
                mincostToHireWorkers(new int[]{10, 20, 5}, new int[]{70, 50, 30}, 2)));
        System.out.println("k = 3 -> " + String.format(Locale.ROOT, "%.5f",
                mincostToHireWorkers(new int[]{3, 1, 10, 10, 1}, new int[]{4, 8, 2, 2, 7}, 3)));
    }
}
