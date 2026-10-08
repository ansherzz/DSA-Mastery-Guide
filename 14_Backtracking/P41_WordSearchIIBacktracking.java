import java.util.*;

/*
Problem: Word Search II
Pattern: 14. Backtracking
Difficulty: Hard

Interview Prompt:
Given an m x n board of lowercase letters and a list of words, return all words from the list
    that can be found on the board. A word is formed from sequentially adjacent cells
    (horizontal or vertical neighbors), and the same cell cannot be used more than once within a word.

Why this pattern fits:
Running the single-word search once per word repeats the same board exploration many times.
    Storing the words in a trie lets one backtracking pass search for all words at once:
    the DFS walks the board and the trie together, and stops the moment no word has the current prefix.

Approach:
Build a trie of the words, storing the complete word at its final node.
    Start a DFS from every cell. Move to a neighbor only if the current trie node has a child for that letter.
    When a node holding a word is reached, record the word and clear it so it is not reported twice.
    Mark cells as visited during the descent and restore them on the way back.

Worked Example:
Example:
    board = o a a n
            e t a e
            i h k r
            i f l v
    words = ["oath", "pea", "eat", "rain"]
    "oath" and "eat" can be traced on the board; "pea" and "rain" cannot.

Time Complexity: O(m * n * 4 * 3^(L - 1)) where L is the longest word length
Space Complexity: O(total characters in words) for the trie
*/

public class P41_WordSearchIIBacktracking {
    private static class TrieNode {
        final TrieNode[] children = new TrieNode[26];
        String word;
    }

    public static List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.word = word;
        }

        List<String> found = new ArrayList<>();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                search(board, r, c, root, found);
            }
        }
        return found;
    }

    private static void search(char[][] board, int r, int c, TrieNode parent, List<String> found) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] == '#') {
            return;
        }

        char letter = board[r][c];
        TrieNode node = parent.children[letter - 'a'];
        if (node == null) {
            return;
        }
        if (node.word != null) {
            found.add(node.word);
            node.word = null;
        }

        board[r][c] = '#';
        search(board, r + 1, c, node, found);
        search(board, r - 1, c, node, found);
        search(board, r, c + 1, node, found);
        search(board, r, c - 1, node, found);
        board[r][c] = letter;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'o', 'a', 'a', 'n'},
            {'e', 't', 'a', 'e'},
            {'i', 'h', 'k', 'r'},
            {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};

        System.out.println("Words : " + Arrays.toString(words));
        System.out.println("Found : " + findWords(board, words));
    }
}
