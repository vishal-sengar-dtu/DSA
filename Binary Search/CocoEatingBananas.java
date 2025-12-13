class Solution {

    // LINK - https://leetcode.com/problems/koko-eating-bananas/
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 0;

        for(int pile : piles) right = Math.max(pile, right);

        while(left != right) {
            int mid = left + (right - left) / 2;

            if(canCocoFinishBananasWithMidSpeed(piles, mid, h)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    public boolean canCocoFinishBananasWithMidSpeed(int[] piles, int k, int h) {
        int n = piles.length;
        int time = 0;

        for(int pile : piles) {
            time += (pile % k == 0) ? (pile / k) : (pile / k) + 1;
            if(time > h) return false;
        }

        return true;
    }
}