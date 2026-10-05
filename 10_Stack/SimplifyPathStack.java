import java.util.*;

/*
Problem: Simplify Path
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Given an absolute Unix-style file path that starts with '/', convert it to its simplified canonical form.
    - "." refers to the current directory and can be ignored
    - ".." moves up one directory (and does nothing at the root)
    - multiple consecutive slashes count as a single slash
    - any other name, including names like "...", is a regular directory name

    The canonical path starts with a single '/', separates directories with a single '/',
    and has no trailing '/' unless it is the root.

Why this pattern fits:
".." cancels the most recently entered directory.
    Directories are entered and left in last-in-first-out order, so the current path is a stack of names.

Approach:
Split the path on '/'. Empty pieces and "." are skipped.
    ".." pops the most recent directory if one exists. Any other piece is pushed.
    Join the remaining names from the bottom of the stack to the top with '/' in front of each.

Worked Example:
Example:
    path = "/a/./b/../../c/"
    Enter a, ignore ".", enter b, go up (leaves b), go up (leaves a), enter c.
    Answer = "/c"

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class SimplifyPathStack {
    public static String simplifyPath(String path) {
        Deque<String> directories = new ArrayDeque<>();

        for (String part : path.split("/")) {
            if (part.isEmpty() || part.equals(".")) {
                continue;
            }
            if (part.equals("..")) {
                directories.pollLast();
            } else {
                directories.offerLast(part);
            }
        }

        if (directories.isEmpty()) {
            return "/";
        }

        StringBuilder result = new StringBuilder();
        for (String directory : directories) {
            result.append('/').append(directory);
        }
        return result.toString();
    }

    private static void runDemo(String path) {
        System.out.println(path + "  ->  " + simplifyPath(path));
    }

    public static void main(String[] args) {
        runDemo("/home/");
        runDemo("/home//foo/");
        runDemo("/a/./b/../../c/");
        runDemo("/../");
        runDemo("/.../a/../b/c/../d/./");
    }
}
