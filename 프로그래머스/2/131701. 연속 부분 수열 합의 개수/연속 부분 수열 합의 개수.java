import java.util.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> ansSet = new HashSet<>();
        int N = elements.length;
        
        
        // 길이가 1인 수열부터 길이가 N인 수열까지 완전탐색
        for(int chk = 1; chk <= N; ++chk) {
            // System.out.println("chk:: " + chk);
            int front = chk - 1;
            int rear = 0;
            boolean isFirst = true;
            
            while(isFirst || front != chk - 1) {
                isFirst = false;
                // System.out.println("front::" + front);
                // System.out.println("rear::" + rear);
                // front ~ rear 까지 합
                int sum = 0;
                int cnt = 0;
                int idx = rear;
                while(cnt < chk) {
                    // System.out.println("idx::" + idx);
                    sum += elements[idx];
                    idx = (idx + 1) % N;
                    ++cnt;
                }
                // System.out.println(sum);    
                
                // ansSet 더하기
                ansSet.add(sum);
                
                // front, rear 보정
                front = (front + 1) % N;
                rear = (rear + 1) % N;
                
            }
            // System.out.println(ansSet);
        }
        
        
        return ansSet.size();
    }
}

