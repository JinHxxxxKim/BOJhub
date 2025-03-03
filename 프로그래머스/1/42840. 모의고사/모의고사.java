import java.util.*;

class Solution {
    private static final int[] a = {1, 2, 3, 4, 5};
    private static final int[] b = {2, 1, 2, 3, 2, 4, 2, 5};
    private static final int[] c = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    
    public int[] solution(int[] answers) {
        int aCnt = 0;
        int bCnt = 0;
        int cCnt = 0;
        
        for(int idx = 0; idx < answers.length; ++idx){
            int ans = answers[idx];
            if(a[idx % a.length] == ans) ++aCnt;
            if(b[idx % b.length] == ans) ++bCnt;
            if(c[idx % c.length] == ans) ++cCnt;
        }
    
        int maxCnt = Math.max(aCnt, Math.max(bCnt, cCnt));
        int size = 0;
        if(aCnt == maxCnt) ++size;
        if(bCnt == maxCnt) ++size;
        if(cCnt == maxCnt) ++size;
        int[] answer = new int[size];
        int idx = 0;
        if(aCnt == maxCnt) answer[idx++] = 1;
        if(bCnt == maxCnt) answer[idx++] = 2;
        if(cCnt == maxCnt) answer[idx++] = 3;
        return answer;
    }
}