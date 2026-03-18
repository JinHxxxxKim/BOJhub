import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for(int work : works) {
            maxHeap.offer(work);
        }
        
        while(n > 0){
            if(maxHeap.isEmpty()){
                break;
            }
            int currWork = maxHeap.poll();
            if(currWork - 1 > 0) {
                maxHeap.offer(currWork - 1);
            }
            --n;
        }
        
        long answer = 0;
        while(!maxHeap.isEmpty()) {
            answer += (long)Math.pow(maxHeap.poll(), 2);
        }
        
        
        return answer;
    }
}