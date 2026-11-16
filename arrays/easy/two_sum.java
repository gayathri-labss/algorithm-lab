import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1. Two Sum (Easy)
 * https://leetcode.com/problems/two-sum/
 *
 * Problem:
 *   Given an array of integers nums and an integer target, return the
 *   indices of the two numbers that add up to target.
 *   Exactly one solution exists, and the same element cannot be used twice.
 *   The answer can be returned in any order.
 *
 * Examples:
 *   nums = [2, 7, 11, 15], target = 9  ->  [0, 1]   (2 + 7 = 9)
 *   nums = [3, 2, 4],      target = 6  ->  [1, 2]
 *   nums = [3, 3],         target = 6  ->  [0, 1]
 *
 * Constraints:
 *   2 <= nums.length <= 10^4
 *
 * Approach 1: Brute force (twoSumBrute)
 *   Try every pair (i, j) with j > i.
 *   Time:  O(n^2)   Space: O(1)
 *
 * Approach 2: HashMap, one pass (twoSumHash)
 *   For each number, look up target - number in a map of numbers seen so far.
 *   Check the map BEFORE putting the current number, so an element is
 *   never paired with itself.
 *   Time:  O(n)     Space: O(n)
 */
public class TwoSum_1 {

    static class Solution {

        public int[] twoSumBrute(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        return new int[]{i, j};
                    }
                }
            }
            return new int[]{};
        }

        public int[] twoSumHash(int[] nums, int target) {
            Map<Integer, Integer> seen = new HashMap<>();   // number -> index
            for (int i = 0; i < nums.length; i++) {
                int need = target - nums[i];
                if (seen.containsKey(need)) {
                    return new int[]{seen.get(need), i};
                }
                seen.put(nums[i], i);
            }
            return new int[]{};
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.twoSumBrute(new int[]{2, 7, 11, 15}, 9)));  // [0, 1]
        System.out.println(Arrays.toString(s.twoSumHash(new int[]{2, 7, 11, 15}, 9)));   // [0, 1]
        System.out.println(Arrays.toString(s.twoSumHash(new int[]{3, 2, 4}, 6)));        // [1, 2]
        System.out.println(Arrays.toString(s.twoSumHash(new int[]{3, 3}, 6)));           // [0, 1]
    }
}
