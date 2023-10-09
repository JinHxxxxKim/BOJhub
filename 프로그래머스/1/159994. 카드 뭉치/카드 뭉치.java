import java.util.*;

class Solution {
    private static final String YES = "Yes";
    private static final String NO = "No";
        
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int cards1Idx = 0;
        int cards2Idx = 0;
        
        int cards1MaxIdx = cards1.length-1;
        int cards2MaxIdx = cards2.length-1;
        
        // 3가지 경우의 수
        // 1. 두 카드 모두 남아있는 경우
        // 2. card1 모두 소진
        // 3. card2 모두 소진
        for(int i=0;i<goal.length;++i){
            String currStr = goal[i];
            // 1. 두 카드 모두 남아있는 경우
            if(cards1Idx<=cards1MaxIdx && cards2Idx<=cards2MaxIdx){
                if(currStr.equals(cards1[cards1Idx])){
                    ++cards1Idx;
                }else if(currStr.equals(cards2[cards2Idx])){
                    ++cards2Idx;
                }else{
                    return NO;
                }
            }else if(cards1Idx<=cards1MaxIdx){
                if(currStr.equals(cards1[cards1Idx])){
                    ++cards1Idx;
                }else{
                    return NO;
                }
            }else if(cards2Idx<=cards2MaxIdx){
                if(currStr.equals(cards2[cards2Idx])){
                    ++cards2Idx;
                }else{
                    return NO;
                }
            }
        }
        return YES;
    }
}