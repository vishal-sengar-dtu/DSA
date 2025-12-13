class Solution {

    // LINK - https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1
    public int knapsack(int W, int val[], int wt[]) {
        int n = wt.length;
        return recursion(val, wt, W, n);

        int[][] dp = new int[n+1][W+1];
        for(int i=0; i<=n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return memoization(val, wt, W, n, dp);
        return tabulation(val, wt, W);
        return spaceOptimizedTabulation(val, wt, W);
    }

    // Time - O(2^N), Space - O(N)
    public int recursion(int[] val, int[] wt, int W, int n) {
        if(n == 0 || W == 0) return 0;

        if(wt[n-1] <= W) {
            return Math.max(val[n-1] + recursion(val, wt, W - wt[n-1], n-1), recursion(val, wt, W, n-1));
        }

        return recursion(val, wt, W, n-1);
    }

    // Time - O(n*W), Space - O(n*W) + O(n)
    public int memoization(int[] val, int[] wt, int W, int n, int[][] dp) {
        if(dp[n][W] != -1) return dp[n][W];

        if(n == 0 || W == 0) return dp[n][W] = 0;

        if(wt[n-1] <= W) {
            return dp[n][W] = Math.max(
                    val[n-1] + memoization(val, wt, W-wt[n-1], n-1, dp),
                    memoization(val, wt, W, n-1, dp)
            );
        }

        return dp[n][W] = memoization(val, wt, W, n-1, dp);
    }

    // Time - O(n*W), Space - O(n*W)
    // Tabulation is more efficient then memoization as it does not occupy recursion space of O(N)
    public int tabulation(int[] val, int[] wt, int W) {
        int n = val.length;
        int[][] dp = new int[n+1][W+1];

        for(int i=0; i<=n; i++) dp[i][0] = 0;
        for(int j=0; j<=W; j++) dp[0][j] = 0;

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=W; j++) {
                if(wt[i-1] <= j) {
                    dp[i][j] = Math.max(val[i-1] + dp[i-1][j-wt[i-1]], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][W];
    }

    // Time - O(n*W), Space - O(W)
    public int spaceOptimizedTabulation(int[] val, int[] wt, int W) {
        int n = val.length;
        int[] dp = new int[W+1];

        for(int i=1; i<=n; i++) {
            for(int j=W; j>=1; j--) {
                if(wt[i-1] <= j) {
                    dp[j] = Math.max(dp[j], val[i-1] + dp[j-wt[i-1]]);
                }
            }
        }

        return dp[W];
    }
}