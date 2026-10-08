import java.util.*;

/*
Problem: Queue Reconstruction by Height
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
You are given an array of people in random order. Each people[i] = [h, k] represents a person of height h
    with exactly k other people in front of them who have a height greater than or equal to h.
    Reconstruct and return the queue that is described by the input.

Why this pattern fits:
A person's k value counts only people who are at least as tall.
    Shorter people are invisible to it, so if the tallest people are placed first,
    adding shorter people later can never invalidate the taller people's counts.
    That observation fixes a processing order in which each placement is final.

Approach:
Sort the people by height descending, and by k ascending for equal heights.
    Insert each person into a list at index k.
    At the moment of insertion, everyone already in the list is at least as tall,
    so index k puts exactly k taller-or-equal people in front.
    Later insertions involve only shorter (or equal with larger k) people and do not disturb that count.

Worked Example:
Example:
    people = [[7, 0], [4, 4], [7, 1], [5, 0], [6, 1], [5, 2]]
    Sorted: [7, 0], [7, 1], [6, 1], [5, 0], [5, 2], [4, 4].
    Insertions give [[5, 0], [7, 0], [5, 2], [6, 1], [4, 4], [7, 1]].

Time Complexity: O(n^2) because of list insertions
Space Complexity: O(n)
*/

public class QueueReconstructionByHeightHeapGreedy {
    public static int[][] reconstructQueue(int[][] people) {
        int[][] sorted = people.clone();
        Arrays.sort(sorted, (a, b) -> a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]);

        List<int[]> queue = new ArrayList<>();
        for (int[] person : sorted) {
            queue.add(person[1], person);
        }
        return queue.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(reconstructQueue(
            new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}})));
        System.out.println(Arrays.deepToString(reconstructQueue(
            new int[][]{{6, 0}, {5, 0}, {4, 0}, {3, 2}, {2, 2}, {1, 4}})));
    }
}
