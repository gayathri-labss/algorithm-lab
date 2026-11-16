import java.util.Arrays;

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
 * Approach 1: Brute force
 *   Try every pair (i, j) with j > i. Starting j at i + 1 means an element
 *   is never paired with itself, and no pair is checked twice.
 *   Time:  O(n^2)  two nested loops over the array
 *   Space: O(1)    only the loop variables, nothing new that grows with n
 *
 * Approach 2: HashMap (to add next)
 *   Time:  O(n)
 *   Space: O(n)
 */
public class TwoSum_1 {

    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        return new int[]{i, j};
                    }
                }
            }
            return new int[]{};
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.twoSum(new int[]{2, 7, 11, 15}, 9)));  // [0, 1]
        System.out.println(Arrays.toString(s.twoSum(new int[]{3, 2, 4}, 6)));       // [1, 2]
        System.out.println(Arrays.toString(s.twoSum(new int[]{3, 3}, 6)));          // [0, 1]
    }
}
