import java.util.*;

class Solution {
    static int answer;
    static Set<Integer> set;    
    static char[] elems;
    static boolean[] selected;
    static int N;
    
    public int solution(String numbers) {
        N = numbers.length();
        set = new HashSet<>();
        elems = new char[N];
        selected = new boolean[N];
        
        for(int idx = 0; idx < N; ++idx) {
            elems[idx] = numbers.charAt(idx);
        }
        
        // 순열
        comb(0, 0);
        
        return answer;
    }
    
    static void comb(int n, int selectedCnt) {
        if(n == N){
            // System.out.println(Arrays.toString(selected));
            int cnt = 0;
            for(boolean elem : selected){
                if(elem) ++cnt;
            }
            // System.out.println("cnt::" + cnt);
            char[] newElems = new char[cnt];
            int elemIdx = 0;
            for(int idx = 0; idx < N; ++idx){
                if(selected[idx]){
                    newElems[elemIdx++] = elems[idx];
                }
            }
            // System.out.println(Arrays.toString(newElems));
            boolean[] newSelected = new boolean[cnt];
            char[] selectedElems = new char[cnt];
            if(cnt == 0) return;
            permutation(0, cnt, newElems, newSelected, selectedElems);
            return;
        }
        
        // 선택을 하거나, 안하거나
        selected[n] = true;
        comb(n + 1, selectedCnt + 1);
        
        selected[n] = false;
        comb(n + 1, selectedCnt);
    }
    
    static void permutation(int selectedCnt, int newN, char[] newElems, boolean[] newSelected, char[] selectedElems) {
        if(selectedCnt == newN){
            int currNum = Integer.parseInt(new String(selectedElems));
            
            if(set.contains(currNum)) return;
            
            
            if(check(currNum)) {
                System.out.println("CURRNUM::"+currNum);
                set.add(currNum);
                ++answer;
            }
            return;
        }
        for(int idx = 0; idx < newElems.length; ++idx){
            if(newSelected[idx]) continue;
            newSelected[idx] = true;
            selectedElems[selectedCnt] = newElems[idx];
            permutation(selectedCnt + 1, newN, newElems, newSelected, selectedElems);
            newSelected[idx] = false;
        }
    }
    
    // 소수판별
    static boolean check(int num){
        if(num == 1 || num == 0) return false;
        for(int cnt = 2; cnt < num; ++cnt) {
            if(num % cnt == 0) return false;
        }
        return true;
    }
}