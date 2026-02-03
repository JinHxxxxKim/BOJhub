import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        int maxCnt = queue1.length * 4;
        
        // 총합계산
        long totalSum = 0L;
        long initSum = 0L;
        long sumA = 0L;
        long sumB = 0L;
        
        Queue<Long> qA = new LinkedList<>();
        Queue<Long> qB = new LinkedList<>();
        for(int idx = 0; idx < queue1.length; ++idx) {
            totalSum += (long)queue1[idx];
            totalSum += (long)queue2[idx];
            
            initSum += (long)queue1[idx];
            
            sumA += (long)queue1[idx];
            sumB += (long)queue2[idx];
            
            qA.offer((long)queue1[idx]);
            qB.offer((long)queue2[idx]);
        }
        
        // 절반값 계산
        long half = (long)(totalSum / 2);
        
        while(answer == 0 || answer <= maxCnt) {
            // 크기 비교
            // Queue1에서 poll & Queue2에 offer
            if(sumA > sumB){
                long number = qA.poll();
                qB.offer(number);
                sumA = (long)(sumA - number);
                sumB = (long)(sumB + number);
            }
            // Queue2에서 poll & Queue1에 offer
            else if(sumA < sumB){
                long number = qB.poll();
                qA.offer(number);
                sumA = (long)(sumA + number);
                sumB = (long)(sumB - number);
            }else{
                return answer;
            }
            ++answer;
        }
        return -1;
    }
}