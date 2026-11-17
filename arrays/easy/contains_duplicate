import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 217. Contains Duplicate (Easy)
 * https://leetcode.com/problems/contains-duplicate/
 *
 * Problem:
 *   Given an integer array nums, return true if any value appears at least
 *   twice, and false if every element is distinct.
 *
 * Examples:
 *   [1, 2, 3, 1]             -> true
 *   [1, 2, 3, 4]             -> false
 *   [1, 1, 1, 3, 3, 4, 3, 2] -> true
 *
 * Constraints:
 *   1 <= nums.length <= 10^5
 *
 * Approach 1: HashSet
 *   Keep a set of numbers seen so far. If the current number is already in
 *   the set, return true.
 *   Time:  O(n)   Space: O(n)
 *
 * Approach 2: Sort, then compare neighbours
 *   After sorting, duplicates are next to each other.
 *   Time:  O(n log n)   Space: O(1) extra (but it modifies the input)
 */
public class ContainsDuplicate_217 {

    static class Solution {

        public boolean containsDuplicateSet(int[] nums) {
            Set<Integer> seen = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                if (seen.contains(nums[i])) {
                    return true;
                }
                seen.add(nums[i]);
            }
            return false;
        }

        public boolean containsDuplicateSort(int[] nums) {
            Arrays.sort(nums);
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1]) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.containsDuplicateSet(new int[]{1, 2, 3, 1}));                   // true
        System.out.println(s.containsDuplicateSet(new int[]{1, 2, 3, 4}));                   // false
        System.out.println(s.containsDuplicateSort(new int[]{1, 1, 1, 3, 3, 4, 3, 2}));      // true
    }
}
