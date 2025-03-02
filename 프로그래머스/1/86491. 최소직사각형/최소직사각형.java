import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int maxLen = Integer.MIN_VALUE;
        int maxCardIdx = -1;
        // 최대 길이 구하기
        for(int idx = 0; idx < sizes.length; ++idx){
            int[] currCard = sizes[idx];
            int currW = currCard[0];
            int currH = currCard[1];
            if(maxLen < currW){
                maxLen = currW;
                maxCardIdx = idx;
            }
            if(maxLen < currH){
                maxLen = currH;
                maxCardIdx = idx;
            }
        }
        
        int len = Integer.MIN_VALUE;
        for(int idx = 0; idx < sizes.length; ++idx){
            int[] currCard = sizes[idx];
            int currW = currCard[0];
            int currH = currCard[1];
            int minLen = Math.min(currW, currH);
            if(len < minLen){
                len = minLen;
            }
        }
        
        return maxLen * len;
    }
}