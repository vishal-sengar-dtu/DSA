class Solution {

    // LINK - https://leetcode.com/problems/painting-the-walls/
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        return recursion(cost, time, n, n);

        int[][] dp = new int[n+1][n+1];
        for(int i=0; i<=n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return memoisation(cost, time, n, n, dp);
        return tabulation(cost, time);
        return optimised(cost, time);
    }

    public int recursion(int[] cost, int[] time, int index, int remainingWalls) {
        // All walls painted.
        if(remainingWalls <= 0) return 0;

        // This scenario is practically not possible
        if(index == 0) return Integer.MAX_VALUE;

        // Hire this painter
        int hire = cost[index-1] + recursion(cost, time, index-1, remaining - 1 - time[index-1]);
        // Skip this painter
        int skip = recursion(cost, time, index-1, remaining);

        return Math.min(hire, skip);
    }

    public int memoisation(int[] cost, int[] time, int idx, int remainingWalls, int[][] dp) {

        if(remainingWalls <= 0) return 0;
        if(idx == 0) return dp[idx][remainingWalls] = Integer.MAX_VALUE/2;

        if(dp[idx][remainingWalls] != -1) return dp[idx][remainingWalls];
        
        int hire = cost[idx-1] + memoisation(cost, time, idx-1, remainingWalls-1-time[idx-1], dp);
        int skip = memoisation(cost, time, idx-1, remainingWalls, dp);
        
        return dp[idx][remainingWalls] = Math.min(hire, skip);
    }

    public int tabulation(int[] cost, int[] time, int n) {
        int n = cost.length;
        int[][]dp = new int[n+1][n+1];
        for(int j=0; j<=n; j++) dp[0][j] = Integer.MAX_VALUE/2;
        for(int i=0; i<=n; i++) dp[i][0] = 0;

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                int rem = Math.max(0, j-1-time[i-1]);
                int hire = cost[i-1] + dp[i-1][rem];
                int skip = dp[i-1][j];
                dp[i][j] = Math.min(hire, skip);
            }
        }

        return dp[n][n];
    }

    public int optimised(int[] cost, int[] time) {
        int n = cost.length;
        int[] dp = new int[n+1];
        for(int i=1; i<=n; i++) dp[i] = Integer.MAX_VALUE/2;

        for(int i=1; i<=n; i++) {
            for(int j=n; j>=0; j--) {
                int rem = Math.max(0, j-1-time[i-1]);
                int hire = cost[i-1] + dp[rem];
                dp[j] = Math.min(hire, dp[j]);
            }
        }

        return dp[n];
    }
}