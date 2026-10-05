import java.util.*;

/*
Problem: Tower of Hanoi
Pattern: 15. Divide & Conquer
Difficulty: Easy

Interview Prompt:
There are three rods and n disks of different sizes stacked on the first rod, largest at the bottom.
    Move the entire stack to the third rod under these rules:
    - only one disk may be moved at a time
    - only the top disk of a rod may be moved
    - a larger disk may never be placed on top of a smaller disk

    Print the sequence of moves and return how many moves were made.

Why this pattern fits:
The largest disk can only move when all n - 1 smaller disks are out of the way on the spare rod.
    That splits the problem into two smaller copies of itself around one trivial move,
    which is the essence of divide and conquer.

Approach:
To move n disks from source to target using a spare rod:
    1. move the top n - 1 disks from source to spare
    2. move the largest disk from source to target
    3. move the n - 1 disks from spare to target
    The base case is zero disks, which needs no moves.

Worked Example:
Example:
    n = 2: move disk 1 from A to B, move disk 2 from A to C, move disk 1 from B to C. Total = 3 moves.
    In general the number of moves is 2^n - 1.

Time Complexity: O(2^n)
Space Complexity: O(n) recursion stack
*/

public class TowerOfHanoiDivideConquer {
    public static int solve(int disks, char source, char target, char spare, List<String> moves) {
        if (disks == 0) {
            return 0;
        }

        int count = solve(disks - 1, source, spare, target, moves);
        moves.add("disk " + disks + ": " + source + " -> " + target);
        count++;
        count += solve(disks - 1, spare, target, source, moves);
        return count;
    }

    private static void runDemo(int disks) {
        List<String> moves = new ArrayList<>();
        int count = solve(disks, 'A', 'C', 'B', moves);
        System.out.println(disks + " disks need " + count + " moves:");
        for (String move : moves) {
            System.out.println("  " + move);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(2);
        runDemo(3);
        System.out.println("10 disks need " + solve(10, 'A', 'C', 'B', new ArrayList<>()) + " moves");
    }
}
