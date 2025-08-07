import java.util.*;
class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        int[][] map = new int[n/w + 1][w];
        boolean reverse = false;
        int row = 0;
        int col = 0;
        for(int number = 1; number <= n; ++number){
            if(reverse){
                while(col >= 0){
                    map[row][col] = number;
                    ++number;
                    if(number > n) break;
                    --col;
                }
                --number;
                ++col;
            }else{
                while(col < w){
                    map[row][col] = number;
                    ++number;
                    if(number > n) break;
                    ++col;
                }
                --number;
                --col;
            }
            reverse = !reverse;
            ++row;
        }
        // for(int[] r : map){
        //     System.out.println(Arrays.toString(r));
        // }
        boolean flag = false;
        for(int r = 0; r < map.length; ++r){
            for(int c = 0; c < w; ++c){
                if(map[r][c] != num) continue;
                for(int tempRow = r + 1; tempRow < map.length; ++tempRow){
                    if(map[tempRow][c] != 0){
                        // System.out.println(map[tempRow][c]);
                        ++answer;
                    }else{
                        flag = true;
                        break;
                    }
                }
            }
            if(flag) break;
        }
        return answer + 1;
    }
}