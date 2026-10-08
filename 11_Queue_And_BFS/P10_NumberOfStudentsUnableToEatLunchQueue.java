import java.util.*;

/*
Problem: Number of Students Unable to Eat Lunch
Pattern: 11. Queue / BFS
Difficulty: Easy

Interview Prompt:
Sandwiches (0 or 1) are stacked, and students who each prefer 0 or 1 stand in a queue.
The front student takes the top sandwich if it matches, otherwise goes to the back of the queue.
Return the number of students who are unable to eat.

Why this pattern fits:
The process is literally a queue: remove from the front, possibly re-add at the back.
It stops when every remaining student has rejected the same top sandwich.

Approach:
Simulate with a queue and a counter of consecutive rejections.
A match removes the student, advances the sandwich pointer, and resets the counter.
Stop when the counter equals the queue size.

Worked Example:
students = [1, 1, 1, 0, 0, 1], sandwiches = [1, 0, 0, 0, 1, 1] -> after three students eat,
the top sandwich is 0 but only students who want 1 remain. Answer = 3.

Time Complexity: O(n^2) worst case for the simulation
Space Complexity: O(n)
*/

public class P10_NumberOfStudentsUnableToEatLunchQueue {
    public static int countStudents(int[] students, int[] sandwiches) {
        Deque<Integer> queue = new ArrayDeque<>();
        for (int student : students) {
            queue.add(student);
        }
        int top = 0;
        int rejections = 0;
        while (!queue.isEmpty() && rejections < queue.size()) {
            int student = queue.poll();
            if (student == sandwiches[top]) {
                top++;
                rejections = 0;
            } else {
                queue.add(student);
                rejections++;
            }
        }
        return queue.size();
    }

    public static void main(String[] args) {
        System.out.println("[1, 1, 0, 0] / [0, 1, 0, 1] -> " + countStudents(new int[]{1, 1, 0, 0}, new int[]{0, 1, 0, 1}));
        System.out.println("[1, 1, 1, 0, 0, 1] / [1, 0, 0, 0, 1, 1] -> "
                + countStudents(new int[]{1, 1, 1, 0, 0, 1}, new int[]{1, 0, 0, 0, 1, 1}));
    }
}
