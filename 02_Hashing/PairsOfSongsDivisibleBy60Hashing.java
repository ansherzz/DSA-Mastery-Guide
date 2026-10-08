import java.util.*;

/*
Problem: Pairs of Songs With Total Durations Divisible by 60
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
You are given a list of songs where the i-th song has a duration of time[i] seconds.
    Return the number of pairs of songs (i, j) with i < j whose total duration is divisible by 60.

Why this pattern fits:
This is Two Sum with a twist: the target is not a number but a remainder class.
    Only time[i] % 60 matters, so count how many earlier songs fall in each remainder bucket.

Approach:
For a song with remainder r, a partner must have remainder (60 - r) % 60.
    The extra % 60 handles r = 0, whose partner remainder is also 0.
    Scan the songs once. For each song, add the count of earlier songs in the partner bucket to the answer,
    then record the current song's remainder.
    Counting before recording guarantees every pair is counted exactly once with i < j.

Worked Example:
Example:
    time = [30, 20, 150, 100, 40]
    Remainders: 30, 20, 30, 40, 40.
    Pairs: (30, 150), (20, 100), (20, 40). Answer = 3.

Time Complexity: O(n)
Space Complexity: O(1), a fixed 60-slot table
*/

public class PairsOfSongsDivisibleBy60Hashing {
    public static long numPairsDivisibleBy60(int[] time) {
        int[] remainderCount = new int[60];
        long pairs = 0;

        for (int duration : time) {
            int remainder = duration % 60;
            pairs += remainderCount[(60 - remainder) % 60];
            remainderCount[remainder]++;
        }
        return pairs;
    }

    public static void main(String[] args) {
        System.out.println(numPairsDivisibleBy60(new int[]{30, 20, 150, 100, 40}));
        System.out.println(numPairsDivisibleBy60(new int[]{60, 60, 60}));
        System.out.println(numPairsDivisibleBy60(new int[]{10, 20}));
    }
}
