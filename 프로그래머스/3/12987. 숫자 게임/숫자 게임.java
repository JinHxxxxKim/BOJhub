import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num : B) {
            pq.offer(num);
        }
        
        for(int idx = 0; idx < A.length; ++idx) {
            Queue<Integer> q = new LinkedList<>();
            int cmpNum = A[idx];
            while(!pq.isEmpty() && pq.peek() <= cmpNum) {
                q.offer(pq.poll());
            }

            // 종료조건
            if(pq.isEmpty()) break;
            ++answer;
            
            // 후처리
            pq.poll();
        }
        return answer;
    }
}

// 1 3 5 7
// 2 2 6 8