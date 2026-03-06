import java.util.*;

class Solution {
    
    static final String[] OP = {"+", "-", "*"};
    static String[] selected;
    static boolean[] isSelected;
    static long ans;
    
    public void permutation(int selectCnt, String expression) {
        if(selectCnt == OP.length) {
            ans = calculate(selected, expression);            
            return;
        }
        
        for(int idx = 0; idx < OP.length; ++idx) {
            if(isSelected[idx]) continue;
            isSelected[idx] = true;
            selected[selectCnt] = OP[idx];
            permutation(selectCnt + 1, expression);
            isSelected[idx] = false;
        }
    }
    
    public String localCalculate(int opIdx, String expression) {
        String operand = selected[opIdx];
        String[] expList = expression.split("\\" + operand);
        long ret = 0L;
        for(int idx = 0; idx < expList.length; ++idx) {
            if(idx == 0){
                if(expList[idx].contains("+")
                  || expList[idx].contains("-")
                  || expList[idx].contains("*")) {
                    ret = Long.parseLong(localCalculate(opIdx + 1, expList[idx]));    
                }else{
                    ret = Long.parseLong(expList[idx]);    
                }
            } else {
                if(expList[idx].contains("+")
                  || expList[idx].contains("-")
                  || expList[idx].contains("*")) {
                    expList[idx] = localCalculate(opIdx + 1, expList[idx]);
                }
                switch(operand){
                    case "+":
                        ret = ret + Long.parseLong(expList[idx]);
                        break;
                    case "-":
                        ret = ret - Long.parseLong(expList[idx]);
                        break;
                    case "*":
                        ret = ret * Long.parseLong(expList[idx]);
                        break;
                }
            }
        }
        return String.valueOf(ret);
    }
    
    public long calculate(String[] selected, String expression){
        long ret = 0L;
        
        String ansString = localCalculate(0, expression);

        return Math.max(ans, Math.abs(Long.parseLong(ansString)));
    }
    
    public long solution(String expression) {
        // 순열: 연산자 우선순위 역순 생성
        selected = new String[OP.length];
        isSelected = new boolean[OP.length];
        permutation(0, expression);
        
        
        return ans;
    }
}