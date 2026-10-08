import java.util.*;

/*
Problem: Maximum Number of Tasks You Can Assign
Pattern: 07. Binary Search on Answer
Difficulty: Hard

Interview Prompt:
Task i needs strength tasks[i]; worker j has strength workers[j] and can do one task.
You have `pills` pills, each raising one worker's strength by `strength` (at most one pill per worker).
Return the maximum number of tasks that can be completed.

Why this pattern fits:
If k tasks can be done, k - 1 can. To test k, use the k easiest tasks and the k strongest workers.
Processing the hardest task first makes the greedy choice clear.

Approach:
For the hardest remaining task: if the strongest remaining worker can do it unaided, use that worker.
Otherwise a pill is required, so give it to the weakest worker who can do the task with the pill.
If neither is possible, k is infeasible. An ordered multiset supplies both lookups.

Worked Example:
tasks = [3, 2, 1], workers = [0, 3, 3], pills = 1, strength = 1
Task 3 -> worker 3, task 2 -> worker 3, task 1 -> worker 0 with the pill. Answer = 3.

Time Complexity: O(n log^2 n)
Space Complexity: O(n)
*/

public class P45_MaximumNumberOfTasksYouCanAssignBinarySearchAnswer {
    private static void removeOne(TreeMap<Integer, Integer> multiset, int key) {
        if (multiset.merge(key, -1, Integer::sum) == 0) {
            multiset.remove(key);
        }
    }

    private static boolean canAssign(int[] tasks, int[] workers, int pills, int strength, int k) {
        TreeMap<Integer, Integer> available = new TreeMap<>();
        for (int i = workers.length - k; i < workers.length; i++) {
            available.merge(workers[i], 1, Integer::sum);
        }
        for (int i = k - 1; i >= 0; i--) {
            int strongest = available.lastKey();
            if (strongest >= tasks[i]) {
                removeOne(available, strongest);
            } else {
                Integer helper = available.ceilingKey(tasks[i] - strength);
                if (pills == 0 || helper == null) {
                    return false;
                }
                pills--;
                removeOne(available, helper);
            }
        }
        return true;
    }

    public static int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int low = 0;
        int high = Math.min(tasks.length, workers.length);
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (canAssign(tasks, workers, pills, strength, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("[3, 2, 1] / [0, 3, 3] -> " + maxTaskAssign(new int[]{3, 2, 1}, new int[]{0, 3, 3}, 1, 1));
        System.out.println("[5, 4] / [0, 0, 0] -> " + maxTaskAssign(new int[]{5, 4}, new int[]{0, 0, 0}, 1, 5));
        System.out.println("[10, 15, 30] / [0, 10, 10, 10, 10] -> "
                + maxTaskAssign(new int[]{10, 15, 30}, new int[]{0, 10, 10, 10, 10}, 3, 10));
    }
}
