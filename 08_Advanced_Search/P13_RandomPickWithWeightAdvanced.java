import java.util.*;

/*
Problem: Random Pick with Weight
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
You are given an array of positive integers w, where w[i] is the weight of index i.
    Implement pickIndex(), which randomly returns an index so that the probability of returning index i
    is w[i] / sum(w).

Why this pattern fits:
Lay the weights end to end on a number line. Index i owns a segment of length w[i].
    Picking a uniformly random point on the line and finding the segment that contains it
    is a binary search over the prefix sums of the weights.

Approach:
Precompute prefix sums of the weights.
    To pick, draw a random integer target in [1, total].
    Binary search for the first prefix sum that is >= target; its index is the answer.

Worked Example:
Example:
    w = [1, 3]
    Prefix sums: [1, 4]. Targets 1 maps to index 0, and targets 2, 3, 4 map to index 1.
    So index 0 is returned 25% of the time and index 1 is returned 75% of the time.

Time Complexity: O(n) to build, O(log n) per pick
Space Complexity: O(n)
*/

public class P13_RandomPickWithWeightAdvanced {
    static class WeightedPicker {
        private final int[] prefix;
        private final Random random;

        WeightedPicker(int[] weights, Random random) {
            this.random = random;
            prefix = new int[weights.length];
            int running = 0;
            for (int i = 0; i < weights.length; i++) {
                running += weights[i];
                prefix[i] = running;
            }
        }

        public int pickIndex() {
            int target = random.nextInt(prefix[prefix.length - 1]) + 1;

            int left = 0;
            int right = prefix.length - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (prefix[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return left;
        }
    }

    private static void runDemo(int[] weights) {
        WeightedPicker picker = new WeightedPicker(weights, new Random(7));
        int[] hits = new int[weights.length];
        int trials = 100000;
        for (int i = 0; i < trials; i++) {
            hits[picker.pickIndex()]++;
        }

        System.out.println("Weights : " + Arrays.toString(weights));
        System.out.println("Hits out of " + trials + " picks: " + Arrays.toString(hits));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 3});
        runDemo(new int[]{2, 5, 3});
    }
}
