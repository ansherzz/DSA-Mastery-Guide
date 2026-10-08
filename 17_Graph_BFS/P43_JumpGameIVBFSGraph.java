import java.util.*;

/*
Problem: Jump Game IV
Pattern: 17. Graph BFS
Difficulty: Hard

Interview Prompt:
Given an array of integers arr, you are initially positioned at the first index of the array.
    In one step you can jump from index i to index i + 1, to index i - 1,
    or to any index j where arr[i] == arr[j] and i != j.
    Return the minimum number of steps to reach the last index of the array.

Why this pattern fits:
Every jump costs one step, so BFS gives the minimum.
    The danger is the equal-value edges: a value that appears k times creates about k^2 edges,
    and scanning them from every one of those indices makes the search quadratic.

Approach:
Group the indices by value in a map.
    Run a level-order BFS from index 0. For each index, enqueue its unvisited neighbors i - 1, i + 1,
    and every index in its value group.
    Then clear that value's group. After the first index with a given value is expanded, every index of that value
    has been enqueued, so the group never needs to be scanned again. This one line keeps the total work linear.

Worked Example:
Example:
    arr = [100, -23, -23, 404, 100, 23, 23, 23, 3, 404]
    0 -> 4 (same value 100) -> 3 (neighbor) -> 9 (same value 404). Answer = 3.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P43_JumpGameIVBFSGraph {
    public static int minJumps(int[] arr) {
        int n = arr.length;
        Map<Integer, List<Integer>> indicesByValue = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indicesByValue.computeIfAbsent(arr[i], key -> new ArrayList<>()).add(i);
        }

        boolean[] visited = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        visited[0] = true;

        for (int steps = 0; !queue.isEmpty(); steps++) {
            for (int size = queue.size(); size > 0; size--) {
                int index = queue.poll();
                if (index == n - 1) {
                    return steps;
                }

                List<Integer> neighbors = indicesByValue.get(arr[index]);
                neighbors.add(index - 1);
                neighbors.add(index + 1);
                for (int next : neighbors) {
                    if (next >= 0 && next < n && !visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
                neighbors.clear();
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(minJumps(new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3, 404}));
        System.out.println(minJumps(new int[]{7}));
        System.out.println(minJumps(new int[]{7, 6, 9, 6, 9, 6, 9, 7}));
    }
}
