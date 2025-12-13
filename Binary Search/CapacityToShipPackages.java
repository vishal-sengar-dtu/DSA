class Solution {

    // LINK - https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
    public int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;

        for(int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }

        while(left != right) {
            int mid = left + (right - left) / 2;

            if(isShipmentPossible(weights, mid, days)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    public boolean isShipmentPossible(int[] weights, int capacity, int limit) {
        int days = 1, load = 0;

        for(int weight : weights) {
            if(load + weight > capacity) {
                days++;
                load = 0;
            }
            load += weight;

            if(days > limit) return false;
        }
        return true;
    }
}