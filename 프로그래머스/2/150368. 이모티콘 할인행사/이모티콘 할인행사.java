import java.util.*;

class Solution {
    static int emoticonCnt;
    static int[] discountRate;
    static int maxEmoticonPlus;
    static int maxSell;
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};
        // 이모티콘 플러스 서비스 가입자를 최대한 늘리는 것.
        // ================> 모든 사람의 충족가격을 넘겨야함
        
        // 이모티콘 판매액을 최대한 늘리는 것.
        // BEST => 모든 사람이 임티플에 가입하고 최대금액으로 임티를 구매한다
        
        // 완전 탐색:
        // 모든 임티에 대해 모든 할인률을 정해둔 뒤, 해당 조합으로 users의 수만클 탐색한다
        maxEmoticonPlus = Integer.MIN_VALUE;
        maxSell = Integer.MIN_VALUE;
        emoticonCnt = emoticons.length;
        discountRate = new int[emoticonCnt];
        getAnswer(0, users, emoticons); 
        
        return new int[]{maxEmoticonPlus, maxSell};
    }
    
    static void getAnswer(int selectCnt, int[][] users, int[] emoticons) {
        if(selectCnt == emoticonCnt){
            int[] discountCost = new int[emoticonCnt];
            for(int idx = 0; idx < emoticonCnt; ++idx){
                discountCost[idx] = emoticons[idx] * (10 - discountRate[idx]) / 10;
            }
            // System.out.println(Arrays.toString(discountRate));
            // System.out.println(Arrays.toString(discountCost));
            int localPlusUser = 0;
            int localCostSum = 0;
            // 모든 user 확인
            for(int[] user : users) {
                int userSum = 0;
                int userRate = user[0];     // 사용자 지정 할인률
                int userBound = user[1];    // 사용자 구매선
                
                for(int idx = 0; idx < emoticonCnt; ++idx){
                    // 할인률을 만족할 경우(해당 임티의 할인률 <= 사용자지정할인률)
                    // 구매한다
                    if(discountRate[idx] * 10 >= userRate){
                        userSum += discountCost[idx];
                    }
                    // 구매안한다
                    else{
                        
                    }
                }
                if(userBound <= userSum){
                    localPlusUser++;
                }else{
                    localCostSum += userSum;
                }
            }
            
            if(maxEmoticonPlus < localPlusUser){
                maxEmoticonPlus = localPlusUser;
                maxSell = localCostSum;
            }else if(maxEmoticonPlus == localPlusUser){
                if(maxSell < localCostSum){
                    maxSell = localCostSum;
                }
            }
            return;
        }
        for(int rate = 1; rate <=4; ++rate){
            discountRate[selectCnt] = rate;
            getAnswer(selectCnt + 1, users, emoticons);
        }
    }
}