import java.util.*;

/*
Problem: Online Election
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
You are given two integer arrays persons and times. The i-th vote was cast for persons[i] at time times[i],
    and times is strictly increasing.
    Implement a class with a query q(t) that returns the person leading the election at time t.
    Votes cast at time t count toward the query. In the case of a tie, the most recent vote among the tied candidates wins.

Why this pattern fits:
The leader only changes at vote times, so the leader after every vote can be precomputed.
    A query at an arbitrary time needs the last vote at or before it, which is an upper-bound search on the sorted times.

Approach:
Constructor: replay the votes in order, keeping each person's count and the current leader.
    A person becomes the leader when their count is >= the leader's count, which implements the "most recent vote wins" tie rule.
    Store the leader after each vote.
    Query: binary search for the last index with times[index] <= t and return the stored leader.

Worked Example:
Example:
    persons = [0, 1, 1, 0, 0, 1, 0], times = [0, 5, 10, 15, 20, 25, 30]
    Leaders after each vote: 0, 1, 1, 0, 0, 1, 0.
    q(3) = 0, q(12) = 1, q(25) = 1, q(15) = 0, q(24) = 0, q(8) = 1.

Time Complexity: O(n) to build, O(log n) per query
Space Complexity: O(n)
*/

public class P30_OnlineElectionBinarySearch {
    static class TopVotedCandidate {
        private final int[] times;
        private final int[] leaders;

        TopVotedCandidate(int[] persons, int[] times) {
            this.times = times;
            this.leaders = new int[persons.length];

            Map<Integer, Integer> votes = new HashMap<>();
            int leader = -1;
            for (int i = 0; i < persons.length; i++) {
                int count = votes.merge(persons[i], 1, Integer::sum);
                if (leader == -1 || count >= votes.get(leader)) {
                    leader = persons[i];
                }
                leaders[i] = leader;
            }
        }

        int q(int t) {
            int left = 0;
            int right = times.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (times[mid] <= t) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return leaders[left - 1];
        }
    }

    public static void main(String[] args) {
        TopVotedCandidate election = new TopVotedCandidate(
            new int[]{0, 1, 1, 0, 0, 1, 0}, new int[]{0, 5, 10, 15, 20, 25, 30});

        for (int t : new int[]{3, 12, 25, 15, 24, 8}) {
            System.out.println("Leader at time " + t + ": " + election.q(t));
        }
    }
}
