package com.algo.mergeintervals;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class MergeIntervals{
    public static void main(String[]args){
        System.out.println("Gunankar2");
        Solution sol  = new Solution();
        int[][] input= new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] result = sol.merge(input);
        System.out.println(Arrays.deepToString(result));

    }
}
class Solution{

    public int[][] merge(int[][] intervals) {
        if (intervals.length<1)
            return intervals;
        Arrays.sort(intervals,(int[] t1, int[] t2)-> (t1[0]-t2[0]));

        int start = intervals[0][0];
        int end = intervals[0][1];
        List<int[]> result = new ArrayList<>();
        //int[][] result = new int[intervals.length][2];  // note this might create unwanted empty slots.

        for (int[] interval : intervals){
            int intervalStart = interval[0];
            int intervalEnd = interval[1];
            if( end >= intervalStart) // overlapping intervals
                end = end>intervalEnd ? end:intervalEnd;
            else{
                result.add(new int[]{start,end});
                start = intervalStart;
                end = intervalEnd;
            }
        }
        result.add(new int[]{start,end}); // last elements
        return (result.toArray(new int[result.size()][]));
    }
}

