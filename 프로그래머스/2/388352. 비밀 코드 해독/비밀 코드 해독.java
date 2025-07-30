import java.util.*;

class Solution {
    static int answer = 0;
    static int[] preAns = new int[5]; // 정답 조합
    static int N;
    
    public int solution(int n, int[][] q, int[] ans) {
        // 모든 조합을 구한다 (오름차순으로 Pruning)
        // 시스템 응답과 비교 후 count
        
        N = n;
        
        for(int startNum = 1; startNum <= N; ++startNum) {
            preAns[0] = startNum;
            sol(startNum, 0, q, ans);
        }
        
        return answer;
    }
    
    static void sol(int currNum, int currPos, int[][] q, int[] ans) {
        // 기저조건
        if(currPos == 4) {
            if(canAns(q, ans)) {
                ++answer;
            }
            return;
        }
        
        for(int nextNum = currNum + 1; nextNum <= N; ++nextNum) {
            preAns[currPos + 1] = nextNum;
            sol(nextNum, currPos + 1, q, ans);
        }
    }
    
    static boolean canAns(int[][] q, int[] ans) {
        Set<Integer> ansSet = new HashSet<>();
        for(int elem : preAns){
            ansSet.add(elem);
        }
        
        for(int idx = 0; idx < ans.length; ++idx) {
            int currAns = ans[idx];
            int[] currQ = q[idx];
            
            int cnt = 0;
            for(int jdx = 0; jdx < currQ.length; ++jdx) {
                if(ansSet.contains(currQ[jdx])) {
                    ++cnt;
                }
            }
            if(cnt != currAns) return false;
            
        }
        return true;
    }
}