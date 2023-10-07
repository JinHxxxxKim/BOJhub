import java.util.*;

class Solution {
    public int solution(int n, int m, int[] section) {
        // 모든 배열의 시작점 = 1
        boolean[] walls = new boolean[n+1];
        Arrays.fill(walls, true);
        for(int wall : section){
            walls[wall] = false;
        }
        int answer = 0;
        
        for(int i=1;i<=n;++i){
            if(walls[i]){
                // 페인트 칠할 필요가 없는 경우
                continue;
            } else{
                // 페인트를 칠해야 하는 경우
                for(int j=0;j<m;++j){
                    // ArrayIndexOutOfBoundsException 고려 필요
                    if(i+j>n)
                        break;
                    walls[j+i] = true;
                }
                ++answer;
            }
        }
        
        return answer;
    }
}