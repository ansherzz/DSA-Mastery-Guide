import java.util.*;

/*
Problem: Nearest Free Slot Assignment
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
There are n parking slots numbered 0..n - 1, all free. Each arriving car has a preferred slot p and takes
the lowest-numbered free slot that is >= p. Return the slot given to each car, or -1 if none is available.

Why this pattern fits:
Scanning forward for a free slot is O(n) per car when slots fill up in runs.
If every occupied slot is merged into "whatever comes after it", path compression skips whole runs at once.

Approach:
parent[i] = i for free slots, plus a sentinel at index n. To place a car, slot = find(p).
If slot == n there is no room; otherwise occupy it by setting parent[slot] = slot + 1.

Worked Example:
n = 5, preferences [2, 2, 2, 0, 4, 3] -> slots 2, 3, 4, 0, then no slot at or after 4, then none at or after 3.

Time Complexity: O((n + q) * alpha(n))
Space Complexity: O(n)
*/

public class P27_NearestFreeSlotAssignmentUnionFind {
    private static int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public static int[] assignSlots(int n, int[] preferences) {
        int[] parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        int[] assigned = new int[preferences.length];
        for (int i = 0; i < preferences.length; i++) {
            int slot = find(parent, preferences[i]);
            if (slot == n) {
                assigned[i] = -1;
            } else {
                assigned[i] = slot;
                parent[slot] = slot + 1;
            }
        }
        return assigned;
    }

    public static void main(String[] args) {
        System.out.println("n = 5, [2, 2, 2, 0, 4, 3] -> " + Arrays.toString(assignSlots(5, new int[]{2, 2, 2, 0, 4, 3})));
    }
}
