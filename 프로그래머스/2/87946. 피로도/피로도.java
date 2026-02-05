import java.util.*;

class Solution {
    
    static boolean[] visited;
    static int answer = 0;
    static int N;
    
    public int solution(int k, int[][] dungeons) {
        
        N = dungeons.length;
        visited = new boolean[N];
        // 초기 State Setting
        for(int idx = 0; idx < N; ++idx){
            if(dungeons[idx][0] > k) { //  최소 필요 피로도 검증
                continue;
            }
            visited[idx] = true;
            process(k - dungeons[idx][1], 1, dungeons);
            visited[idx] = false;
        }
        
        return answer;
    }
    
    public void process(int localK, int localAns, int[][] dungeons) {
        if(localAns > answer) {
            answer = localAns;
        }
        for(int idx = 0; idx < N; ++idx){
            if(visited[idx]) { // 방문 검증
                continue;
            }
            if(dungeons[idx][0] > localK) { //  최소 필요 피로도 검증
                continue;
            }
            visited[idx] = true;
            process(localK - dungeons[idx][1], localAns + 1, dungeons);
            visited[idx] = false;
        }
    }
}