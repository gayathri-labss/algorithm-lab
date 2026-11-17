import java.util.Arrays;

/**
 * LeetCode 283. Move Zeroes (Easy)
 * https://leetcode.com/problems/move-zeroes/
 *
 * Problem:
 *   Given an integer array nums, move all 0s to the end of it while keeping
 *   the relative order of the non-zero elements.
 *   Do this in-place, without making a copy of the array.
 *
 * Examples:
 *   [0, 1, 0, 3, 12] -> [1, 3, 12, 0, 0]
 *   [0]              -> [0]
 *
 * Constraints:
 *   1 <= nums.length <= 10^4
 *
 * Approach 1: Extra array (breaks the in-place rule)
 *   Copy the non-zeros into a new array, then copy it back.
 *   Time:  O(n)   Space: O(n)
 *
 * Approach 2: Two pointers, copy then fill zeros (read / write)
 *   read scans the array, write marks where the next non-zero goes.
 *   After the loop, boxes from write to the end are set to 0.
 *   Time:  O(n)   Space: O(1)   (two loops one after the other: n + n = O(n))
 *
 * Approach 3: Two pointers, one pass with swaps
 *   Swap each non-zero with the box at write. Zeros get pushed to the back.
 *   Time:  O(n)   Space: O(1)
 *
 * Why void: the array is passed by reference, so the method changes the
 * caller's array directly. Nothing needs to be returned.
 */
public class MoveZeroes_283 {

    static class Solution {

        public void moveZeroesExtraArray(int[] nums) {
            int[] result = new int[nums.length];
            int idx = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    result[idx] = nums[i];
                    idx++;
                }
            }
            for (int i = 0; i < nums.length; i++) {
                nums[i] = result[i];
            }
        }

        public void moveZeroes(int[] nums) {
            int write = 0;
            for (int read = 0; read < nums.length; read++) {
                if (nums[read] != 0) {
                    nums[write] = nums[read];
                    write++;
                }
            }
            for (int i = write; i < nums.length; i++) {
                nums[i] = 0;
            }
        }

        public void moveZeroesSwap(int[] nums) {
            int write = 0;
            for (int read = 0; read < nums.length; read++) {
                if (nums[read] != 0) {
                    int temp = nums[write];
                    nums[write] = nums[read];
                    nums[read] = temp;
                    write++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] a = {0, 1, 0, 3, 12};
        s.moveZeroes(a);
        System.out.println(Arrays.toString(a));        // [1, 3, 12, 0, 0]

        int[] b = {0, 1, 0, 3, 12};
        s.moveZeroesSwap(b);
        System.out.println(Arrays.toString(b));        // [1, 3, 12, 0, 0]

        int[] c = {0};
        s.moveZeroes(c);
        System.out.println(Arrays.toString(c));        // [0]

        int[] d = {1, 2, 3};
        s.moveZeroes(d);
        System.out.println(Arrays.toString(d));        // [1, 2, 3]

        int[] e = {0, 0, 5};
        s.moveZeroesExtraArray(e);
        System.out.println(Arrays.toString(e));        // [5, 0, 0]
    }
}
