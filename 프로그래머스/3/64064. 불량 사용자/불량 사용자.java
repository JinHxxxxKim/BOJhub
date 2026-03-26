import java.util.*;

class Solution {
    
    static String[] selected;
    static String[] bannedId;
    static String[] userId;
    static int N, R, answer;
    
    // 
    static boolean[] pIsSelected;
    static String[] pSelected;
    
    static boolean pEnd;
    
    
    public int solution(String[] user_id, String[] banned_id) {
        N = user_id.length;
        R = banned_id.length;
        
        bannedId = banned_id;
        userId = user_id;
        
        selected = new String[R];
        
        combination(0, 0);
        
        return answer;
    }
    
    public void combination(int cnt, int start) {
        if(cnt == R) {
            // System.out.println(Arrays.toString(selected));
            // LOGIC
            pIsSelected = new boolean[R];
            pSelected = new String[R];
            pEnd = false;
            permutation(0);
            return;
        }
        
        for(int idx = start; idx < N; ++idx) {
            selected[cnt] = userId[idx];
            combination(cnt + 1, idx + 1);
        }
    }
    
    
    public static void permutation(int depth) {
        if(depth == R){
            // System.out.println("P / " + Arrays.toString(pSelected));
            
            boolean isValid = true;
            for(int idx = 0; idx < R; ++idx){
                String id = pSelected[idx];
                String banId = bannedId[idx];
                if(id.length() != banId.length()) {
                    isValid = false;
                    break;
                }
                for(int pIdx = 0; pIdx < banId.length(); ++pIdx) {
                    if(banId.charAt(pIdx) == '*') continue;
                    if(banId.charAt(pIdx) != id.charAt(pIdx)) {
                        isValid = false;
                        break;
                    }
                }
                if(!isValid) break;
            }
            if(isValid) {
                ++answer;
                pEnd = true;
            }
            return;
        }
        for(int idx = 0; idx < R; ++idx) {
            if(pIsSelected[idx]) continue;
            pIsSelected[idx] = true;
            pSelected[depth] = selected[idx];
            permutation(depth + 1);
            if(pEnd) return;
            pIsSelected[idx] = false;
        }
    }
}