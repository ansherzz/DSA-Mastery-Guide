import java.util.*;

/*
Problem: My Calendar I
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
Implement a calendar that accepts a new event only if it does not cause a double booking.
    An event is a half-open interval [start, end). A double booking happens when two events share some moment in time.
    book(start, end) returns true and stores the event if it can be added, and returns false otherwise.

Why this pattern fits:
The stored events never overlap, so they are totally ordered by start time.
    A new event can only collide with its two neighbors in that order: the event starting just before it
    and the event starting just after it. Finding neighbors in an ordered set is a search problem.

Approach:
Keep a TreeMap from start to end. For a new event [start, end):
    - floorEntry(start) is the previous event; it collides if its end is greater than start.
    - ceilingEntry(start) is the next event; it collides if its start is less than end.
    If neither collides, insert the event.

Worked Example:
Example:
    book(10, 20) -> true
    book(15, 25) -> false, because it overlaps [10, 20)
    book(20, 30) -> true, because the first event ends exactly when this one starts

Time Complexity: O(log n) per booking
Space Complexity: O(n)
*/

public class P31_MyCalendarAdvanced {
    static class MyCalendar {
        private final TreeMap<Integer, Integer> events = new TreeMap<>();

        boolean book(int start, int end) {
            Map.Entry<Integer, Integer> previous = events.floorEntry(start);
            if (previous != null && previous.getValue() > start) {
                return false;
            }

            Map.Entry<Integer, Integer> next = events.ceilingEntry(start);
            if (next != null && next.getKey() < end) {
                return false;
            }

            events.put(start, end);
            return true;
        }
    }

    public static void main(String[] args) {
        MyCalendar calendar = new MyCalendar();
        System.out.println(calendar.book(10, 20));
        System.out.println(calendar.book(15, 25));
        System.out.println(calendar.book(20, 30));
        System.out.println(calendar.book(5, 10));
        System.out.println(calendar.book(5, 11));
    }
}
