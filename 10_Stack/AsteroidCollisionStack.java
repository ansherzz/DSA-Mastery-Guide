import java.util.*;

/*
Problem: Asteroid Collision
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
You are given an array of integers representing asteroids in a row.
    The absolute value is the size, and the sign is the direction: positive moves right, negative moves left.
    All asteroids move at the same speed.

    When two asteroids meet, the smaller one explodes. If they are the same size, both explode.
    Asteroids moving in the same direction never meet. Return the state of the asteroids after all collisions.

Why this pattern fits:
A collision only happens between a right-moving asteroid and a later left-moving one.
    The left-moving asteroid fights the most recent surviving right-mover first, then the one before it, and so on.
    That "most recent survivor first" order is a stack.

Approach:
Process asteroids left to right, keeping survivors on a stack.
    For a left-moving asteroid, keep colliding while the top of the stack is a right-mover:
    pop the top if it is smaller, destroy both if they are equal, or destroy the incoming one if it is smaller.
    Push the asteroid only if it survived.

Worked Example:
Example:
    asteroids = [10, 2, -5]
    -5 destroys 2, then is destroyed by 10. Answer = [10].

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class AsteroidCollisionStack {
    public static int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> survivors = new ArrayDeque<>();

        for (int asteroid : asteroids) {
            boolean alive = true;

            while (alive && asteroid < 0 && !survivors.isEmpty() && survivors.peekLast() > 0) {
                int top = survivors.peekLast();
                if (top < -asteroid) {
                    survivors.pollLast();
                } else {
                    if (top == -asteroid) {
                        survivors.pollLast();
                    }
                    alive = false;
                }
            }

            if (alive) {
                survivors.offerLast(asteroid);
            }
        }

        int[] result = new int[survivors.size()];
        int i = 0;
        for (int asteroid : survivors) {
            result[i++] = asteroid;
        }
        return result;
    }

    private static void runDemo(int[] asteroids) {
        System.out.println("Before: " + Arrays.toString(asteroids));
        System.out.println("After : " + Arrays.toString(asteroidCollision(asteroids)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{5, 10, -5});
        runDemo(new int[]{8, -8});
        runDemo(new int[]{10, 2, -5});
        runDemo(new int[]{-2, -1, 1, 2});
    }
}
