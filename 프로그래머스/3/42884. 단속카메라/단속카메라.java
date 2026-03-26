import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        
        // 진출점 기준 오름차순 정렬
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b){
                if(a[1] == b[1]) return a[0] - b[0];
                return a[1] - b[1];
            }
        });
        
        int currPoint = routes[0][1];
        for(int idx = 1; idx < routes.length; ++idx) {
            int[] nextRange = routes[idx];
            // 다음 차량의 진입, 진충ㄹ점의 범위가 기준점(currPoint)에 속할 경우
            // , 해당 차량을 위한 별도의 CCTV를 설치할 필요 없다.
            if(nextRange[0] <= currPoint && nextRange[1] >= currPoint) {
                continue;
            }else{
                currPoint = nextRange[1];
                ++answer;
            }
        }
        return answer;
    }
}



/**

진입점 기준 오름차순 정렬
[-20,-15], [-18,-13], [-14,-5], [-5,-3]

가장 진출점이 큰 차량을 바라보고있어야함.
*/