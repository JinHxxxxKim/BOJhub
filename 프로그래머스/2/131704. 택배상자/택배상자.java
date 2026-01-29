import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        int itemMaxCnt = order.length;  // 최대 물품개수
        int currOrderIdx = 0;           // 햔재 요구물품 인덱스
        Stack<Integer> stack = new Stack<>();  // 보조컨테이너
        
        // 주 컨테이너 확인
            // 번호일치 > 적재
            // 번호불일치 
                // 보조컨테이너 확인 
                    // 보조 컨테이너 peek 번호일치 > 보조컨테이너 poll & 적재
                    // 보조 컨테니어 peek 번호 미일치 > 주 컨테이너 물품 보조컨테이너 적재
        
        // 1. 주 컨테이너 물품 완전 탐색
        for(int itemNum = 1; itemNum <= itemMaxCnt; ++itemNum) {
            int currOrderItemNum = order[currOrderIdx];
            // 주 컨테이너 물품번호와 일치
            if(itemNum == currOrderItemNum){
                ++answer;
                ++currOrderIdx;
            }
            // 주 컨테이너 물품번호와 불일치 > 보조컨테이너 확인
            else{
                // 보조 컨테이너 peek 번호일치 > 보조컨테이너 poll & 적재
                if(!stack.isEmpty() && stack.peek() == currOrderItemNum) {
                    ++answer;
                    ++currOrderIdx;
                    stack.pop();
                    --itemNum;
                }
                // 보조 컨테니어 peek 번호 미일치 > 주 컨테이너 물품 보조컨테이너 적재
                else{
                    stack.push(itemNum);
                }
            }
        }
    
        // 2. 보조컨테이너 잔여 물품 탐색
        while(!stack.isEmpty()) {
            int currOrderItemNum = order[currOrderIdx];
            if(stack.peek() == currOrderItemNum) {
                ++answer;
                ++currOrderIdx;
                stack.pop();
            }else{
                break;
            }
        }
        
        
        return answer;
    }
}