import java.util.*;

/*
Problem: Maximum Number of Achievable Transfer Requests
Pattern: 14. Backtracking
Difficulty: Hard

Interview Prompt:
Each request [from, to] moves one employee between buildings. A set of requests is achievable
if every building ends with the same number of employees it started with.
Return the maximum number of requests that can be granted together.

Why this pattern fits:
There are at most 16 requests, so all 2^16 subsets can be examined.
The balance array is updated on the way down and restored on the way up, which keeps each step O(1).

Approach:
dfs(index, taken): at the end, if all balances are zero, update the best count.
Otherwise skip the request, then take it (balance[from]--, balance[to]++), recurse, and undo.

Worked Example:
n = 5, requests = [[0,1],[1,0],[0,1],[1,2],[2,0],[3,4]] -> all except [3, 4] can be granted. Answer = 5.

Time Complexity: O(2^m * n)
Space Complexity: O(m + n)
*/

public class P50_MaximumNumberOfAchievableTransferRequestsBacktracking {
    private static int best;

    private static void dfs(int[][] requests, int index, int taken, int[] balance) {
        if (index == requests.length) {
            for (int value : balance) {
                if (value != 0) {
                    return;
                }
            }
            best = Math.max(best, taken);
            return;
        }
        if (taken + requests.length - index <= best) {
            return;
        }
        dfs(requests, index + 1, taken, balance);
        balance[requests[index][0]]--;
        balance[requests[index][1]]++;
        dfs(requests, index + 1, taken + 1, balance);
        balance[requests[index][0]]++;
        balance[requests[index][1]]--;
    }

    public static int maximumRequests(int n, int[][] requests) {
        best = 0;
        dfs(requests, 0, 0, new int[n]);
        return best;
    }

    public static void main(String[] args) {
        System.out.println("n = 5 -> " + maximumRequests(5, new int[][]{{0, 1}, {1, 0}, {0, 1}, {1, 2}, {2, 0}, {3, 4}}));
        System.out.println("n = 3 -> " + maximumRequests(3, new int[][]{{0, 0}, {1, 2}, {2, 1}}));
        System.out.println("n = 4 -> " + maximumRequests(4, new int[][]{{0, 3}, {3, 1}, {1, 2}, {2, 0}}));
    }
}
