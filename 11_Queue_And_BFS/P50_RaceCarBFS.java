import java.util.*;

/*
Problem: Race Car
Pattern: 11. Queue / BFS
Difficulty: Hard

Interview Prompt:
A car starts at position 0 with speed +1. Instruction A moves it by its speed and doubles the speed.
Instruction R keeps the position and sets the speed to -1 if it was positive, otherwise to +1.
Return the length of the shortest instruction sequence that reaches the target position.

Why this pattern fits:
Position alone does not describe the car; the speed determines every future move.
The shortest instruction sequence is a BFS over (position, speed) pairs.

Approach:
BFS from (0, 1). For each state try A -> (pos + speed, speed * 2) and R -> (pos, +/-1).
Only keep positions in (0, 2 * target), since going further away is never needed.

Worked Example:
target = 6 -> "AAARA": positions 0 -> 1 -> 3 -> 7 -> 7 -> 6. Answer = 5.

Time Complexity: O(target * log target) states
Space Complexity: O(target * log target)
*/

public class P50_RaceCarBFS {
    public static int racecar(int target) {
        Deque<int[]> queue = new ArrayDeque<>();
        Set<String> seen = new HashSet<>();
        queue.add(new int[]{0, 1});
        seen.add("0,1");
        int steps = 0;
        while (!queue.isEmpty()) {
            for (int size = queue.size(); size > 0; size--) {
                int[] state = queue.poll();
                int position = state[0];
                int speed = state[1];
                if (position == target) {
                    return steps;
                }
                int[][] nextStates = {{position + speed, speed * 2}, {position, speed > 0 ? -1 : 1}};
                for (int[] next : nextStates) {
                    String key = next[0] + "," + next[1];
                    if (next[0] > 0 && next[0] < 2 * target && seen.add(key)) {
                        queue.add(next);
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("target = 3 -> " + racecar(3));
        System.out.println("target = 6 -> " + racecar(6));
    }
}
