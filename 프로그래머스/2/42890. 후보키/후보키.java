import java.util.*;

class Solution {
    
    static int rowNum, colNum;
    static int ans;
    static Set<boolean[]> ansSet;
    
    static int[] elems;
    static boolean[] isSelected;
    
    public int solution(String[][] relation) {
        rowNum = relation.length;
        colNum = relation[0].length;
        elems = new int[colNum];
        ansSet = new HashSet<>();
        
        // 후보키를 만족하는 조합은 더이상 볼 필요가 없다.
        for(int keyCnt = 1; keyCnt <= colNum; ++keyCnt) {
            // System.out.println(keyCnt);
            isSelected = new boolean[colNum];
            combination(0, 0, keyCnt, relation);
            // System.out.println("ans:"+ans);
            // for(boolean[] candidate : ansSet) {
            //     System.out.println("candidate:"+Arrays.toString(candidate));
            // }
            // System.out.println("========================");
        }
        
        // 1개 뽑기
        // 2개 뽑기
        // 3개 뽑기
        //...
        // column개 뽑기
        
        
        return ans;
    }
    
    public static void combination(int selected, int currIdx, int keyCnt, String[][] relation) {
        // 기저조건
        if(selected == keyCnt){
            // TODO: 로직 시작
            // System.out.println(Arrays.toString(isSelected));
            
            Set<String> keySet = new HashSet<>();
            boolean isValid = true;
            for(String[] tuple : relation) {
                StringBuilder sb = new StringBuilder();
                
                // key 생성
                for(int col = 0; col < colNum; ++col) {
                    if(isSelected[col]) {
                        sb.append(tuple[col]);
                    }
                }
                String candidateKey = sb.toString();
                // 유일성 만족여부 검증
                if(!keySet.contains(candidateKey)){
                    keySet.add(candidateKey);
                }else{
                    isValid = false;
                    break;
                }
            }
            
            // 최소성 만족여부 검증
            for(boolean[] candidate : ansSet) {
                int candidateCnt = 0;
                int cnt = 0;
                for(boolean isKey : candidate){
                    if(isKey) ++candidateCnt;
                }
                for(boolean isKey : isSelected){
                    if(isKey) ++cnt;
                }
                
                if(candidateCnt == cnt) continue;
                int moreCnt = 0;
                boolean flag = false;
                
                for(int col = 0; col < colNum; ++col) {
                    if(!isSelected[col] && !candidate[col]) {       // false, false
                    } else if(isSelected[col] && !candidate[col]) { // true, false
                        ++moreCnt;
                    } else if(!isSelected[col] && candidate[col]) { // false, true
                        flag = true;
                    } else if(isSelected[col] && candidate[col]) {  // true, true
                    }
                }
                // System.out.println("moreCnt::" + moreCnt);
                // System.out.println("cnt::" + cnt);
                // System.out.println("candidateCnt::" + candidateCnt);
                if(flag) continue;
                if(moreCnt == cnt - candidateCnt) {
                    isValid = false;
                }
            }
            
            // System.out.println("isValid::" + isValid);
            if(isValid){
                ansSet.add(Arrays.copyOf(isSelected, colNum));
                ans++;
            }
            // System.out.println("========================");
            return;
        }
        
        for(int num = currIdx; num < colNum; ++num) {
            if(isSelected[num]) continue;
            isSelected[num] = true;
            // System.out.println("isSelected[" + num + "] = true");
            combination(selected + 1, num + 1, keyCnt, relation);
            isSelected[num] = false;
            // System.out.println("isSelected[" + num + "] = false");
            // System.out.println("==========================");
        }
    }
}