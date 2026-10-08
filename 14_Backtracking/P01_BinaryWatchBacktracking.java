import java.util.*;

/*
Problem: Binary Watch
Pattern: 14. Backtracking
Difficulty: Easy

Interview Prompt:
A binary watch has 4 LEDs for the hours (values 8, 4, 2, 1) and 6 LEDs for the minutes (values 32, 16, 8, 4, 2, 1).
    Given the number of LEDs that are currently on, return all possible times the watch could show.
    Hours range from 0 to 11 with no leading zero. Minutes range from 0 to 59 and always use two digits.

Why this pattern fits:
The task is to choose which LEDs are on, which is a "pick k items out of 10" search.
    Backtracking builds each choice one LED at a time and abandons a branch as soon as
    the hours exceed 11 or the minutes exceed 59.

Approach:
Treat the 10 LEDs as a list of values, the first 4 belonging to hours and the last 6 to minutes.
    Recursively decide which LED to switch on next, always moving forward in the list to avoid duplicates.
    Track the running hour and minute totals and prune invalid ones.
    When the required number of LEDs has been chosen, format and record the time.

Worked Example:
Example:
    turnedOn = 1
    One hour LED gives 1:00, 2:00, 4:00, 8:00.
    One minute LED gives 0:01, 0:02, 0:04, 0:08, 0:16, 0:32.

Time Complexity: O(C(10, k)), a small constant
Space Complexity: O(k) recursion depth, excluding the output
*/

public class P01_BinaryWatchBacktracking {
    private static final int[] LED_VALUES = {8, 4, 2, 1, 32, 16, 8, 4, 2, 1};

    public static List<String> readBinaryWatch(int turnedOn) {
        List<String> times = new ArrayList<>();
        backtrack(0, turnedOn, 0, 0, times);
        return times;
    }

    private static void backtrack(int start, int remaining, int hours, int minutes, List<String> times) {
        if (hours > 11 || minutes > 59) {
            return;
        }
        if (remaining == 0) {
            times.add(hours + ":" + (minutes < 10 ? "0" : "") + minutes);
            return;
        }

        for (int led = start; led < LED_VALUES.length; led++) {
            if (led < 4) {
                backtrack(led + 1, remaining - 1, hours + LED_VALUES[led], minutes, times);
            } else {
                backtrack(led + 1, remaining - 1, hours, minutes + LED_VALUES[led], times);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("0 LEDs: " + readBinaryWatch(0));
        System.out.println("1 LED : " + readBinaryWatch(1));
        System.out.println("9 LEDs: " + readBinaryWatch(9));
        System.out.println("2 LEDs give " + readBinaryWatch(2).size() + " times");
    }
}
