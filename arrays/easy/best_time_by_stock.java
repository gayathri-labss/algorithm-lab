/**
 * LeetCode 121. Best Time to Buy and Sell Stock (Easy)
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * Problem:
 *   prices[i] is the price of a stock on day i. Buy on one day and sell on a
 *   later day. Return the maximum profit, or 0 if no profit is possible.
 *
 * Examples:
 *   [7, 1, 5, 3, 6, 4] -> 5   (buy at 1, sell at 6)
 *   [7, 6, 4, 3, 1]    -> 0
 *
 * Constraints:
 *   1 <= prices.length <= 10^5, 0 <= prices[i] <= 10^4
 *
 * Approach 1: Brute force
 *   For every buy day, try every later sell day.
 *   Time:  O(n^2)   Space: O(1)
 *
 * Approach 2: One pass
 *   Walk left to right, tracking the lowest price so far and the best profit
 *   if we sold today (today's price - lowest price so far).
 *   Time:  O(n)     Space: O(1)
 */
public class BestTimeToBuySellStock_121 {

    static class Solution {

        public int maxProfitBrute(int[] prices) {
            int best = 0;
            for (int i = 0; i < prices.length; i++) {
                for (int j = i + 1; j < prices.length; j++) {
                    best = Math.max(best, prices[j] - prices[i]);
                }
            }
            return best;
        }

        public int maxProfit(int[] prices) {
            int minPrice = prices[0];
            int best = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] < minPrice) {
                    minPrice = prices[i];
                }
                int profit = prices[i] - minPrice;
                if (profit > best) {
                    best = profit;
                }
            }
            return best;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.maxProfitBrute(new int[]{7, 1, 5, 3, 6, 4}));  // 5
        System.out.println(s.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));       // 5
        System.out.println(s.maxProfit(new int[]{7, 6, 4, 3, 1}));          // 0
        System.out.println(s.maxProfit(new int[]{5}));                      // 0
    }
}
