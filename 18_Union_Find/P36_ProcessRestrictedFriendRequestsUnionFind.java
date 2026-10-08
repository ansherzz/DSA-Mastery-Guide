import java.util.*;

/*
Problem: Process Restricted Friend Requests
Pattern: 18. Union Find
Difficulty: Hard

Interview Prompt:
restrictions[i] = [x, y] means x and y can never be friends, directly or indirectly.
Process friend requests in order: a request succeeds (and the two become friends) unless it would violate a restriction.
Return whether each request succeeds.

Why this pattern fits:
A request merges two whole groups, so the check must be made on group representatives, not individuals.
Union find provides the representatives; the restriction list is small enough to scan per request.

Approach:
For a request (u, v) with roots ru and rv: for every restriction (x, y) with roots rx and ry,
the request fails if {rx, ry} equals {ru, rv}. If no restriction is violated, union the two groups.

Worked Example:
n = 3, restriction (0, 1): request (0, 2) succeeds; request (2, 1) would link 0 and 1 through 2, so it fails.

Time Complexity: O(requests * restrictions * alpha(n))
Space Complexity: O(n)
*/

public class P36_ProcessRestrictedFriendRequestsUnionFind {
    static class UnionFind {
        final int[] parent;
        final int[] size;
        int components;

        UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            components = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return false;
            }
            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
            components--;
            return true;
        }
    }

    public static boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        UnionFind sets = new UnionFind(n);
        boolean[] result = new boolean[requests.length];
        for (int i = 0; i < requests.length; i++) {
            int rootU = sets.find(requests[i][0]);
            int rootV = sets.find(requests[i][1]);
            boolean allowed = true;
            for (int[] restriction : restrictions) {
                int rootX = sets.find(restriction[0]);
                int rootY = sets.find(restriction[1]);
                if ((rootX == rootU && rootY == rootV) || (rootX == rootV && rootY == rootU)) {
                    allowed = false;
                    break;
                }
            }
            if (allowed) {
                sets.union(rootU, rootV);
            }
            result[i] = allowed;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("requests (0,2), (2,1) -> " + Arrays.toString(
                friendRequests(3, new int[][]{{0, 1}}, new int[][]{{0, 2}, {2, 1}})));
        System.out.println("requests (1,2), (0,2) -> " + Arrays.toString(
                friendRequests(3, new int[][]{{0, 1}}, new int[][]{{1, 2}, {0, 2}})));
        System.out.println("five people -> " + Arrays.toString(friendRequests(5,
                new int[][]{{0, 1}, {1, 2}, {2, 3}}, new int[][]{{0, 4}, {1, 2}, {3, 1}, {3, 4}})));
    }
}
