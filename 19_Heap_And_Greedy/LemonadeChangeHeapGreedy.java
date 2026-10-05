import java.util.*;

/*
Problem: Lemonade Change
Pattern: 19. Heap & Greedy
Difficulty: Easy

Interview Prompt:
At a lemonade stand each lemonade costs 5 dollars. Customers stand in a queue and buy one lemonade each,
    paying with a 5, 10, or 20 dollar bill. You must give each customer the correct change,
    and you start with no money at all.

    Given the bills in the order the customers pay, return true if you can give every customer correct change.

Why this pattern fits:
The only real decision is how to give 15 dollars of change for a 20: one 10 plus one 5, or three 5s.
    The greedy choice is to use the 10 whenever possible, because 5 dollar bills are more flexible:
    they are needed for every kind of change, while a 10 can only help with a 20.

Approach:
Track how many 5s and 10s you hold.
    For a 5, keep it. For a 10, give back one 5 (fail if you have none).
    For a 20, prefer giving a 10 and a 5; otherwise give three 5s; otherwise fail.

Worked Example:
Example:
    bills = [5, 5, 5, 10, 20]
    After three 5s you hold 5, 5, 5. The 10 takes one 5 as change. The 20 takes the 10 and a 5.
    Everyone received correct change. Answer = true.

    bills = [5, 5, 10, 10, 20] fails: after the two 10s you hold no 5s, so 15 dollars of change is impossible.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class LemonadeChangeHeapGreedy {
    public static boolean lemonadeChange(int[] bills) {
        int fives = 0;
        int tens = 0;

        for (int bill : bills) {
            if (bill == 5) {
                fives++;
            } else if (bill == 10) {
                if (fives == 0) {
                    return false;
                }
                fives--;
                tens++;
            } else if (tens > 0 && fives > 0) {
                tens--;
                fives--;
            } else if (fives >= 3) {
                fives -= 3;
            } else {
                return false;
            }
        }
        return true;
    }

    private static void runDemo(int[] bills) {
        System.out.println(Arrays.toString(bills) + " -> " + lemonadeChange(bills));
    }

    public static void main(String[] args) {
        runDemo(new int[]{5, 5, 5, 10, 20});
        runDemo(new int[]{5, 5, 10, 10, 20});
        runDemo(new int[]{10});
    }
}
