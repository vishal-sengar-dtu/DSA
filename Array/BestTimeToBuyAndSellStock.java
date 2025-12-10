class Solution {

    // LINK - https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    // Only 1 trade allowed.
    public int maxProfitI(int[] prices) {
        int profit = 0, mx = 0, mn = Integer.MAX_VALUE;

        for(int price : prices) {
            if(price < mn) {
                mx = mn = price;
            } else if(price  > mx) {
                mx = price;
                profit = Math.max(profit, mx - mn);
            }
        }

        return profit;
    }

    // LINK - https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
    // Multiple trades allowed.
    public int maxProfit(int[] prices) {
        int profit = 0, curr = Integer.MAX_VALUE;

        for(int price : prices) {
            if(price > curr) {
                profit += price - curr;
            }
            curr = price;
        }

        return profit;
    }
}