class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        int ansN, ansM;
        int total = brown + yellow;
        // 가로 * 세로 = total
        // 세로의 최소길이: 3
        int n, m;
        n = 3;
        m = 3;
        // System.out.println("total::" + total);
        
        while(n >= m) {
            // System.out.println("N::" + n + " M::" + m);
            if(total % m != 0){
                ++m;
                n = total / m;
                continue;
            }
            n = total / m;
            
            // 검증 
            if(chkBrown(brown, n, m) && chkYellow(yellow, n, m)) {
                ansN = n;
                ansM = m;
                break;
            }
            ++m;
        }
        
        return new int[]{n, m};
    }
    
    static boolean chkBrown(int brownCnt, int n, int m) {
        if(2*n + 2*m - 4 == brownCnt) return true;
        return false;
    }
    
    static boolean chkYellow(int yellowCnt, int n, int m) {
        if(n*m - 2*n - 2*m + 4 == yellowCnt) return true;
        return false;
    }
}

// 가로길이 * 2 + (세로길이 - 2) * 2 = brown이 되는 경우
//(가로길이 - 2) * (세로길이 - 2) = yellow가 되는 경우

// 2N + 2M - 4 = ?  
// NM - 2N - 2M + 4 = #
// NM = ? + #