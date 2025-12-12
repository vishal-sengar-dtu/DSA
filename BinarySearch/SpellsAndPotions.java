class Solution {

    // LINK - https://leetcode.com/problems/successful-pairs-of-spells-and-potions/
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length, m = potions.length;
        int[] ans = new int[n];

        Arrays.sort(potions);

        for(int i=0; i<n; i++) {
            long threshold = (success + spells[i] - 1) / spells[i];
            int upb = upperBound(potions, threshold);
            ans[i] = m-upb;
        }

        return ans;
    }

    public int upperBound(int[] nums, long k) {
        int left = 0, right = nums.length;

        while(left < right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

}