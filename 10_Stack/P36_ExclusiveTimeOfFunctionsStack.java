import java.util.*;

/*
Problem: Exclusive Time of Functions
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
On a single-threaded CPU, logs have the form "id:start:time" or "id:end:time", where an end happens
at the end of that time unit. Return the exclusive time of each function (time spent in it, excluding its callees).

Why this pattern fits:
Function calls nest, and the function that is running is always the most recently started unfinished one:
the top of a call stack.

Approach:
Keep the id stack and the previous timestamp. On a start log, charge (time - previous) to the current top,
push the new id, and set previous = time. On an end log, charge (time - previous + 1) to the popped id
and set previous = time + 1.

Worked Example:
n = 2, logs = ["0:start:0", "1:start:2", "1:end:5", "0:end:6"]
Function 0 runs during [0, 1] and [6, 6] = 3 units; function 1 runs during [2, 5] = 4 units.

Time Complexity: O(number of logs)
Space Complexity: O(call depth)
*/

public class P36_ExclusiveTimeOfFunctionsStack {
    public static int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        Deque<Integer> callStack = new ArrayDeque<>();
        int previous = 0;
        for (String log : logs) {
            String[] parts = log.split(":");
            int id = Integer.parseInt(parts[0]);
            int time = Integer.parseInt(parts[2]);
            if (parts[1].equals("start")) {
                if (!callStack.isEmpty()) {
                    result[callStack.peek()] += time - previous;
                }
                callStack.push(id);
                previous = time;
            } else {
                result[callStack.pop()] += time - previous + 1;
                previous = time + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("two functions -> " + Arrays.toString(
                exclusiveTime(2, Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6"))));
        System.out.println("recursive calls -> " + Arrays.toString(
                exclusiveTime(1, Arrays.asList("0:start:0", "0:start:2", "0:end:5", "0:start:6", "0:end:6", "0:end:7"))));
    }
}
