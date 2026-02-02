// 바나나 3개, 사과 2개, 쌀 2개, 돼지고기 2개, 냄비 1개
// 치킨, 사과, 사과, 바나나, 쌀, 사과, 돼지고기, 바나나, 돼지고기, 쌀, 냄비, 바나나, 사과, 바나나

import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        // 1. 정현이가 구매하고자하는 물품 및 개수를 Map으로 저장
        Map<String, Integer> wantMap = new HashMap<>();
        for(int idx = 0; idx < want.length; ++idx){
            String key = want[idx];
            if(wantMap.containsKey(key)){
                wantMap.put(key, wantMap.get(key) + number[idx]);
            }else{
                wantMap.put(key, number[idx]);
            }
        }
        
        // 2. 할인 품목 초기세팅(초기 10일치)
        Map<String, Integer> discountMap = new HashMap<>();
        for(int idx = 0; idx < 10; ++idx) {
            String key = discount[idx];
            if(discountMap.containsKey(key)){
                discountMap.put(key, discountMap.get(key) + 1);
            }else{
                discountMap.put(key, 1);
            }
        }
        
        // 3. 슬라이딩 윈도우방식으로 로직 실행
        int front = 0; 
        int rear = 9;
        while(rear < discount.length) {
            if(chk(wantMap, discountMap)){
                answer++;
            }
            
            if(rear == discount.length - 1) {
                break;
            }
            
            // 3-1. front 제거
            if(discountMap.get(discount[front]) == 1){
                discountMap.remove(discount[front]);
            }else{
                discountMap.put(discount[front], discountMap.get(discount[front]) - 1);
            }
            ++front;
            ++rear;
            // 3-2. rear 추가
            if(discountMap.containsKey(discount[rear])){
                discountMap.put(discount[rear], discountMap.get(discount[rear]) + 1);
            }else{
                discountMap.put(discount[rear], 1);
            }
        }
        
        return answer;
    }
    
    static boolean chk(Map<String, Integer> wantMap, Map<String, Integer> discountMap){
        for(String key : wantMap.keySet()) {
            if(!discountMap.containsKey(key) || wantMap.get(key) != discountMap.get(key)){
                return false;
            }
        }
        return true;
    }
}