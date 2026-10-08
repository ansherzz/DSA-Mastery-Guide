import java.util.*;

/*
Problem: Encode and Decode TinyURL
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
Design a URL shortening service. Implement:
    - encode(longUrl): return a short URL for the given long URL.
    - decode(shortUrl): return the original long URL for a short URL produced by encode.
    There is no restriction on how the algorithm works, as long as decode(encode(url)) returns url.

Why this pattern fits:
A short URL is a key, and the long URL is its value. The service is a pair of hash maps:
    one to resolve a short code, and one to avoid creating a second code for a URL already seen.

Approach:
Keep a counter and two maps: code -> long URL and long URL -> code.
    To encode, return the existing code if the URL was seen before.
    Otherwise take the next counter value and write it in base 62 (digits, lowercase, uppercase) to get a compact code.
    To decode, strip the fixed prefix and look the code up.
    A counter is used instead of random codes so that codes never collide and the demo output is deterministic.

Worked Example:
Example:
    encode("https://leetcode.com/problems/design-tinyurl") -> "http://tinyurl.com/1"
    decode("http://tinyurl.com/1") -> "https://leetcode.com/problems/design-tinyurl"

Time Complexity: O(L) per operation for hashing a URL of length L
Space Complexity: O(total length of all stored URLs)
*/

public class EncodeAndDecodeTinyURLHashing {
    static class Codec {
        private static final String PREFIX = "http://tinyurl.com/";
        private static final String ALPHABET =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        private final Map<String, String> codeToUrl = new HashMap<>();
        private final Map<String, String> urlToCode = new HashMap<>();
        private long counter = 0;

        String encode(String longUrl) {
            String code = urlToCode.get(longUrl);
            if (code == null) {
                code = toBase62(++counter);
                urlToCode.put(longUrl, code);
                codeToUrl.put(code, longUrl);
            }
            return PREFIX + code;
        }

        String decode(String shortUrl) {
            return codeToUrl.get(shortUrl.substring(PREFIX.length()));
        }

        private String toBase62(long value) {
            StringBuilder sb = new StringBuilder();
            while (value > 0) {
                sb.append(ALPHABET.charAt((int) (value % 62)));
                value /= 62;
            }
            return sb.reverse().toString();
        }
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        String first = codec.encode("https://leetcode.com/problems/design-tinyurl");
        String second = codec.encode("https://example.com/a/very/long/path?query=1");

        System.out.println(first + " -> " + codec.decode(first));
        System.out.println(second + " -> " + codec.decode(second));
        System.out.println("Same URL again: " + codec.encode("https://leetcode.com/problems/design-tinyurl"));
    }
}
