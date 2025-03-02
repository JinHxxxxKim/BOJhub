class Solution {
    public int solution(String dirs) {
        int answer = 0;
        // [이전x][이전y][이후x][이후y]
        boolean[][][][] visited = new boolean[11][11][11][11];
        int x = 5;
        int y = 5;
        
        for(int idx = 0; idx < dirs.length(); ++idx){
            // System.out.println("X: " + (x -5));
            // System.out.println("Y: " + (y -5));
            // System.out.println("===============");
            char currDir = dirs.charAt(idx);
            // System.out.println("DIR: " + currDir);
            int nextX = x;
            int nextY = y;
            switch(currDir){
                case 'U':
                    nextY = y + 1;
                    break;
                case 'D':
                    nextY = y - 1;
                    break;
                case 'R':
                    nextX = x + 1;
                    break;
                case 'L':
                    nextX = x - 1;
                    break;            
            }
            // 범위 체크
            if(nextX < 0 || nextY < 0 || nextX > 10 || nextY > 10)
                continue;
            // 방문 체크
            if(!visited[x][y][nextX][nextY] && !visited[nextX][nextY][x][y]){
                visited[x][y][nextX][nextY] = true;
                ++answer;
            }
            x = nextX;
            y = nextY;
        }
        
        return answer;
    }
}