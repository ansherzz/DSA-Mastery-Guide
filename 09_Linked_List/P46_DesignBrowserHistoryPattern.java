import java.util.*;

/*
Problem: Design Browser History
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
Implement a browser history: BrowserHistory(homepage), visit(url) (clears all forward history),
back(steps) and forward(steps) which move at most that many steps and return the current url.

Why this pattern fits:
Back and forward are moves along prev and next pointers.
Visiting a new page replaces everything after the current node, which is one pointer assignment in a linked list.

Approach:
Keep `current`. visit: create a node, link it after current (dropping the old forward chain), and move to it.
back / forward: follow prev / next up to `steps` times while the link exists.

Worked Example:
Visit google, facebook, youtube. back(1) = facebook, back(1) = google, forward(1) = facebook.
Visit linkedin (youtube is discarded). forward(2) = linkedin, back(2) = google, back(7) = leetcode.

Time Complexity: O(1) for visit, O(steps) for back and forward
Space Complexity: O(n)
*/

public class P46_DesignBrowserHistoryPattern {
    static class BrowserHistory {
        private static class Page {
            String url;
            Page prev, next;
            Page(String url) { this.url = url; }
        }

        private Page current;

        BrowserHistory(String homepage) {
            current = new Page(homepage);
        }

        public void visit(String url) {
            Page page = new Page(url);
            current.next = page;
            page.prev = current;
            current = page;
        }

        public String back(int steps) {
            while (steps-- > 0 && current.prev != null) {
                current = current.prev;
            }
            return current.url;
        }

        public String forward(int steps) {
            while (steps-- > 0 && current.next != null) {
                current = current.next;
            }
            return current.url;
        }
    }

    public static void main(String[] args) {
        BrowserHistory history = new BrowserHistory("leetcode.com");
        history.visit("google.com");
        history.visit("facebook.com");
        history.visit("youtube.com");
        System.out.println("back(1) -> " + history.back(1));
        System.out.println("back(1) -> " + history.back(1));
        System.out.println("forward(1) -> " + history.forward(1));
        history.visit("linkedin.com");
        System.out.println("forward(2) -> " + history.forward(2));
        System.out.println("back(2) -> " + history.back(2));
        System.out.println("back(7) -> " + history.back(7));
    }
}
