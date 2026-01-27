import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        Map<Integer, Integer> aMap = new HashMap<>();
        Map<Integer, Integer> bMap = new HashMap<>();
        // 초기 Setting: 모든 토핑을 B에 할당
        // 이후, 하나씩 확인하며, A에 할당 및 B에서 제거
        
        for(int tp : topping){
            if(bMap.containsKey(tp)){
                bMap.put(tp, bMap.get(tp) + 1);
            }else{
                bMap.put(tp, 1);
            }
        }
        // System.out.println(bMap);
        // System.out.println(bMap.size());
        
        for(int tp : topping){
            // B에서 하나 뺴기 
            if(bMap.get(tp) == 1){
                bMap.remove(tp);
            }else{
                bMap.put(tp, bMap.get(tp) - 1);
            }
            // A에 할당
            if(aMap.containsKey(tp)){
                aMap.put(tp, aMap.get(tp) + 1);
            }else{
                aMap.put(tp, 1);
            }
            // 비교
            if(aMap.size() == bMap.size()){
                ++answer;
            }
        }
        
        return answer;
    }
}