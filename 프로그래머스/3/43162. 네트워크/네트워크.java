import java.util.*;

class Solution {
    
    static int N;
    static boolean[] visited;
    static int[][] computerss;
    
    public int solution(int n, int[][] computers) {
        N = n;
        int ans = 0;
        visited = new boolean[n];
        computerss = computers;
        // 모든 정점을 시작점으로 하여 BFS 탐색
        for(int startNode = 0; startNode < n; ++startNode){
            if(visited[startNode])
                continue;
            
            bfs(startNode);
            ++ans;
        }
        
        return ans;
    }
    public static void bfs(int startNode){
        Queue<Integer> q = new ArrayDeque<>();
        visited[startNode] = true;
        q.offer(startNode);
        while(!q.isEmpty()){
            int currNode = q.poll();
            for(int nextNode = 0; nextNode < N; ++nextNode){
                if(computerss[currNode][nextNode] == 0)
                    continue;
                if(visited[nextNode])
                    continue;
                q.offer(nextNode);
                visited[nextNode] = true;
            }
        }
    }
}