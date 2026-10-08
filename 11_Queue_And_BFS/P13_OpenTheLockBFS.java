import java.util.*;

/*
Problem: Open the Lock
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
You have a lock represented by a 4-wheel string from "0000" to "9999".
    Each move turns one wheel up or down by one digit.
    Some combinations are deadends and cannot be used.
    Return the minimum number of moves to reach the target from "0000", or -1 if impossible.

    This is BFS over an implicit state graph.

Why this pattern fits:
Each lock combination is a node, and each wheel turn generates neighbors.
    Since every move costs 1, BFS finds the shortest path.

Approach:
Use a queue starting from "0000", skipping deadends.
    For each state, generate 8 neighbors by turning one wheel up or down.

Worked Example:
Example:
    deadends = ["0201","0101","0102","1212","2002"], target = "0202"
    The minimum number of moves is 6.

Time Complexity: O(10^4) states in the worst case
Space Complexity: O(10^4)
*/

public class P13_OpenTheLockBFS {
    private static List<String> neighbors(String state) {
        List<String> result = new ArrayList<>();
        char[] chars = state.toCharArray();

        for (int i = 0; i < 4; i++) {
            char original = chars[i];

            chars[i] = original == '9' ? '0' : (char) (original + 1);
            result.add(new String(chars));

            chars[i] = original == '0' ? '9' : (char) (original - 1);
            result.add(new String(chars));

            chars[i] = original;
        }

        return result;
    }

    public static int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        if (dead.contains("0000")) {
            return -1;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.offer("0000");
        visited.add("0000");

        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                if (current.equals(target)) {
                    return steps;
                }

                for (String next : neighbors(current)) {
                    if (!dead.contains(next) && visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }
            steps++;
        }

        return -1;
    }

    public static void main(String[] args) {
        String[] deadends = {"0201","0101","0102","1212","2002"};
        System.out.println(openLock(deadends, "0202"));
        System.out.println(openLock(new String[]{"8888"}, "0009"));
    }
}
