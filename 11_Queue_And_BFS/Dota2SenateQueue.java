import java.util.*;

/*
Problem: Dota2 Senate
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
The senate consists of senators from two parties, Radiant ('R') and Dire ('D'), given as a string in seating order.
    Voting proceeds in rounds, in seating order. In a round, each senator who still has rights can either
    ban one other senator from voting for the rest of the procedure, or announce victory
    if all remaining senators belong to the same party.
    Every senator plays optimally for their own party. Return "Radiant" or "Dire" for the party that wins.

Why this pattern fits:
The optimal move is greedy: ban the next opposing senator who would otherwise act soonest.
    Senators act in a repeating circular order, which a queue models by sending a senator to the back after acting.

Approach:
Keep one queue of seat indices per party.
    Repeatedly take the front of each queue. The smaller index acts first, bans the other,
    and rejoins its own queue with index + n so that it sits behind everyone in the next round.
    The banned senator is simply not re-added. The party whose queue is non-empty at the end wins.

Worked Example:
Example:
    senate = "RDD"
    R (seat 0) bans D (seat 1). D (seat 2) bans R (seat 0, now waiting for round 2).
    Only a Dire senator remains. Answer: "Dire".

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class Dota2SenateQueue {
    public static String predictPartyVictory(String senate) {
        int n = senate.length();
        Deque<Integer> radiant = new ArrayDeque<>();
        Deque<Integer> dire = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                radiant.offer(i);
            } else {
                dire.offer(i);
            }
        }

        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int r = radiant.poll();
            int d = dire.poll();
            if (r < d) {
                radiant.offer(r + n);
            } else {
                dire.offer(d + n);
            }
        }
        return radiant.isEmpty() ? "Dire" : "Radiant";
    }

    public static void main(String[] args) {
        System.out.println(predictPartyVictory("RD"));
        System.out.println(predictPartyVictory("RDD"));
        System.out.println(predictPartyVictory("DRRDRDRDRDDRDRDR"));
    }
}
