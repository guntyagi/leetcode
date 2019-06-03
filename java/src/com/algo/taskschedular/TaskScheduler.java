package com.algo.taskschedular;

import java.util.*;

public class TaskScheduler{
    public static void main(String[]args){
        Solution sol  = new Solution();
        //char[]input= new char[]{'A','A','A','B','B','B'};
        char[]input= new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'};
        int result = sol.leastInterval(input,2);
        System.out.println(result);

    }
}
class Solution {
    public int leastInterval(char[] tasks, int n) {
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for (char task : tasks)
            map.merge(task, 1, Integer::sum);
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
                Comparator.comparing(Map.Entry::getValue,Comparator.reverseOrder()));

        pq.addAll(map.entrySet());

        List<Character> result = new ArrayList<>();
        int index =0;
        while(!pq.isEmpty()) {
            int cycle = n + 1;
            List<Map.Entry<Character,Integer>>tempList = new ArrayList<>();
            while (cycle >0 && !pq.isEmpty()) {
                Map.Entry<Character,Integer> slot = pq.poll();
                slot.setValue(slot.getValue()-1);
                tempList.add(slot); // this will ensure its picked up again
                cycle--;
                result.add(slot.getKey());

            } // time to re-evaluate the pq
            for(Map.Entry<Character,Integer> slot: tempList) {
                if (slot.getValue() > 0) pq.add(slot);
            }
            while(cycle>0 && !pq.isEmpty()){
                result.add('#');
                cycle--;
            }




        }
        System.out.println(result);
        return(result.size());

    }
}