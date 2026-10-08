import java.util.*;

/*
Problem: Crawler Log Folder
Pattern: 10. Stack
Difficulty: Easy

Interview Prompt:
A file system keeps a log each time a user performs a change-folder operation:
    - "../" moves to the parent folder; if you are already in the main folder, you stay there.
    - "./" stays in the same folder.
    - "x/" moves to the child folder named x.
    The file system starts in the main folder. Given the list of operations,
    return the minimum number of operations needed to go back to the main folder afterward.

Why this pattern fits:
The current path is a stack of folder names: entering a child pushes, going to the parent pops.
    Because only the number of steps back is asked, the stack can be reduced to its depth.

Approach:
Track the depth of the current folder.
    "../" decreases the depth unless it is already 0, "./" does nothing, and anything else increases the depth.
    The final depth is the number of "../" operations needed to return.

Worked Example:
Example:
    logs = [d1/, d2/, ../, d21/, ./]
    Depth: 1, 2, 1, 2, 2. Answer = 2.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P12_CrawlerLogFolderStack {
    public static int minOperations(String[] logs) {
        int depth = 0;
        for (String log : logs) {
            if (log.equals("../")) {
                depth = Math.max(0, depth - 1);
            } else if (!log.equals("./")) {
                depth++;
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        System.out.println(minOperations(new String[]{"d1/", "d2/", "../", "d21/", "./"}));
        System.out.println(minOperations(new String[]{"d1/", "d2/", "./", "d3/", "../", "d31/"}));
        System.out.println(minOperations(new String[]{"d1/", "../", "../", "../"}));
    }
}
