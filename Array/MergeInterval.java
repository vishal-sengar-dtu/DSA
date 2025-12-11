class Solution {

    // LINK - https://leetcode.com/problems/merge-intervals/
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> ans = new ArrayList<>();

        for(int [] interval : intervals) {
            int n = ans.size();
            if(n > 0) {
                int[] last = ans.get(n-1);
                if(last[1] >= interval[0]) {
                    last[1] = Math.max(last[1], interval[1]);
                    continue;
                }
            }
            ans.add(interval);
        }

        return ans.toArray(new int[ans.size()][]);
    }
}