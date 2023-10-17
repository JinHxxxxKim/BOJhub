import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        
        Arrays.sort(score);
        int cnt = 0;
        int currBoxMinScore = Integer.MAX_VALUE;
        for(int i=score.length-1;i>=0;--i){
            if(cnt<m){
                if(currBoxMinScore>score[i]){
                    currBoxMinScore = score[i];
                }
                ++cnt;
            }
            if(cnt==m){
                answer += currBoxMinScore*m;
            
                cnt = 0;
                currBoxMinScore = Integer.MAX_VALUE;
            }
        }
        
        
        return answer;
    }
}