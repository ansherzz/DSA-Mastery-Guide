import java.util.*;

/*
Problem: Longest Absolute File Path
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
A file system is given as text where each line is a name preceded by tab characters showing its depth.
Return the length of the longest absolute path to a file (a name containing '.'), using '/' as the separator.
Return 0 if there is no file.

Why this pattern fits:
The current path is a stack of directories. A line at depth d means "go back to depth d and descend",
i.e. pop until the stack size is d, then push.

Approach:
For each line count its tabs (depth) and name length. Pop while the stack is deeper than depth.
The path length so far is the stack top plus the name length plus one separator.
For a file, record that length without the trailing separator; for a directory, push it.

Worked Example:
dir / subdir2 / file.ext -> "dir/subdir2/file.ext" has length 20.

Time Complexity: O(n)
Space Complexity: O(depth)
*/

public class P20_LongestAbsoluteFilePathStack {
    public static int lengthLongestPath(String input) {
        Deque<Integer> lengths = new ArrayDeque<>();
        int best = 0;
        for (String line : input.split("\n")) {
            int depth = line.lastIndexOf('\t') + 1;
            int nameLength = line.length() - depth;
            while (lengths.size() > depth) {
                lengths.pop();
            }
            int pathLength = (lengths.isEmpty() ? 0 : lengths.peek()) + nameLength + 1;
            if (line.contains(".")) {
                best = Math.max(best, pathLength - 1);
            } else {
                lengths.push(pathLength);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("dir with one file -> " + lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
        System.out.println("dir with two files -> " + lengthLongestPath(
                "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
        System.out.println("no file -> " + lengthLongestPath("a"));
    }
}
