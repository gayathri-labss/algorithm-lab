import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 242. Valid Anagram (Easy)
 * https://leetcode.com/problems/valid-anagram/
 *
 * Problem:
 *   Given two strings s and t, return true if t is an anagram of s,
 *   and false otherwise. An anagram uses exactly the same letters,
 *   the same number of times, in any order.
 *
 * Examples:
 *   s = "anagram", t = "nagaram"  -> true
 *   s = "rat",     t = "car"      -> false
 *
 * Constraints:
 *   1 <= s.length, t.length <= 5 * 10^4, lowercase English letters only
 *
 * Approach 1: HashMap letter counts
 *   Count the letters of s, then cross them off with t.
 *   Time:  O(n)   Space: O(1)  (at most 26 letters)
 *
 * Approach 2: int[26] counts
 *   Same idea with an array, index = letter - 'a'.
 *   Time:  O(n)   Space: O(1)
 */
public class ValidAnagram_242 {

    static class Solution {

        public boolean isAnagramMap(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            Map<Character, Integer> count = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                count.put(ch, count.getOrDefault(ch, 0) + 1);
            }
            for (int i = 0; i < t.length(); i++) {
                char ch = t.charAt(i);
                int c = count.getOrDefault(ch, 0);
                if (c == 0) {
                    return false;
                }
                count.put(ch, c - 1);
            }
            return true;
        }

        public boolean isAnagramArray(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
                count[t.charAt(i) - 'a']--;
            }
            for (int c : count) {
                if (c != 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.isAnagramMap("anagram", "nagaram"));    // true
        System.out.println(sol.isAnagramMap("rat", "car"));            // false
        System.out.println(sol.isAnagramArray("anagram", "nagaram"));  // true
        System.out.println(sol.isAnagramArray("rat", "car"));          // false
    }
}
