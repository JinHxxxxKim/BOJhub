import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int food : scoville) {
            pq.offer(food);
        }
        
        while(pq.peek() < K) {
            int food1 = pq.poll();
            if(pq.isEmpty()) {
                return -1;
            }
            int food2 = pq.poll();
            
            int newFood = food1 + food2 * 2;
            pq.offer(newFood);
            ++answer;
        }
        
        
        return answer;
    }
}