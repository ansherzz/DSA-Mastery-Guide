import java.util.*;

/*
Problem: Jump Game VII
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
You start at index 0 of a binary string s (s[0] = '0'). From index i you can jump to any index j with
i + minJump <= j <= min(i + maxJump, n - 1) and s[j] == '0'. Return true if the last index is reachable.

Why this pattern fits:
Each index is a node with edges to a range of later indices. Plain BFS would rescan overlapping ranges,
costing O(n * maxJump). Since ranges only move right, a "farthest scanned" pointer makes every index examined once.

Approach:
BFS from 0. For a popped index i, scan j from max(i + minJump, farthest + 1) to min(i + maxJump, n - 1),
enqueueing the zeros, then set farthest = i + maxJump.

Worked Example:
s = "011010", minJump = 2, maxJump = 3 -> 0 -> 3 -> 5. Answer = true.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P18_JumpGameVIIBFSGraph {
    public static boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        if (s.charAt(n - 1) != '0') {
            return false;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        int farthest = 0;
        while (!queue.isEmpty()) {
            int i = queue.poll();
            if (i == n - 1) {
                return true;
            }
            for (int j = Math.max(i + minJump, farthest + 1); j <= Math.min(i + maxJump, n - 1); j++) {
                if (s.charAt(j) == '0') {
                    queue.add(j);
                }
            }
            farthest = Math.max(farthest, i + maxJump);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("011010, 2, 3 -> " + canReach("011010", 2, 3));
        System.out.println("01101110, 2, 3 -> " + canReach("01101110", 2, 3));
    }
}
